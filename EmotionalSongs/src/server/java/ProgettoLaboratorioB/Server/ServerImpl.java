package ProgettoLaboratorioB.Server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * With this class server can implement the methods defined in the interface.
 * So the client can use the methods defined in the interface using the remote object of this class.
 * The remote object is created by the RMI registry.
 */
public class ServerImpl implements ServerInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int SERVER_PORT = 1099;

    /**
     * Constructor of the class:
     */
    public ServerImpl() throws RemoteException
    {
        super();
        InitiServerConnection(this);
    }

    /**
     * Start the server, through the RMI registry.
     */
    public static void InitiServerConnection(ServerImpl server)
    {
        try {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.rebind("Server", server);
            System.out.println("@Server is ready...");
        } catch (RemoteException e) {
            System.out.println("@Server Error to connect the server: " + e.getMessage());
        }
    }
    //TODO: implement the methods defined in the interface:

}

