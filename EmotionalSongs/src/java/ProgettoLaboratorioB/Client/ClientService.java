package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ClientService {


    public static void StartClientApplication() throws RemoteException
    {
        new Client();
    }
    public static void RegisterNewUser(User user) throws SQLException{
        try {
            Client.server.RegisterNewUser(user);
            System.out.println("request to server send");
        } catch (RemoteException e) {
            System.out.println("Error: SERVER IS OFFLINE");
        }
    }

    public static boolean Login(String username, String password) throws SQLException {
        try
        {
            return Client.server.Login(username, password);
        } catch (RemoteException e) {
            System.out.println("Error: SERVER IS OFFLINE");
            return false;
        }
    }
    public static void Anonymous(User user)
    {


    }


    public static void Exit(){
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


}

