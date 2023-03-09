package ProgettoLaboratorioB.Client;

import ProgettoLaboratorioB.Server.ServerInterface;

import java.rmi.RemoteException;

public class Client {
    /**
     * Client class is the class that the client use to connect to the server.
     * The client can use the methods defined in the interface using the remote object of the server.
     * The remote object is created by the RMI registry.
     * @param ServerInterface server is the remote object of the server.
     */
    static ServerInterface server = null;

    public static void main(String[] args) throws RemoteException {

        // get connection with the server;
    }

    public static void getConnection() {
        // get connection with the server through the RMI registry;
    }
}
