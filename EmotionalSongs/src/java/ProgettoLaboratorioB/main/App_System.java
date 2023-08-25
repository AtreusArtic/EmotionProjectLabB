package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.main.Enums.SYSTEM_STATE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

public class App_System{

    public static App_System appSystem;

    public static DATABASE_STATUS crntDatabaseStatus = DATABASE_STATUS.OFFLINE;
    //set singleton pattern
    public void SetInstance()
    {
        if (appSystem == null)
        {
            appSystem = this;
        }
    }

    public App_System () throws RemoteException {

        SetInstance();

        InitStartSession();
    }

    /**
     * Enum states to manage the main menu of the system.
     */

    private static SYSTEM_STATE crntState;

    public static void SetNewState(SYSTEM_STATE newState)
    {
        crntState = newState;
    }

    public static SYSTEM_STATE GetCrntState()
    {
        return crntState;
    }


    public void InitStartSession()
    {
            if(IsDBOnline())
            {
                 crntDatabaseStatus = DATABASE_STATUS.ONLINE;
            }
            else
            {
                crntDatabaseStatus = DATABASE_STATUS.OFFLINE;
            }

            SystemAlert();
    }
    public static enum DATABASE_STATUS {
        OFFLINE,
        ONLINE
    }

    public static boolean IsDBOnline()
    {
        if(appSystem != null) {

            return true;
        }
        else {
            return false;
        }
    }

    public static void SystemAlert()
    {
        if(crntDatabaseStatus == DATABASE_STATUS.OFFLINE)
        {
            System.out.println("!!!SYSTEM ALERT!!!\n" +
                    "The database is offline.\n" +
                    "Please, check if the Postgres Server is online.\n" +
                    "!!!ATTENTION!!!");
        }
        else {
            System.out.println("!!!SYSTEM ALERT!!!\n" +
                    "The database is online :) .\n" +
                    "!!!ATTENTION!!!");
        }
    }


    /**
    * This function uses the binary search algorithm to search for an element in a list.
     * @param <T> generic type of the list, so all Object that implements Comparable interface, can be used.
     * @param element the element to search.
     * @param list the list where to search the element.
    **/

    public static <T extends Comparable<T>> T binarySearch(T element, List<T> list) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = element.compareTo(list.get(mid));

            if (comparison == 0) {
                return list.get(mid);
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return null;
    }


    // SERVER CONFIGURATION METHODS... //

    /**
     * This function writes the server ip in the ServerConfig.properties file,
     * every time the Server starts, before to connect with the server.
     * @param server_ip the server ip to write.
     */
    public static void WriteServerIP(String server_ip)
    {
        String filename = "ServerConfig.properties";
        File server_config;

        String file_path = SetFilePath(filename);
        if(file_path != null)
        {
            server_config = new File(file_path);
        }
        else
        {
            System.out.println("APP_SYSTEM Error: file path is null");
            return;
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(server_config))
        {
            Properties properties = new Properties();

            properties.setProperty("server_ip", server_ip);

            properties.store(fileOutputStream, null);

            System.out.println("APP_SYSTEM write Server IP in the file: " + server_ip);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * This function loads the server ip from the ServerConfig.properties file,
     * every time the client starts, before to connect with the server.
     * @return the server ip.
     */
    public static String LoadServerIP()
    {
        String filename = "ServerConfig.properties";
        File server_config = null;

        String file_path = SetFilePath(filename);

        if(file_path != null)
        {
            server_config = new File(file_path);
        }
        else
        {
            System.out.println("APP_SYSTEM Error: file path is null");
        }

        try(FileInputStream fileInputStream = new FileInputStream(server_config))
        {
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String server_ip = properties.getProperty("server_ip");

            System.out.println("APP_SYSTEM, Server IP loaded succesfully: " + server_ip);
            return server_ip;
        }
        catch (Exception e)
        {
            System.out.println("APP_SYSTEM Error: failed to load server ip: " + e.getMessage());
            return null;
        }
    }

    public static String SetFilePath(String filename)
    {
        if(System.getProperty("os.name").contains("Windows 10"))
        {
            filename = "data/" + filename;
        }
        else if(System.getProperty("os.name").contains("Mac OS X")) {
            filename = "data/" + filename;
        }
        else
        {
            filename = "data/" + filename;
        }

        String dir = System.getProperty("user.dir");
        String file_path = dir + File.separator + filename;
        return file_path;
    }



}
