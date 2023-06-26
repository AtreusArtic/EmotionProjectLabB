package ProgettoLaboratorioB.Database;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is an abstract where all the table and relative queries
 * are defined in the postgres database;
 *
 */


public class QueryModule
{
    /**
     * ENUMS: This enums are used to map table_name(KEY) and the list of queries associated to it(VALUE).
     * by using the Java Hashmap Class with <Table)><QueriesAssociated>
     * */
    public enum QUERY
    {
        /**
        * Users table queries
        * */
        LOGIN,
        REGISTER,

        /**
        * Song table queries
        */

        ADD_SONG,
        SEARCH_SONG_BY_YEARARTIST,
        SEARCH_SONG_BY_TITLE,

        /**
        * Playlist table queries
         */
        CREATE_PLAYLIST,
        ADD_SONG_TO_PLAYLIST,
        DELETE_SONG_FROM_PLAYLIST,
        DELETE_PLAYLIST,

        /**
         * Emotions table queries
         */
        ADD_EMOTION,

        DELETE_EMOTION,

        GET_EMOTION,
    }

    /**
     * Enum TABLE is used to show every table in the database
     * */
    public enum TABLE
    {
        USERS,
        SONGS,
        PLAYLISTS,
        EMOTIONS
    }

    /**
     * DATA:
     *  File path to songs file (check in 'data' folder of the project)
     *  Thanks to File Separator property we can set the path dynamically for every OS.
     *  And the file path directory is set to the current working directory of the project,
     *  thanks to the System.getProperty("user.dir") method.
     * */

    private static String filename = "data/Canzoni.Dati.txt";
    private static String dir = System.getProperty("user.dir");
    private static final String path = dir + File.separator + filename;
    File songfile = new File(path);

    /**
     * MAP:
     *  @param tableMapping is a HashMap that contains all the tables and the queries associated to it.
     *  The map is initialized in the constructor of the class.
     *  The map is used by the QueryExecutor class to execute the queries.
     * */
    public static Map<TABLE, Map<QUERY, String>> tableMapping;

    /**
     * CONSTRUCTOR:
     *  The constructor initializes the tableMapping hashmap.
     *  The map is initialized by calling the init<TableName>Table() methods.
     * */
    public QueryModule()
    {
        tableMapping = new HashMap<>();

        initUsersTable();

        initSongsTable();

        initPlaylistsTable();

        initEmotionsTable();
    }

    /**
     * This method set all the queries associated to the users table.
     * The queries are mapped to the enum TABLE.USERS
     **/
    public void initUsersTable()
    {
        Map<QUERY, String> users_table_queries = new HashMap<>();

        users_table_queries.put
                (QUERY.LOGIN,
                        "SELECT * FROM users WHERE username = ? AND password = ?");

        users_table_queries.put
                (QUERY.REGISTER,
                        "insert into users" +
                                "(username, password, email, name, surname, address, CF ) " +
                                "values('%s', '%s', '%s','%s', '%s', '%s','%s');");

        tableMapping.put(TABLE.USERS, users_table_queries);
    }


    /*TODO Per BUGLIO:
       1.Queste queries sono ancora provvisorie (con parametri non congrui ai tuoi diagrammi ER)
       ed eventualmente vanno modificate a seconda delle tue esigenze
       2.Inoltre verificare che le tabelle definite nella classe DatabaseService,
       siano congrue a quelle definite nei tuoi schemi ER.*/

    /**
     * This method set all the queries associated to the songs table.
     * The queries are mapped to the enum TABLE.SONGS
     **/
    public void initSongsTable()
    {
        Map<QUERY, String> songs_table_queries = new HashMap<>();

        songs_table_queries.put
                (QUERY.ADD_SONG,
                        "insert into songs(year, id, artist, title) values('%s', '%s', '%s', '%s');");
        songs_table_queries.put
                (QUERY.SEARCH_SONG_BY_TITLE,
                        "SELECT * FROM songs WHERE title LIKE ?");

        songs_table_queries.put
                (QUERY.SEARCH_SONG_BY_YEARARTIST,
                        "SELECT * FROM songs WHERE year = ? AND artist = ?");

        tableMapping.put(TABLE.SONGS, songs_table_queries);
    }

    /**
     * This method set all the queries associated to the playlists table.
     * The queries are mapped to the enum TABLE.PLAYLISTS
     **/
    public void initPlaylistsTable()
    {
        Map<QUERY, String> playlists_table_queries = new HashMap<>();

        playlists_table_queries.put
                (QUERY.CREATE_PLAYLIST,
                        "INSERT INTO playlists (name, username) VALUES (?, ?)");

        playlists_table_queries.put
                (QUERY.ADD_SONG_TO_PLAYLIST,
                        "INSERT INTO playlists (playlist_id, song_id) VALUES (?, ?)");

        playlists_table_queries.put
                (QUERY.DELETE_SONG_FROM_PLAYLIST,
                        "DELETE FROM playlists WHERE playlist_id = ? AND song_id = ?");

        playlists_table_queries.put
                (QUERY.DELETE_PLAYLIST,
                        "DELETE FROM playlists WHERE id = ?");

        tableMapping.put(TABLE.PLAYLISTS, playlists_table_queries);
    }

    /**
     * This method set all the queries associated to the emotions table.
     * The queries are mapped to the enum TABLE.EMOTIONS
     **/
    public void initEmotionsTable()
    {
        Map<QUERY, String> emotions_table_queries = new HashMap<>();

        emotions_table_queries.put(QUERY.ADD_EMOTION,
                "INSERT INTO emotions (song_id, username, emotion) VALUES (?, ?, ?)");

        emotions_table_queries.put(QUERY.DELETE_EMOTION,
                "DELETE FROM emotions WHERE song_id = ? AND username = ?");

        emotions_table_queries.put(QUERY.GET_EMOTION,
                "SELECT emotion FROM emotions WHERE song_id = ? AND username = ?");

        tableMapping.put(TABLE.EMOTIONS, emotions_table_queries);
    }

    /**
     * Function that returns a String corresponding to the query,
     * specifying the table name and one of its related queries
     * defined in the enum QUERY
     * */
    public static String getQuery(TABLE table, QUERY query)
    {
        String query_string;
        if(tableMapping == null)
        {
            System.out.println("QUERY-MODULE: Table map not initialized");
            return null;
        }
        else
            query_string = tableMapping.get(table).get(query);

        if(query_string == null)
        {
            System.out.println("QUERY-MODULE: Query not found");
            return null;
        }
        else
        {
            return query_string;
        }
    }
}
