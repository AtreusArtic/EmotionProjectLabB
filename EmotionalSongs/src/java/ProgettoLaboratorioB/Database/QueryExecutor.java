package ProgettoLaboratorioB.Database;
/**
 * Query module is an abstract where all the queries defined in the database are stored.
 * The queries are used by the server to interact with the database.
 * For example, the server can use the query to insert a new user in the database.
 *
 * Database class will declare a QueryModule object and use it to interact with the database.
 */

import ProgettoLaboratorioB.Serializables.Song;

import java.sql.*;

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
            System.out.println("Database connection established");
        }
        catch (SQLException e)
        {
            System.out.println("Database connection error: " + e);
        }
    }

    public static QueryExecutor GetQueryObject(String url, String user, String password) throws SQLException {
        return new QueryExecutor(url, user, password);
    }

    public static Song RicercaTitoloAnno(String titolo , int anno ) throws SQLException {
        Song srcSong = null;
        try
        {
            PreparedStatement queryParPstmt = con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and anno = ?");
            ResultSet rs = queryParPstmt.executeQuery();
            while(rs.next()) {

                System.out.println(rs.getString("titolo"));
                System.out.println(rs.getString("autore"));
                System.out.println(rs.getInt("anno"));
                //DA TESTARE:
                srcSong = rs.getObject("canzone", Song.class);
                return srcSong;
            }
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-MODULE error ! " + e);
            return null;
        }
        return null;
    }
    public static void RicercaTitoloAutore(String titolo, String autore) throws SQLException {
        try
        {
            PreparedStatement queryParPstmt = con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and autore = ?");
            ResultSet rs = queryParPstmt.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("titolo"));
                System.out.println(rs.getString("autore"));
                System.out.println(rs.getInt("anno"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-MODULE error ! " + e);
        }
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
                    System.out.println("User exist in the db...");
                    return true;
                }else
                {
                    System.out.println("Now user in the db, with NULL reference, please retry...");
                    return false;
                }
            }
            return false;
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-MODULE error:  " + e);
            return false;
        }
    }

    public static void RegisterNewUser(String username, String password, String email){
        Statement stmt = null;
        String query;
        try
        {
            System.out.println("Connection is: " + con);
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
            System.out.println("User " + username + " created successfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("CATCH: now conn is: " + con);
        }
    }
}


