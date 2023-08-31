package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLogin extends MenuManager implements Initializable {

    @FXML
    private Button logout;

    @FXML
    private Text welcome_field;

    /**
     * This function is callback for the after login event,
     * it initializes the welcome field
     * with the username of the user connected.
    * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcome_field.setText("Welcome "+ MenuManager.getUser_connected().GetUsername());
    }
    @FXML
    void userLogOut(ActionEvent event) throws IOException {

        m.changeScene("Filexml/FirstMenu.fxml");
    }

    @FXML
    void openSearchView(ActionEvent event) throws IOException {
        m.changeScene("Filexml/SearchTitle.fxml");
    }

    @FXML
    void openSearchYearAuthorView(ActionEvent event) throws IOException {
        m.changeScene("Filexml/SearchYearAuthor.fxml");
    }


}
