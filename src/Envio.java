import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Un objeto Envio con un localizador asociado, un porte asociado, un cliente asociado, un hueco (fila, columna)
 * asociado y con un percio determinado.
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class Envio {

    private String localizador;
    private Porte porte;
    private Cliente cliente;
    private int fila;
    private int columna;
    private double precio;

    /**
     * Constructor of the class
     *
     * @param localizador id del porte
     * @param porte porte que contiene los envios
     * @param cliente la persona
     * @param fila fila donde esta el envio
     * @param columna columna donde esta el envio
     * @param precio precio del envio
     */
    public Envio(String localizador, Porte porte, Cliente cliente, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.porte = porte;
        this.cliente = cliente;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
    }

    /**
     * @return devuelve el id del envio
     */
    public String getLocalizador() {
        return localizador;
    }

    /**
     * @return devuelve el porte
     */
    public Porte getPorte() {
        return porte;
    }

    /**
     * @return devuelve el cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @return devuelve fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * @return devuelve la columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @return un string que representa un hueco de la matriz
     */
    public String getHueco() {
        char columnaCar = (char) (columna+64);
        return ""+getFila()+columnaCar;
    }

    /**
     * @return devuelve el precio
     */
    public double getPrecio() {
        return precio;
    }

    /** Texto que debe generar: Envío PM1111AAAABBBBC para Porte PM0066 de GGT M5 (01/01/2023 08:15:00) a CID M1 (01/01/2024 11:00:05) en hueco 6C por 13424,56 SSD
     * @return devuelve el string
     */

    public String toString() {
        return "Envio "+getLocalizador()+" para "+porte.toStringSimple()+" en hueco "+getHueco()+" por "+getPrecio();
    }

    /** Cancela este envío, eliminándolo de la lista de envíos del porte y del cliente correspondiente
     * @return true si ha podido cancelar el envio, false en caso contrario
     */
    public boolean cancelar(){
        boolean resultado = false;
        getPorte().desocuparHueco(getLocalizador());
        cliente.cancelarEnvio(getLocalizador());
        return resultado;
    }

    /** Método para imprimir la información de este envío en un fichero que respecta el formato descrito en el
     *  enunciado
     * @param fichero nombre del fichero
     * @return Devuelve la información con formato
     */
    public boolean generarFactura(String fichero) {
        try (PrintWriter pw = new PrintWriter(fichero)) {
            pw.println("---------------------------------------------------------------------------");
            pw.println("--------- Factura del envío " + getLocalizador() + " ---------");
            pw.println("---------------------------------------------------------------------------");
            pw.println("Porte: " + getPorte().getID());
            pw.println("Origen: " + getPorte().getOrigen().toStringSimple());
            pw.println("Destino: " + getPorte().getDestino().toStringSimple());
            pw.println("Salida: " + getPorte().getSalida().toString());
            pw.println("Llegada: " + getPorte().getLlegada().toString());
            pw.println("Cliente: " + getCliente().toString());
            pw.println("Hueco: " + getHueco());
            pw.println("Precio: " + getPrecio());

            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            System.out.println("\tFactura creada correctamente\n");
        }
    }



    /**
     * Genera un localizador de envío. Este consistirá en una cadena de 15 caracteres, de los cuales los seis
	 *   primeros será el ID del porte asociado y los 9 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
     *   NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand un numero random
     * @param idPorte el localizador del encio
     * @return localizador
     */
    public static String generarLocalizador(Random rand, String idPorte) {
        StringBuilder localizador = new StringBuilder(idPorte);
        String partealfabetica = "";
        for (int i = 0; i < 9; i++) {
            int aleatorio = rand.nextInt(65, 90);
            char caracter = (char) aleatorio;
            partealfabetica += caracter;
        }
        localizador.append(partealfabetica);
        return localizador.toString();
    }


    /**
     * Método para crear un nuevo envío para un porte y cliente específico, pidiendo por teclado los datos
     *  necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     *  La función solicita repetidamente los parámetros hasta que sean correctos
     * @param teclado objeto para leer el teclado
     * @param rand un numero random
     * @param porte porte que contiene los envios
     * @param cliente persona que tiene envios
     * @return Envio para el porte y cliente especificados
     */
    public static Envio altaEnvio(Scanner teclado, Random rand, Porte porte, Cliente cliente) {
        String localizador = generarLocalizador(rand, porte.getID());
        porte.imprimirMatrizHuecos();
        System.out.println();
        boolean resultado = false;
        int fila;
        char columna;
        double precio = 0.0;
        do {
            fila = Utilidades.leerNumero(teclado,"Fila del hueco: ",1,porte.getNave().getFilas());
            columna = Utilidades.leerLetra(teclado,"Columna del hueco: ",'A',((char)porte.getNave().getColumnas()));
            if (!porte.huecoOcupado(fila,columna-64)){
                precio = Utilidades.leerNumero(teclado," Ingrese precio de pasaje (use como separador la coma): ",0,5000.00);
                resultado = true;
            } else{
                System.out.println("El hueco está ocupado");
            }
        } while (!resultado);
        Envio envio = new Envio(localizador, porte, cliente, fila, columna ,precio);
        cliente.aniadirEnvio(envio);
        return envio;
    }
}