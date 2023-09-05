package ProgettoLaboratorioB.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to create the tables in the database, if it is not already created.
 * The tables are:
 * • Users
 * • Songs
 * • Playlists
 * • PlaylistSaved
 * • Emotions
 * The tables are created using SQL queries, ATTENTION:
 * see the documentation for more details about constraints and relations.
 *
 * @author Enrico Artese
 * @version 0.0.1
 */
public class DatabaseService
{
    public static void CreateUserTable(Connection connection, String table_name)
    {
        Statement stmt;
        try
        {
            String query = "create table " + table_name + "(username varchar(200), password varchar(200), email varchar(200), name varchar(200), surname varchar(200), address varchar(200),CF varchar(200) , primary key(username));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: " + e);
        }
    }

    public static void CreateSongsTable(Connection connection, String table_name){
        Statement stmt;
        try
        {
            String query = "create table " + table_name + "(year varchar(200), id varchar(200), artist varchar(200), title varchar(200), primary key(id));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: " + e);
        }
    }

    public static void CreatePlaylistTable(Connection connection, String table_name)
    {
        Statement stmt;
        try
        {
            String query = "create table " + table_name + "(name varchar(200), username varchar(200), ID varchar(200)," +
                    " primary key(ID), foreign key(username) references users(username));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: " + e);
        }
    }

    public static void CreatePlaylistSavedTable(Connection connection, String table_name)
    {
        Statement stmt;
        try
        {
            String query = "create table " + table_name + "(username varchar(200), playlistID varchar(200), songID varchar(200), primary key(playlistID, songID)," +
                    " foreign key(playlistID) references playlists(ID),foreign key(username) references users(username), foreign key(songID) references songs(ID));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: " + e);
        }
    }

    public static void CreateEmotionsTable(Connection connection, String table_name)
    {
        Statement stmt;

        try
        {
            String query = "create table " + table_name + "(songID varchar(200), userID varchar(200), emotion varchar(200), description varchar(250), primary key(songID, userID)," +
                    " foreign key(userID) references users(username), foreign key(songID) references songs(id));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: " + e);
        }
    }
}
