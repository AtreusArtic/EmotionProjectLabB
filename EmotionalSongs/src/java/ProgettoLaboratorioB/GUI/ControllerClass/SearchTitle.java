package ProgettoLaboratorioB.GUI.ControllerClass;

import ProgettoLaboratorioB.Client.ClientService;
import ProgettoLaboratorioB.Serializables.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;

public class SearchTitle {

    ClientService clientService = new ClientService();

    @FXML
    private TableColumn<?, ?> listSongs_table;

    @FXML
    private Button search_btn;

    @FXML
    private TextField search_title_lbl;

    @FXML
    void searchSong(ActionEvent event) throws SQLException {
        GoSearch();
    }

    private void GoSearch() throws SQLException {
        String title = search_title_lbl.getText();

        List<Song> songs =  clientService.SearchSongByTitle(title);

        if(songs != null)
        {
            for(Song song : songs)
            {
                listSongs_table.setText(song.toString());
            }
        }
        else
        {
            System.out.println("No songs found!");
        }

    }

}


