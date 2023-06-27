package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Server interface define the methods that the server must implement,
 * in order to be used by the client through the RMI architecture.
 */
public interface ServerInterface extends Remote
{

    /*
    *  Client actions:
    *  1. Create a method that allow the client to register a new user;
    *  2. Create a method that allow the client to login;
    */
    public boolean RegisterNewUser(User user) throws RemoteException;
    public User Login(String username, String password) throws RemoteException, SQLException;

    /*
    *  Song actions:
    *  1. Create a method that allow the client to logout;
    *  2. Create a method that allow the client to search a song by title or by artist and year;
    */
    public List<Song> SearchSongByTitle(String title) throws RemoteException, SQLException;
    public List<Song> SearchSongByYearArtist(String year, String artist) throws RemoteException, SQLException;

    /*
    *
    *  Playlist actions:
    *  1. Create a method that allow the client to create a new playlist;
    *  2. Create a method that allow the client to get all playlist of a user;
    *  3. Create a method that allow the client to get all songs of a playlist;
    *  4. Create a method that allow the client to add a song to a playlist;
    *  5. Create a method that allow the client to remove a song from a playlist;
    *  6. Create a method that allow the client to delete a playlist;
    */
    public boolean CreateNewPlaylist(String playlist_name, String username, String playlistID) throws RemoteException;
    public List<Playlist> GetPlaylist(String username, String playlistID) throws RemoteException;
    public List<Song> GetSongsFromPlaylist(String playlistID) throws RemoteException;
    public boolean AddSongToPlaylist(String playlistID, String songID) throws RemoteException;
    public boolean RemoveSongFromPlaylist(String playlistID, String songID) throws RemoteException;
    public boolean DeletePlaylist(String playlistID) throws RemoteException;


    /*
    *  Emotion actions:
    *  1. Create a method that allow the client to read an Emotion about a song;
    *  2. Create a method that allow the client to register a new Emotion about specific song;
    *  3. Create a method that allow the client to remove an Emotion about a song;
    */


    /*
    *  Service actions:
    * TODO: server take into account of how many clients are connected.
     */
    public boolean SendMessageToClient(String message) throws RemoteException;

}



