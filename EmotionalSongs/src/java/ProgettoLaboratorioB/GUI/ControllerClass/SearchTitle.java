package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchTitle extends MenuManager implements Initializable {
    //Table properties:
    @FXML
    private TableView<Song> table = new TableView<Song>();
    @FXML
    private TableColumn<Song, String> titleColumn;
    @FXML
    private TableColumn<Song, Integer> yearColumn;
    @FXML
    private TableColumn<Song, String> artistColumn;
    @FXML
    private TableColumn<Song, String> songID;

    //Search function GUI properties:
    @FXML
    private Button search_btn;
    @FXML
    private Label wrongTitle_lbl;
    @FXML
    private TextField search_title_lbl;

    /**
     * This function is callback for the event "search_btn" click.
     * @param event is the event of the click on search btn.
     * And it calls the function GoSearch(), so it starts the search of the song by title.
     * @throws SQLException if the query SEARCH_SONG_BY_TITLE, from DatabaseModule class is wrong.
     */
    @FXML
    void searchSong(ActionEvent event) throws SQLException {
        System.out.println("GUI ADVERTISE: Search song button clicked!");
        GoSearch();
    }

    private void GoSearch() throws SQLException {
        String title = search_title_lbl.getText();

        List<Song> songs =  clientService.SearchSongByTitle(title);

        if(songs != null)
        {
            UpdateTable(songs);
        }
        else
        {
            System.out.println("No songs found!");
            wrongTitle_lbl.setText("Song not found! Try again.");
        }
    }

    // Declare a function that updates the table with the list of songs:
    private void UpdateTable(List<Song> songs) {
       table.setItems(GetList(songs));
    }

    /**
     * This function is called when the scene is loaded.
     * It initializes the table with the columns and the relative values,
     * in order to map between song properties and TableViewFX obj columns.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        songID.setCellValueFactory(new PropertyValueFactory<Song, String>("ID"));
    }

    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");
    }
}


