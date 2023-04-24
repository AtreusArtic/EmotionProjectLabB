package ProgettoLaboratorioB.Database;
import java.sql.*;

/**
 * With this class define the methods to connect to the database provided by the server.
 * The database use PostgreSQL library: version 42.2.5.
 */




public class Database
{

    static String porta = "jdbc:postgresql://localhost:5432/Emotionals_songs_lab_b";
    static String username = "postgres";
    static String pw = "marco2000";

    public static void conn () {
        try (Connection connection = DriverManager.getConnection(porta, username, pw)) {

            System.out.println("Java JDBC PostgreSQL Example");

            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();

        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    public Database instance;
    public Database()
    {
        getInstance();
    }

    public Database getInstance()
    {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private QueryModule _queryModule;

    /**
     * Connect to the database.
     */
    public void DatabaseConnection()
    {
        // connect to the database
    }

    /**
     * Create the database.
     */
    public void CreateDatabase()
    {
        // create the database
    }

    /**
     * Use the query module to interact with the database.
     */
}
