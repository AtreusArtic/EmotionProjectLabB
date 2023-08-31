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
    public TextField search_title_lbl;
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
        authorCol.setCellValueFactory(new PropertyValueFactory<Song, String>("author"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<Song, String>("id"));
    }

    @FXML
    void searchSong() throws SQLException {
        System.out.println("GUI ADVERTISE: Search song button clicked!");
        GoSearch();
    }

    private void GoSearch() throws SQLException {
        String year = search_title_lbl.getText();
        String author = search_title_lbl.getText();

        List<Song> songs = clientService.SearchSongByYearTitle(year, author);

        if(songs != null)
        {
            UpdateTable(songs);

        }
        else
        {
            wrongTitle_lbl.setText("No song found!");
        }
    }

    private void UpdateTable(List<Song> songs)
    {
        table.setItems(GetList(songs));
    }
}
