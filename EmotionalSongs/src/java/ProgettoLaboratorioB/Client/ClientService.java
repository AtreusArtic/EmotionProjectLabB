package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ClientService {

    public static boolean StartClientApplication() throws RemoteException
    {
        new Client();
        return CheckConnection();
    }

    public static boolean CheckConnection() throws RemoteException
    {
        try
        {
            return Client.server.SendMessageToClient("CLIENT-SERVICE: Connection with server established");
        } catch (RemoteException | NullPointerException e) {

            return false;
        }
    }
    public static void RegisterNewUser(User user) throws SQLException{
        try {
            Client.server.RegisterNewUser(user);
            System.out.println("CLIENT-SERVICE request to server sent.");
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
        }
    }

    public static boolean Login(String username, String password) throws SQLException {
        try
        {
            return Client.server.Login(username, password);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline.");
            return false;
        }
    }
    public static void Anonymous() {}


    public static void Exit()
    {
        System.exit(0);
    }


    ///SONG MODULE FUNCTIONS:

    public static Song SearchSongByTitleArtist(String title, String artist) throws SQLException {
        try
        {
            return Client.server.SearchSongByTitleArtist(title, artist);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    public static Song SearchSongByYearTitle(int year, String title) throws SQLException {
        try
        {
            return Client.server.SearchSongByTitleYear(year, title);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }


}

