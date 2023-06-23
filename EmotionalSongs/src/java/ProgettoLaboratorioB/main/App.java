package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;


public class App
{
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) throws RemoteException, SQLException {

        RunApplication();
    }

    /**
     * MODULES:
     * 1. Init User Module
     * 2. Main Module
     * 3. User Home Module
     * 4. Song Module
     * 5. Playlist Module
     * 6. Emotion Module
     *
     */

    public static void RunApplication() throws RemoteException, SQLException {
        ClientService.StartClientApplication();
        App_System.appSystem.SetNewState(SYSTEM_STATE.MAIN_MENU);
        StartMainModule();

    }

    public static void StartMainModule() throws RemoteException, SQLException {
        int switchState = 0;

        while(App_System.appSystem.GetCrntState().equals(SYSTEM_STATE.MAIN_MENU))
        {
            System.out.println("Choose the function to call:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Enter as guest");
            System.out.println("4. Search song by title and artist");
            System.out.println("5. Exit");
            switchState = sc.nextInt(); //Remember to cast the input to int, cause an error if you don't;
            switch(switchState)
            {
                case 1:
                    App_System.appSystem.SetNewState(SYSTEM_STATE.REGISTER_MENU);
                    User new_user = UserRegistrationUtility();
                    if(new_user != null)
                    {
                        System.out.println("Send registration request to server");
                        ClientService.RegisterNewUser(new_user);
                    }
                    else
                    {
                        System.out.println("Error: user reference is null");
                        App_System.SetNewState(SYSTEM_STATE.MAIN_MENU);
                    }

                    break;
                case 2:
                    // Call the login module to login
                    if(UserLoginUtility())
                    {
                        //Start the user home module.
                        App_System.appSystem.SetNewState(SYSTEM_STATE.USER_MENU);
                    }
                    break;
                case 3:
                    App_System.appSystem.SetNewState(SYSTEM_STATE.GUEST_MENU);
                    break;
                case 4:
                    SongSearchUtility();
                    break;
                case 5:
                    App_System.appSystem.SetNewState(SYSTEM_STATE.EXIT);
                    break;
                default:
                    System.out.println("Invalid function");
                    break;
            }
        }
    }

    /**
     * UTILITIES MODULE FUNCTIONS:
     * 1. User Registration utility
     * 2. User Login utility
     * 3. Song Search utility
     * 4. Playlist utility
     * 4. Emotion utility
     */

    public static User UserRegistrationUtility()
    {
        //TODO: add all the user reference fields
        //TODO: add a while loop to check if the user reference is null or not
        //TODO: set new functions to create other id;
        System.out.println("Insert the username:");
        String username = sc.next();

        System.out.println("Insert the password:");
        String password = sc.next();

        String email = "enrico_email@ciao.com";

        User user = new User(username, password, email);

        return user;
    }

    public static boolean UserLoginUtility() throws SQLException, RemoteException {
        //System.out.println("Insert the username:");
        //String username = sc.next();
        String username = "Davo";
        //System.out.println("Insert the password:");

        //String password = sc.next();
        String password = "favolerosa";
        if(ClientService.Login(username, password))
        {
            System.out.println("Login successful");
            return true;
        }
        else
        {
            System.out.println("Login failed");
            return false;
        }


    }

    public static void SongSearchUtility() throws SQLException {
        System.out.println("Insert the song title:");
        String song_title = "Adam Ant";

        System.out.println("Insert the song artist:");
        String song_artist = "Something Girls";

        //implement the function into the remote object, to search the song;
        Song song = ClientService.SearchSongByTitleArtist(song_title, song_artist);
        if(song != null)
        {
            System.out.println("Song found");
            System.out.println("Song title: " + song.GetTitle());
            System.out.println("Song artist: " + song.GetArtist());
            System.out.println("Song year: " + song.GetYear());
        }
        else
        {
            System.out.println("Song not found");
        }

    }


}


