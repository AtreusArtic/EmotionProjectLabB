package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.QueryModule;
import ProgettoLaboratorioB.Serializables.User;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

/**
 * With this class server can implement the methods defined in the interface.
 * So the client can use the methods defined in the interface using the remote object of this class.
 * The remote object is created by the RMI registry.
 */
public class ServerImpl implements ServerInterface, Serializable {

    /**
     * @param serialVersionUID: the serial version UID of serializable class.
     * it is used to verify that the sender and receiver of a serialized object have loaded classes
     * for that object that are compatible with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param SERVER_PORT: the port of the remote server.
     */
    private static final int SERVER_PORT = 1099;

    /**
     * Constructor of the class: so the server is online.
     */
    public ServerImpl() throws RemoteException
    {
        super();
        InitServerConnection(this);
    }

    /**
     * This method initialize the connection with the server, using the RMI registry.
     * @param server: the remote object of the server,
     * @throws RemoteException if the SERVER_PORT is not available.
     */
    public static void InitServerConnection(ServerImpl server)
    {
        try {
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.rebind("Server", server);
            System.out.println("@Server is online...");
        } catch (RemoteException e) {
            System.out.println("@Server Error to connect the server: " + e.getMessage());
        }
    }

    @Override
    public void RegisterNewUser(User user) throws RemoteException
    {
        QueryModule.RegisterNewUser(user.GetUsername(), user.GetPassword(), user.GetEmail());
    }

    @Override
    public boolean Login(String username, String password) throws RemoteException, SQLException {
        return QueryModule.UtenteLoggato(username, password);
    }
    public void Anonymous(User user) throws RemoteException{
        //vedere querymodule

    }


    /**
     * This TESTING method is used by the client to notify it, that the connection is fine.
     * @param message: the message sent by the client.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public void SendMessageToClient(String message) throws RemoteException
    {
        System.out.println("Server: Hello " + message + ", now you are connected with me.");
    }

}

