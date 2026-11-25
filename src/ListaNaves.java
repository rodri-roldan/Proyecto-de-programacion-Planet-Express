import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista de naves
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class ListaNaves {
    private Nave[] naves;

    /**
     * Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad espacio del array
     */
    public ListaNaves(int capacidad) {
        naves = new Nave[capacidad];
    }

    /** Devuelve el número de naves que hay en la lista
     * @return la ocupacion
     */
    public int getOcupacion() {
        return naves.length;
    }

    /** metodo para saber si esta lleno el array
     * @return si la lista esta llena
     */
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < getOcupacion(); i++){
            if (naves[i] != null){
                contador++;
            }
        }
        if (contador == getOcupacion()){
            resultado = true;
        }
        return resultado;
    }

    /** Devuelve nave dado un indice
     * @param posicion lugar
     * @return la nave
     */
    public Nave getNave(int posicion) {
        return naves[posicion];
    }

    /**
     * insertamos una nueva nave en la lista
     * @param nave nave
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarNave(Nave nave) {
        boolean resultado = false;
        int contador = 0;
        if (estaLlena()){
            System.out.println("Límite máximo de Naves alcanzado");
        } else{
            while (!resultado) {
                if (contador == naves.length) {
                    System.out.println("Ha ocurrido un error insertando la nave");
                    resultado = true;
                }
                if (naves[contador] == null) {
                    naves[contador] = nave;
                    resultado = true;
                } else {
                    contador++;
                }
            }
        }
        return resultado;
    }
    /**
     * Buscamos la nave a partir de la matricula pasada como parámetro
     * @param matricula matricula de la nave
     * @return la nave que encontramos o null si no existe
     */
    public Nave buscarNave(String matricula) {
        Nave naveBuscada = null;
        for (Nave nave: naves){
            if (matricula.equals(nave.getMatricula())){
                naveBuscada = nave;
            }
        }
        return naveBuscada;
    }

    /**
     * Muestra por pantalla las naves de la lista con el formato indicado en el enunciado
     */
    public void mostrarNaves() {
        for (Nave nave : naves) {
            if (nave != null) {
                System.out.println(nave);
            }
        }
    }



    /**
     * Permite seleccionar una nave existente a partir de su matrícula, y comprueba si dispone de un alcance
     *  mayor o igual que el pasado como argumento, usando el mensaje pasado como argumento para la solicitud y
     *  siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente la matrícula de la nave hasta que se introduzca una con alcance suficiente
     * @param teclado objeto para leer el teclado
     * @param mensaje texto
     * @param alcance alcance
     * @return la nave
     */
    public Nave seleccionarNave(Scanner teclado, String mensaje, double alcance) {
        Nave naveBuscada;
        System.out.print(mensaje);
        String busqueda = teclado.nextLine();
        boolean bool = false;
        do {
            busqueda = teclado.nextLine();
            naveBuscada = buscarNave(busqueda);
            if (naveBuscada == null){
                System.out.println("Matrícula de avión no encontrada.");
                System.out.print(mensaje);
            } else {
                if (naveBuscada.getAlcance() < alcance) {
                    System.out.println("Avión seleccionado con alcance insuficiente.");
                    System.out.print(mensaje);
                } else{
                    bool = true;
                }
            }
        } while (!bool);
        return naveBuscada;
    }


    /**
     * Genera un fichero CSV con la lista de Naves, SOBREESCRIBIÉNDOLO
     * @param nombre nombre fichero
     * @return boolean
     */
    public boolean escribirNavesCsv(String nombre) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(nombre);
            for (Nave nave : naves){
                if (nave != null){
                    pw.println(nave.toStringCSV());
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            pw.close();
        }
    }


    /**
     * Genera una lista de naves a partir del fichero CSV, usando el argumento como capacidad máxima de la lista
     * @param fichero fichero
     * @param capacidad capacidad del array
     * @return lista naves
     */
    public static ListaNaves leerNavesCsv(String fichero, int capacidad) {
        ListaNaves listaNaves = new ListaNaves(capacidad);
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fichero));
            sc.useDelimiter(";");
            int i=0;
                String marca = "";
                String modelo = "";
                String matricula = "";
                int columnas = 0;
                int filas = 0;
                double alcance = 0.0;
                while (sc.hasNext()) {
                    i++;
                    if (i == 1) {
                        marca = sc.next();
                    }
                    if (i == 2) {
                        modelo = sc.next();
                    }
                    if (i == 3) {
                        matricula = sc.next();
                    }
                    if (i == 4) {
                        columnas = Integer.parseInt(sc.next());
                    }
                    if (i == 5) {
                        filas = Integer.parseInt(sc.next());
                    }
                    if (i == 6) {
                        alcance = Double.parseDouble(sc.next());
                        listaNaves.insertarNave(new Nave(marca, modelo, matricula, columnas, filas, alcance));
                        i = 0;
                        sc.nextLine();
                    }
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listaNaves;
    }
}
