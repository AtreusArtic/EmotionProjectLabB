package ProgettoLaboratorioB.main;

import ProgettoLaboratorioB.Client.ClientMain;

import java.rmi.RemoteException;


public class App
{
    public static void main( String[] args ) throws RemoteException
    {
        if(App_System.crntDatabaseStatus == App_System.DATABASE_STATUS.ONLINE)
                ClientMain.StartClientApplication();
    }

}


