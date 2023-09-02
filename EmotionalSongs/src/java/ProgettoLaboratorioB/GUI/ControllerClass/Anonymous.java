package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Anonymous extends MenuManager implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private Button emotion_btn;

    @FXML
    private Button sign_in_btn;

    @FXML
    private Button src_authoryear_btn;

    @FXML
    private Button src_title_btn;

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        m.changeScene("Filexml/FirstMenu.fxml");
    }

    @FXML
    void openEmotionMenu(ActionEvent event) throws IOException {
        m.changeScene("Filexml/EmotionMenu.fxml");
    }

    @FXML
    void openRegisterMenu(ActionEvent event) throws IOException {
        m.changeScene("Filexml/RegistrationMenu.fxml");
    }

    @FXML
    void searchSongAuthorYear(ActionEvent event) throws IOException {
        m.changeScene("Filexml/SearchYearAuthor.fxml");
    }

    @FXML
    void searchSongTitle(ActionEvent event) throws IOException {
        m.changeScene("Filexml/SearchTitle.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Nothing to do here...
    }
}
