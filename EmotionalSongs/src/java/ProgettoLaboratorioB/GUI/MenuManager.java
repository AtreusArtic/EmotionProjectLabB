package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Serializables.User;

public abstract class MenuManager {

    private static User user_connected = null;

    public static User getUser_connected() {
        return user_connected;
    }

    public static void setUser_connected(User user_con)
    {
        user_connected = user_con;
    }


}
