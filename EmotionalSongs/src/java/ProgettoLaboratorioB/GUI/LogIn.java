package ProgettoLaboratorioB.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LogIn {
    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    private TextField username;
    private PasswordField password;

    //check if the user exist in the database
    //if the user not exist in the database "Your account doesn't exist"

    public void userLog(ActionEvent actionEvent) throws IOException {
        checkLogIn();
    }
    private void checkLogIn() throws IOException {
        ManagerGUI m = new ManagerGUI(); //ask leader if is correct
        if (username.getText().toString().equals(" javacoding") && password.getText().toString().equals ("123")){
            wrongLogIn.setText("Success!");
            m.changeScene("AfterLogin.fxml");
        } else if (username.getText().isEmpty() && password.getText ().isEmpty()) {
            wrongLogIn.setText("Please enter your data. ");
        } else {
            wrongLogIn.setText ("Wrong username or password!");
        }
    }
}
