import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista de envios
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class ListaEnvios {
    private Envio[] envios;
    /**
     * Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad espacio del array
     */
    public ListaEnvios(int capacidad) {
        envios = new Envio[capacidad];
    }

    /** Devuelve el número de envíos que hay en la lista
     * @return la capacidad
     */
    public int getOcupacion() {
        return envios.length;

    }

    /**  metodo que dice si esta llena la lista
     * @return si esta llena
     */
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < getOcupacion(); i++){
            if (envios[i] != null){
                contador++;
            }
        }
        if (contador == getOcupacion()){
            resultado = true;
        }
        return resultado;

    }



    /** Devuelve el envio dado un indice
     * @param i
     * @return envios
     */
    public Envio getEnvio(int i) {
        return envios[i];
    }

    /**
     * insertamos un nuevo envío en la lista
     * @param envio envios del array
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarEnvio(Envio envio) {
        int contador = 0;
        boolean huecoEncontrado = false;
        if (estaLlena()){
            System.out.println("Límite máximo de envíos alcanzado");
        } else{
            while (!huecoEncontrado) {
                if (contador == getOcupacion()) {
                    System.out.println("Ha ocurrido un error insertando el envio");
                    huecoEncontrado = true;
                }
                if (envios[contador] == null) {
                    envios[contador] = envio;
                    huecoEncontrado = true;
                } else {
                    contador++;
                }

            }
        }
        return huecoEncontrado;
    }

    /**
     * Buscamos el envio a partir del localizador pasado como parámetro
     * @param localizador id del envio
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String localizador) {
        Envio envioBuscado = null;
        for (Envio envio: envios){
            if (envio != null){
                if (localizador.equals(envio.getLocalizador())){
                    envioBuscado = envio;
                }
            }
        }
        return envioBuscado;
    }

    /**
     * Buscamos el envio a partir del idPorte, fila y columna pasados como parámetros
     * @param idPorte localizador de un envio
     * @param fila fila donde esta el envio
     * @param columna columna donde esta el envio
     * @return el envio que encontramos o null si no existe
     */
    public Envio buscarEnvio(String idPorte, int fila, int columna) {
        ListaPortes portes = new ListaPortes(5);
        Envio envioBuscado = null;
        Porte porte = portes.buscarPorte(idPorte);
        if (porte == null){
            System.out.println("El ID del porte es incorrecto o no existe");
        } else {
            envioBuscado = porte.buscarEnvio(fila, columna);
            if (envioBuscado == null ){
                System.out.println("Los limites de fila o columna han sido excedidos");
            }
        }
        return envioBuscado;
    }

    /**
     * Eliminamos un envio a través del localizador pasado por parámetro
     * @param localizador id del envio
     * @return True si se ha borrado correctamente, false en cualquier otro caso
     */
    public boolean eliminarEnvio(String localizador) {
        Envio envioEliminado = buscarEnvio(localizador);
        boolean resultado = false;
        if (envioEliminado == null){
            System.out.println("Este envío no existe");
        }
        if (envioEliminado != null){
            resultado = true;
            for (int i = 0; i < getOcupacion(); i++){
                if (getEnvio(i) == envioEliminado){
                    envios[i] = null;
                }
            }
        }
        return resultado;
    }


    /**
     * Muestra por pantalla los Envios de la lista, con el formato que aparece
     * en el enunciado
     */
    public void listarEnvios() {
        for (Envio envio: envios) {
            if (envio != null){
                System.out.println("\t"+envio);
            }
        }
    }

    /**
     * Permite seleccionar un Envio existente a partir de su localizador, usando el mensaje pasado como argumento
     *  para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente hasta que se introduzca un localizador correcto
     * @param teclado objeto para leer el teclado
     * @param mensaje texto para saber que te pide
     * @return envio
     */
    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        Envio envio = null;
        boolean resultado = false;
        do {
            System.out.print(mensaje);
            String localizador = teclado.next();
            if (localizador.equals("cancelar")){
                PlanetExpress.menu(teclado);
                resultado = true;
            }
            envio = buscarEnvio(localizador);
            if (envio != null){
                resultado = true;
            }
        } while (!resultado);
        return envio;
    }



    /**
     * Añade los Envios al final de un fichero CSV, SIN SOBREESCRIBIR la información
     * @param fichero lugar donde esta la informacion
     * @return boolean
     */
    public boolean aniadirEnviosCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (Envio envio: envios){
                pw.println(envio);
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
     * Lee los Envios del fichero CSV y los añade a las listas de sus respectivos Portes y Clientes
     * @param ficheroEnvios lugar donde esta la informacion
     * @param portes lugar de envios
     * @param clientes persona que tiene los envios
     */
    public static void leerEnviosCsv(String ficheroEnvios, ListaPortes portes, ListaClientes clientes) {
        ListaEnvios listaEnvios = new ListaEnvios(clientes.getOcupacion());
        Envio envio = null;
        try (Scanner sc = new Scanner(new File(ficheroEnvios))) {
            sc.useDelimiter(";");
            int contador = 0;
                String localizador = "";
                String id = "";
                String email = "";
                int fila = 0;
                int columna = 0;
                double precio = 0;
                while (sc.hasNext()) {
                    contador++;
                    if (contador == 1) {
                        localizador = sc.next();
                    }
                    if (contador == 2) {
                        id = sc.next();
                    }
                    if (contador == 3) {
                        email = sc.next();
                    }
                    if (contador == 4) {
                        fila = Integer.parseInt(sc.next());
                    }
                    if (contador == 5) {
                        columna = Integer.parseInt(sc.next());
                    }
                    if (contador == 6) {
                        precio = Double.parseDouble(sc.next());
                        Cliente cliente = clientes.buscarClienteEmail(email);
                        Porte porte = portes.buscarPorte(id);
                        envio = new Envio(localizador, porte, cliente, fila, columna, precio);
                        cliente.aniadirEnvio(envio);
                        porte.insertarEnvioAPorte(envio);
                        listaEnvios.insertarEnvio(envio);
                        if (portes.buscarPorte(id) != null){
                            portes.buscarPorte(id).ocuparHueco(envio);
                        }
                        contador = 0;
                        sc.nextLine();
                    }
                }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
