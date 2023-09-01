package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
/*import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;*/
import java.util.ResourceBundle;

public class NewEmotion extends MenuManager implements Initializable{
    public Button back_Menu_btn;
    public Button conf_newEmo_btn;
    @FXML
    private ChoiceBox<Integer> amaz_score_box;
    @FXML
    private ChoiceBox<Integer> calm_score_box;
    @FXML
    private ChoiceBox<Integer> joy_score_box;
    @FXML
    private ChoiceBox<Integer> nost_score_box;
    @FXML
    private ChoiceBox<Integer> pow_score_box;
    @FXML
    private ChoiceBox<Integer> sad_score_box;
    @FXML
    private ChoiceBox<Integer> sole_score_box;
    @FXML
    private ChoiceBox<Integer> tend_score_box;
    @FXML
    private ChoiceBox<Integer> tens_score_box;
    @FXML
    private Label name_song_lbl;
    @FXML
    private Label check_lbl;

    //List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amaz_score_box.getItems().addAll(1, 2, 3, 4, 5);
        calm_score_box.getItems().addAll(1, 2, 3, 4, 5);
        joy_score_box.getItems().addAll(1, 2, 3, 4,5 );
        nost_score_box.getItems().addAll(1, 2, 3, 4, 5);
        pow_score_box.getItems().addAll(1, 2, 3, 4, 5);
        sad_score_box.getItems().addAll(1, 2, 3, 4, 5);
        sole_score_box.getItems().addAll(1, 2, 3, 4, 5);
        tend_score_box.getItems().addAll(1, 2, 3, 4, 5);
        tens_score_box.getItems().addAll(1, 2, 3, 4, 5);
    }


    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");

    }

}
