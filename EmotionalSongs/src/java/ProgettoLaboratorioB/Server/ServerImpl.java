package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.DatabaseService;
import ProgettoLaboratorioB.Database.QueryExecutor;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * With this class server can implement the methods defined in the interface.
 * So the client can use the methods defined in the interface using the remote object of this class.
 * The remote object is created by the RMI registry.
 */
public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

  /**
     * @param url: the url of the database.
     * @param username: the username of the database.
     * @param pw: the password of the database.
     */
    static String url;
    static String username;
    static String pw;
    public QueryExecutor qrExecute;

    private static String filename = "EnricoDBConfigOSX.properties";
    public static void LoadDBConfig()
    {
        File configfile;
        String filepath = SetDBconfigPath(filename);
        if(filepath != null)
        {
            configfile = new File(filepath);
        }
        else
        {
            System.out.println("SERVER-MAIN: Config file is null");
            return;
        }
        try(FileInputStream fileInputStream = new FileInputStream(configfile)){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            pw = properties.getProperty("db.password");
        }
        catch (Exception e)
        {
            System.out.println("SERVER-MAIN: Error to load the database configuration.");
        }
    }

    public static String SetDBconfigPath(String filename)
    {
        if(System.getProperty("os.name").equals("Mac OS X"))
        {
            System.out.println("SERVER-MAIN: Mac OS X detected.");
            filename = "DatabaseConfig/" + filename;
        }
        else if(System.getProperty("os.name").equals("Windows 10"))
        {
            System.out.println("SERVER-MAIN:Windows 10 detected.");
            filename = "EmotionalSongs/DatabaseConfig/" + filename;
        }
        else
        {
            System.out.println("SERVER-MAIN: OS not supported.");
            return null;
        }

        String dir = System.getProperty("user.dir");
        String path = dir + File.separator + filename;
        return path;
    }

    /**
     * Constructor of the remote object, call every time a client connect to the server.
     * @throws RemoteException if the client is not connected with the server.
     * @throws FileNotFoundException if the song file is not found, when is necessary to load the song file.
     */
    public ServerImpl() throws RemoteException, FileNotFoundException {
        super();
        LoadDBConfig();
        qrExecute = QueryExecutor.GetQueryObject(url, username, pw);
        DatabaseService.CreateUserTable(qrExecute.con,"users");
        DatabaseService.CreateSongsTable(qrExecute.con,"songs");
        DatabaseService.CreatePlaylistTable(qrExecute.con,"playlists");
        DatabaseService.CreatePlaylistSavedTable(qrExecute.con,"playlist_saved");
        //qrExecuter.LoadSongData();
        // ATTENZIONE: se si vuole caricare le canzoni nel database, decommentare la riga sovraintesa;
        // Se invece, il database contiente già le canzoni, lasciare commentata la riga sovraintesa.
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
    public synchronized boolean RegisterNewUser(User new_user) throws RemoteException
    {
        return qrExecute.RegisterNewUser(new_user.GetUsername(), new_user.GetPassword(),
                new_user.GetEmail(), new_user.GetName(), new_user.GetSurname(),
                new_user.GetIndirizzo(), new_user.GetCF());
    }

    @Override
    public synchronized User Login(String username, String password) throws RemoteException
    {
        return qrExecute.UserLogin(username, password);
    }

    public synchronized void Anonymous() throws RemoteException{

    }

    /**
       ••• SONG SERVICE METHODS IMPLEMENTATION •••
     */
    @Override
    public synchronized List<Song> SearchSongByTitle(String title) throws RemoteException, SQLException {
        return qrExecute.GetSongByTitle(title);
    }

    @Override
    public synchronized List<Song> SearchSongByYearArtist(String year, String artist) throws RemoteException, SQLException {
        return qrExecute.GetSongYearTitle(year, artist);
    }


    /**
     ••• PLAYLIST SERVICE METHODS IMPLEMENTATION •••
     */
    @Override
    public synchronized boolean CreateNewPlaylist(String playlist_name, String username, String playlistID) throws RemoteException {
        return qrExecute.CreatePlaylist(playlist_name, username, playlistID);
    }

    @Override
    public synchronized List<Playlist> GetPlaylist(String username, String playlistID) throws RemoteException {
        return qrExecute.GetUserPlaylists(username, playlistID);
    }

    @Override
    public synchronized List<Song> GetSongsFromPlaylist(String playlistID) throws RemoteException {
        return qrExecute.GetSongsFromPlaylist(playlistID);
    }

    @Override
    public synchronized boolean AddSongToPlaylist(String playlistID, String songID) throws RemoteException {
        return qrExecute.AddSongToPlaylist(playlistID, songID);
    }

    @Override
    public synchronized boolean RemoveSongFromPlaylist(String playlistID, String songID) throws RemoteException {
        return qrExecute.RemoveSongFromPlaylist(playlistID, songID);
    }

    @Override
    public synchronized boolean DeletePlaylist(String playlistID) throws RemoteException {
        return qrExecute.DeletePlaylist(playlistID);
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

