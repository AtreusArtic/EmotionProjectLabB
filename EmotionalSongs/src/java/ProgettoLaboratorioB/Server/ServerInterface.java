package ProgettoLaboratorioB.Server;

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
    // TODO: define the methods that the server must implement in order to be used by the client
    /*
    * TODO list:
    *  Client INFO:
    *  -Impo: send message to server to check if the client is connected;
    *  Client actions:
    *  1. Create a method that allow the client to register a new user;
    */
    public boolean RegisterNewUser(User user) throws RemoteException;

    /*
    *  2. Create a method that allow the client to login;
    */
    public User Login(String username, String password) throws RemoteException, SQLException;
   /*
    *  3. Create a method that allow the client to logout;
    *  4. Create a method that allow the client to search a song by title and by artist and year;
    */

    public List<Song> SearchSongByTitle(String title) throws RemoteException, SQLException;

    public List<Song> SearchSongByYearArtist(String year, String artist) throws RemoteException, SQLException;
    /*
    *  5. Create a method that allow the client to read an Emotion about a song;
    *  User actions:
    *  1. Create a method that allow the client to create a new playlist;
    *  2. Create a method that allow the client to add a song to a playlist;
    *  3. Create a method that allow the client to remove a song from a playlist;
    *  4. Create a method that allow the client to delete a playlist;
    *  5. Create a method that allow the client to register a new Emotion about specific song;
    *  6. Create a method that allow the client to remove an Emotion about a song;
    */

    public boolean SendMessageToClient(String message) throws RemoteException;

}



