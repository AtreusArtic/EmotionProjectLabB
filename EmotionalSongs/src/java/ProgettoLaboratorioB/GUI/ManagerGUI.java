package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Database.QueryModule;
import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstMenu.fxml"));
        FXMLLoader fxml_Loader = new FXMLLoader(getClass().getResource("AnonymousMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Parent root1 = (Parent) fxml_Loader.load();
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(root), 640, 480);
        stage.setTitle("Emotional Songs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
