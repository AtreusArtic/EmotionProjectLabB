package ProgettoLaboratorioB.Server;

import ProgettoLaboratorioB.main.App_System;

import java.io.FileNotFoundException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * ServerMain class is the class used to start the server.
 * In this class the remote object is initialized and the server is started.
 * The remote object is created by the RMI registry.
 * Also, the remote obj can connect to the database,
 * and implement the methods to interact with it through the QueryExecutor class.
 */
public class ServerMain
{

    /**
     * @param SERVER_PORT: the port of the remote server.
     */
    private static final int SERVER_PORT = 1099;
    public static void main(String[] args) {

        try
        {
            LoadServerConfig();
            InitServerConnection();
        }catch (Exception e) {
            System.out.println("SERVER-MAIN: initialization error: " + e);
        }

        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    private static void LoadServerConfig()
    {
        String server_address = GetServerIP();
        App_System.WriteServerIP(server_address);
    }

    /**
     * This method initializes the connection with the server, using the RMI registry.
     * and wait for new client access.
     */
    public static void InitServerConnection(){
        try
        {
            ServerImpl server = new ServerImpl();
            Registry registry = LocateRegistry.createRegistry(SERVER_PORT);
            registry.rebind("Server", server);
            System.out.println("SERVER-MAIN: IM ONLINE");
        } catch (RemoteException e)
        {
            System.out.println("SERVER-MAIN: Error to connect the server: " + e);
        } catch (FileNotFoundException e)
        {
            System.out.println("SERVER-MAIN Error: failed to load songs file into database");
        }
    }


    /**
     * This method is used to get the server IP.
     * @throws Exception if the Server_PORT is .
     */
    private static String GetServerIP()
    {
        String server_address;
        try (final DatagramSocket datagramSocket = new DatagramSocket())
        {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), SERVER_PORT);
            server_address = datagramSocket.getLocalAddress().getHostAddress();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            server_address = "localhost";
        }
        return server_address;
    }

}
