package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LogIn {
    private static ClientService clientService = new ClientService();

    @FXML
    private Button conbutton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void userLog(ActionEvent event) throws IOException, SQLException {
        checkLogIn();
    }

    private void checkLogIn() throws IOException, SQLException {
        ManagerGUI m = new ManagerGUI(); //ask leader if is correct
        /*if (username.getText().toString().equals(" javacoding") && password.getText().toString().equals ("123")){
            conbutton.setText("Success!");
            m.changeScene("AfterLogin.fxml");
        } else if (username.getText().isEmpty() && password.getText ().isEmpty()) {
            conbutton.setText("Please enter your data. ");
        } else {
            conbutton.setText ("Wrong username or password!");
        }*/

        String username = this.username.getText();
        String password = this.password.getText();

        if(ClientService.Login(username, password))
        {
            conbutton.setText("Success!");
            m.changeScene("AfterLogin.fxml");
        }
        else
        {
            conbutton.setText("Wrong username or password!");
        }
    }

}



