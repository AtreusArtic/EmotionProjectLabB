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
import java.util.Optional;
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

        conf_newEmo_btn.setDisable(true);
        emotion_registered = null;
    }

    @FXML
    void getSong(ActionEvent event)
    {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        song_selected =  (Song) stage.getUserData();
        if(song_selected != null)
        {
            name_song_lbl.setText(song_selected.getTitle());
            conf_newEmo_btn.setDisable(false);
        }
        else
        {
            name_song_lbl.setText("NO SONG SELECTED!");
            conf_newEmo_btn.setDisable(true);
        }
    }
    @FXML
    void registerEmotion(ActionEvent event)
    {
        if(song_selected == null)
        {
            check_lbl.setText("SELECT A SONG!");
            return;

        }

        Map<Enums.EMOTION,Integer> mapper = new HashMap<>();

        mapper.put(Enums.EMOTION.AMAZEMENT, Optional.ofNullable(amaz_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.CALMNESS, Optional.ofNullable(calm_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.JOY, Optional.ofNullable(joy_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.NOSTALGIA, Optional.ofNullable(nost_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.POWER, Optional.ofNullable(pow_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.SADNESS, Optional.ofNullable(sad_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.SOLEMNITY, Optional.ofNullable(sole_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.TENDERNESS, Optional.ofNullable(tend_score_box.getValue()).orElse(0));
        mapper.put(Enums.EMOTION.TENSION, Optional.ofNullable(tens_score_box.getValue()).orElse(0));
        try {
            emotion_registered = new Emotions(song_selected.getID(),
                    clientService.GetUserConnected().GetUsername(),
                    "", mapper);
            if(clientService.RegisterNewEmotion(emotion_registered))
                check_lbl.setText("EMOTION REGISTERED!");
            else
                check_lbl.setText("ERROR!");
        } catch (Exception e) {
            check_lbl.setText("ERROR!");
        }

    }


    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");

    }

}
