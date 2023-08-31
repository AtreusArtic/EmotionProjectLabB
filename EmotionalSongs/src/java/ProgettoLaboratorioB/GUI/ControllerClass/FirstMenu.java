package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstMenu extends MenuManager implements Initializable{

    @FXML
    public Label client_lbl;
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
    void logAnonim(ActionEvent event) throws IOException {
        m.changeScene("Filexml/AnonymousMenu.fxml");
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        m.changeScene("Filexml/LogInMenu.fxml");
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        m.changeScene("Filexml/RegistrationMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(clientService.CheckConnection())
        {
            logButton.setDisable(false);
            signInbutton.setDisable(false);
            anonButton.setDisable(false);
        }
        else
        {
            logButton.setDisable(true);
            signInbutton.setDisable(true);
            anonButton.setDisable(true);
            client_lbl.setText("Server offline. Try later.");
        }
    }
}

