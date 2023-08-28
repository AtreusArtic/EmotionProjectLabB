package ProgettoLaboratorioB.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class AfterLogin {

    @FXML
    private Button logout;

    @FXML
    void userLogOut(ActionEvent event) throws IOException {
        ManagerGUI m = new ManagerGUI();
        m.changeScene("FirstMenu.fxml");
    }

    void confReg(){

    }

}
