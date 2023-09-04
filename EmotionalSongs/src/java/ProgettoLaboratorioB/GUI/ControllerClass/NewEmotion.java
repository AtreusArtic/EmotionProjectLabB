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
import javafx.scene.control.TextField;
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
    private ChoiceBox<String> amaz_score_box;
    @FXML
    private ChoiceBox<String> calm_score_box;
    @FXML
    private ChoiceBox<String> joy_score_box;
    @FXML
    private ChoiceBox<String> nost_score_box;
    @FXML
    private ChoiceBox<String> pow_score_box;
    @FXML
    private ChoiceBox<String> sad_score_box;
    @FXML
    private ChoiceBox<String> sole_score_box;
    @FXML
    private ChoiceBox<String> tend_score_box;
    @FXML
    private ChoiceBox<String> tens_score_box;

    @FXML
    private TextField amaz_desc_box;
    @FXML
    private TextField calm_desc_box;
    @FXML
    private TextField joy_desc_box;
    @FXML
    private TextField nost_desc_box;
    @FXML
    private TextField pow_desc_box;
    @FXML
    private TextField sad_desc_box;
    @FXML
    private TextField sole_desc_box;
    @FXML
    private TextField tend_desc_box;
    @FXML
    private TextField tens_desc_box;


    @FXML
    private Label name_song_lbl;
    @FXML
    private Label check_lbl;

    private Emotions emotion_registered;
    private Song song_selected;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amaz_score_box.getItems().addAll("1", "2", "3", "4", "5");
        calm_score_box.getItems().addAll("1", "2", "3", "4", "5");
        joy_score_box.getItems().addAll("1", "2", "3", "4", "5");
        nost_score_box.getItems().addAll("1", "2", "3", "4", "5");
        pow_score_box.getItems().addAll("1", "2", "3", "4", "5");
        sad_score_box.getItems().addAll("1", "2", "3", "4", "5");
        sole_score_box.getItems().addAll("1", "2", "3", "4", "5");
        tend_score_box.getItems().addAll("1", "2", "3", "4", "5");
        tens_score_box.getItems().addAll("1", "2", "3", "4", "5");

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

        Map<Enums.EMOTION,String> mapper = new HashMap<>();
        Map<Enums.EMOTION,String> mapper2 = new HashMap<>();

        mapper.put(Enums.EMOTION.AMAZEMENT, Optional.ofNullable(amaz_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.CALMNESS, Optional.ofNullable(calm_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.JOY, Optional.ofNullable(joy_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.NOSTALGIA, Optional.ofNullable(nost_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.POWER, Optional.ofNullable(pow_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.SADNESS, Optional.ofNullable(sad_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.SOLEMNITY, Optional.ofNullable(sole_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.TENDERNESS, Optional.ofNullable(tend_score_box.getValue()).orElse(""));
        mapper.put(Enums.EMOTION.TENSION, Optional.ofNullable(tens_score_box.getValue()).orElse(""));

        mapper2.put(Enums.EMOTION.AMAZEMENT, Optional.ofNullable(amaz_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.CALMNESS, Optional.ofNullable(calm_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.JOY, Optional.ofNullable(joy_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.NOSTALGIA, Optional.ofNullable(nost_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.POWER, Optional.ofNullable(pow_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.SADNESS, Optional.ofNullable(sad_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.SOLEMNITY, Optional.ofNullable(sole_desc_box.getText()).orElse(""));
        mapper2.put(Enums.EMOTION.TENDERNESS, Optional.ofNullable(tend_desc_box.getText()).orElse(""));

        try {
            emotion_registered = new Emotions(song_selected.getID(),
                    clientService.GetUserConnected().GetUsername(),
                    mapper, mapper2);
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
