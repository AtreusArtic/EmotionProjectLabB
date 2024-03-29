package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LogIn extends MenuManager {
    public Label wrong_Log_lbl;
    @FXML
    private Button conbutton;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;


    @FXML
    void userLog(ActionEvent event) throws IOException, SQLException {
        checkLogIn();
    }
    @FXML
    void backFirst(ActionEvent event) throws IOException{
        m.changeScene("Filexml/FirstMenu.fxml");
    }

    private void checkLogIn() throws IOException, SQLException {
        String username = this.username.getText();
        String password = this.password.getText();
        User user_logged = clientService.Login(username, password);
        if(user_logged != null)
        {

            conbutton.setText("Success!");
            m.changeScene("Filexml/AfterLogin.fxml");
        }
        else
        {
            wrong_Log_lbl.setText("Wrong username or password!");
        }
    }

}



