package ProgettoLaboratorioB.GUI;

import javafx.fxml.FXML;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class AfterLogin {
    @FXML
    private Button logout;

    public void userLogOut(javafx.event.ActionEvent actionEvent) throws IOException {
        ManagerGUI m = new ManagerGUI();
        m.changeScene ( "FirstMenu.fxml");
    }
}
