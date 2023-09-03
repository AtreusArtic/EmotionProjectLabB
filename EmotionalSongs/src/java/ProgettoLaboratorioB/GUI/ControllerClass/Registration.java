package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.GUI.ManagerGUI;
import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Registration extends MenuManager {

    private ManagerGUI m = new ManagerGUI();
    private ClientService clientService = new ClientService();

    @FXML
    private TextField CF;
    @FXML
    public Label reg_unsuc_lbl;
    @FXML
    public TextField password;
    @FXML
    public TextField username;
    @FXML
    private TextField address;
    @FXML
    private Button backTofirst;
    @FXML
    private Button confbutton;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;



    @FXML
    void backToFirst(ActionEvent event) throws IOException {
        m.changeScene("Filexml/FirstMenu.fxml");
    }
    @FXML
    void confRegistration(ActionEvent event) throws IOException {
        Boolean isEmailCorrect = false;
        Boolean isCodFiscCorrect = false;
        int counter = 0;

        String name_str = name.getText();

        String surname_str = surname.getText();

        String CF_str = CF.getText();
        while(!isCodFiscCorrect && counter <= 3)
        {
            if(User.CodFiscalePattern(CF_str))
                isCodFiscCorrect = true;
            else
            {
                CF.setText("Codice Fiscale non valido!");
                counter++;
            }
        }
        counter = 0;

        String email_str = email.getText();
        while(!isEmailCorrect && counter <= 3)
        {
            if(User.EmailPattern(email_str))
                isEmailCorrect = true;
            else
            {
                email.setText("Email non valida!");
                counter++;
            }
        }
        counter = 0;
        String address_str = address.getText();

        String username_str = username.getText();

        String password_str = password.getText();

        User u = new User(username_str, password_str,  email_str, name_str, surname_str, address_str,  CF_str);

        if(clientService.RegisterNewUser(u)){
            clientService.SetUserConnected(u);
            MenuManager.setUser_connected(u);
            m.changeScene("Filexml/AfterLogin.fxml");

        }
        else
        {
            reg_unsuc_lbl.setText("Something go wrong. Try again!!");
        }
    }
}

