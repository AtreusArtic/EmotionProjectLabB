package ProgettoLaboratorioB.Server;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * ServerMain class is the class used to start the server.
 * The server can use the methods defined in the interface using the remote object of the server.
 * The remote object is created by the RMI registry.
 * Also, the server can connect to the database, using the database class.
 * The database class is used by the server to interact with the database.
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
     * Initialize the server and create the remote object.
     */
    public static void ServerInitialization() throws RemoteException
    {
        new ServerImpl();
    }

    /**
     * Connect to the database.
     */
    public static void DatabaseConnection()
    {
        // connect to the database
    }

}
