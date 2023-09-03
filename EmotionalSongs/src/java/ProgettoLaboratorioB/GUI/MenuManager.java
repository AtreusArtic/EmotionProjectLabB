package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public abstract class MenuManager {

    protected ClientService clientService = new ClientService();
    protected ManagerGUI m = new ManagerGUI();

    protected static <T extends ObservableList<T>> ObservableList<T> GetList(List<T> list)
    {
        ObservableList<T> data = FXCollections.observableArrayList();
        data.addAll(list);
        return data;
    }

}
