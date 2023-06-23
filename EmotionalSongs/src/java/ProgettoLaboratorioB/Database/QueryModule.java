package ProgettoLaboratorioB.Database;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is an abstract where all the table and relative queries
 * are defined in the postgres database;
 *
 */


public class QueryModule
{
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
        SEARCH_SONG,
        DELETE_SONG,

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

    public enum TABLE
    {
        USERS,
        SONGS,
        PLAYLISTS,
        EMOTIONS
    }
    public static Map<TABLE, Map<QUERY, String>> tableMapping;


    public QueryModule()
    {

        tableMapping = new HashMap<>();

        initUsersTable();

        initSongsTable();

        initPlaylistsTable();

        initEmotionsTable();

    }

    public void initUsersTable()
    {
        Map<QUERY, String> users_table_queries = new HashMap<>();

        users_table_queries.put
                (QUERY.LOGIN,
                        "SELECT * FROM users WHERE username = ? AND password = ?");

        users_table_queries.put
                (QUERY.REGISTER,
                        "insert into users(username, password, email) values('%s', '%s', '%s');");

        tableMapping.put(TABLE.USERS, users_table_queries);
    }


    /*TODO: Buglio queste queries sono ancora provvisorie (con parametri sbagliati)
       ed eventualmente vanno modificate a seconda delle tue esigenze*/
    public void initSongsTable()
    {
        Map<QUERY, String> songs_table_queries = new HashMap<>();

        songs_table_queries.put
                (QUERY.SEARCH_SONG,
                        "SELECT * FROM songs WHERE title = ? AND artist = ?");

        songs_table_queries.put
                (QUERY.DELETE_SONG,
                        "DELETE FROM songs WHERE title = ? AND artist = ?");

        tableMapping.put(TABLE.SONGS, songs_table_queries);
    }

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
     * A Function that returns the query string
     *  by using the table name and query enum value
     *  passed as parameters
     * */
    public static String getQuery(TABLE table, QUERY query)
    {
        String query_string = null;
        if(tableMapping == null)
        {
            System.out.println("Table mapping not initialized");
            return null;
        }
        else
            query_string = tableMapping.get(table).get(query);

        if(query_string == null)
        {
            System.out.println("Query not found");
            return null;
        }
        else
        {
            return query_string;
        }
    }



}
