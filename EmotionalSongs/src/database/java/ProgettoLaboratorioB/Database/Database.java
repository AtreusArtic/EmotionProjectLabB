package ProgettoLaboratorioB.Database;

/**
 * With this class define the methods to connect to the database provided by the server.
 * The database use PostgreSQL library: version 42.2.5.
 */
public class Database
{
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
