package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Database.Database;

import java.rmi.RemoteException;

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
        if(Database.instance.con != null) {

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

}
