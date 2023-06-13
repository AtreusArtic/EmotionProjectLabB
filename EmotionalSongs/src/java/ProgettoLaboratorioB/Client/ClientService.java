package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;

public class ClientService {

    public static void StartClientApplication() throws RemoteException
    {
        new Client();
    }
    public static void RegisterNewUser(User user){
        try {
            Client.server.RegisterNewUser(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static boolean Login(String username, String password) throws SQLException, RemoteException {
        try
        {
            return Client.server.Login(username, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void Exit(){
        System.exit(0);
    }


}

