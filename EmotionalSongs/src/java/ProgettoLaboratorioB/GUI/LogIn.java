package ProgettoLaboratorioB.GUI;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class LogIn {

    //check if the user exist in the database
    //if the user exit but he doesn't insert password or username, "please insert your data"
    //if the user not exist in the database "Your account doesn't exist"
    //if the user insert the username or password incorrect "wrong username or password"

    public void userLog(ActionEvent actionEvent) throws IOException {
        checkLogIn();
    }

    private void checkLogIn() {
        
    }
}
