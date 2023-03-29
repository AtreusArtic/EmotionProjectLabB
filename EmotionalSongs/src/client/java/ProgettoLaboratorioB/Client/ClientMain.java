package ProgettoLaboratorioB.Client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws RemoteException
    {
        new ClientImpl();
        //Only for test: wait for the user to press enter
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
