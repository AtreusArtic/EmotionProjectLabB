package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ClientService {

    static User user_connected = null;

    //  ------- SYSTEM MODULE FUNCTIONS --------:
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

    //  ------- USER MODULE FUNCTIONS --------:
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

    //  ------- SONG MODULE FUNCTIONS --------:
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

    //  ------- PLAYLIST MODULE FUNCTIONS --------:
    public static boolean CreateNewPlaylist(String ply_name, String plt_id){
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

    public static List<Playlist> GetUserPlaylists(){
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

    public static List<Song> GetPlaylistSongs(String playlist_id){
        try
        {
            return Client.server.GetSongsFromPlaylist(playlist_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return null;
        }
    }

    public static boolean AddSongToPlaylist(String playlist_id, String song_id){
        try
        {
            return Client.server.AddSongToPlaylist(playlist_id, song_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    public static boolean RemoveSongFromPlaylist(String playlist_id, String song_id){
        try
        {
            return Client.server.RemoveSongFromPlaylist(playlist_id, song_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

    public static boolean DeletePlaylist(String playlist_id){
        try
        {
            return Client.server.DeletePlaylist(playlist_id);
        } catch (RemoteException e) {
            System.out.println("CLIENT-SERVICE Error: Server is offline");
            return false;
        }
    }

}

