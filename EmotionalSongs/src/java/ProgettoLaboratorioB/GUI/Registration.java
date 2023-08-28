package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.User;
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

    private void checkRegUser() throws IOException {
        Boolean isEmailCorrect = false;

        Boolean isCodFiscCorrect = false;

        String name_str = name.getText();

        String surname_str = surname.getText();

        String CF_str = CF.getText();
        while(!isCodFiscCorrect){
            User.CodFiscalePattern(CF_str);
        }

        String email_str = email.getText();
        while(!isEmailCorrect){
            User.EmailPattern(email_str);
        }
        String address_str = address.getText();

        String username_str = username.getText();

        String password_str = password.getText();

        User u = new User(username_str, password_str,  email_str, name_str, surname_str, address_str,  CF_str);

        if(ClientService.RegisterNewUser(u)){
            m.changeScene("AfterLogin.fxml");
        }
    }





}
