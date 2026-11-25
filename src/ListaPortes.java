import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lista de portes
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad espacio del array
     */
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
    }

    /** Devuelve el número de portes que hay en la lista

     * @return  portes
     */
    public int getOcupacion() {
        return portes.length;
    }

    /** metodo que dice si eta lleno el array
     * @return  boolean
     */
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < getOcupacion(); i++){
            if (portes[i] != null){
                contador++;
            }
        }
        if (contador == getOcupacion()){
            resultado = true;
        }
        return resultado;
    }

    /**devuelve un porte dado un indice
     * @param i variable
     * @return portes
     */
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * Devuelve true si puede insertar el porte
     * @param porte porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        int contador = 0;
        boolean resultado = false;
        if (estaLlena()){
            System.out.println("Límite máximo de Portes alcanzado");
        } else{
            while (!resultado) {
                if (contador == portes.length) {
                    System.out.println("Ha ocurrido un error insertando el porte");
                    resultado = true;
                }
                if (portes[contador] == null) {
                    portes[contador] = porte;
                    resultado = true;
                } else {
                    contador++;
                }

            }
        }
        return resultado;
    }


    /**
     * Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id localizador del envio
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte porteBuscado = null;
        int contador = 0;
        boolean idCorrecto = false;
        while (!idCorrecto){
            if (portes[contador] == null) idCorrecto = false;
            else{
                if (id.equals(portes[contador].getID())){
                    porteBuscado = portes[contador];
                    idCorrecto = true;
                }
            }
            contador++;
        }
        return porteBuscado;
    }

    /**
     * Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen codigo de oprigen
     * @param codigoDestino codigo de destino
     * @param fecha dia mes y el año
     * @return portes
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        int capacidadLista = 0;
        for (Porte porte : portes) {
            if (porte != null && porte.coincide(codigoOrigen, codigoDestino, fecha)) {
                capacidadLista++;
            }
        }
        ListaPortes portesBuscados = new ListaPortes(capacidadLista);
        for (int i = 0; i < portes.length; i++){
            if (portes[i] != null && portes[i].coincide(codigoOrigen,codigoDestino, fecha)){
                portesBuscados.insertarPorte(portes[i]);
            }
        }
        return portesBuscados;
    }

    /**
     * Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {
        for (Porte porte: portes){
            if (porte != null){
                System.out.println(porte.toStringSimple());
            }
        }
        System.out.println();
    }


    /**
     * Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado objeto para leer el teclado
     * @param mensaje texto
     * @param cancelar cancelar
     * @return porte
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        Porte porte = null;
        boolean resultado = false;
        String idSeleccionado = Utilidades.leerCadena(teclado, mensaje);
        do {
            if (idSeleccionado.equals(cancelar)){
                PlanetExpress.menu(teclado);
                resultado = true;
            }
            for (Porte porteAux : portes){
                if (porteAux  != null){
                    if (idSeleccionado.equals(porteAux.getID())){
                        porte = porteAux;
                        resultado = true;
                    } else {
                        resultado = true;
                    }
                }
            }
        } while (!resultado);
        return porte;
    }

    /**
     *  Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero fichero
     * @return boolean
     */
    public boolean escribirPortesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (Porte porte : portes){
                pw.println(porte.toStringCSV());
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            System.out.println("\tEl fichero se ha creado correctamente\n");
            pw.close();
        }
    }

    /**
     * Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero el fichero
     * @param capacidad esapcio del array
     * @param puertosEspaciales los puertos
     * @param naves las naves
     * @returnlista portes
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {
            Scanner sc = new Scanner(new File(fichero));
            sc.useDelimiter(";");
            boolean bool = true;
            int i = 0;
                String id = "";
                Nave nave = null;
                PuertoEspacial origen = null;
                int muelle_orig = 0;
                Fecha salida = null;
                PuertoEspacial destino = null;
                int muelle_dest = 0;
                Fecha llegada = null;
                double precio = 0.0;
                while (sc.hasNext()){
                    i++;
                    if (i == 1){
                        id = sc.next();
                    }
                    if (i == 2){
                        nave = naves.buscarNave(sc.next());
                    }
                    if (i == 3){
                        origen = puertosEspaciales.buscarPuertoEspacial(sc.next());
                    }
                    if (i == 4){
                        muelle_orig = sc.nextInt();
                    }
                    if (i == 5){
                        salida = Fecha.fromString(sc.next());
                    }
                    if (i == 6){
                        destino = puertosEspaciales.buscarPuertoEspacial(sc.next());
                    }
                    if (i == 7){
                        muelle_dest = sc.nextInt();
                    }
                    if (i == 8){
                        llegada = Fecha.fromString(sc.next());
                    }
                    if (i == 9){
                        precio = Double.parseDouble(sc.next());
                        listaPortes.insertarPorte(new Porte(id,nave,origen,muelle_orig,salida,destino,muelle_dest,llegada,precio));
                        i = 0;
                        sc.nextLine();
                    }
                }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listaPortes;
    }
}
