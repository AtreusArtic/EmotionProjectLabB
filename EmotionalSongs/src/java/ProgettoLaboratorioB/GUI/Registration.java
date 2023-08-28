package ProgettoLaboratorioB.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Registration {
    ManagerGUI m = new ManagerGUI();

    @FXML
    private TextField CF;

    @FXML
    private TextField address;

    @FXML
    private Button backTofirst;

    @FXML
    private TextField cap;

    @FXML
    private TextField city;

    @FXML
    private Button confbutton;

    @FXML
    private TextField district;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField province;

    @FXML
    private TextField streetNumber;

    @FXML
    private TextField surname;

    @FXML
    private TextField username;


    void backtoFirst(ActionEvent actionEvent) throws IOException {
        m.changeScene("FirstMenu.fxml");
    }

    void confReg() throws IOException {
        m.changeScene("LoginMenu.fxml");
    }





}
