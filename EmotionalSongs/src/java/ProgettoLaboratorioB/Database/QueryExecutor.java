package ProgettoLaboratorioB.Database;


import ProgettoLaboratorioB.Serializables.Emotions;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;
import ProgettoLaboratorioB.main.Enums;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
/**
 * QueryExecutor class will be used to execute queries to the database
 * when the client sends a request to the server.
 *
 * Database class will declare a QueryModule object, and it is a class that implements
 * a mapping system between tables and relative queries
 * that are defined in the postgres database;
 *
 * QueryExecutor needs also a Connection object to connect to the database.
 *
 * @author Enrico Artese, Marco Buglioni.
 * @version 0.0.1
 */
public class QueryExecutor
{
    /**
     * Connection object is used to connect to the database.
     *
     * */
    public static Connection con;

    /**
     * QueryModule object is used to map tables and queries.
     * */
    public static QueryModule queryModule;

    /**
     * QueryExecutor constructor will be used initialize a connection to the Postgres database.
     *
     * */
    public QueryExecutor(String url, String user, String password)
    {
        queryModule = new QueryModule();

        try
        {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("QUERY-EXECUTOR: Database connection established");
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR: Database connection error: " + e);
        }
    }

    /**
     * This method will be used to get a QueryExecutor object.
     * @param url is the url of the database
     * @param user is the username of the database
     * @param password is the password of the database
     *
     * @return a QueryExecutor object
     * */
    public static QueryExecutor GetQueryObject(String url, String user, String password){
        return new QueryExecutor(url, user, password);
    }

    /**
     * This method is used to load data from the song file to the database.
     * If the song relation is empty, the method will load the data from the song file.
     */
    public void LoadSongData() {
        try (Scanner in = new Scanner(queryModule.songfile)){
            while(in.hasNextLine()){
                String objString = in.nextLine();
                String [] songAttrs = objString.split("<SEP>");
                if(songAttrs.length >= 3)
                {
                    try
                    {
                        Statement stm = con.createStatement();
                        String query = String.format(Objects.requireNonNull(queryModule.getQuery(
                                        QueryModule.TABLE.SONGS, QueryModule.QUERY.ADD_SONG)),
                                songAttrs[0], songAttrs[1], songAttrs[2], songAttrs[3].replace("'", "''"));

                        stm.executeUpdate(query);
                    }catch (SQLException e)
                    {
                        System.out.println("QUERY-EXECUTOR: Error loading data: " + e);
                    }
                }
            }
        } catch (FileNotFoundException e)
        {
            System.err.println("QUERY-EXECUTOR Error: SongFile data not found! \n");
        }
    }

    /**
     * This function is used to get all songs in the database,
     * that have input String in the title.
     * @param title is the title of the song to be searched.
     * @return a list of songs that have the input String in the title.
     */
    public static List<Song> GetSongByTitle(String title){
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.SONGS,
                    QueryModule.QUERY.SEARCH_SONG_BY_TITLE)));

            ps = con.prepareStatement(query);

            ps.setString(1, "%" + title + "%");

            rs = ps.executeQuery();

            while(rs.next())
            {
                songs.add(new Song(rs.getInt(1),  //year
                        rs.getString(2),          //id
                        rs.getString(3),          //artist
                        rs.getString(4)));        //title
            }
            return songs;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function is used to get all songs in the database,
     * by specifying the year and the artist.
     * @param artist is the artist of the song to be searched.
     * @param year is the year when the song was published.
     * @return a list of songs that have the input String in the artist.
     */
    public static List<Song> GetSongYearTitle(String year, String artist){
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.SONGS,
                    QueryModule.QUERY.SEARCH_SONG_BY_YEARARTIST)));


            ps = con.prepareStatement(query);

            ps.setString(1, year);
            ps.setString(2, artist);

            rs = ps.executeQuery();

            while(rs.next())
            {
                songs.add(new Song(rs.getInt(1),  //year
                        rs.getString(2),          //id
                        rs.getString(3),          //artist
                        rs.getString(4)));        //title
            }
            return songs;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function is used to get a song in the database,
     * by specifying the id.
     * @param id is the id of the song to be searched.
     * @return a song that has the input String in the id.
     */
    public static Song GetSongsByID(String id)
    {
        PreparedStatement ps;
        ResultSet rs;
        Song song = null;
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.SONGS,
                    QueryModule.QUERY.GET_SONGS_BY_ID)));

            ps = con.prepareStatement(query);

            ps.setString(1, id);

            rs = ps.executeQuery();

            while(rs.next())
            {
                song = new Song(rs.getInt(1),     //year
                        rs.getString(2),          //id
                        rs.getString(3),          //artist
                        rs.getString(4));         //title
            }
            return song;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function is called when a request to login in the system is received.
     * @param username is the username of the user to be logged in.
     * @param password is the password of the user to be logged in.
     * @return a User object if the user exists in the database, null otherwise.
     */
    public static User UserLogin(String username, String password){
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery
                    (QueryModule.TABLE.USERS, QueryModule.QUERY.LOGIN)));

            ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);

            rs = ps.executeQuery();

            while(rs.next())
            {
                username = rs.getString(1);
                password = rs.getString(2);
                if(username != null && password != null) //Nota: questo controllo è ridondante.
                {
                    System.out.println("QUERY-EXECUTOR: User exist in the db...");
                    return new User(username, password, rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                }else
                {
                    System.out.println("QUERY-EXECUTOR: None user in the db, with these credentials.");
                    return null;
                }
            }
            return null;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error:  " + e);
            return null;
        }
    }

    /**
     * This function is called when a request to register a new user in the system is received.
     * @param username is the username of the user to be registered.
     * @param password is the password of the user to be registered.
     * @param email is the email of the user to be registered.
     * @param name is the name of the user to be registered.
     * @param surname is the surname of the user to be registered.
     * @param address is the address of the user to be registered.
     * @param CF is the CF of the user to be registered.
     * @return true if the user is registered successfully, false otherwise.
     */
    public static boolean RegisterNewUser(String username, String password, String email,String name, String surname, String address, String CF){
        Statement stmt;
        String query;
        try
        {
            System.out.println("QUERY-EXECUTOR: Connection reference set: " + con);
            try
            {
                query = java.lang.String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.USERS,
                        QueryModule.QUERY.REGISTER)), username, password, email, name, surname, address, CF);
            }
            catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: User " + username + " created successfully");
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }


    /**
     * This function is called when a request to create a new playlist in the system is received.
     * @param plt_name is the name of the playlist to be created.
     * @param username is the username of the user that creates the playlist.
     * @param ID is the ID of the playlist to be created.
     *
     * @return true if the playlist is created successfully, false otherwise.
     */
    public static boolean CreatePlaylist(String plt_name, String username, String ID){
        Statement stmt;
        String query;
        try {
            try
            {
                query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.CREATE_PLAYLIST)), plt_name, username, ID);
            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: Playlist " + plt_name + " created successfully");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }

    /**
     * This function is called when a request to get all playlists of a user is received.
     * @param username is the username of the user that wants to get all his playlists.
     * @return a list of playlists of the user.
     */
    public static List<Playlist> GetUserPlaylists(String username)
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Playlist> playlists = new ArrayList<>();
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                    QueryModule.QUERY.GET_USER_PLAYLISTS)));

            ps = con.prepareStatement(query);

            ps.setString(1, username);

            rs = ps.executeQuery();

            while(rs.next())
            {
                playlists.add(new Playlist(
                        rs.getString(1),  //plt_name
                        rs.getString(2),  //username
                        rs.getString(3)   //Playlist ID
                        ));
            }
            return playlists;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function is called when a request to get all songs of a playlist is received.
     * @param playlist_id is the ID of the playlist that wants to get all his songs.
     * @return a list of songs of the playlist.
     */
    public static List<Song> GetSongsFromPlaylist(String playlist_id)
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                    QueryModule.QUERY.GET_ALL_SONGS_FROM_PLAYLIST)));

            ps = con.prepareStatement(query);

            ps.setString(1, playlist_id);

            rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.println("QUERY-EXECUTOR: Song " + rs.getString(2) + " founded in playlist: " + rs.getString(1));
                songs.add(GetSongsByID(rs.getString(2)));
            }
            return songs;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function is called when a request to add a song to a playlist is received.
     * @param playlist_id is the ID of the playlist that wants to add a song.
     * @param song_id is the ID of the song that wants to be added to the playlist.
     * @return true if the song is added successfully, false otherwise.
     */
    public static boolean AddSongToPlaylist(String playlist_id, String song_id){
        Statement stmt;
        String query;
        try {
            try
            {
                query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.ADD_SONG_TO_PLAYLIST)), playlist_id, song_id);
            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: Song " + song_id + " added successfully to playlist selected.");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }

    /**
     * This function is called when a request to remove a song from a playlist is received.
     * @param playlist_id is the ID of the playlist that wants to remove a song.
     * @param song_id is the ID of the song that wants to be removed from the playlist.
     * @return true if the song is removed successfully, false otherwise.
     */
    public static boolean RemoveSongFromPlaylist(String playlist_id, String song_id){
        PreparedStatement ps;
        String query;
        try {
            try
            {
                query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.DELETE_SONG_FROM_PLAYLIST)));
            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            ps = con.prepareStatement(query);
            ps.setString(1, playlist_id);
            ps.setString(2, song_id);
            ps.executeUpdate();

            System.out.println("QUERY-EXECUTOR: Song " + song_id + " removed successfully from playlist selected.");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }

    /**
     * This function is called when a request to delete a playlist is received.
     * @param playlist_id is the ID of the playlist that wants to be deleted.
     * @return true if the playlist is deleted successfully, false otherwise.
     */
    public static boolean DeletePlaylist(String playlist_id){
        PreparedStatement ps;
        String query;
        try {
            try
            {
                query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.DELETE_PLAYLIST)));

            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }

            ps = con.prepareStatement(query);
            ps.setString(1, playlist_id);
            ps.executeUpdate();

            System.out.println("QUERY-EXECUTOR: Playlist " + playlist_id + " deleted successfully.");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }


    /**
     * This function is called when a request to get all emotions of a song is received.
     * @param emotion is the emotion recorder by a registered user
     * @return true if the emotion is registered successfully, false otherwise.
     */
    public static boolean RegisterNewEmotion(Emotions emotion)
    {
        Statement stmt;
        String query;
        try
        {
            try
            {
                query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.EMOTIONS,
                                QueryModule.QUERY.REGISTER_EMOTION)), emotion.GetSong(), emotion.GetUser(),
                        emotion.GetEvaluateEmotion(), emotion.GetEmotionDescription());
            }
            catch (Exception e)
            {
                System.out.println("QUERY-EXECUTOR error:  " + e);
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: Emotion registered successfully");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }

    /**
     * This function is called when a request to get all emotions of a song is received.
     * @param songid is the ID of the song that wants to get all his emotions.
     * @return a list of emotions of the song.
     */
    public List<Emotions> GetEmotionBySongID(String songid)
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Emotions> emotions = new ArrayList<>();
        try
        {
            String query = String.format(Objects.requireNonNull(queryModule.getQuery(QueryModule.TABLE.EMOTIONS,
                    QueryModule.QUERY.GET_EMOTIONS)));

            ps = con.prepareStatement(query);

            ps.setString(1, songid);
            rs = ps.executeQuery();

            while(rs.next())
            {
                emotions.add(new Emotions(
                        rs.getString(1),
                        rs.getString(2),
                        parseEmotionString(rs.getString(3)),
                        parseEmotionString(rs.getString(4))));
            }
            System.out.println("QUERY-EXECUTOR: list of emotions loading successfully");
            return emotions;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
    }

    /**
     * This function takes a String obj from the database and parses it to a Map<Enums.EMOTION, String>
     * @param input is the String obj to be parsed.
     * @return a Map<Enums.EMOTION, String>
     */
    private static Map<Enums.EMOTION, String> parseEmotionString(String input) {
        input = input.substring(1, input.length() - 1);
        String[] keyValuePairs = input.split(", ");
        Map<Enums.EMOTION, String> emotionMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                String emotion = parts[0];
                String value = parts[1];
                emotionMap.put(Enums.EMOTION.valueOf(emotion), value);
            }
        }

        return emotionMap;
    }

}




