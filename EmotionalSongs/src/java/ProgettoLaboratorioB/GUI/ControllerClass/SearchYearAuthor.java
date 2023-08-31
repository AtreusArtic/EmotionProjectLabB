package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchYearAuthor extends MenuManager implements Initializable {
    @FXML
    public Button search_Year_Author_btn;
    @FXML
    public TextField year_song_lbl;
    @FXML
    public TextField author_song_lbl;
    @FXML
    public Label wrongTitle_lbl;
    @FXML
    public Button backAfterMenu_btn;

    @FXML
    private TableView<Song> table = new TableView<Song>();
    @FXML
    private TableColumn<Song, String> authorCol;
    @FXML
    private TableColumn<Song, Integer> yearCol;
    @FXML
    private TableColumn<Song, String> titleCol;
    @FXML
    private TableColumn<Song, String> idCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        authorCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<Song, String>("ID"));
    }

    @FXML
    void searchSong() throws SQLException {
        System.out.println("GUI ADVERTISE: Search song button clicked!");
        GoSearch();
    }

    private void GoSearch() throws SQLException {
        String year = year_song_lbl.getText();
        String author = author_song_lbl.getText();

        List<Song> songs = clientService.SearchSongByYearTitle(year, author);

        if(songs != null && !songs.isEmpty())
        {
            UpdateTable(songs);

        }
        else if(songs != null && songs.isEmpty())
        {
            wrongTitle_lbl.setText("No song found!");
        }
        else
        {
            System.out.println("SYSTEM ERROR: songs list is null!");

        }
    }

    private void UpdateTable(List<Song> songs)
    {
        table.setItems(GetList(songs));
    }

    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");
    }
}
