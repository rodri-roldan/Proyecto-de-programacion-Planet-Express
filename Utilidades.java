import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Utilidades {

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return int numero
     */
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo) {
        int numero;
        do{
            System.out.print(mensaje);
            numero = teclado.nextInt();
        } while (numero > maximo || numero < minimo);
        teclado.close();
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return long numero
     */
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo) {
        long numero;
        do{
            System.out.print(mensaje);
            numero = teclado.nextLong();
        } while (numero > maximo || numero < minimo);
        teclado.close();
        return numero;
    }

    /**
     * TODO: Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return double numero
     */
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo) {
        double numero;
        do{
            System.out.print(mensaje);
            numero = teclado.nextDouble();
        } while (numero > maximo || numero < minimo);
        teclado.close();
        return numero;
    }

    /**
     * TODO: TODO: Solicita una letra repetidamente hasta que se introduzca uno correcto (dentro de los límites)
     * @param teclado
     * @param mensaje
     * @param minimo
     * @param maximo
     * @return char letra
     */
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo) {
        char letra;
        do{
            System.out.print(mensaje);
            letra = teclado.nextLine().charAt(0);
        } while (letra > maximo || letra < minimo);
        teclado.close();
        return letra;
    }


    /**
     * TODO: Solicita una fecha repetidamente hasta que se introduzca una correcta
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFecha(Scanner teclado, String mensaje) {
        int dia, mes, anio;
        System.out.println(mensaje);
        do{
            dia = leerNumero(teclado, "Introduzca dia: ", 1, Fecha.DIAS_MES);
            mes = leerNumero(teclado, "Introduzca mes: ", 1, Fecha.MESES_ANIO);
            anio = leerNumero(teclado, "Introduzca año: ", Fecha.PRIMER_ANIO,Fecha.ULTIMO_ANIO);
            if (!Fecha.comprobarFecha(dia, mes, anio)){
                System.out.println("Fecha incorrecta");
            }
        } while(!Fecha.comprobarFecha(dia, mes, anio));
        return new Fecha(dia, mes, anio);
    }


    /**
     * TODO: Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
     * @param teclado
     * @param mensaje
     * @return Fecha
     */
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int dia, mes, anio, hora, minuto, segundo;
        Fecha fechaSimple = leerFecha(teclado, mensaje);
        dia = fechaSimple.getDia();
        mes = fechaSimple.getMes();
        anio = fechaSimple.getAnio();
        do{
            hora = leerNumero(teclado, "Introduzca hora: ", 0, Fecha.HORAS_DIA);
            minuto = leerNumero(teclado, "Introduzca minuto: ", 0, Fecha.MINUTOS_HORA);
            segundo = leerNumero(teclado, "Introduzca segundo: ", 0, Fecha.SEGUNDOS_MINUTO);
            if (!Fecha.comprobarHora(hora, minuto, segundo)){
                System.out.println("Hora incorrecta");
            }
        } while (!Fecha.comprobarHora(hora, minuto, segundo));

        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }

    /**
     * TODO: Imprime por pantalla el String pasado por parámetro
     * @param teclado
     * @param s
     * @return
     */
    public static String leerCadena(Scanner teclado, String s) {
        System.out.print(s);
        return teclado.next();
    }
}
