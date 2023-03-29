package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Server.ServerInterface;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    /**
     * Client class is the class that the client use to connect to the server.
     * The client can use the methods defined in the interface using the remote object of the server.
     * The remote object is created by the RMI registry.
     * @param ServerInterface server is the remote object of the server.
     */
    static ServerInterface server = null;
    static Registry registry = null;
    public static void GetConnection() throws RemoteException
    {
        //Get connection with the server:
        try
        {
            registry = LocateRegistry.getRegistry(1099);
        }catch (RemoteException e)
        {
            System.out.println("Client Error: client not connected with the server: " + e.getMessage());
        }
        //Get the remote object by the server:
        try
        {
            server = (ServerInterface) registry.lookup("Server");
            System.out.println("Client: connected with the server");
        } catch (NotBoundException | ConnectException e)
        {
            if(e instanceof NotBoundException)
            {
                System.out.println("Client Error: server not bound: ");
            }
            else if(e instanceof ConnectException)
            {
                System.out.println("Client Error: server not connected: ");
            }
        }

    }

    /**
     * Constructor of the class:
     */
    public Client() throws RemoteException
    {
        GetConnection();
    }
}
