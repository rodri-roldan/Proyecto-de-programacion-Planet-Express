import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;



/**
 * Lista de puertos espaciales
 *
 * @author Alejandro Abello
 * @author Rodrigo Roldán
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad espacio del array
     */
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];
    }

    /** Devuelve el número de puertos espaciales que hay en la lista
     * @return  la lista
     */
    public int getOcupacion() {
        return lista.length;
    }

    /** metodo que dice si esta llena la lista
     * @return boolean
     */
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < getOcupacion(); i++){
            if (lista[i] != null){
                contador++;
            }
        }
        if (contador == getOcupacion()){
            resultado = true;
        }
        return resultado;
    }
	// Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }
    public void mostrarPuertosEspaciales(){
        for (PuertoEspacial puertoEspacial: lista){
            if (puertoEspacial != null) {
                System.out.println(puertoEspacial);
            }
        }
    }

    /**
     * insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial los puerto espaciales
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        int contador = 0;
        boolean resultado = false;
        if (estaLlena()){
            System.out.println("Límite máximo de Puertos Espaciales alcanzado");
        } else{
            while (!resultado) {
                if (contador == lista.length) {
                    System.out.println("Ha ocurrido un error insertando el puerto");
                    resultado = true;
                }
                if (lista[contador] == null) {
                    lista[contador] = puertoEspacial;
                    resultado = true;
                } else {
                    contador++;
                }
            }
        }
        return resultado;
    }

    /**
     * Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo codigo de los puertos
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial puertoEspacialBuscado = null;
        for (PuertoEspacial puerto: lista){
            if (codigo.equals(puerto.getCodigo())){
                puertoEspacialBuscado = puerto;
            }
        }
        if (puertoEspacialBuscado == null){
            System.out.println("No se ha encontrado el puerto");
        }
        return puertoEspacialBuscado;
    }

    /**
     * Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado objeto que lee el teclado
     * @param mensaje texto
     * @return puerto espacial
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        boolean codigoCorrecto = false;
        PuertoEspacial puertoEspacial = null;
        System.out.print(mensaje);
        String codigoBuscado = teclado.nextLine();
        do {
            codigoBuscado = teclado.nextLine();
            if (codigoBuscado.equals("cancelar")) {
                PlanetExpress.menu(teclado);
                codigoCorrecto = true;
            }
            puertoEspacial = buscarPuertoEspacial(codigoBuscado);
            if (puertoEspacial != null){
                codigoCorrecto = true;
            } else {
                System.out.print(mensaje);
            }
        } while (!codigoCorrecto);
        return puertoEspacial;
    }

    /**
     * Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre nombre del fichero
     * @return true si ha podido escribir el fichero, false en caso contrario
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        try (PrintWriter pw = new PrintWriter(nombre)) {
            for (PuertoEspacial puertoEspacial : lista) {
                if (puertoEspacial != null) {
                    pw.println(puertoEspacial.toStringCSV());
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero fichero d epuertos
     * @param capacidad espacio del array
     * @return lista puertos
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        try (Scanner sc = new Scanner(new File(fichero))) {
            sc.useDelimiter(";");
            int i = 0;
                String nombre = "";
                String codigo = "";
                double radio = 0.0;
                double azimut = 0.0;
                double polar = 0.0;
                int numMuelles = 0;
                while (sc.hasNext()) {
                    i++;
                    if (i == 1) {
                        nombre = sc.next();
                    }
                    if (i == 2) {
                        codigo = sc.next();
                    }
                    if (i == 3) {
                        radio = Double.parseDouble(sc.next());
                    }
                    if (i == 4) {
                        azimut = Double.parseDouble(sc.next());
                    }
                    if (i == 5) {
                        polar = Double.parseDouble(sc.next());
                    }
                    if (i == 6) {
                        numMuelles = Integer.parseInt(sc.next());
                        listaPuertosEspaciales.insertarPuertoEspacial(new PuertoEspacial(nombre, codigo, radio, azimut, polar, numMuelles));
                        i = 0;
                        sc.nextLine();
                    }
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaPuertosEspaciales;
    }
}
