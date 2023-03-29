package ProgettoLaboratorioB.Server;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * ServerMain class is the class used to start the server.
 * The server can use the methods defined in the interface using the remote object of the server.
 * The remote object is created by the RMI registry.
 */
public class ServerMain
{
    /**
     * Start the server.
     */
    public static void main(String[] args) throws RemoteException {
        /*
        * 1. CreateDatabase connection; */
        DatabaseConnection();
        /*
        * 2. Connect the server by initialise
        * */
        ServerInitialization();

        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * Connect to the database.
     */
    public static void DatabaseConnection()
    {
        // connect to the database
    }

    /**
     * Initialize the server, starting the RMI registry and the server
     * in order to be used by the client.
     */
    public static void ServerInitialization() throws RemoteException
    {
        new ServerImpl();
    }

    /**
     * Constructor of the class:
     */

}
