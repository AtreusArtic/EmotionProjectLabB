package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Database.Database;

public class App_System{


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

    Database database;
    public static enum DATABASE_STATUS {
        OFFLINE,
        ONLINE
    }
    public static DATABASE_STATUS crntDatabaseStatus = DATABASE_STATUS.OFFLINE;


    public static void SetNewStatusDatabase() {

        if(crntDatabaseStatus == DATABASE_STATUS.OFFLINE) {
            crntDatabaseStatus = DATABASE_STATUS.ONLINE;
        }
        else {
            crntDatabaseStatus = DATABASE_STATUS.OFFLINE;
        }
    }
    public static boolean IsDBOnline()
    {
        if(crntDatabaseStatus == DATABASE_STATUS.OFFLINE) {
            System.out.println("Database offline");
            return false;
        }
        else {
            System.out.println("Database online");
            return true;
        }
    }

}
