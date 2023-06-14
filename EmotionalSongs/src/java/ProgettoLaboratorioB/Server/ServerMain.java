package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.Database.Database;
import ProgettoLaboratorioB.Database.DatabaseService;
import ProgettoLaboratorioB.main.App_System;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ServerMain class is the class used to start the server.
 * The server can use the methods defined in the interface using the remote object of the server.
 * The remote object is created by the RMI registry.
 * Also, the server can connect to the database, using the database class.
 * The database class is used by the server to interact with the database.
 */
public class ServerMain
{
    /**
     * Start the server.
     */
    public static void main(String[] args) throws RemoteException {
        /**
        * 1. CreateDatabase connection;
         * */
        try
        {
            ServerInitialization();
        }catch (Exception e) {
            System.out.println("Server initialization error: " + e);
        }


        /**
         * 3. When the server is initialized, and the database is connected, update app system.
         */
        new App_System();

        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    /**
     * Initialize the server and create the remote object, and pass the database connection to the remote object.
     */
    public static void ServerInitialization() throws RemoteException
    {
        try {
            if(new Database().instance.DatabaseConnection().isValid(3000))
                System.out.println("SERBERO: Connection is valid");
            else
                System.out.println("SERBERO: Connection is not valid");
        }catch (SQLException e)
        {
            e.printStackTrace();}
        new ServerImpl(new Database().DatabaseConnection());
    }


}
