import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Clase
 *
 * @author Rodrigo Roldan Bordas
 * @author Alejandro Abello Herranz
 * @version     1.0
 */
public class ListaClientes {
    private Cliente[] clientes;

    /**
     * Constructor de la clase para inicializar la lista a una capacidad determinada
     *
     * @param capacidad del array
     */
    public ListaClientes(int capacidad) {
        clientes = new Cliente[capacidad];
    }

    /** Devuelve el número de clientes que hay en la lista de clientes
     * @return el numero de clientes
     */
    public int getOcupacion() {
        return clientes.length;
    }

    /**metodo que dice si la lista esta llena
     * @return  true si esta llena y false si no esta
     */
    public boolean estaLlena() {
        boolean resultado = false;
        int contador = 0;
        for (int i = 0; i < getOcupacion(); i++){
            if (clientes[i] != null){
                contador++;
            }
        }
        if (contador == getOcupacion()){
            resultado = true;
        }
        return resultado;

    }

    /**    Devuelve el cliente dada el indice
     * @param i variable
     * @return cliente
     */
    public Cliente getCliente(int i) {
        return clientes[i];
    }

    /**
     *  Inserta el cliente en la lista de clientes
     * @param cliente una persona
     * @return true si el cliente se ha insertado y false si no se ha insertado
     */
    public boolean insertarCliente(Cliente cliente) {
        int contador = 0;
        boolean resultado = false;
        if (estaLlena()){
            System.out.println("Límite máximo de Puertos Espaciales alcanzado");
        } else{
            while (!resultado) {
                if (contador == getOcupacion()) {
                    System.out.println("Ha ocurrido un error insertando el cliente");
                    resultado = true;
                }
                if (clientes[contador] == null) {
                    clientes[contador] = cliente;
                    resultado = true;
                } else {
                    contador++;
                }

            }
        }
        return resultado;
    }

    /** Busca un cliente dado un email
     * @param email email
     * @return Devuelve el cliente que coincida con el email, o null en caso de no encontrarlo
     */
    public Cliente buscarClienteEmail(String email) {
        Cliente clienteBuscado = null;
        boolean emailCorrecto = false;
        int contador = 0;
        while (!emailCorrecto){
            if (clientes[contador] == null) {
                if (contador == getOcupacion()){
                    return null;
                }
            }
            else {
                if (email.equals(clientes[contador].getEmail())){
                    clienteBuscado = clientes[contador];
                    emailCorrecto = true;
                }
            }
            if (contador == clientes.length-1){
                emailCorrecto = true;
            }
            contador++;
        }
        return clienteBuscado;
    }

    /**
     * Método para seleccionar un Cliente existente a partir de su email, usando el mensaje pasado como argumento
     *  para la solicitud y, siguiendo el orden y los textos mostrados en el enunciado.
     *  La función debe solicitar repetidamente hasta que se introduzca un email correcto
     * @param teclado objeto tipo scanner
     * @param mensaje mensaje que se quiere imprimir por pantalla
     * @return cliente seleccionado
     */
    public Cliente seleccionarCliente(Scanner teclado, String mensaje) {
        Cliente cliente = null;
        boolean resultado = false;
        do {
            System.out.print(mensaje);
            String email = teclado.next();
            if (email.equals("cancelar")){
                PlanetExpress.menu(teclado);
                resultado = true;
            }
            for (Cliente clienteAux : clientes){
                if (clienteAux != null){
                    if (email.equals(clienteAux.getEmail())){
                        cliente = clienteAux;
                        resultado = true;
                    }
                }
            }
        } while (!resultado);
        return cliente;
    }

    /**
     * Método para guardar la lista de clientes en un fichero .csv, sobreescribiendo la información del mismo
     *  fichero
     * @param fichero nombre del fichero
     * @return true si ha podido escribir el fichero, false en caso contrario
     */
    public boolean escribirClientesCsv(String fichero) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fichero);
            for (Cliente cliente : clientes){
                if (cliente != null){
                    pw.println(cliente);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            pw.close();
        }
        return true;
    }

    /**
     * Genera una lista de Clientes a partir del fichero CSV, usando los límites especificados como argumentos
     *  para la capacidad de la lista y el número de billetes máximo por pasajero
     * @param fichero nombre del fichero
     * @param capacidad capacidad de la lista del clientes
     * @param maxEnviosPorCliente numero maximo de envios permitidos por clientes
     * @return lista de clientes
     */
    public static ListaClientes leerClientesCsv(String fichero, int capacidad, int maxEnviosPorCliente) {
        ListaClientes listaClientes = new ListaClientes(capacidad);
        try (Scanner sc = new Scanner(new File(fichero))) {
            sc.useDelimiter(";");
            int i = 0;
                String nombre = "";
                String apellido = "";
                String email = "";
                while (sc.hasNext()) {
                    i++;
                    if (i == 1) {
                        nombre = sc.next();
                    }
                    if (i == 2) {
                        apellido = sc.next();
                    }
                    if (i == 3) {
                        email = sc.next();
                        listaClientes.insertarCliente(new Cliente(nombre, apellido, email, maxEnviosPorCliente));
                        i = 0;
                        sc.nextLine();
                    }
                }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return listaClientes;
    }
}