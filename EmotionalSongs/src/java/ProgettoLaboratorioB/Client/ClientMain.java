package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Serializables.User;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain {

    public static void StartClientApplication() throws RemoteException
    {
        new Client();
        Scanner sc = new Scanner(System.in);

        int switchState = 0;

        while(switchState != 3)
        {
            System.out.println("Choose the function to call:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Anonymous Mode");
            System.out.println("4. Exit");
            switchState = sc.nextInt(); //Remember to cast the input to int, cause an error if you don't;
            switch(switchState)
            {
                case 1:
                    // Call the registry module to a register
                    System.out.println("Insert the username:");
                    String username = sc.next();

                    System.out.println("Insert the password:");

                    String password = sc.next();

                    System.out.println("Insert the email:");
                    String email = sc.next();

                    User user = new User(username, password, email);
                    Client.server.RegisterNewUser(user);

                    break;
                case 2:
                    System.out.println("Login function called");
                    break;
                case 3:
                    System.out.println("Anonymous menu called");
                    break;
                case 4:
                    System.out.println("Exit function called");
                    break;
                default:
                    System.out.println("Invalid function");
                    break;
            }
        }

        // choose 1 to select the register function
        // choose 2 to select the login function
        // choose 3 to select anonymous menu

    }
}
