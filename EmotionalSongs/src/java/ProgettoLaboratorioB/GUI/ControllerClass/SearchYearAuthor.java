package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchYearAuthor extends MenuManager implements Initializable {
    @FXML
    public Button search_Year_Author_btn;
    @FXML
    public TextField search_title_lbl;
    @FXML
    public Label wrongTitle_lbl;
    @FXML
    public Button backAfterMenu_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO implementare l'inizializzazione della table tree view, vedi classe SearchTitle.java

    }
}
