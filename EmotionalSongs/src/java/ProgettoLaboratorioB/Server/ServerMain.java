package ProgettoLaboratorioB.Server;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
     * @param SERVER_PORT: the port of the remote server.
     */
    private static final int SERVER_PORT = 1099;
    public static void main(String[] args) {

        try
        {
            InitServerConnection();
        }catch (Exception e) {
            System.out.println("SERVER-MAIN: initialization error: " + e);
        }

        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * This method initialize the connection with the server, using the RMI registry.
     * and wait new client access.
     */
    public static void InitServerConnection(){
        try
        {
            ServerImpl server = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.rebind("Server", server);
            System.out.println("SERVER-MAIN: IM ONLINE");
        } catch (RemoteException e)
        {
            System.out.println("SERVER-MAIN: Error to connect the server: " + e);
        } catch (FileNotFoundException e)
        {
            System.out.println("SERVER-MAIN Error: failed to load songs file into database");
        }
    }

}
