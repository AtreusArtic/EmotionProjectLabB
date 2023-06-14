package ProgettoLaboratorioB.Database;
/**
 * Query module is an abstract where all the queries defined in the database are stored.
 * The queries are used by the server to interact with the database.
 * For example, the server can use the query to insert a new user in the database.
 *
 * Database class will declare a QueryModule object and use it to interact with the database.
 */

import ProgettoLaboratorioB.Serializables.Song;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

/**
 * I think that this class should be an abstract class, in order to make a singleton pattern.
 * So it could be not possible to create an instance of this class.
 */
public class QueryModule
{
    static Connection con;
    public QueryModule(String url, String user, String password) throws SQLException
    {
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

    public static QueryModule GetQueryObject(String url, String user, String password) throws SQLException {
        return new QueryModule(url, user, password);
    }
    /* !!!NOTA PER BUGLIO !!!:
    In questa query sto cercando una CANZONE,
    quindi mi aspetto che la funzione mi returni un oggetto CANZONE;
    Sotto ho provato tramite la funzione getObject()
    a convertire il risultato della query in un oggetto CANZONE, ma è da testare. -Artic
    */
    public static Song RicercaTitoloAnno(String titolo , int anno ) throws SQLException {
        Song srcSong = null;
        try
        {
            PreparedStatement queryParPstmt = Database.con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and anno = ?");
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

    /*!!!NOTA PER BUGLIO!!!:
   Cerca di eseguire un try catch per le query,
   invece che delegarla a Database.java con il throws,
   altrimenti l'applicazione si blocca se la query non va a buon fine.
   Qui sotto ti ho fatto un esempio di come potresti gestire un eventuale blocco di codice
   che potrebbe causare un SQLException*/
    public static void RicercaTitoloAutore(String titolo, String autore) throws SQLException {
        try
        {
            PreparedStatement queryParPstmt = Database.con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and autore = ?");
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


    public static boolean UtenteLoggato(String userid, String password){
        try
        {
            PreparedStatement queryParPstmt = Database.instance.con.prepareStatement("SELECT * FROM utentiregistrati WHERE userid = ? and password = ?");
            ResultSet rs = queryParPstmt.executeQuery();
            userid = rs.getString("userid");
            if(userid == null) {
                return false;
            }else{
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("QUERY-MODULE error ! " + e);
            return false;
        }
    }

    public static void RegisterNewUser(String username, String password, String email){
         Statement stmt = null;
        try
        {
            System.out.println("Connection is: " + con);
            String query = String.format("insert into users(username, password, email) values('%s', '%s', '%s');", username, password, email);
            stmt = con.createStatement(); // This line cause an error, Because connection will be closed after the try block.
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


