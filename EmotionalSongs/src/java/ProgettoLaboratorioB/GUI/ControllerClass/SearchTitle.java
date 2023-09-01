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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchTitle extends MenuManager implements Initializable {

    public Button backAfterMenu_btn;
    //Table song list properties:
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

    //Playlist properties:
    @FXML
    private ListView<String> list_playlist = new ListView<String>();

    private Song song_selected;

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
        song_selected = null;
        list_playlist.setDisable(true);
        SetPlaylist();
    }

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
        if (event.getClickCount() == 1) {

            song_selected = new Song(table.getSelectionModel().getSelectedItem().getYear(),
                    table.getSelectionModel().getSelectedItem().getID(),
                    table.getSelectionModel().getSelectedItem().getArtist(),
                    table.getSelectionModel().getSelectedItem().getTitle());
            if(song_selected != null )
                UpdateListView();
            System.out.println("Song selected is: " + song_selected);
        }
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
    void addSongPlaylist(MouseEvent event)
    {
        if(event.getClickCount() == 1)
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

    public void UpdateListView()
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


