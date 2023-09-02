package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.GUI.MenuManager;
import ProgettoLaboratorioB.Serializables.Playlist;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    public Button add_song_btn;
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
    private Playlist playlist_selected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorCol.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<Song, String>("ID"));

        song_selected = null;
        playlist_selected = null;

        list_playlist.setDisable(true);
        add_song_btn.setDisable(true);

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
    void turnBackToMenu() throws IOException {
        System.out.println("GUI ADVERTISE: Back to menu button clicked!");
        try {
            m.changeScene("Filexml/AfterLogin.fxml");
        } catch (IOException e) {
            m.changeScene("Filexml/AnonymousMenu.fxml");
        }
    }

    @FXML
    void selectSong(MouseEvent event) {
        if(clientService.GetUserConnected() == null)
        {
            return;
        }
        if (event.getClickCount() == 1) {

             song_selected = new Song(table.getSelectionModel().getSelectedItem().getYear(),
                    table.getSelectionModel().getSelectedItem().getID(),
                    table.getSelectionModel().getSelectedItem().getArtist(),
                    table.getSelectionModel().getSelectedItem().getTitle());
            UpdateListView();
            System.out.println("Song selected is: " + song_selected);
        }
    }

    @FXML
    void selectPlaylist(MouseEvent event)
    {
        if(clientService.GetUserConnected() == null)
        {
            return;
        }
        if(event.getClickCount() == 1)
        {
            String playlist_name = list_playlist.getSelectionModel().getSelectedItem();
            playlist_selected = null;
            for(Playlist p : ply)
            {
                if(p.GetPlaylistName().equals(playlist_name))
                {
                    playlist_selected = p;
                    UpdateAddButtonView();
                    return;
                }
            }
            UpdateAddButtonView();
        }
    }

    @FXML
    void addSongToPlaylist(ActionEvent event)
    {
        if(clientService.GetUserConnected() == null)
        {
            return;
        }
        if(song_selected == null || playlist_selected == null)
        {
            System.out.println("SYSTEM ERROR: song_selected or playlist_selected is null!");
            return;
        }
        clientService.AddSongToPlaylist(playlist_selected.GetPlaylistID(), song_selected.getID());
    }


    private void UpdateAddButtonView()
    {
        if(song_selected != null && playlist_selected != null)
        {
            add_song_btn.setDisable(false);
        }
        else
        {
            add_song_btn.setDisable(true);
        }
    }

    private void UpdateListView()
    {
        if(list_playlist.isDisable())
        {
            list_playlist.setDisable(false);
        }
        else
        {
            list_playlist.setDisable(true);
        }
    }

}
