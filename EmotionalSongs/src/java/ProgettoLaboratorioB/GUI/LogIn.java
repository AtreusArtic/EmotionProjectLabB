package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LogIn extends MenuManager{
    private static ClientService clientService = new ClientService();
    private ManagerGUI m = new ManagerGUI();
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
        m.changeScene("FirstMenu.fxml");
    }

    private void checkLogIn() throws IOException, SQLException {
        String username = this.username.getText();
        String password = this.password.getText();
        User user_logged = clientService.Login(username, password);
        if(user_logged != null)
        {
            MenuManager.setUser_connected(user_logged);
            conbutton.setText("Success!");
            m.changeScene("AfterLogin.fxml");
        }
        else
        {
            conbutton.setText("Wrong username or password!");
        }
    }

}



