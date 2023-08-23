package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.GUI.ManagerGUI;
import ProgettoLaboratorioB.Serializables.Emotions;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;
import ProgettoLaboratorioB.main.Enums.SYSTEM_STATE;

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
        LaunchGUIModules(args);
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

    public static void LaunchGUIModules(String[] args) throws RemoteException, SQLException {
        ManagerGUI.main(args);
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
                        if(ClientService.RegisterNewUser(new_user))
                        {
                            System.out.println("Registration request completed successfully ");
                            App_System.appSystem.SetNewState(SYSTEM_STATE.MAIN_MENU);
                        }
                        else
                        {
                            System.out.println("Error: registration request failed, please use a different username");
                            App_System.appSystem.SetNewState(SYSTEM_STATE.MAIN_MENU);
                        }
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



    public static void UserModuleMenu() throws SQLException, RemoteException {
        int switchcase;

        while (App_System.appSystem.GetCrntState().equals(SYSTEM_STATE.USER_MENU))
        {
            System.out.println("Choose the function to call:");
            System.out.println("1. To show your credentials");
            System.out.println("2. Create new playlist");
            System.out.println("3. Add song to playlist");
            System.out.println("4. Show all your playlist");
            System.out.println("5. Create new emotion");
            System.out.println("6. Read emotion evaluation");
            System.out.println("7. Logout");

            switchcase = Integer.parseInt(sc.nextLine());
            switch(switchcase)
            {
                case 1:
                    ClientService.ShowUserProfile();
                    break;
                case 2:
                    CreatePlaylistModule();
                    break;
                case 3:
                    AddSongToPlaylistModule();
                    break;
                case 4:
                    GetAllUserPlaylist();
                    break;
                case 5:
                    RegisterNewEmotion();
                    break;
                case 6:
                    ReadEmotionEvalutation();
                    break;
                case 7:
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
        //TODO: add a while loop to check if the user reference is null or not
        //TODO: implement a function that check if CF, indiriizzo, email are valid format or not;
        System.out.println("Insert the username:");
        String username = sc.nextLine();

        System.out.println("Insert the password:");
        String password = sc.nextLine();

        System.out.println("Insert the email:");
        String email = sc.nextLine();

        System.out.println("Insert your name:");
        String name = sc.nextLine();

        System.out.println("Insert your surname:");
        String surname = sc.nextLine();

        System.out.println("Insert your address:");
        String indirizzo = sc.nextLine();

        System.out.println("Insert your CF");
        String CF = sc.nextLine();

        User user = new User(username, password, email, name, surname, indirizzo, CF);

        return user;
    }

    public static boolean UserLoginUtility() throws SQLException, RemoteException {
        System.out.println("Insert the username:");
        String username = sc.nextLine();

        System.out.println("Insert the password:");
        String password = sc.nextLine();

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
    public static List<Song> SongSearchUtility(boolean isByTitle) throws SQLException {
        List<Song> songs;
        if(isByTitle)
        {
            System.out.println("Insert the song title:");
            String song_title = sc.nextLine();

            if(song_title == null)
            {
                System.out.println("Error: song title is null");
                return null;
            }
            songs = ClientService.SearchSongByTitle(song_title);
        }
        else
        {
            System.out.println("Insert the song year:");
            String song_year = sc.nextLine();

            System.out.println("Insert the song title:");
            String song_title = sc.nextLine();

            songs = ClientService.SearchSongByYearTitle(song_year, song_title);
        }

        if(songs != null)
        {
           System.out.println("Song found! \n");
           for(Song s : songs)
           {
               System.out.println(s);
               System.out.println("\n");
           }
           return songs;
        }
        else
        {
            System.out.println("Song not found");
            return null;
        }
    }

    public static void CreatePlaylistModule(){
        System.out.println("Insert the playlist name:");
        String playlist_name = sc.nextLine();

        System.out.println("Insert the playlist id:");
        String playlist_id = sc.nextLine();

        if(ClientService.CreateNewPlaylist(playlist_name,playlist_id))
        {
            System.out.println("Playlist created successfully");
        }
        else
        {
            System.out.println("Error: playlist creation failed");
        }
    }

    private static void AddSongToPlaylistModule() throws SQLException {
        List<Song> songs = SongSearchUtility(true);

        //TODO: implement a function that get a song from the list of songs.

        Song desired_song = new Song(1978, "TRAAAQO12903CD8E1C", "Chaka Khan_ Rufus", "King Of Scurf (2007 Digital Remaster)");

        Song found = App_System.binarySearch(desired_song, songs);

        if (found != null) {
            if(ClientService.AddSongToPlaylist("0001", found.GetID()))
            {
                System.out.println("Song:" + found.GetTitle() + "added successfully");
            }
            else
            {
                System.out.println("Error: song:" + found.GetTitle() + " not added");
            }
        } else {
            System.out.println("Song:" + found.GetTitle() + "not found");

        }

    }

    public static void GetAllUserPlaylist()
    {
        List<Playlist> playlists = ClientService.GetUserPlaylists();
        if (playlists != null) {
            for (Playlist p : playlists) {
                System.out.println(p);
                System.out.println("\n");
            }
        } else {
            System.out.println("Error: no playlist found");
        }

        System.out.println("Do you want to see the songs of a playlist? (y/n)");
        String answer = sc.nextLine();
        if(answer.equals("y"))
        {
            System.out.println("Insert the playlist id:");
            String playlist_id = sc.nextLine();
            GetAllSongsFromPlaylist(playlist_id);
        }
    }

    public static void GetAllSongsFromPlaylist(String playlist_id)
    {
        List<Song> songs = ClientService.GetPlaylistSongs(playlist_id);
        if (songs != null) {
            for (Song s : songs) {
                System.out.println(s);
                System.out.println("\n");
            }
        } else {
            System.out.println("Error: no songs found in the playlist: " + playlist_id);
        }
    }

    private static void RegisterNewEmotion() throws RemoteException {
        System.out.println("Insert the emotion name:");
        System.out.println("Now whe are going to register a new emotion...");
        if(ClientService.RegisterNewEmotion())
        {
            System.out.println("Emotion created successfully");
        }
        else
        {
            System.out.println("Error: emotion creation failed");
        }
    }

    public static void ReadEmotionEvalutation() throws RemoteException {
        String song_id = "TRAAABD128F429CF47";

        List<Emotions> emotions = ClientService.GetEmotionsFromSong(song_id);
        if (emotions != null) {
            for (Emotions e : emotions) {
                System.out.println(e);
                System.out.println("\n");
            }
        } else {
            System.out.println("Error: no emotions found for the song: " + song_id);
        }
    }
}


