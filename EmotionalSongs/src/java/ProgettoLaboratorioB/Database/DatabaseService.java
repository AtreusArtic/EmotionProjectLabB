package ProgettoLaboratorioB.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService
{
    public static void CreateUserTable(Connection connection, String table_name)
    {
        Statement stmt;
        try
        {
            String query = "create table " + table_name + "(username varchar(200), password varchar(200), email varchar(200),  primary key(username));";
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            System.out.println("DB_SERVICE: Table " + table_name + " created successfully");
        } catch (SQLException e)
        {
            System.out.println("DB_SERVICE: Table " + table_name + " already exists");
        }
    }

    //TODO: change year column to NUMERIC or DECIMAL type.
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
            System.out.println("DB_SERVICE: Table " + table_name + " already exists");
        }
    }
}