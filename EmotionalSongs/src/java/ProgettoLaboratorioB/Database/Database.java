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
    static String pw = "enrico1234";
    public static Connection con;

    /**
     * Singleton pattern for the database.
     * @param instance: the instance of the database.
     */
    public static Database instance;

    public void SetInstance()
    {
        if (instance == null)
        {
            instance = this;
        }
    }
    /**
     * Connect to the database.
     */
    public  Connection DatabaseConnection()
    {
        //Set database instance:
        SetInstance();

        //Connect the database:
        if(instance.con != null)
        {
            return instance.con;
        }

        try (Connection connection = DriverManager.getConnection(port, username, pw)) {
            instance.con = connection;
            System.out.println(instance.con);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return con;

    }


}
