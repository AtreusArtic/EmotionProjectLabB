package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Database.QueryModule;
import ProgettoLaboratorioB.Serializables.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * With this class define the methods to create the GUI of the manager.
 * IMPO: queste keyword sotto riportate, sono solo d'esempio illustrativo per Marika e Sam
 * @link https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
 * @see QueryModule to see information about the database.
 */
public class ManagerGUI extends Application{
    private static Stage stg;

    public static User user = null;

    @Override
    public void start(Stage stage) throws Exception{
        stg = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Filexml/FirstMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(new StackPane(root), 1050, 850);
        stage.setTitle("Welcome to Emotional Songs!");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
