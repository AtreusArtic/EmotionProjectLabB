package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Database.QueryModule;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * With this class define the methods to create the GUI of the manager.
 * IMPO: queste keyword sotto riportate, sono solo d'esempio illustrativo per Marika e Sam
 * @link https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
 * @see QueryModule to see information about the database.
 */
public class ManagerGUI extends Application{
    /**
     * Create the GUI of the manager.
     */
    @Override
    public void start(Stage stage){
        try {
            System.out.println("sta caricando");
            Parent root = FXMLLoader.load(getClass().getResource("EmotionalSongs/src/java//Users/marikascalise/Documents/GitHub/EmotionProjectLabB/EmotionalSongs/src/java/ProgettoLaboratorioB/GUI/FirstMenu.fxml"));
            System.out.println("caricato");
            Scene scene = new Scene(root);
            //stage.setTitle("Emotional Songs");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
