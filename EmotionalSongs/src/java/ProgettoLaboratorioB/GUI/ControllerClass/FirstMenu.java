package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.ManagerGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FirstMenu {

    ManagerGUI m = new ManagerGUI();

    @FXML
    private Button anonButton;
    @FXML
    private Button exit;

    @FXML
    private Button logButton;

    @FXML
    private Button signInbutton;

    @FXML
    void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logAnonim(ActionEvent event) {
        //TODO implementare il cambio scena
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        m.changeScene("Filexml/LogInMenu.fxml");
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        m.changeScene("Filexml/RegistrationMenu.fxml");
    }

}

