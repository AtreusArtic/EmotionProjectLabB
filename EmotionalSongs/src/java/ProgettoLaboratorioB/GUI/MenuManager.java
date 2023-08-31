package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public abstract class MenuManager {

    protected ClientService clientService = new ClientService();
    protected ManagerGUI m = new ManagerGUI();
    private static User user_connected = null;

    public static User getUser_connected() {
        return user_connected;
    }

    public static void setUser_connected(User user_con)
    {
        user_connected = user_con;
    }

    protected static <T extends ObservableList<T>> ObservableList<T> GetList(List<T> list)
    {
        ObservableList<T> data = FXCollections.observableArrayList();
        data.addAll(list);
        return data;
    }

}
