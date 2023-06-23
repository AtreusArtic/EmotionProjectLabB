package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.DatabaseService;
import ProgettoLaboratorioB.Database.QueryExecutor;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * With this class server can implement the methods defined in the interface.
 * So the client can use the methods defined in the interface using the remote object of this class.
 * The remote object is created by the RMI registry.
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    /**
     * @param serialVersionUID: the serial version UID of serializable class.
     * it is used to verify that the sender and receiver of a serialized object have loaded classes
     * for that object that are compatible with respect to serialization.
     */
    private static final long serialVersionUID = 1L;


    /**
     * @param connection: the connection to the database.
     */
    static String url = "jdbc:postgresql://localhost:5432/Emotionals_songs_lab_b";
    static String username = "postgres";
    static String pw = "enrico98";
    public QueryExecutor qrModule;

    /**
     * Constructor of the class: so the server is online.
     */
    public ServerImpl() throws RemoteException, SQLException {
        super();
        qrModule = QueryExecutor.GetQueryObject(url, username, pw);
        DatabaseService.CreateUserTable(qrModule.con,"users");
    }

    @Override
    public void RegisterNewUser(User new_user) throws RemoteException
    {
        qrModule.RegisterNewUser(new_user.GetUsername(), new_user.GetPassword(), new_user.GetEmail());
    }

    @Override
    public boolean Login(String username, String password) throws RemoteException
    {
        return qrModule.UserLogin(username, password);
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

