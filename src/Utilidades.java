import java.util.Scanner;

/**
 * Una clase con distintos métodos usados para leer las entradas del teclado.
 *
 * @author Alejandro Abello Herranz
 * @author Rodrigo Roldan Bordas
 * @version     1.0
 */
public class Utilidades {

    /**
     *Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @param minimo numero minimo  entre un rango
     * @param maximo numero maximo entre un rango
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero = Integer.MIN_VALUE;
        while (numero > maximo || numero < minimo){
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(teclado.next());
            }catch (Exception e){
                System.out.println("Entrada introducida incorrecta");
            }
        }
        return numero;
    }

    /**
     *Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @param minimo numero minimo  entre un rango
     * @param maximo numero maximo entre un rango
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero = Long.MIN_VALUE;
        while (numero > maximo || numero < minimo){
            System.out.print(mensaje);
            try {
                numero = Long.parseLong(teclado.next());
            }catch (Exception e){
                System.out.println("Entrada introducida incorrecta");
            }
        }
        return numero;
    }

    /**
     * Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @param minimo numero minimo  entre un rango
     * @param maximo numero maximo entre un rango
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero = Double.MIN_VALUE;
        while (numero > maximo || numero < minimo){
            System.out.print(mensaje);
            try {
                numero = Double.parseDouble(teclado.next());
            }catch (Exception e){
                System.out.println("Entrada introducida incorrecta");
            }
        }
        return numero;
    }

    /**
     *Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @param minimo numero minimo  entre un rango
     * @param maximo numero maximo entre un rango
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra = Character.MIN_VALUE;
        boolean bool = false;
        while (!bool){
            System.out.print(mensaje);
            try {
                letra = teclado.next().charAt(0);
                if (letra != maximo || letra != minimo){
                    bool = true;
                }
            }catch (Exception e){
                System.out.println("Entrada introducida incorrecta");
            }
        }
        return letra;
    }


    /**
     * Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @return Fecha dia mes y año
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia, mes, anio;
        System.out.println(mensaje);
        do{
            dia = leerNumero(teclado, "\tIntroduzca dia: ", 1, Fecha.DIAS_MES);
            mes = leerNumero(teclado, "\tIntroduzca mes: ", 1, Fecha.MESES_ANIO);
            anio = leerNumero(teclado, "\tIntroduzca año: ", Fecha.PRIMER_ANIO,Fecha.ULTIMO_ANIO);
            if (!Fecha.comprobarFecha(dia, mes, anio)){
                System.out.println("Fecha incorrecta");
            }
        } while(!Fecha.comprobarFecha(dia, mes, anio));
        return new Fecha(dia, mes, anio);
    }


    /**
     *Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado objeto scanner para leer el teclado
     * @param mensaje Es la informacion que te piden
     * @return Fecha dia mes año hora minuto y segundos
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia, mes, anio, hora, minuto, segundo;
        Fecha fechaSimple = leerFecha(teclado, mensaje);
        dia = fechaSimple.getDia();
        mes = fechaSimple.getMes();
        anio = fechaSimple.getAnio();
        do{
            hora = leerNumero(teclado, "\tIntroduzca hora: ", 0, Fecha.HORAS_DIA);
            minuto = leerNumero(teclado, "\tIntroduzca minuto: ", 0, Fecha.MINUTOS_HORA);
            segundo = leerNumero(teclado, "\tIntroduzca segundo: ", 0, Fecha.SEGUNDOS_MINUTO);
            if (!Fecha.comprobarHora(hora, minuto, segundo)){
                System.out.println("Hora incorrecta");
            }
        } while (!Fecha.comprobarHora(hora, minuto, segundo));
        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * Imprime por pantalla el String pasado por parámetro
     * @param teclado objeto scanner para leer el teclado
     * @param s Es la informacion que te piden
     * @return teclado
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}
