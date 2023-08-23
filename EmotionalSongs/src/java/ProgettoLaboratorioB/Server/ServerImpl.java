package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.DatabaseService;
import ProgettoLaboratorioB.Database.QueryExecutor;
import ProgettoLaboratorioB.Serializables.Emotions;
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
     * @param qrExecute is the class used to execute the queries.
     */
    public QueryExecutor qrExecute;

    /**
    * Database Connection parameters:
    * @param url: the url of the database.
    * @param username: the username of the database.
    * @param pw: the password of the database.
    */
    static String url;
    static String username;
    static String pw;

    /**
     * @param filename the name of the file that contains the database configuration.
     *  please check in the folder DatabaseConfig and change it,
     *  to your database configuration file.
     */
    private static String filename = "EnricoDBConfigOSX.properties";

    /**
     * This method load the database configuration from the filename.
     */
    public static void LoadDBConfig()
    {
        File configfile;
        String filepath = SetDBConfigPath(filename);

        if(filepath != null)
        {
            configfile = new File(filepath);
        }
        else
        {
            return;
        }

        try(FileInputStream fileInputStream = new FileInputStream(configfile)){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            pw = properties.getProperty("db.password");

            System.out.println("SERVER-MAIN: Database configuration loaded.");
        }
        catch (Exception e)
        {
            System.out.println("SERVER-MAIN: Error to load the database configuration.");
        }
    }

    /**
     * This function set the path of the database configuration file.
     * @param filename the name of the file that contains the database configuration.
     * @return the path of the database configuration file.
     */
    public static String SetDBConfigPath(String filename)
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
        DatabaseService.CreateEmotionsTable(qrExecute.con,"emotions");
        //qrExecuter.LoadSongData();
        // ATTENZIONE: se si vuole caricare le canzoni nel database, decommentare la riga sovraintesa;
        // Se invece, il database contiente già le canzoni, lasciare commentata la riga sovraintesa.
    }

    /**
     ••• USER SERVICE METHODS IMPLEMENTATION •••
     */


    /**
     * This function is used by the client to register a new user.
     * @param new_user: the new user to register.
     * @throws RemoteException if the client is not connected with the server.
     * @return true if the user is registered, false otherwise.
     */
    @Override
    public synchronized boolean RegisterNewUser(User new_user) throws RemoteException
    {
        return qrExecute.RegisterNewUser(new_user.GetUsername(), new_user.GetPassword(),
                new_user.GetEmail(), new_user.GetName(), new_user.GetSurname(),
                new_user.GetIndirizzo(), new_user.GetCF());
    }

    /**
     * This function is used by the client to login.
     * @param username: the username of the user.
     * @param password: the password of the user.
     * @throws RemoteException if the client is not connected with the server.
     * @return the user if the login is successful, null otherwise.
     */
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

    /**
     * This function is used by the client to search a song by title.
     * @param title the title of the song.
     * @return the list of the songs that contains the title.
     * @throws RemoteException if the client is not connected with the server.
     * @throws SQLException if there is an error in the query.
     */
    @Override
    public synchronized List<Song> SearchSongByTitle(String title) throws RemoteException, SQLException {
        return qrExecute.GetSongByTitle(title);
    }

    /**
     * This function is used by the client to search a song by year and artist.
     * @param year the year of the song.
     * @param artist the artist of the song.
     * @return the list of the songs those match the year and the artist.
     * @throws RemoteException if the client is not connected with the server.
     * @throws SQLException if there is an error in the query.
     */
    @Override
    public synchronized List<Song> SearchSongByYearArtist(String year, String artist) throws RemoteException, SQLException {
        return qrExecute.GetSongYearTitle(year, artist);
    }


    /**
     ••• PLAYLIST SERVICE METHODS IMPLEMENTATION •••
     */

    /**
     * This function is used by the client to create a new playlist.
     * @param playlist_name the name of the playlist.
     * @param username the username of the user that create the playlist.
     * @param playlistID the id of the playlist.
     * @return true if the playlist is created, false otherwise.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized boolean CreateNewPlaylist(String playlist_name, String username, String playlistID) throws RemoteException {
        return qrExecute.CreatePlaylist(playlist_name, username, playlistID);
    }

    /**
     * This function is used by the client to get the playlist of a user.
     * @param username the username of the user.
     * @return the list of the playlist of the user.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized List<Playlist> GetPlaylist(String username) throws RemoteException {
        return qrExecute.GetUserPlaylists(username);
    }

    /**
     * This function is used by the client to get all songs of a playlist.
     * @param playlistID the id of the playlist.
     * @return the list of the songs of the playlist.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized List<Song> GetSongsFromPlaylist(String playlistID) throws RemoteException {
        return qrExecute.GetSongsFromPlaylist(playlistID);
    }

    /**
     * This function is used by the client to add a song to a playlist.
     * @param playlistID the id of the playlist.
     * @param songID the id of the song to add.
     * @return true if the song is added to the playlist, false otherwise.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized boolean AddSongToPlaylist(String playlistID, String songID) throws RemoteException {
        return qrExecute.AddSongToPlaylist(playlistID, songID);
    }

    /**
     * This function is used by the client to remove a song from a playlist.
     * @param playlistID the id of the playlist.
     * @param songID the id of the song to remove.
     * @return true if the song is removed from the playlist, false otherwise.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized boolean RemoveSongFromPlaylist(String playlistID, String songID) throws RemoteException {
        return qrExecute.RemoveSongFromPlaylist(playlistID, songID);
    }

    /**
     * This function is used by the client to delete a playlist.
     * @param playlistID the id of the playlist to delete.
     * @return true if the playlist is deleted, false otherwise.
     * @throws RemoteException if the client is not connected with the server.
     */
    @Override
    public synchronized boolean DeletePlaylist(String playlistID) throws RemoteException {
        return qrExecute.DeletePlaylist(playlistID);
    }

    /**
     * This function is used by the user client to register an emotion about a song.
     * @param emotion the new emotion to register.
     */
    @Override
    public boolean RegisterNewEmotion(Emotions emotion) throws RemoteException {
        return qrExecute.RegisterNewEmotion(emotion);
    }

    @Override
    public List<Emotions> GetEmotionsFromSong(String songID) throws RemoteException {
        return qrExecute.GetEmotionBySongID(songID);
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

