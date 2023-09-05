package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * This class is the superclass of all the class controller of the GUI.
 * It contains a reference to the ClientService and to the ManagerGUI.
 * It also contains a method to convert a List to an ObservableList,
 * used to populate the TableView or similar.
 *
 * @author Marika Scalise, Enrico Artese
 * @version 0.0.1
 */
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
