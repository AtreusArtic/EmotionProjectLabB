package ProgettoLaboratorioB.Database;
/**
 * Query module is an abstract where all the queries defined in the database are stored.
 * The queries are used by the server to interact with the database.
 * For example, the server can use the query to insert a new user in the database.
 *
 * Database class will declare a QueryModule object and use it to interact with the database.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * I think that this class should be an abstract class, in order to make a singleton pattern.
 * So it could be not possible to create an instance of this class.
 */
public abstract class QueryModule
{
    //Make a singleton pattern of this class:
    //TODO: insert all (?) the queries defined in the database;

    public void RicercaTitoloAutore(String titolo, String autore) throws SQLException {
        PreparedStatement queryParPstmt = Database.con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and autore = ?");
        PreparedStatement.setString(1, titolo);
        PreparedStatement.setString(2, autore);
        ResultSet rs = queryParPstmt.executeQuery();
        while(rs.next()) {
         System.out.println(rs.getString("titolo"));
         System.out.println(rs.getString("autore"));
         System.out.println(rs.getInt("anno"));
        }
    }
    public void RicercaTitoloAnno() throws SQLException {
        PreparedStatement queryParPstmt = Database.con.prepareStatement("SELECT * FROM canzone WHERE titolo = ? and anno = ?");
        ResultSet rs = queryParPstmt.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getString("titolo"));
            System.out.println(rs.getString("autore"));
            System.out.println(rs.getInt("anno"));
        }
    }

    //TODO: change name function to UserLogin
    public boolean UtenteLoggato() throws SQLException {
        PreparedStatement queryParPstmt = Database.con.prepareStatement("SELECT * FROM utentiregistrati WHERE userid = ? and password = ?");
        ResultSet rs = queryParPstmt.executeQuery();
        String userid = rs.getString("userid");
        if(userid == null) {
            return false;
        }else{
            return true;
        }
    }
}


