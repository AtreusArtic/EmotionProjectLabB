package ProgettoLaboratorioB.Database;

import ProgettoLaboratorioB.main.App_System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class define the methods to connect to the database provided by the server.
 * The database use PostgresSQL library: version 42.2.5.
 */

public class Database
{


    static String port = "jdbc:postgresql://localhost:5432/Emotionals_songs_lab_b";
    static String username = "postgres";
    static String pw = "enrico1234"; //Change this password with your postgres password.
    public static Connection con;

    /**
     * Singleton pattern for the database.
     * @param instance: the instance of the database.
     */
    public static Database instance;

    public Database()
    {
        if (instance == null)
        {
            instance = this;
        }
    }
    /**
     * Connect to the database.
     */
    public Connection DatabaseConnection()
    {
        try (Connection connection = DriverManager.getConnection(port, username, pw)) {
            instance.con = connection;
            DatabaseService.CreateUserTable(connection, "users");
            //DatabaseService.RegistrationRequest(connection, "enrico", "enrico1234", "ciaone");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return instance.con;

    }


}
