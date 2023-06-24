package ProgettoLaboratorioB.Database;
/**
 * Query module is an abstract where all the queries defined in the database are stored.
 * The queries are used by the server to interact with the database.
 * For example, the server can use the query to insert a new user in the database.
 *
 * Database class will declare a QueryModule object and use it to interact with the database.
 */

import ProgettoLaboratorioB.Serializables.Song;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

/**
 * I think that this class should be an abstract class, in order to make a singleton pattern.
 * So it could be not possible to create an instance of this class.
 */
public class QueryExecutor
{
    public static Connection con;
    public static QueryModule queryModule;
    public QueryExecutor(String url, String user, String password) throws SQLException
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

    public static QueryExecutor GetQueryObject(String url, String user, String password) throws SQLException {
        return new QueryExecutor(url, user, password);
    }
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


    public static Song GetSongByTitleAuthor(String title , String artist) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            String query = String.format(queryModule.getQuery(QueryModule.TABLE.SONGS,
                            QueryModule.QUERY.SEARCH_SONG_BY_TITLEARTIST));


            ps = con.prepareStatement(query);

            ps.setString(1, artist);
            ps.setString(2, title);

            rs = ps.executeQuery();

            while(rs.next())
            {
                return new Song(rs.getInt(1),  //year
                        rs.getString(2),       //id
                        rs.getString(3),       //artist
                        rs.getString(4));      //title
            }
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
        return null;
    }

    public static Song GetSongYearTitle(int year, String title) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        try
        {
            String query = String.format(queryModule.getQuery(QueryModule.TABLE.SONGS,
                    QueryModule.QUERY.SEARCH_SONG_BY_YEARARTIST));


            ps = con.prepareStatement(query);

            ps.setInt(1, year);
            ps.setString(2, title);

            rs = ps.executeQuery();

            while(rs.next())
            {
                return new Song(rs.getInt(1),  //year
                        rs.getString(2),       //id
                        rs.getString(3),       //artist
                        rs.getString(4));      //title
            }
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error: " + e);
            return null;
        }
        return null;
    }


    public static boolean UserLogin(String username, String password){
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
                if(username != null)
                {
                    System.out.println("QUERY-EXECUTOR: User exist in the db...");
                    return true;
                }else
                {
                    System.out.println("QUERY-EXECUTOR: None user in the db, with these credentials.");
                    return false;
                }
            }
            return false;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-EXECUTOR error:  " + e);
            return false;
        }
    }

    public static void RegisterNewUser(String username, String password, String email){
        Statement stmt = null;
        String query;
        try
        {
            System.out.println("QUERY-EXECUTOR: Connection reference set: " + con);
            try
            {
                query = String.format(queryModule.getQuery(QueryModule.TABLE.USERS,
                        QueryModule.QUERY.REGISTER), username, password, email);
            }
            catch (NullPointerException e)
            {
                System.out.println("QUERY-EXECUTOR error: query string is null ");
                return;
            }
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("QUERY-EXECUTOR: User " + username + " created successfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("QUERY-EXECUTOR error: now conn is: " + con);
        }
    }
}


