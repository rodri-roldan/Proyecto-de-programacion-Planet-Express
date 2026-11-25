/**
 * Un objeto Nave que tiene asociado una marca, un modelo, una matricula,
 * un numero determinado de huecos (filas y columnas) y un alcance determinado.
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class Nave {
    private String marca;
    private String modelo;
    private String matricula;
    private int columnas;
    private int filas;
    private double alcance;


    /**
     * Constructor of the class
     *
     * @param marca marca de la nave
     * @param modelo modelo de la nave
     * @param matricula matricula de la nave
     * @param columnas numero de columnas que tiene de capacidad la nave
     * @param filas numero de filas que tiene de capacidad la nave
     * @param alcance alcance de la nave en Unidades Astronómicas (UA)
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @return modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @return columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * @return filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * @return alcance
     */
    public double getAlcance() {
        return alcance;
    }


    /**
     * Crea un String con los datos de una nave con el siguiente formato:
     * "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     * @return string
     */
    public String toString() {
        return getMarca()+" "+getModelo()+" ("+getMatricula()+"): "+(getColumnas()*getColumnas())+" contenedores, hasta "+getAlcance()+" UA";
    }

    /**
     * Crea un String con los datos de una nave con el siguiente formato:
     * "Planet Express One (EP-245732X)"
     * @return string
     */
    public String toStringSimple() {
        return getMarca()+" "+getModelo()+" ("+getMatricula()+")";
    }

    /** Crea un string con los datos de la nave con formato csv
     * @return string
     */
    public String toStringCSV(){
        return getMarca()+";"+getModelo()+";"+getMatricula()+";"+getFilas()+";"+getColumnas()+";"+getAlcance();
    }
}
