package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.Emotions;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    static User user_connected = null;

    //  ------- SYSTEM MODULE FUNCTIONS --------:


    public ClientService () {}

    /**
     * This function allows the client to start the client-application.
     * @return true if the application is started, false otherwise.
     * @throws RemoteException if the connection with the server fails.
     */
    public static boolean StartClientApplication() throws RemoteException
    {
        new Client();
        return CheckConnection();
    }

    /**
     * This function allows the client if the server is online.
     * @return true if the server is online, false otherwise.
     */
    public static boolean CheckConnection()
    {
        try
        {
            return Client.server.SendMessageToClient("CLIENT-SERVICE: Connection with server established");

        } catch (RemoteException | NullPointerException e) {

            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return false;
        }
    }

    //  ------- USER MODULE FUNCTIONS --------:
    public void SetUserConnected(User user_con)
    {
        user_connected = user_con;
    }


    public User GetUserConnected()
    {
        return user_connected;
    }
    /**
     * This function allows the user to register in the application.
     * and go online on the server as user registered in the application database.
     * @param user the new user that wants to register in the application.
     * @return true if the registration is successful, false otherwise.
     */
    public boolean RegisterNewUser(User user){
        try {
            System.out.println("CLIENT-SERVICE request to server sent.");
            if(Client.server.RegisterNewUser(user))
            {
                System.out.println("CLIENT-SERVICE request to server successful.");
                user_connected = user;
                return true;
            }
            else
            {
                System.out.println("CLIENT-SERVICE request to server failed.");
                return false;
            }

        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return false;
        }
    }

    /**
     * This function allows the user to login in the application.
     * and go online on the server as user registered in the application database.
     * @param username the username of the user that wants to log in.
     * @param password the password of the user that wants to log in.
     * @return true if the login is successful, false otherwise.
     * @throws SQLException if the query to the database fails.
     */
    public User Login(String username, String password) throws SQLException {
        User user_conn = null;
        try
        {
            user_conn =  Client.server.Login(username, password);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return null;
        }
        if(user_conn != null)
        {
            System.out.println("CLIENT-SERVICE: user logged in:" + user_conn.GetUsername());
            user_connected = user_conn;
            return user_connected;
        }
        else
        {
            System.out.println("CLIENT-SERVICE Error: user not found.");
            return null;
        }

    }

    /**
     * This function allows the user to logout from the application.
     * and go offline from the server as users.
     */
    public void Logout(){
        try
        {
            user_connected = null;
        } catch (NullPointerException e)
        {
            System.out.println("CLIENT-SERVICE Error: user already logged out.");
        }
    }

    /**
     * This method returns the user that is logged in the application
     * @return the user that is logged in the application
     */
    public void ShowUserProfile()
    {
        try
        {
            System.out.println(user_connected);
        } catch (NullPointerException e)
        {
            System.out.println("CLIENT-SERVICE Error: user not logged in.");
        }
    }

    //  ------- SONG MODULE FUNCTIONS --------:

    /**
     * This method request to the server to search a song by title
     * @param title a string that the user types in the search bar.
     * @return a list of songs where the title compare with the string passed as parameter.
     * @throws SQLException if the query to the database fails.
     */
    public static List<Song> SearchSongByTitle(String title) throws SQLException {
        try
        {
            return Client.server.SearchSongByTitle(title);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }


    /**
     * This method request to the server to search a song by year and title
     * @param year when the song was released
     * @param title the title of the song
     * @return a list of songs that match the search
     * @throws SQLException
     */
    public static List<Song> SearchSongByYearTitle(String year, String title) throws SQLException {
        try
        {
            return Client.server.SearchSongByYearArtist(year, title);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    //  ------- PLAYLIST MODULE FUNCTIONS --------:

    /**
     * This method request to the server to create a new playlist
     * @param ply_name the name of the playlist to create
     * @param plt_id the id of the playlist
     * @return true if the playlist is created, false otherwise
     */
    public static boolean CreateNewPlaylist(String ply_name, String plt_id){
        if(user_connected == null)
        {
            System.out.println("CLIENT-SERVICE Error: user not logged in.");
            return false;
        }
        try
        {
            return Client.server.CreateNewPlaylist(
                    ply_name,
                    user_connected.GetUsername(),
                    plt_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    /**
     * This method request to the server to get all the playlists of the user
     * @return a list of playlists by the username of the user connected.
     */
    public static List<Playlist> GetUserPlaylists(User user_connected){
        if(user_connected == null)
        {
            System.out.println("CLIENT-SERVICE Error: user not logged in.");
            return null;
        }
        try
        {
            try
            {
                return Client.server.GetPlaylist(user_connected.GetUsername());
            }catch (NullPointerException e)
            {
                System.out.println("CLIENT-SERVICE !!Critical - Error !!: user reference is null...");
                return null;
            }
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    /**
     * This method request to the server to get all the songs from a playlist
     * @param playlist_id the id of the playlist
     * @return a list of songs
     */
    public static List<Song> GetPlaylistSongs(String playlist_id){
        try
        {
            return Client.server.GetSongsFromPlaylist(playlist_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    /**
     * This method request to the server to add a song to a playlist
     * @param playlist_id the id of the playlist where the song will be added
     * @param song_id the id of the song to add
     * @return true if the song has been added, false otherwise
     */
    public static boolean AddSongToPlaylist(String playlist_id, String song_id){
        try
        {
            return Client.server.AddSongToPlaylist(playlist_id, song_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    /**
     * This method request to the server to remove a song from a playlist
     * @param playlist_id the id of the playlist where the song is
     * @param song_id the id of the song to remove
     * @return true if the song has been removed, false otherwise
     */
    public static boolean RemoveSongFromPlaylist(String playlist_id, String song_id){
        try
        {
            return Client.server.RemoveSongFromPlaylist(playlist_id, song_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    /**
     * This method request to the server to delete a playlist
     * @param playlist_id the id of the playlist to delete
     * @return true if the playlist has been deleted, false otherwise
     */
    public static boolean DeletePlaylist(String playlist_id){
        try
        {
            return Client.server.DeletePlaylist(playlist_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    //  ------- EMOTION MODULE FUNCTIONS --------:

    /**
     * This method request to the server to register a new emotion
     * @return true if the emotion has been registered, false otherwise
     */
    public static boolean RegisterNewEmotion(Emotions emotions) throws RemoteException
    {
        return Client.server.RegisterNewEmotion(emotions);
    }


    /**
     * This method request to the server to get all the emotions of a song
     * @param songID the id of the song
     * @return a list of emotions
     * this fynction is accessible either by the user and by the anonymous client that is not registered in the application.
     */
    public static List<Emotions> GetEmotionsFromSong(String songID) throws RemoteException
    {
        return Client.server.GetEmotionsFromSong(songID);
    }
}

