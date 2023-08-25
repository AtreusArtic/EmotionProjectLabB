package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Server.ServerInterface;
import ProgettoLaboratorioB.main.App_System;

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
public class Client {

    /**
     * @param server: the remote object of the server,
     * used by the client to use services offered by the server.
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
    public static String server_address;
    public static String default_server_address = "localhost";

    /**
     * @param SERVER_PORT: the port of the remote server.
     */
    static final int SERVER_PORT = 1099;

    /**
     * Constructor of the class: when a new client is created,
     * the client try to connect with the server.
     */
    public Client() throws RemoteException
    {
        GetConnection();
    }

    /**
     * This method initialize the connection with the server, using the RMI registry.
     * @throws RemoteException if the client is not connected with the server.
     * @throws NotBoundException if an attempt is made to lookup or unbind in the registry a name that has no associated binding.
     * @throws ConnectException Server is offline; signals that an error occurred while attempting to connect a socket to a remote address and port.
     */
    public static void GetConnection() throws RemoteException
    {
        //Get the server address from the ServerConfig.properties file:
        server_address = App_System.LoadServerIP();

        if (server_address == null)
        {
            server_address = default_server_address; //Localhost
        }

        System.out.println("CLIENT: server address: " + server_address);
        //Get connection with the server.
        try
        {
            registry = LocateRegistry.getRegistry(default_server_address ,SERVER_PORT);
        }catch (RemoteException e)
        {
            System.out.println("CLIENT Error: client not connected with the server: " + e.getMessage());
        }
        //Get the remote object by the server:
        try
        {
            server = (ServerInterface) registry.lookup("Server");
            System.out.println("CLIENT: connected with the server");
        } catch (Exception e)
        {
            if(e instanceof NotBoundException)
            {
                System.out.println("CLIENT Error: server not bound");
            }
            else if(e instanceof ConnectException)
            {
                System.out.println("CLIENT: server offline");
            }
            else
            {
                System.out.println("CLIENT Critical Error: " + e.getMessage());
            }
        }
    }




}
