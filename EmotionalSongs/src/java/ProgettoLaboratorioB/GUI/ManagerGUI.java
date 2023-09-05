package ProgettoLaboratorioB.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is the main class of the GUI package,
 * it is used to start the GUI using the JavaFX library, and Scene Builder as generator of the FXML files.
 * It contains the method to change the scene of the GUI, every time an event is triggered.
 *
 * @Author Marika Scalise, Enrico Artese.
 * @version 0.0.1
 */
public class ManagerGUI extends Application{
    private static Stage stg;


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
