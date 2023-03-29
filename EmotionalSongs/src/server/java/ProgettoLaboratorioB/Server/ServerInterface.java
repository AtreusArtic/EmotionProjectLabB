package ProgettoLaboratorioB.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
    *  2. Create a method that allow the client to login;
    *  3. Create a method that allow the client to logout;
    *  4. Create a method that allow the client to search a song by title and by artist and year;
    *  5. Create a method that allow the client to read an Emotion about a song;
    *  User actions:
    *  1. Create a method that allow the client to create a new playlist;
    *  2. Create a method that allow the client to add a song to a playlist;
    *  3. Create a method that allow the client to remove a song from a playlist;
    *  4. Create a method that allow the client to delete a playlist;
    *  5. Create a method that allow the client to register a new Emotion about specific song;
    *  6. Create a method that allow the client to remove an Emotion about a song;
    */

    public void SendMessageToServer(String message) throws RemoteException;
}



