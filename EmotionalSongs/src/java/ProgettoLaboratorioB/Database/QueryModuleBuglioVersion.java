package ProgettoLaboratorioB.Database;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class QueryModuleBuglioVersion{

    /**
     * This class implements a mapping system between tables and relative queries those are defined in the postgres database;
     */
        /**
         * ENUMS: These enums are used to map table_name(KEY) and the list of queries associated to it(VALUE).
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
            GET_SONGS_BY_ID,

            /**
             * Playlist table queries
             */
            CREATE_PLAYLIST,
            GET_USER_PLAYLISTS,
            GET_ALL_SONGS_FROM_PLAYLIST,
            ADD_SONG_TO_PLAYLIST,
            DELETE_SONG_FROM_PLAYLIST,
            DELETE_PLAYLIST,

            /**
             * Emotions table queries
             */
            REGISTER_EMOTION,
            DELETE_EMOTION,
            GET_EMOTIONS,
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
         *  File path-to-songs file (check in the 'data' folder of the project)
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
        public static Map<ProgettoLaboratorioB.Database.QueryModule.TABLE, Map<ProgettoLaboratorioB.Database.QueryModule.QUERY, String>> tableMapping;

        /**
         * CONSTRUCTOR:
         *  The constructor initializes the tableMapping hashmap.
         *  The map is initialized by calling the init<TableName>Table() methods.
         * */
        public QueryModuleBuglioVersion()
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
            Map<ProgettoLaboratorioB.Database.QueryModule.QUERY, String> users_table_queries = new HashMap<>();

            users_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.LOGIN,
                            "SELECT * FROM utenti WHERE userid = ? AND password = ?");

            users_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.REGISTER,
                            "insert into users" +
                                    "(nome, cognome, codicefiscale, via, civico, citta, provincia, cap, userid, email, password ) " +
                                    "values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');");

            tableMapping.put(ProgettoLaboratorioB.Database.QueryModule.TABLE.USERS, users_table_queries);
        }


        /**
         * This method set all the queries associated to the songs table.
         * The queries are mapped to the enum TABLE.SONGS
         **/
        public void initSongsTable()
        {
            Map<ProgettoLaboratorioB.Database.QueryModule.QUERY, String> songs_table_queries = new HashMap<>();

            songs_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.ADD_SONG,
                            "INSERT INTO canzone(titolo, autore, anno) VALUES('%s', '%s', '%s');");

            songs_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.SEARCH_SONG_BY_TITLE,
                            "SELECT * FROM canzone WHERE titolo LIKE ?");

            songs_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.SEARCH_SONG_BY_YEARARTIST,
                            "SELECT * FROM canzone WHERE anno = ? AND autore = ?");
/*
        songs_table_queries.put
                (QUERY.GET_SONGS_BY_ID,
                        "SELECT * FROM canzone WHERE id = ?");
        tableMapping.put(TABLE.SONGS, songs_table_queries);

 */

        }

        /**
         * This method set all the queries associated to the playlists table.
         * The queries are mapped to the enum TABLE.PLAYLISTS
         **/
        public void initPlaylistsTable()
        {
            Map<ProgettoLaboratorioB.Database.QueryModule.QUERY, String> playlists_table_queries = new HashMap<>();

            playlists_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.CREATE_PLAYLIST,
                            "INSERT INTO playlist (nomePlaylist, userId) VALUES ('%s', '%s');");
            playlists_table_queries.put(
                    ProgettoLaboratorioB.Database.QueryModule.QUERY.GET_USER_PLAYLISTS,
                    "SELECT nomePlaylist FROM playlist WHERE userId = ?");

            playlists_table_queries.put(
                    ProgettoLaboratorioB.Database.QueryModule.QUERY.GET_ALL_SONGS_FROM_PLAYLIST,
                    "SELECT titolo, autore FROM composta natural join playlist natural join canzone WHERE userId = ? and nomePlaylist = ?");

            playlists_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.ADD_SONG_TO_PLAYLIST,
                            "INSERT INTO composta (nomePlaylist, userId, titolo, autore) VALUES ('%s', '%s', '%s', '%s');");

            playlists_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.DELETE_SONG_FROM_PLAYLIST,
                            "DELETE FROM composta WHERE nomePlaylist = ? AND userId = ? and titolo = ? and autore = ?");

            playlists_table_queries.put
                    (ProgettoLaboratorioB.Database.QueryModule.QUERY.DELETE_PLAYLIST,
                            "DELETE FROM playlist WHERE nomePlaylist = ? and userId = ? ");

            tableMapping.put(ProgettoLaboratorioB.Database.QueryModule.TABLE.PLAYLISTS, playlists_table_queries);
        }

        /**
         * This method set all the queries associated to the emotions table.
         * The queries are mapped to the enum TABLE.EMOTIONS
         **/
        public void initEmotionsTable()
        {
            Map<ProgettoLaboratorioB.Database.QueryModule.QUERY, String> emotions_table_queries = new HashMap<>();

            emotions_table_queries.put(ProgettoLaboratorioB.Database.QueryModule.QUERY.REGISTER_EMOTION,
                    "INSERT INTO emotions (songid, userid, emotion, description) VALUES ('%s', '%s', '%s', '%s');");

            emotions_table_queries.put(ProgettoLaboratorioB.Database.QueryModule.QUERY.GET_EMOTIONS,
                    "SELECT emotion FROM emotions WHERE songid = ?");

            tableMapping.put(ProgettoLaboratorioB.Database.QueryModule.TABLE.EMOTIONS, emotions_table_queries);
        }

        /**
         * Function that returns a String corresponding to the query,
         * specifying the table name and one of its related queries
         * defined in the enum QUERY
         * */
        public static String getQuery(ProgettoLaboratorioB.Database.QueryModule.TABLE table, ProgettoLaboratorioB.Database.QueryModule.QUERY query)
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