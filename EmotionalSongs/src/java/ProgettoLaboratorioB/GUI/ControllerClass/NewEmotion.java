package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Emotions;
import ProgettoLaboratorioB.Serializables.Song;
import ProgettoLaboratorioB.main.Enums;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

    private Emotions emotion_registered;
    private Song song_selected;
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

        emotion_registered = null;
        System.out.println("HELLO");
    }

    @FXML
    void getSong(ActionEvent event)
    {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        song_selected =  (Song) stage.getUserData();
        System.out.println("GUI NEW EMOTION: Song selected: " + song_selected);
    }
    @FXML
    void registerEmotion()
    {
        if(song_selected == null)
        {
            return;
        }

        Map<Enums.EMOTION,Integer> mapper = new HashMap<>();
        //Get values from choice boxes:
        int amaz_score = amaz_score_box.getValue();
        int calm_score = calm_score_box.getValue();
        int joy_score = joy_score_box.getValue();
        int nost_score = nost_score_box.getValue();
        int pow_score = pow_score_box.getValue();
        int sad_score = sad_score_box.getValue();
        int sole_score = sole_score_box.getValue();
        int tend_score = tend_score_box.getValue();
        int tens_score = tens_score_box.getValue();

        //for EMOTION enum, put the value of the choice box in the mapper:
        mapper.put(Enums.EMOTION.AMAZEMENT,amaz_score);
        mapper.put(Enums.EMOTION.CALMNESS,calm_score);
        mapper.put(Enums.EMOTION.JOY,joy_score);
        mapper.put(Enums.EMOTION.NOSTALGIA,nost_score);
        mapper.put(Enums.EMOTION.POWER,pow_score);
        mapper.put(Enums.EMOTION.SADNESS,sad_score);
        mapper.put(Enums.EMOTION.SOLEMNITY,sole_score);
        mapper.put(Enums.EMOTION.TENDERNESS,tend_score);
        mapper.put(Enums.EMOTION.TENSION,tens_score);

        //Create a new emotion obj:
        emotion_registered = new Emotions(song_selected.getID(),
                clientService.GetUserConnected().GetUsername(),
                "Bella canzone", mapper);

    }


    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");

    }

}
