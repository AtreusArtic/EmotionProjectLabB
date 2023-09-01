package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public Button show_Emotion_btn;
    @FXML
    public Button rec_Emotion_btn;

    @FXML
    public ListView<String> list_playlist;


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

    private Song song_selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<Song, String>("ID"));

        SetPlaylist();
    }

    List<Playlist> ply;
    public void SetPlaylist() {
        ply = clientService.GetUserPlaylists(MenuManager.getUser_connected());
        if (ply == null) {
            System.out.println("SYSTEM ERROR: ply is null!");
            return;
        }
        List<String> ply_names = new ArrayList<>();
        for (Playlist p : ply) {
            ply_names.add(p.GetPlaylistName());
        }

        list_playlist.getItems().addAll(ply_names);
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

        if (songs != null && !songs.isEmpty()) {
            UpdateTable(songs);

        } else if (songs != null && songs.isEmpty()) {
            wrongTitle_lbl.setText("No song found! Try Again.");
        } else {
            System.out.println("SYSTEM ERROR: songs list is null!");

        }
    }

    private void UpdateTable(List<Song> songs) {
        table.setItems(GetList(songs));
    }

    @FXML
    void turnBackToMenu() throws Exception {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        m.changeScene("Filexml/AfterLogin.fxml");
    }

    @FXML
    void selectSong(MouseEvent event) {
        if (event.getClickCount() == 2) {

             song_selected = new Song(table.getSelectionModel().getSelectedItem().getYear(),
                    table.getSelectionModel().getSelectedItem().getID(),
                    table.getSelectionModel().getSelectedItem().getArtist(),
                    table.getSelectionModel().getSelectedItem().getTitle());

            System.out.println("Song selected is: " + song_selected);
        }
    }

    @FXML
    void addSongPlaylist(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            String playlist_name = list_playlist.getSelectionModel().getSelectedItem();
            Playlist playlist = null;
            for(Playlist p : ply)
            {
                if(p.GetPlaylistName().equals(playlist_name))
                {
                    playlist = p;
                    break;
                }
            }
            if(playlist == null)
            {
                System.out.println("SYSTEM ERROR: playlist is null!");
                return;
            }
            if(song_selected == null)
            {
                System.out.println("SYSTEM ERROR: song_selected is null!");
                return;
            }
            if(clientService.AddSongToPlaylist(playlist.GetPlaylistID(), song_selected.getID()))
            {
                System.out.println("Song added to playlist!");
            }
            else
            {
                System.out.println("SYSTEM ERROR: song not added to playlist!");
            }
        }
    }
}
