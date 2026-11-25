/**
 * Un objeto Puerto Espacial que tiene asociado un nombre, un codigo, unas coordenadas (radio, azimut, polar)
 * y un número de muelles disponibles.
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;
    private double azimut;
    private double polar;
    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre nombre del puerto
     * @param codigo codigo del puerto
     * @param radio radio del puerto
     * @param azimut azimut del puerto
     * @param polar polar del puerto
     * @param numMuelles numMuelles del puerto
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @return aizmut
     */
    public double getAzimut() {
        return azimut;
    }

    /**
     * @return polar
     */
    public double getPolar() {
        return polar;
    }

    /**
     * @return numero de muelles
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     *Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino lugar de destino
     * @return distancia entre dos puertos
     */
    public double distancia(PuertoEspacial destino) {
        double x2 = destino.radio*Math.sin(destino.azimut)*Math.cos(destino.polar);
        double y2 = destino.radio*Math.sin(destino.azimut)*Math.sin(destino.polar);
        double z2 = destino.radio*Math.cos(destino.azimut);
        double x1 = radio*Math.sin(azimut)*Math.cos(polar);
        double y1 = radio*Math.sin(azimut)*Math.sin(polar);
        double z1 = radio*Math.cos(azimut);
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2)+Math.pow(z2-z1,2));
    }

    /**
     * Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return nombre + " ("+codigo+"), en ("+radio+azimut+polar+"), con "+numMuelles+" muelles";
    }

    /**
     * Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return nombre+" ("+codigo+")";
    }

    /**
     * @return un string que ya ordenado en formato csv.
     */
    public String toStringCSV(){
        return nombre+";"+codigo+";"+radio+";"+azimut+";"+polar+";"+numMuelles;
    }
}
