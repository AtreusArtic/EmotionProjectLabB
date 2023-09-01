package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Song_Playlist extends MenuManager implements Initializable {
    @FXML
    public Button rec_Emotion_btn;
    @FXML
    public Button del_Songply_btn;
    @FXML
    public Button show_Emotion_btn;
    @FXML
    public Button backButton;
    @FXML
    public TableColumn<Song, String> titleColumn;
    @FXML
    public TableColumn<Song, Integer> yearColumn;
    @FXML
    public TableColumn<Song, String> artistColumn;

    private Song song_selected;

    @FXML
    void turnBackToMenu(ActionEvent actionEvent) throws IOException {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        song_selected = null;

    }
}
