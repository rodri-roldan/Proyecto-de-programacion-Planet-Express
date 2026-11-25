import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Un objeto Porte que tiene asociado un id, una nave, un puerto espacial de origen y su muelle,
 * uno de llegada y su muelle, una fecha de salida, otra de llegada, una matriz de huecos disponibles
 * y una lista de envios asociada al porte.
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class Porte {
    private boolean[][] huecos;
    private String id;
    private Nave nave;
    private PuertoEspacial origen;
    private int muelleOrigen;
    private Fecha salida;
    private PuertoEspacial destino;
    private int muelleDestino;
    private Fecha llegada;
    private double precio;
    private ListaEnvios listaEnvios;

    /**
     *Completa el constructo de la clase
     * 
     * @param id identificador
     * @param nave nave
     * @param origen origen de donde sale la nave
     * @param muelleOrigen muelleOrigen de donde sale la nave
     * @param salida salida de donde sale la nave
     * @param destino destino de donde sale la nave
     * @param muelleDestino muelleDestino de donde sale la nave
     * @param llegada llegada de donde sale la nave
     * @param precio precio del envio
     */
    public Porte(String id, Nave nave, PuertoEspacial origen, int muelleOrigen, Fecha salida, PuertoEspacial destino, int muelleDestino, Fecha llegada, double precio) {
    this.id = id;
    this.nave = nave;
    this.muelleOrigen = muelleOrigen;
    this.precio = precio;
    this.origen = origen;
    this.salida = salida;
    this.destino = destino;
    this.muelleDestino=  muelleDestino;
    this.llegada = llegada;
    this.huecos = new boolean[nave.getFilas()][nave.getColumnas()];
    for (int i = 0; i < nave.getFilas(); i++){
        for (int j = 0; j < nave.getColumnas(); j++){
            huecos[i][j] = false;
        }
    }
    this.listaEnvios = new ListaEnvios(nave.getColumnas()*nave.getFilas());

    }

    /**
     * @return identificador
     */
    public String getID() {
        return id;
    }

    /**
     * @return nave del porte
     */
    public Nave getNave(){
        return nave;
    }

    /**
     * @return Puerto espacial de origen
     */
    public PuertoEspacial getOrigen() {
        return origen;
    }

    /**
     * @return muelle de origen del puerto espacial de origen
     */
    public int getMuelleOrigen() {
        return muelleOrigen;
    }

    /**
     * @return fecha salida de la nave
     */
    public Fecha getSalida(){
        return salida;
    }

    /**
     * @return Puerto espacial de destion
     */
    public PuertoEspacial getDestino() {
        return destino;
    }

    /**
     * @return muelle de destino del puerto espacial del destino
     */
    public int getMuelleDestino() {
        return muelleDestino;
    }

    /**
     * @return fecha llegada de la nave
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     * @return precio del porte
     */
    public double getPrecio() {
        return precio;
    }

    /**Devuelve el número de huecos libres que hay en el porte
     * @return numero de huecos libres
     */
    public int numHuecosLibres() {
        int contador = 0;
        for(int i = 0; i < huecos.length; i++){
            for (int j = 0; j < huecos[i].length; j++){
                boolean revision = huecos[i][j];
                if (revision){
                    contador++;
                }
            }
        }
        return contador;
    }

    /** metodo que dice si esta lleno el array
     * @return  true si el porte esta lleno, false en caso contrario
     */
    public boolean porteLleno() {
        return numHuecosLibres() != huecos.length;
    }

    /** metodo que permite ocupar un hueco en funcion de filas y columnas
     * @param fila filas del array
     * @param columna columnas del array
     * @return true si se ha podido ocupar el hueco, false en caso contrario
     */

    public boolean huecoOcupado(int fila, int columna) {
        boolean huecoOcupado = false;
        if (huecos == null){
            return false;
        } else {
            huecoOcupado = huecos[fila-1][columna-1];
        }
        return huecoOcupado;
    }

    /**metodo que busca envios a partir de un parametro
     * @param localizador identificador del envio
     * @return envio buscado
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**metodo que inserta envios al porte
     * @param envio envios del porte que se van a insertar
     * @return boolean
     */
    public boolean insertarEnvioAPorte (Envio envio) {
        boolean bool = false;
        int contador = 0;
        while (!bool) {
            if (listaEnvios.getEnvio(contador) == null) {
                listaEnvios.insertarEnvio(envio);
                bool = true;
            } else {
                contador++;
            }
        }
        return bool;
    }

    /**
     *Devuelve el objeto Envio que corresponde con una fila o columna,
     * @param fila filas del array
     * @param columna columnas del array
     * @return el objeto Envio que corresponde, o null si está libre o se excede en el límite de fila y columna
     */
    public Envio buscarEnvio(int fila, int columna) {
        Envio envio = listaEnvios.buscarEnvio(getID(),fila,columna);
        return envio;
    }


    /**
     * Método que Si está desocupado el hueco que indica el envio, lo pone ocupado y devuelve true,
     *  si no devuelve false
     * @param envio envios del porte
     * @return true si ha podido ocupar el hueco del envio, false en caso contrario
     */
    public boolean ocuparHueco(Envio envio) {
        boolean resultado = false;
        if (!huecoOcupado(envio.getFila(),envio.getColumna())){
            huecos[envio.getFila()-1][envio.getColumna()-1] = true;
            resultado = true;
        }
        return resultado;
    }


    /**
     * A través del localizador del envio, se desocupará el hueco
     * @param localizador identificador del porte
     * @return true si ha podido desocupar el hueco del envio con localizador, false
     * en caso contrario
     */
    public boolean desocuparHueco(String localizador) {
        boolean resultado = false;
        Envio envio = buscarEnvio(localizador);
        if (envio != null){
            huecos[envio.getFila()][envio.getColumna()] = false;
            resultado = true;
        }
        return resultado;
    }

    /**
     *Devuelve una cadena con información completa del porte
     * @return string con formato
     */
    public String toString() {
        return "Porte "+id+" de "+origen.toStringSimple()+" Terminal "+ muelleOrigen+" ("+salida.toString()+") a "
                + destino.toStringSimple() +" "+ muelleDestino + " ("+llegada.toString()+") en "+nave.toStringSimple()+"por "+precio+
                ", huecos libres: "+numHuecosLibres();

    }


    /**
     *Devuelve una cadena con información abreviada del vuelo
     * @return string con formato
     */
    public String toStringSimple() {
        return "Porte "+id+" de "+origen.toStringSimple() +" "+muelleOrigen+ " ("+salida.toString()+") a "+destino.toStringSimple()+" "
                +muelleDestino+ " ("+llegada.toString()+")";
    }

    /** Devuelve una cadena ya ordenada para el formato csv
     * @return
     */
    public String toStringCSV(){
        return id+";"+origen.getCodigo()+";"+muelleOrigen+";"+salida.toString()+";"+destino.getCodigo()+";"+muelleDestino+";"+llegada.toString();
    }


    /**
     *Devuelve true si el código origen, destino y fecha son los mismos que el porte
     * @param codigoOrigen codigo de origen del porte
     * @param codigoDestino codigo de destino del porte
     * @param fecha fecha de salida del porte
     * @return
     */
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha) {
        return codigoOrigen.equals(getOrigen().getCodigo()) && codigoDestino.equals(getDestino().getCodigo())&& fecha.coincide(salida);
    }


    /**
     *Muestra la matriz de huecos del porte, ejemplo:
     *      A  B  C
     *   1[ ][ ][ ]
     *   2[X][X][X]
     *   3[ ][ ][ ]
     *   4[ ][X][ ]
     *  10[ ][ ][ ]
     */
    public void imprimirMatrizHuecos() {
        int aux = huecos.length-1;
        int letra = 64;
        for (int i = 0; i < huecos.length+1; i++){
            for (int j = 0; j < huecos[aux].length; j++){
                if (i == 0){
                    if (j == 0){
                        System.out.print("   ");
                    }
                    letra++;
                    System.out.print((char) letra+"  ");
                } else {
                    if (j == 0){
                        System.out.print(i+" ");
                    }
                    if (huecos[aux][j]){
                        System.out.print("[ ]");
                    } else {
                        System.out.print("[X]");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     *Devuelve true si ha podido escribir en un fichero la lista de envíos del porte, siguiendo las indicaciones
     * del enunciado
     * @param fichero fichero de envios
     * @return true si la lista ha sido generada y false si no ha sido generada
     */
    public boolean generarListaEnvios(String fichero){
        PrintWriter salidaFichero = null;
        try {
            salidaFichero = new PrintWriter(fichero);
            for (int i = 0; i < listaEnvios.getOcupacion(); i++){
                Envio envio = listaEnvios.getEnvio(i);
                if (envio != null){
                    salidaFichero.println(envio);
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            System.out.println("\tEl fichero se ha creado correctamente\n");
            salidaFichero.close();
        }
    }


    /**
     *Genera un ID de porte. Este consistirá en una cadena de 6 caracteres, de los cuales los dos primeros
     *  serán PM y los 4 siguientes serán números aleatorios.
     *  NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
     * @param rand variable aleatoria
     * @return ejemplo -> "PM0123"
     */
    public static String generarID(Random rand) {
        String parteAlfabetica = "PM";
        String ID = parteAlfabetica;

        for (int i = 0; i < 4; i++) {
            int aleatorio = rand.nextInt(10);
            String parteNumerica = String.valueOf(aleatorio);
            ID += parteNumerica;
        }
        return ID;
    }

    /**
     *Crea y devuelve un objeto Porte de los datos que selecciona el usuario de puertos espaciales
     * y naves y la restricción de que no puede estar repetido el identificador, siguiendo las indicaciones
     * del enunciado.
     * La función solicita repetidamente los parametros hasta que sean correctos
     * @param teclado objeto que lee el teclado
     * @param rand variable aleatoria
     * @param puertosEspaciales los puertos espaciales
     * @param naves naves del porte
     * @param portes portes
     * @return nuevo porte
     */
    public static Porte altaPorte(Scanner teclado, Random rand, ListaPuertosEspaciales puertosEspaciales, ListaNaves naves, ListaPortes portes) {
        Porte porte = null;
        if (!portes.estaLlena()){
            System.out.println();
            puertosEspaciales.mostrarPuertosEspaciales();
            PuertoEspacial origen = puertosEspaciales.seleccionarPuertoEspacial(teclado, "\tIngrese código de puerto Origen: ");
            Utilidades.leerNumero(teclado, "\t\tIngrese el muelle de Origen (1 - "+origen.getMuelles()+"): ",1,origen.getMuelles());
            PuertoEspacial destino = puertosEspaciales.seleccionarPuertoEspacial(teclado, "\tIngrese código de puerto Destino: ");
            Utilidades.leerNumero(teclado, "\t\tIngrese Terminal Destino (1 - " + destino.getMuelles()+"): ",1,destino.getMuelles());
            System.out.println();
            naves.mostrarNaves();
            Nave nave = naves.seleccionarNave(teclado, "\tIngrese matrícula de la nave: ", origen.distancia(destino));
            System.out.println();
            Fecha fechaSalida;
            Fecha fechaLlegada;
            do {
                fechaSalida = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de salida: ");
                fechaLlegada = Utilidades.leerFechaHora(teclado, "Introduzca la fecha de llegada: ");
                if(!(fechaSalida).anterior(fechaLlegada)){
                    System.out.println("Llegada debe ser posterior a salida\n");
                }
            } while (!(fechaSalida).anterior(fechaLlegada));
            System.out.println();
            double precio = Utilidades.leerNumero(teclado,"Ingrese precio de pasaje (use como separador la coma): ",0,5000.00);
            String id = generarID(rand);
            porte = new Porte(id, nave,origen, origen.getMuelles(), fechaSalida ,destino, destino.getMuelles(), fechaLlegada, precio);
            System.out.println("\tEl porte "+id+" ha sido creado correctamente\n");
            portes.insertarPorte(porte);
        } else {
            System.out.println("\t\tNo se pueden dar de alta más vuelos.\n");
        }
        return porte;
    }
}
