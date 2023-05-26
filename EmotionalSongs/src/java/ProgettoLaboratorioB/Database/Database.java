package ProgettoLaboratorioB.Database;

import java.sql.*;

/**
 * This class define the methods to connect to the database provided by the server.
 * The database use PostgresSQL library: version 42.2.5.
 */

public class Database
{
    static String port = "jdbc:postgresql://localhost:5432/Emotionals_songs_lab_b";
    static String username = "postgres";
    static String pw = "marco2000";
    static Connection con;

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
    public Connection DatabaseConnection()
    {
        //Set database instance:
        SetInstance();

        //Connect the database:
        try (Connection connection = DriverManager.getConnection(port, username, pw)) {
            instance.con = connection;
            System.out.println("Java JDBC PostgreSQL Example");

            System.out.println("Connected to PostgreSQL database!");
        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return con;
    }

}
