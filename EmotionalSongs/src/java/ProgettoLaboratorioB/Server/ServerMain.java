package ProgettoLaboratorioB.Server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
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

    /**
     * @param SERVER_PORT: the port of the remote server.
     */
    private static final int SERVER_PORT = 1099;
    public static void main(String[] args) throws RemoteException {
        /**
        * 1. CreateDatabase connection;
         * */
        try
        {
            InitServerConnection();
        }catch (Exception e) {
            System.out.println("Server initialization error: " + e);
        }


        /**
         * 3. When the server is initialized, and the database is connected, update app system.
         */

        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * Initialize the server and create the remote object, and pass the database connection to the remote object.
     */

    public static void InitServerConnection() throws SQLException {

        try
        {
            ServerImpl server = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.rebind("Server", server);
            System.out.println("@SERVER IS ONLINE...");
        } catch (RemoteException e)
        {
            System.out.println("@SERVER ERROR to connect the server: " + e.getMessage());
        } catch (FileNotFoundException e)
        {
            System.out.println("@SERVER ERROR: failed to load songs file into database");
        }
    }


}
