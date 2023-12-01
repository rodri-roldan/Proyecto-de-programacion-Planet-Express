import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPortes {
    private Porte[] portes;
    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPortes(int capacidad) {
        portes = new Porte[capacidad];
    }
    // TODO: Devuelve el número de portes que hay en la lista
    public int getOcupacion() {
        return portes.length;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < portes.length; i++){
            if (portes[i] != null){
                contador++;
            }
        }
        if (contador == portes.length){
            resultado = true;
        }
        return resultado;
    }

	//TODO: devuelve un porte dado un indice
    public Porte getPorte(int i) {
        return portes[i];
    }


    /**
     * TODO: Devuelve true si puede insertar el porte
     * @param porte
     * @return false en caso de estar llena la lista o de error
     */
    public boolean insertarPorte(Porte porte) {
        boolean resultado = false;
        if (estaLlena()){
            System.out.println("No hay portes disponibles");
        } else{
            for (int i = 0; i < portes.length; i++){
                if (portes[i] != null){
                    portes[i] = porte;
                    resultado = true;
                    System.out.println("Se ha introducido en el porte "+i+1);
                    break;
                }
            }
        }
        return resultado;
    }


    /**
     * TODO: Devuelve el objeto Porte que tenga el identificador igual al parámetro id
     * @param id
     * @return el objeto Porte que encontramos o null si no existe
     */
    public Porte buscarPorte(String id) {
        Porte porteBuscado = null;
        for (Porte porte: portes){
            if (id.equals(porte.getID())){
                porteBuscado = porte;
            }
        }
        return porteBuscado;
    }

    /**
     * TODO: Devuelve un nuevo objeto ListaPortes conteniendo los Portes que vayan de un puerto espacial a otro
     *  en una determinada fecha
     * @param codigoOrigen
     * @param codigoDestino
     * @param fecha
     * @return
     */
    public ListaPortes buscarPortes(String codigoOrigen, String codigoDestino, Fecha fecha) {
        int capacidadLista = 0;
        for (Porte porte : portes) {
            if (porte.coincide(codigoOrigen, codigoDestino, fecha)) {
                capacidadLista++;
            }
        }
        ListaPortes portesBuscados = new ListaPortes(capacidadLista);
        for (int i = 0; i < portes.length; i++){
            if (portes[i].coincide(codigoOrigen,codigoDestino, fecha)){
                portesBuscados.insertarPorte(portes[i]);
            }
        }
        return portesBuscados;
    }

    /**
     * TODO: Muestra por pantalla los Portes siguiendo el formato de los ejemplos del enunciado
     */
    public void listarPortes() {

    }


    /**
     * TODO: Permite seleccionar un Porte existente a partir de su ID, usando el mensaje pasado como argumento para
     *  la solicitud y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para
     *  salir devolviendo null.
     *  La función solicita repetidamente hasta que se introduzca un ID correcto
     * @param teclado
     * @param mensaje
     * @param cancelar
     * @return
     */
    public Porte seleccionarPorte(Scanner teclado, String mensaje, String cancelar) {
        listarPortes();
        Porte porte = null;

        return porte;
    }

    /**
     * TODO: Ha de escribir la lista de Portes en la ruta y nombre del fichero pasado como parámetro.
     *  Si existe el fichero, SE SOBREESCRIBE, si no existe se crea.
     * @param fichero
     * @return
     */
    public boolean escribirPortesCsv(String fichero) {
        try {

            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * TODO: Genera una lista de Portes a partir del fichero CSV, usando los límites especificados como argumentos para
     *  la capacidad de la lista
     * @param fichero
     * @param capacidad
     * @param puertosEspaciales
     * @param naves
     * @return
     */
    public static ListaPortes leerPortesCsv(String fichero, int capacidad, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves) {
        ListaPortes listaPortes = new ListaPortes(capacidad);
        try {

        } catch (Exception e) {
            return null;
        }
        return listaPortes;
    }
}
