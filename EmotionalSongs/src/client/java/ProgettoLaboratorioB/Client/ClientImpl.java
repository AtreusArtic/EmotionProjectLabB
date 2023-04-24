package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Server.ServerInterface;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Client class is the class that the client use to connect to the server.
 * The client can use the methods defined in the interface using the remote object of the server.
 * The remote object is created by the RMI registry.
 */
public class ClientImpl {

    /**
     * @param server: the remote object of the server,
     * used by the client to use the methods defined in the interface.
     */
    static ServerInterface server = null;

    /**
     * @param registry: the RMI registry, used by the client to get the remote object of the server.
     */
    static Registry registry = null;

    /**
     * @param server_address: the address of the remote server.
     * NB: if the server is in the same machine of the client,
     * set the server address to : "localhost" or null reference.
     */
    static final String server_address_home = "192.168.1.109";

    static final String server_address_home2 = "192.168.1.19";
    static final String server_address_mobile = "192.168.60.42";

    /**
     * @param SERVER_PORT: the port of the remote server.
     */
    static final int SERVER_PORT = 1099;

    /**
     * This method initialize the connection with the server, using the RMI registry.
     * @throws RemoteException if the client is not connected with the server.
     * @throws NotBoundException if the server is not bound.
     * @throws ConnectException if the server is offline.
     */
    public static void GetConnection() throws RemoteException
    {
        //Get connection with the server.
        try
        {
            registry = LocateRegistry.getRegistry(server_address_mobile ,SERVER_PORT);
        }catch (RemoteException e)
        {
            System.out.println("Client Error: client not connected with the server: " + e.getMessage());
        }
        //Get the remote object by the server:
        try
        {
            server = (ServerInterface) registry.lookup("Server");
            server.SendMessageToServer("client_zero");
            System.out.println("Client: connected with the server");
        } catch (Exception e)
        {
            if(e instanceof NotBoundException)
            {
                System.out.println("Client Error: server not bound");
            }
            else if(e instanceof ConnectException)
            {
                System.out.println("Client Error: server offline");
            }
            else
            {
                System.out.println("Client Critical Error: " + e.getMessage());
            }
        }

    }

    /**
     * Constructor of the class: when a new client is created,
     * the client try to connect with the server.
     */
    public ClientImpl() throws RemoteException
    {
        GetConnection();
    }
}
