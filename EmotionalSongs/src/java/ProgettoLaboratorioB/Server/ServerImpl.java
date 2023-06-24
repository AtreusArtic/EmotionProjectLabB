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
         * @param url: the url of the database.
         * @param username: the username of the database.
         * @param pw: the password of the database.
         */
    static String url = "jdbc:postgresql://localhost:5432/Emotionals_songs_lab_b";
    static String username = "postgres";
    static String pw = "enrico98";
    public QueryExecutor qrExecute;

    /**
     * Constructor of the remote object, call every time a client connect to the server.
     * @throws RemoteException if the client is not connected with the server.
     * @throws SQLException if the database is not connected with the server.
     * @throws FileNotFoundException if the song file is not found, when is necessary to load the song file.
     */
    public ServerImpl() throws RemoteException, SQLException, FileNotFoundException {
        super();
        qrExecute = QueryExecutor.GetQueryObject(url, username, pw);
        DatabaseService.CreateUserTable(qrExecute.con,"users");
        DatabaseService.CreateSongsTable(qrExecute.con,"songs");
        //qrExecuter.LoadSongData();
        // ATTENZIONE: se si vuole caricare le canzoni nel database, decommentare la riga soprastante;
        // Se invece, il database contiente già le canzoni, lasciare commentata la riga soprastante.
    }


    /**
     ••• USER SERVICE METHODS IMPLEMENTATION •••
     */
    /**
     * This method is used by the client to register a new user.
     * @param new_user: the new user to register.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized void RegisterNewUser(User new_user) throws RemoteException
    {
        qrExecute.RegisterNewUser(new_user.GetUsername(), new_user.GetPassword(), new_user.GetEmail());
    }

    @Override
    public synchronized boolean Login(String username, String password) throws RemoteException
    {
        return qrExecute.UserLogin(username, password);
    }

    public synchronized void Anonymous(User user) throws RemoteException{
        //vedere querymodule

    }

    /**
       ••• SONG SERVICE METHODS IMPLEMENTATION •••
     */
    @Override
    public synchronized Song SearchSongByTitleArtist(String title, String artist) throws RemoteException, SQLException {
        return qrExecute.GetSongByTitleAuthor(title, artist);
    }

    @Override
    public synchronized Song SearchSongByTitleYear(int year, String title) throws RemoteException, SQLException {
        return qrExecute.GetSongYearTitle(year, title);
    }



    /**
     * This TESTING method is used by the client to notify it, that the connection is fine.
     * @param message: the message sent by the client.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized boolean SendMessageToClient(String message) throws RemoteException
    {
        System.out.println("Server: Hello " + message + ", now you are connected with me.");
        return true;
    }

}

