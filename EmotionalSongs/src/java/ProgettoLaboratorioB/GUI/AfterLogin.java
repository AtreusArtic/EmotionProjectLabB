package ProgettoLaboratorioB.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class AfterLogin extends MenuManager {

    @FXML
    private Button logout;

    @FXML
    private Text welcome_field;

    public AfterLogin()
    {
        welcome_field = new Text();
        welcome_field.setText("Welcome "+ MenuManager.getUser_connected().GetUsername());
    }
    @FXML
    void userLogOut(ActionEvent event) throws IOException {
        ManagerGUI m = new ManagerGUI();
        m.changeScene("FirstMenu.fxml");
    }

}
