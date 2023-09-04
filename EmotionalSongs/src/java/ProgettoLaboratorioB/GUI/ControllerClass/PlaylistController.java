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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaylistController extends MenuManager implements Initializable {

    public Label success_wrong_lbl;
    @FXML
    private Button create_Playlist_btn;

    @FXML
    private Button delete_Playlist_btn;

    @FXML
    private Button delete_Song_btn;

    @FXML
    private ListView<String> table;

    @FXML
    private TableView<Song> table_songs;
    @FXML
    private TableColumn<Song, String> titleColumn;
    @FXML
    private TableColumn<Song, Integer> yearColumn;
    @FXML
    private TableColumn<Song, String> artistColumn;

    @FXML
    private TextField name_to_add_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Song, Integer>("year"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));

        create_Playlist_btn.setDisable(true);
        delete_Playlist_btn.setDisable(true);
        
        SetPlaylistTable();
    }
    @FXML
    void add_Playlist(ActionEvent event)
    {
        String name = name_to_add_lbl.getText();
        if(clientService.CreateNewPlaylist(name, Integer.toString(Playlist.IDGenerator()))){
            SetPlaylistTable();
            success_wrong_lbl.setStyle("-fx-background-color: #008000");
            success_wrong_lbl.setText("Successfully added");
        }else{
            success_wrong_lbl.setText("Something go wrong. Try Again!");
        }
    }

    @FXML
    void delete_Playlist(ActionEvent event)
    {
        if(selected_playlist!= null)
        {
            clientService.DeletePlaylist(selected_playlist.GetPlaylistID());
            SetPlaylistTable();
            success_wrong_lbl.setStyle("-fx-background-color: #008000");
            success_wrong_lbl.setText("Successfully deleted");
        }
    }

    @FXML
    void enabledBtn(ActionEvent event)
    {
        if(name_to_add_lbl.getText().length() > 0)
        {
            create_Playlist_btn.setDisable(false);
        }
        else
        {
            create_Playlist_btn.setDisable(true);
        }
    }

    @FXML
    void deleteSong(ActionEvent event)
    {
        if(song_selected == null || selected_playlist == null)
        {
            success_wrong_lbl.setStyle("-fx-background-color: #DC143C");
            success_wrong_lbl.setText("Something go wrong. Try Again!");
            return;

        }
        if(clientService.RemoveSongFromPlaylist(selected_playlist.GetPlaylistID(), song_selected.getID()))
            initSongsTable(selected_playlist);
            success_wrong_lbl.setStyle("-fx-background-color: #008000");
            success_wrong_lbl.setText("Successfully deleted");

    }

    private Song song_selected = null;
    @FXML
    void selectSong(MouseEvent event)
    {
        if(clientService.GetUserConnected() == null)
        {
            return;
        }
        if (event.getClickCount() == 1) {

            song_selected = new Song(table_songs.getSelectionModel().getSelectedItem().getYear(),
                    table_songs.getSelectionModel().getSelectedItem().getID(),
                    table_songs.getSelectionModel().getSelectedItem().getArtist(),
                    table_songs.getSelectionModel().getSelectedItem().getTitle());
            UpdateAddButtonView();
            System.out.println("Song selected is: " + song_selected);
        }
    }

    private void UpdateAddButtonView() {
        if(delete_Song_btn.isDisable())
        {
            delete_Song_btn.setDisable(false);
        }
        else
        {
            delete_Song_btn.setDisable(true);
        }
    }

    List<Playlist> playlists;
    public void SetPlaylistTable()
    {
        if(table.getItems().size() > 0)
        {
            clearPlaylistTable();
        }
        playlists = clientService.GetUserPlaylists();
        if(playlists == null)
        {
            System.out.println("SYSTEM ERROR: playlists is null!");
            return;
        }

        List<String> ply_names = new ArrayList<>();
        for(Playlist p : playlists)
        {
            ply_names.add(p.GetPlaylistName());
        }

        table.getItems().addAll(ply_names);
    }


    Playlist selected_playlist;
    @FXML
    void selectPlaylist(MouseEvent event)
    {
        if(table_songs.getItems().size() > 0)
        {
            clearSongsTable();
        }

        String ply_name = "";
        if(event.getClickCount() == 1 )
        {
            ply_name = table.getSelectionModel().getSelectedItem();
        }

        Playlist playlist = null;
        for(Playlist p : playlists)
        {
            if(p.GetPlaylistName().equals(ply_name))
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
        selected_playlist = playlist;
        initSongsTable(playlist);
    }

    void clearSongsTable()
    {
        table_songs.getItems().clear();
    }
    void clearPlaylistTable()
    {
        table.getItems().clear();
    }

    void initSongsTable(Playlist playlist)
    {
        clearSongsTable();
        List<Song> songs = clientService.GetPlaylistSongs(playlist.GetPlaylistID());
        if(songs == null)
        {
            System.out.println("SYSTEM ERROR: songs is null!");
            return;
        }
        table_songs.getItems().addAll(songs);
        success_wrong_lbl.setStyle("-fx-background-color: #008000");
        success_wrong_lbl.setText("Successfully added");

    }

    public void EnableLblText()
    {
        if(create_Playlist_btn.isDisable())
        {
            create_Playlist_btn.setDisable(false);
        }
        else
        {
            create_Playlist_btn.setDisable(true);
        }
    }

    @FXML
    void backToUserMenu(ActionEvent event) throws Exception
    {
        m.changeScene("Filexml/AfterLogin.fxml");
    }
}




