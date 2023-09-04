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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RecordedEmotions extends MenuManager implements Initializable {
    @FXML
    public Label sole_Score_lbl;
    @FXML
    public Label amaz_Score_lbl;
    @FXML
    public Label tend_Score_lbl;
    @FXML
    public Label nost_Score_lbl;
    @FXML
    public Label calm_Score_lbl;
    @FXML
    public Label pow_Score_lbl;
    @FXML
    public Label joy_Score_lbl;
    @FXML
    public Label tens_Score_lbl;
    @FXML
    public Label sad_Score_lbl;
    @FXML
    public Label amaz_Note_lbl;
    @FXML
    public Label sole_Note_lbl;
    @FXML
    public Label tend_Note_lbl;
    @FXML
    public Label nost_Note_lbl;
    @FXML
    public Label calm_Note_lbl;
    @FXML
    public Label joy_Note_lbl;
    @FXML
    public Label pow_Note_lbl;
    @FXML
    public Label tens_Note_lbl;
    @FXML
    public Label sad_Note_lbl;
    @FXML
    public Button back_Menu_btn;
    @FXML
    public Button back_btn;
    @FXML
    public Button forward_btn;
    @FXML
    public Label sole_Tot_lbl;
    @FXML
    public Label tend_Tot_lbl;
    @FXML
    public Label nost_Tot_lbl;
    @FXML
    public Label calm_Tot_lbl;
    @FXML
    public Label pow_Tot_lbl;
    @FXML
    public Label joy_Tot_lbl;
    @FXML
    public Label ten_Tot_lbl;
    @FXML
    public Label sad_Tot_lbl;
    @FXML
    public Label amaz_Users_lbl;
    @FXML
    public Label sole_Users_lbl;
    @FXML
    public Label tend_Users_lbl;
    @FXML
    public Label nost_Users_lbl;
    @FXML
    public Label calm_Users_lbl;
    @FXML
    public Label pow_Users_lbl;
    @FXML
    public Label joy_Users_lbl;
    @FXML
    public Label ten_Users_lbl;
    @FXML
    public Label sad_Users_lbl;
    @FXML
    public Label name_Song_lbl;
    @FXML
    public Button get_Song_btn;
    @FXML
    public Label username_lbl;

    private Song song_selected;

    private List<Emotions> emotionsList;

    int global_index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        song_selected = null;
        emotionsList = null;
        global_index = -1;
    }

    @FXML
    void getSong(ActionEvent event) throws RemoteException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        song_selected = (Song) stage.getUserData();

        if(song_selected != null)
        {
            GetEmotionList(song_selected);
            name_Song_lbl.setText(song_selected.getTitle());
        }
        else
        {
            name_Song_lbl.setText("NULL!");
        }
    }


    private void GetEmotionList(Song song) throws RemoteException {
        emotionsList = clientService.GetEmotionsFromSong(song.getID());

        if(emotionsList != null)
        {
            //Load first emotion on gridPanel;
            initMainGridPanel(++global_index);

            //Load Emotion total scoring...
            initTotalGridPanel();
        }
    }


    private void initMainGridPanel(int index)
    {
        if(index < -1)
            index = -1;

        Emotions emotion_selected = null;
        try
        {
            emotion_selected = emotionsList.get(index);
        }
        catch (IndexOutOfBoundsException e )
        {

            emotion_selected = emotionsList.get(0);
            global_index = -1;
        }

        if(emotion_selected == null)
            username_lbl.setText("ERROR!");

        Map<Enums.EMOTION, String> map_emotion = emotion_selected.GetEvaluateEmotion();
        Map<Enums.EMOTION, String> map_description = emotion_selected.GetEmotionDescription();

        sad_Note_lbl.setText(map_description.get(Enums.EMOTION.SADNESS));
        sad_Score_lbl.setText(map_emotion.get(Enums.EMOTION.SADNESS));

        amaz_Note_lbl.setText(map_description.get(Enums.EMOTION.AMAZEMENT));
        amaz_Score_lbl.setText(map_emotion.get(Enums.EMOTION.AMAZEMENT));

        tend_Note_lbl.setText(map_description.get(Enums.EMOTION.TENDERNESS));
        tend_Score_lbl.setText(map_emotion.get(Enums.EMOTION.TENDERNESS));

        tens_Note_lbl.setText(map_description.get(Enums.EMOTION.TENSION));
        tens_Score_lbl.setText(map_emotion.get(Enums.EMOTION.TENSION));

        sole_Note_lbl.setText(map_description.get(Enums.EMOTION.SOLEMNITY));
        sole_Score_lbl.setText(map_emotion.get(Enums.EMOTION.SOLEMNITY));

        pow_Note_lbl.setText(map_description.get(Enums.EMOTION.POWER));
        pow_Score_lbl.setText(map_emotion.get(Enums.EMOTION.POWER));

        nost_Note_lbl.setText(map_description.get(Enums.EMOTION.NOSTALGIA));
        nost_Score_lbl.setText(map_emotion.get(Enums.EMOTION.NOSTALGIA));

        joy_Note_lbl.setText(map_description.get(Enums.EMOTION.JOY));
        joy_Score_lbl.setText(map_emotion.get(Enums.EMOTION.JOY));

        calm_Note_lbl.setText(map_description.get(Enums.EMOTION.CALMNESS));
        calm_Score_lbl.setText(map_emotion.get(Enums.EMOTION.CALMNESS));
    }

    @FXML
    void goNextEmotion(ActionEvent event)
    {
        initMainGridPanel(++global_index);
    }

    @FXML
    void goPrevEmotion(ActionEvent event)
    {
        initMainGridPanel(--global_index);
    }

    private void initTotalGridPanel()
    {
        if(emotionsList == null)
            return;



    }





    @FXML
    void backToUserMenu(ActionEvent event) throws Exception
    {
        if(clientService.GetUserConnected() != null)
            m.changeScene("Filexml/AfterLogin.fxml");
        else
            m.changeScene("Filexml/AnonymousMenu.fxml");
    }

}
