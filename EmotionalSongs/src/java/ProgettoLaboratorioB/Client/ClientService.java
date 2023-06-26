package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    static User user_connected = null;
    public static boolean StartClientApplication() throws RemoteException
    {
        new Client();
        return CheckConnection();
    }

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
    public static boolean RegisterNewUser(User user){
        try {
            System.out.println("CLIENT-SERVICE request to server sent.");
            return Client.server.RegisterNewUser(user);

        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return false;
        }
    }

    public static boolean Login(String username, String password) throws SQLException {
        try
        {
            user_connected =  Client.server.Login(username, password);
            if(user_connected != null) return true;
            else return false;
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return false;
        }
    }

    public static void Logout(){
        try
        {
            user_connected = null;
        } catch (NullPointerException e)
        {
            System.out.println("CLIENT-SERVICE Error: user already logged out.");
        }
    }

    public static void ShowUserProfile()
    {
        try
        {
            System.out.println(user_connected);
        } catch (NullPointerException e)
        {
            System.out.println("CLIENT-SERVICE Error: user not logged in.");
        }
    }
    public static void Anonymous() {}


    public static void Exit()
    {
        System.exit(0);
    }


    ///SONG MODULE FUNCTIONS:

    public static List<Song> SearchSongByTitle(String title) throws SQLException {
        try
        {
            return Client.server.SearchSongByTitle(title);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    public static List<Song> SearchSongByYearTitle(String year, String title) throws SQLException {
        try
        {
            return Client.server.SearchSongByYearArtist(year, title);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

}

