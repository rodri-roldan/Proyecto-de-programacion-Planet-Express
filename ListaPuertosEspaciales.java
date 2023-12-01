import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPuertosEspaciales {
    private PuertoEspacial[] lista;

    /**
     * TODO: Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad
     */
    public ListaPuertosEspaciales(int capacidad) {
        lista = new PuertoEspacial[capacidad];
    }
    // TODO: Devuelve el número de puertos espaciales que hay en la lista
    public int getOcupacion() {
        return lista.length;
    }
    // TODO: ¿Está llena la lista?
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < lista.length; i++){
            if (lista[i] != null){
                contador++;
            }
        }
        if (contador == lista.length){
            resultado = true;
        }
        return resultado;

    }
	// TODO: Devuelve un puerto espacial dado un indice
    public PuertoEspacial getPuertoEspacial(int i) {
        return lista[i];
    }

    /**
     * TODO: insertamos un Puerto espacial nuevo en la lista
     * @param puertoEspacial
     * @return true en caso de que se añada correctamente, false en caso de lista llena o error
     */
    public boolean insertarPuertoEspacial(PuertoEspacial puertoEspacial) {
        boolean resultado = false;
        if (estaLlena()){
            System.out.println("No hay puertos disponibles");
        } else{
            for (int i = 0; i < lista.length; i++){
                if (lista[i] != null){
                    puertoEspacial = lista[i];
                    resultado = true;
                    System.out.println("Se ha estacionado en el puerto"+i);
                    break;
                }
            }
        }
        return resultado;
    }

    /**
     * TODO: Buscamos un Puerto espacial a partir del codigo pasado como parámetro
     * @param codigo
     * @return Puerto espacial que encontramos o null si no existe
     */
    public PuertoEspacial buscarPuertoEspacial(String codigo) {
        PuertoEspacial puertoEspacialBuscado = null;
        for (PuertoEspacial puerto: lista){
            if (codigo.equals(puerto.getCodigo())){
                puertoEspacialBuscado = puerto;
            }
        }
        return puertoEspacialBuscado;
    }

    /**
     * TODO: Permite seleccionar un puerto espacial existente a partir de su código, usando el mensaje pasado como
     *  argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado.
     *  La función solicita repetidamente el código hasta que se introduzca uno correcto
     * @param teclado
     * @param mensaje
     * @return
     */
    public PuertoEspacial seleccionarPuertoEspacial(Scanner teclado, String mensaje) {
        PuertoEspacial puertoEspacial = null;
        buscarPuertoEspacial(Utilidades.leerCadena(teclado, mensaje));


        return puertoEspacial;
    }

    /**
     * TODO: Genera un fichero CSV con la lista de puertos espaciales, SOBREESCRIBIENDOLO
     * @param nombre
     * @return
     */
    public boolean escribirPuertosEspacialesCsv(String nombre) {
        PrintWriter pw = null;
        try {

            return true;
        } catch (Exception e) {
            return false;
        } finally {

        }
    }


    /**
     * TODO: Genera una lista de PuertosEspaciales a partir del fichero CSV, usando el argumento como capacidad máxima
     *  de la lista
     * @param fichero
     * @param capacidad
     * @return
     */
    public static ListaPuertosEspaciales leerPuertosEspacialesCsv(String fichero, int capacidad) {
        ListaPuertosEspaciales listaPuertosEspaciales = new ListaPuertosEspaciales(capacidad);
        Scanner sc = null;
        try {

        } catch (Exception e) {
            return null;
        } finally {

        }
        return listaPuertosEspaciales;
    }
}
