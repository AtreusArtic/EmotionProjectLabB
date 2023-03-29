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

    static ServerInterface server = null;
    static Registry registry = null;
    static final String server_address = "192.168.1.109";
    static final int SERVER_PORT = 1099;
    public static void GetConnection() throws RemoteException
    {
        //Get connection with the server:
        try
        {
            registry = LocateRegistry.getRegistry(server_address,SERVER_PORT);
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
        } catch (NotBoundException | ConnectException e)
        {
            if(e instanceof NotBoundException)
            {
                System.out.println("Client Error: server not bound");
            }
            else if(e instanceof ConnectException)
            {
                System.out.println("Client Error: server offline");
            }
        }

    }

    /**
     * Constructor of the class:
     */
    public ClientImpl() throws RemoteException
    {
        GetConnection();
    }
}
