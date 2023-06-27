package ProgettoLaboratorioB.Database;
/**
 * Query module is an abstract where all the queries defined in the database are stored.
 * The queries are used by the server to interact with the database.
 * For example, the server can use the query to insert a new user in the database.
 *
 * Database class will declare a QueryModule object and use it to interact with the database.
 */

import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.Serializables.User;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * I think that this class should be an abstract class, in order to make a singleton pattern.
 * So it could be not possible to create an instance of this class.
 */
public class QueryExecutor
{
    public static Connection con;
    public static QueryModule queryModule;
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

    public static QueryExecutor GetQueryObject(String url, String user, String password){
        return new QueryExecutor(url, user, password);
    }


    /**
     * SONG QUERIES METHODS:
     */
    public void LoadSongData() throws SQLException, FileNotFoundException {
        try (Scanner in = new Scanner(queryModule.songfile)){
            while(in.hasNextLine()){
                String objString = in.nextLine();
                String [] songAtrs = objString.split("<SEP>");
                if(songAtrs.length >= 3)
                {
                    try
                    {
                        Statement stm = con.createStatement();
                        String query = String.format(queryModule.getQuery(
                                QueryModule.TABLE.SONGS, QueryModule.QUERY.ADD_SONG),
                                songAtrs[0], songAtrs[1], songAtrs[2], songAtrs[3].replace("'", "''"));

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

    public static List<Song> GetSongByTitle(String title){
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.SONGS,
                            QueryModule.QUERY.SEARCH_SONG_BY_TITLE));

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

    public static List<Song> GetSongYearTitle(String year, String artist){
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.SONGS,
                    QueryModule.QUERY.SEARCH_SONG_BY_YEARARTIST));


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
     * USER QUERIES METHODS:
     */
    public static User UserLogin(String username, String password){
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            String query = queryModule.getQuery
                    (QueryModule.TABLE.USERS, QueryModule.QUERY.LOGIN);

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

    public static boolean RegisterNewUser(String username, String password, String email,String name, String surname, String address, String CF){
        Statement stmt;
        String query;
        try
        {
            System.out.println("QUERY-EXECUTOR: Connection reference set: " + con);
            try
            {
                query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.USERS,
                        QueryModule.QUERY.REGISTER), username, password, email, name, surname, address, CF);
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
     * PLAYLISTS QUERIES METHODS:
     */
    public static boolean CreatePlaylist(String plt_name, String username, String ID){
        Statement stmt;
        String query;
        try {
            try
            {
                query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.CREATE_PLAYLIST), plt_name, username, ID);
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

    public static List<Playlist> GetUserPlaylists(String username, String ID)
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Playlist> playlists = new ArrayList<>();
        try
        {
            String query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                    QueryModule.QUERY.GET_USER_PLAYLISTS));

            ps = con.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, ID);

            rs = ps.executeQuery();

            while(rs.next())
            {
                playlists.add(new Playlist(
                        rs.getString(1),  //plt_name
                        rs.getString(2)   //username
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

    public static List<Song> GetSongsFromPlaylist(String playlist_id)
    {
        PreparedStatement ps;
        ResultSet rs;
        List<Song> songs = new ArrayList<>();
        try
        {
            String query = java.lang.String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                    QueryModule.QUERY.GET_ALL_SONGS_FROM_PLAYLIST));

            ps = con.prepareStatement(query);

            ps.setString(1, playlist_id);

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
    public static boolean AddSongToPlaylist(String playlist_id, String song_id){
        Statement stmt;
        String query;
        try {
            try
            {
                query = String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.ADD_SONG_TO_PLAYLIST),playlist_id, song_id);
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

    public static boolean RemoveSongFromPlaylist(String playlist_id, String song_id){
        Statement stmt;
        String query;
        try {
            try
            {
                query = String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.DELETE_SONG_FROM_PLAYLIST),playlist_id, song_id);
            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: Song " + song_id + " removed successfully from playlist selected.");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }

    public static boolean DeletePlaylist(String playlist_id){
        Statement stmt;
        String query;
        try {
            try
            {
                query = String.format(queryModule.getQuery(QueryModule.TABLE.PLAYLISTS,
                        QueryModule.QUERY.DELETE_PLAYLIST),playlist_id);
            } catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return false;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: Playlist " + playlist_id + " deleted successfully.");
            return true;

        } catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return false;
        }
    }
}




