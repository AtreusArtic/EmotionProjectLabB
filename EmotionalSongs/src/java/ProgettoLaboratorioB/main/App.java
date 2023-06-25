package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class App
{
    /**
     * @param sc: scanner object used to read user input from the console.
     */
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) throws RemoteException, SQLException
    {
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
     */

    public static void RunApplication() throws RemoteException, SQLException {
        if(!ClientService.StartClientApplication())
        {
            App_System.appSystem.SetNewState(SYSTEM_STATE.EXIT);
        }
        else
        {
            App_System.appSystem.SetNewState(SYSTEM_STATE.MAIN_MENU);
            StartMainModule();
        }
    }

    public static void StartMainModule() throws RemoteException, SQLException {
        int switchState;

        while(App_System.appSystem.GetCrntState().equals(SYSTEM_STATE.MAIN_MENU))
        {
            System.out.println("Choose the function to call:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Enter as guest");
            System.out.println("4. Search song by title");
            System.out.println("5. Search song by year and title");
            System.out.println("6. Exit");
            switchState = Integer.parseInt(sc.nextLine());
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
                    if(UserLoginUtility())
                    {
                        //Start the user home menu module.
                        App_System.appSystem.SetNewState(SYSTEM_STATE.USER_MENU);
                        UserModuleMenu();
                    }
                    break;
                case 3:
                    App_System.appSystem.SetNewState(SYSTEM_STATE.GUEST_MENU);
                    break;
                case 4:
                    SongSearchUtility(true);
                    break;
                case 5:
                    SongSearchUtility(false);
                case 6:
                    App_System.appSystem.SetNewState(SYSTEM_STATE.EXIT);
                    break;
                default:
                    System.out.println("Invalid function");
                    break;
            }
        }
    }

    public static void UserModuleMenu()
    {
        int switchcase;

        while (App_System.appSystem.GetCrntState().equals(SYSTEM_STATE.USER_MENU))
        {
            System.out.println("Choose the function to call:");
            System.out.println("1. To show your credentials");
            System.out.println("5. Logout");

            switchcase = Integer.parseInt(sc.nextLine());
            switch(switchcase)
            {
                case 1:
                    ClientService.ShowUserProfile();
                    break;
                case 5:
                    ClientService.Logout();
                    App_System.appSystem.SetNewState(SYSTEM_STATE.MAIN_MENU);
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
        System.out.println("Insert the username:");
        String username = sc.next();

        System.out.println("Insert the password:");
        String password = sc.next();

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
    public static void SongSearchUtility(boolean isByTitle) throws SQLException {
        List<Song> song;
        if(isByTitle)
        {
            System.out.println("Insert the song title:");
            String song_title = sc.nextLine();

            if(song_title == null)
            {
                System.out.println("Error: song title is null");
                return;
            }
            song = ClientService.SearchSongByTitle(song_title);
        }
        else
        {
            System.out.println("Insert the song year:");
            String song_year = sc.nextLine();

            System.out.println("Insert the song title:");
            String song_title = sc.nextLine();

            song = ClientService.SearchSongByYearTitle(song_year, song_title);
        }

        if(song != null)
        {
           System.out.println("Song found! \n");
           for(Song s : song)
           {
               System.out.println(s);
               System.out.println("\n");
           }
        }
        else
        {
            System.out.println("Song not found");
        }
    }
}


