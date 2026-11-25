import java.util.Random;
import java.util.Scanner;
import java.io.File;

/**
 * Clase principal de Planet Express App, la práctica de Taller de Programación
 *
 * @author      Taller de Progamación
 * @version     1.0
 */
public class PlanetExpress {
    private final int maxPortes;
    private final int maxNaves;
    private final int maxClientes;
    private final int maxEnviosPorCliente;
    private ListaPuertosEspaciales listaPuertosEspaciales;
    private final int maxPuertosEspaciales;
    private ListaNaves listaNaves;
    private ListaClientes listaClientes;
    private ListaPortes listaPortes;


    /**
     * Constructor de la clase
     *
     * @param maxPuertosEspaciales Máximo número de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * @param maxNaves Máximo número de naves que tendrá la lista de naves de PlanetExpress App.
     * @param maxPortes Máximo número de portes que tendrá la lista de portes de PlanetExpress App.
     * @param maxClientes Máximo número de clientes que tendrá la lista de clientes de PlanetExpress App.
     * @param maxEnviosPorCliente Máximo número de envíos por cliente.
     */
    public PlanetExpress(int maxPuertosEspaciales, int maxNaves, int maxPortes, int maxClientes, int maxEnviosPorCliente) {
        this.maxPuertosEspaciales = maxPuertosEspaciales;
        this.maxNaves = maxNaves;
        this.maxPortes = maxPortes;
        this.maxClientes = maxClientes;
        this.maxEnviosPorCliente = maxEnviosPorCliente;
        this.listaNaves = new ListaNaves(maxNaves);
        this.listaClientes = new ListaClientes(maxClientes);
        this.listaPortes = new ListaPortes(maxPortes);
        this.listaPuertosEspaciales = new ListaPuertosEspaciales(maxPuertosEspaciales);
    }


    /**
     * Metodo para leer los datos de los ficheros específicados en el enunciado y los agrega a
     *  la información de PlanetExpress (listaPuertosEspaciales, listaNaves, listaPortes, listaClientes)
     * @param ficheroPuertos contiene la información de los puertos.
     * @param ficheroNaves contiene la información de las naves.
     * @param ficheroPortes contiene la información de los portes.
     * @param ficheroClientes contiene la información de los clientes.
     * @param ficheroEnvios contiene la información de los envios.
     */
    public void cargarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
            listaPuertosEspaciales = ListaPuertosEspaciales.leerPuertosEspacialesCsv(ficheroPuertos,maxPuertosEspaciales);
            listaNaves= ListaNaves.leerNavesCsv(ficheroNaves,maxNaves);
            listaClientes = ListaClientes.leerClientesCsv(ficheroClientes,maxClientes,maxEnviosPorCliente);
            listaPortes = ListaPortes.leerPortesCsv(ficheroPortes, maxPortes, listaPuertosEspaciales, listaNaves);
            ListaEnvios.leerEnviosCsv(ficheroEnvios,listaPortes,listaClientes);
    }


    /**
     * Metodo para almacenar los datos de PlanetExpress en los ficheros .csv especificados
     *  en el enunciado de la práctica
     * @param ficheroPuertos nombre del fichero que contiene la información de los puertos.
     * @param ficheroNaves nombre del fichero que contiene la información de las naves.
     * @param ficheroPortes nombre del fichero que contiene la información de los portes.
     * @param ficheroClientes nombre del fichero que contiene la información de los clientes.
     * @param ficheroEnvios nombre del fichero que contiene la información de los envios.
     */
    public void guardarDatos(String ficheroPuertos, String ficheroNaves, String ficheroPortes, String ficheroClientes, String ficheroEnvios) {
            listaPuertosEspaciales.escribirPuertosEspacialesCsv(ficheroPuertos);
            listaNaves.escribirNavesCsv(ficheroNaves);
            listaClientes.escribirClientesCsv(ficheroClientes);
            listaPortes.escribirPortesCsv(ficheroPortes);
    }

    /**
     * @return true si la lista de portes esta llena, false en caso contrario.
     */
    public boolean maxPortesAlcanzado() {
        return listaPortes.estaLlena();
    }

    /**
     * @param porte objeto tipo porte
     * @return true si se ha podido insertar el porte, false en caso contrario.
     */
    public boolean insertarPorte (Porte porte) {
        return listaPortes.insertarPorte(porte);
    }

    /**
     * @return true si la lista de clientes está llena, false en caso contrario.
     */
    public boolean maxClientesAlcanzado() {
        return listaClientes.estaLlena();
    }

    /**
     * @param cliente objeto tipo cliente
     * @return true si se ha podido insertar el cliente en la lista, false en caso contrario.
     */
    public boolean insertarCliente (Cliente cliente) {
        return listaClientes.insertarCliente(cliente);
    }

    /**
     * Metodo para buscar los portes especificados tal y como aparece en el enunciado de la práctica,
     *  Devuelve una lista de los portes entre dos puertos espaciales con una fecha de salida solicitados por teclado
     *  al usuario en el orden y con los textos establecidos (tomar como referencia los ejemplos de ejecución en el
     *  enunciado de la prática)
     * @param teclado objeto tipo scanner para leer el teclado.
     * @return porte buscado
     */
    public ListaPortes buscarPorte(Scanner teclado) {
        String codigoOrigen = Utilidades.leerCadena(teclado, "Ingrese código de puerto Origen: ");
        String codigoDestino = Utilidades.leerCadena(teclado, "Ingrese código de puerto Destino: ");
        Fecha fechaSalida = Utilidades.leerFecha(teclado, "Fecha de Salida: ");
        System.out.println();
        ListaPortes portesBuscados = listaPortes.buscarPortes(codigoOrigen, codigoDestino, fechaSalida);
        portesBuscados.listarPortes();
        return portesBuscados;
    }


    /**
     * Metodo para contratar un envio tal y como se indica en el enunciado de la práctica. Se contrata un envio para un porte
     *  especificado, pidiendo por teclado los datos necesarios al usuario en el orden y con los textos (tomar como referencia los
     *  ejemplos de ejecución en el enunciado de la prática)
     * @param teclado objeto tipo scanner para leer el teclado.
     * @param rand objeto random.
     * @param porte objeto porte.
     */
    public void contratarEnvio(Scanner teclado, Random rand, Porte porte) {
        if (porte != null) {
            boolean bool = false;
            char letra;
            Envio envio = null;
            while (!bool){
                letra = Utilidades.leerLetra(teclado," ¿Comprar billete para un nuevo pasajero (n) o para uno ya existente (e)? ",'e','n');
                if (letra == 'e' || letra == 'n'){
                    bool = true;
                    if (letra == 'e'){
                        String email = Utilidades.leerCadena(teclado, "Email del cliente");
                        Cliente clienteExistente = listaClientes.buscarClienteEmail(email);
                        envio = Envio.altaEnvio(teclado,rand, porte, clienteExistente);
                        System.out.println(envio+" creado correctamente");
                    }
                    if (letra == 'n'){
                        Cliente clienteNuevo = Cliente.altaCliente(teclado, listaClientes, maxEnviosPorCliente);
                        envio = Envio.altaEnvio(teclado, rand, porte, clienteNuevo);
                        System.out.println(envio+" creado correctamente\n");
                    }
                } else {
                    System.out.println("El valor de entrada debe ser 'n' o 'e'");
                }
            }
        }
    }


    /**
     * Metodo statico con la interfaz del menú de entrada a la App.
     * Tiene que coincidir con las trazas de ejecución que se muestran en el enunciado
     * @param teclado objeto tipo scanner para leer el teclado.
     * @return opción seleccionada
     */
    public static int menu(Scanner teclado) {
        System.out.print(""" 
                            1. Alta de Porte
                            2. Alta de Cliente
                            3. Buscar Porte
                            4. Mostrar envíos de un cliente
                            5. Generar lista de envíos
                            0. Salir
                           """);
        System.out.print("Seleccione opción: ");
        int opcion = teclado.nextInt();
        return opcion;
    }

    /**
     * Método Main que carga los datos de los ficheros CSV pasados por argumento (consola) en PlanetExpress,
     *  llama iterativamente al menú y realiza la opción especificada hasta que se indique la opción Salir. Al finalizar
     *  guarda los datos de PlanetExpress en los mismos ficheros CSV.
     * @param args argumentos de la línea de comandos, recibe **10 argumentos** estrictamente en el siguiente orden:
     * 1. Número máximo de puertos espaciales que tendrá la lista de puertos espaciales de PlanetExpress App.
     * 2. Número máximo de naves que tendrá la lista de naves de PlanetExpress App.
     * 3. Número máximo de portes que tendrá la lita de portes de PlanetExpress App.
     * 4. Número máximo de clientes que tendrá la lista de clientes de PlanetExpress App.
     * 5. Número máximo de envíos por pasajero.
     * 6. Nombre del fichero CSV que contiene la lista de puertos espaciales de PlanetExpress App (tanto para lectura como escritura).
     * 7. Nombre del fichero CSV que contiene la lista de naves de PlanetExpress App (tanto para lectura como escritura).
     * 8. Nombre del fichero CSV que contiene la lista de portes de PlanetExpress App (tanto para lectura como escritura).
     * 9. Nombre del fichero CSV que contiene la lista de clientes de PlanetExpress App (tanto para lectura como escritura).
     * 10. Nombre del fichero CSV que contiene los envíos adquiridos en PlanetExpress App (tanto para lectura como escritura).
     * En el caso de que no se reciban exactamente estos argumentos, el programa mostrará el siguiente mensaje
     * y concluirá la ejecución del mismo: `Número de argumentos incorrecto`.
     */
    public static void main(String [] args) {
        Scanner teclado = new Scanner(System.in);
        if (args.length != 10) {
            System.out.println("Número de argumentos incorrecto");
            return;
        }
        Random rand = new Random();
        PlanetExpress app = new PlanetExpress(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        app.cargarDatos(args[5],args[6],args[7],args[8],args[9]);
        int opcion;
        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 1:
                    Porte.altaPorte(teclado, rand, app.listaPuertosEspaciales, app.listaNaves, app.listaPortes);
                    break;
                case 2:
                    Cliente.altaCliente(teclado, app.listaClientes, Integer.parseInt(args[4]));
                    break;
                case 3:
                    ListaPortes listaPorte = app.buscarPorte(teclado);
                    Porte porte = listaPorte.seleccionarPorte(teclado, "Seleccione un porte: ", "CANCELAR");
                    app.contratarEnvio(teclado, rand, porte);
                    break;
                case 4:
                    Cliente cliente = app.listaClientes.seleccionarCliente(teclado, "Email del cliente: ");
                    cliente.getListaEnvios().listarEnvios();
                    System.out.println();
                    Envio envio = cliente.getListaEnvios().seleccionarEnvio(teclado, "Seleccione un envio: ");
                    char seleccion = Utilidades.leerLetra(teclado, "¿Cancelar envio (c), o generar factura (f)? ",'c','f');
                    if (seleccion == 'c'){
                        envio.cancelar();
                        System.out.println("\tEnvio cancelado correctamente\n");
                    }else if(seleccion=='f'){
                        envio.generarFactura(Utilidades.leerCadena(teclado, "Nombre del fichero: "));
                    }
                    break;
                case 5:
                    app.listaPortes.listarPortes();
                    Porte porteAux = app.listaPortes.seleccionarPorte(teclado, "Seleccione porte: ", "Cancelar");
                    porteAux.generarListaEnvios(Utilidades.leerCadena(teclado, "Nombre del fichero: "));
                    break;
            }
        } while (opcion != 0);


    }
}
