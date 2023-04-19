package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Database.Database;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import javafx.scene.Parent;

/**
 * With this class define the methods to create the GUI of the manager.
 * IMPO: queste keyword sotto riportate, sono solo d'esempio illustrativo per Marika e Sam
 * @link https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
 * @see Database to see information about the database.
 */
public class ManagerGUI extends Application{
    /**
     * Create the GUI of the manager.
     */

    public static void main(String[] args) {
        launch(args);
    }
    public void ManagerGUI() {

        // create the GUI of the manager
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader ();
        URL xmlURL = getClass().getResource("/javaFX/ProgettoLaboratorioB.GUI/FirstMenu");

        loader.setLocation(xmlURL);

        Parent root= loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("prova");

        stage.centerOnScreen();

        //per mettere l'icona del menu, inserire il percorso
        //InputStream icon = getClass().getResourceAsStream("")

        //Image image = new Image(icon);
        //stage.getIcons().add(image);
        stage.show();
        //becomeClient();
    }
}
