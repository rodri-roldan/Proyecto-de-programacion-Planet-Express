import java.util.Scanner;

/**
 * Un objeto Cliente que tiene asociado un nombre, unos apellidos,
 * un email y una lista de envíos realizados por el cliente.
 *
 * @author Taller de Programación
 * @version     1.0
 */
public class Cliente {
    private final ListaEnvios listaEnvios;
    private final String nombre;
    private final String apellidos;
    private final String email;


    /**
     * Constructor of the class
     *
     * @param nombre Nombre del cliente
     * @param apellidos Apellidos del cliente
     * @param email Email del cliente
     * @param maxEnvios Número máximo de envíos que puede tener el cliente
     */
    public Cliente(String nombre, String apellidos, String email, int maxEnvios) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.listaEnvios = new ListaEnvios(maxEnvios);
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /** Texto que debe generar: Zapp Brannigan, zapp.brannigan@dop.gov
     * @return un string con formato determinado
     */
    public String toString() {
        return getNombre() + getApellidos()+", " +getEmail();
    }

    /** Devuelve un booleano que indica si se ha alcanzado el número máximo de envíos
     * @return  true si se listaEnvios esta llena, false en caso contrario.
     *
     */
    public boolean maxEnviosAlcanzado() {
        return listaEnvios.estaLlena();
    }

    /** Devuelve un envío en función de su posición
     * @param i variable
     * @return envio
     */
    public Envio getEnvio(int i) {
        return listaEnvios.getEnvio(i);
    }

    /**
     * @return  la lista de envios
     */
    public ListaEnvios getListaEnvios() {
        return listaEnvios;
    }

    /** Añade un envío al cliente
     * @param envio envio
     * @return true si se ha podido añadir el envio, false en caso contrario.
     */
    public boolean aniadirEnvio(Envio envio) {
        return listaEnvios.insertarEnvio(envio);
    }

    /**
     * @param localizador id del envio
     * @return envio
     */
    public Envio buscarEnvio(String localizador) {
        return listaEnvios.buscarEnvio(localizador);
    }

    /**Elimina el envío de la lista de envíos del pasajero
     * @param localizador id del envio
     * @return true si se ha podido cancelar el envio, false en caso contrario.
     */

    public boolean cancelarEnvio(String localizador) {
        return listaEnvios.eliminarEnvio(localizador);
    }

    /**
     *lista de los envios
     */
    public void listarEnvios() {
        listaEnvios.listarEnvios();
    }

    /**Encapsula la funcionalidad seleccionarEnvio de ListaEnvios
     * @param teclado objeto para leer el teclado
     * @param mensaje texto
     * @return el envio seleecionado
     */

    public Envio seleccionarEnvio(Scanner teclado, String mensaje) {
        return listaEnvios.seleccionarEnvio(teclado, mensaje);
    }


    /**
     * Método estático para crear un nuevo cliente "no repetido", se pide por teclado los datos necesarios
     * al usuario en el orden y con los textos indicados en los ejemplos de ejecución del enunciado
     * La función tiene que solicitar repetidamente los parámetros hasta que sean correctos
     * @param teclado objeto para leer el teclado
     * @param clientes personas
     * @param maxEnvios numero de envios maximos
     * @return Cliente
     */
    public static Cliente altaCliente(Scanner teclado, ListaClientes clientes, int maxEnvios) {
        Cliente cliente = null;
        if (clientes.estaLlena()){
            System.out.println("\tNo se puede crear un nuevo cliente, la lista esta llena.\n");
        } else {
            String nombre = Utilidades.leerCadena(teclado, "Nombre: ");
            String apellidos = Utilidades.leerCadena(teclado, "Apellidos: ");
            String email = Utilidades.leerCadena(teclado, "Email: ");
            boolean bool = false;
            while (!bool) {
                if (clientes.buscarClienteEmail(email) != null) {
                    System.out.println("\tYa existe un cliente con ese email");
                    email = Utilidades.leerCadena(teclado, "Email: ");
                } else {
                    if (!correctoEmail(email)) {
                        System.out.println("\t\tFormato de email incorrecto.\n\t\tEl email debe seguir la estrucutura nombreapellidos@upm.es");
                        email = Utilidades.leerCadena(teclado, "Email: ");
                    } else {
                        bool = true;
                    }
                }
            }
            cliente = new Cliente(nombre, apellidos, email, maxEnvios);
            clientes.insertarCliente(cliente);
            System.out.println("\tCliente con email "+ cliente.getEmail()+" creado correctamente\n");
        }
        return cliente;
    }

    /**
     * Metodo para comprobar que el formato de email introducido sea correcto
     * @param email del cliente
     * @return true si el formato del email es correcto, false en caso contrario
     */
    public static boolean correctoEmail(String email) {
        String[] partes = email.split("@",2);
        return (partes[1].equals("upm.es") || partes[1].equals("planetexpress.com")) && partes[0].matches("[a-zA-Z]+");
    }
}
