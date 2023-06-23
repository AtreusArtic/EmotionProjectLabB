package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.DatabaseService;
import ProgettoLaboratorioB.Database.QueryExecutor;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.io.FileNotFoundException;
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
    public QueryExecutor qrExecuter;

    /**
     * Constructor of the class: so the server is online.
     */
    public ServerImpl() throws RemoteException, SQLException, FileNotFoundException {
        super();
        qrExecuter = QueryExecutor.GetQueryObject(url, username, pw);
        DatabaseService.CreateUserTable(qrExecuter.con,"users");
        DatabaseService.CreateSongsTable(qrExecuter.con,"songs");
        //qrExecuter.LoadSongData();
        // ATTENZIONE: se si vuole caricare le canzoni nel database, decommentare la riga soprastante;
        // Se invece, il database contiente già le canzoni, lasciare commentata la riga soprastante.
    }

    @Override
    public synchronized void RegisterNewUser(User new_user) throws RemoteException
    {
        qrExecuter.RegisterNewUser(new_user.GetUsername(), new_user.GetPassword(), new_user.GetEmail());
    }

    @Override
    public synchronized boolean Login(String username, String password) throws RemoteException
    {
        return qrExecuter.UserLogin(username, password);
    }

    @Override
    public Song SearchSongByTitleArtist(String title, String artist) throws RemoteException, SQLException {
        return qrExecuter.GetSongByTitleAuthor(title, artist);
    }

    public synchronized void Anonymous(User user) throws RemoteException{
        //vedere querymodule

    }


    /**
     * This TESTING method is used by the client to notify it, that the connection is fine.
     * @param message: the message sent by the client.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized void SendMessageToClient(String message) throws RemoteException
    {
        System.out.println("Server: Hello " + message + ", now you are connected with me.");
    }

}

