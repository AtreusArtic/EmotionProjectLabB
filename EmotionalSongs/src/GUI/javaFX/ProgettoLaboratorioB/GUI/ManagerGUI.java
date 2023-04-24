package ProgettoLaboratorioB.GUI;

import ProgettoLaboratorioB.Database.Database;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
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
    @Override
    public void start(Stage stage) throws Exception {
        /*FXMLLoader loader = new FXMLLoader ();
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
        //becomeClient();*/

        Menu fileMenu = new Menu("file");
        Menu nuovoMenu = new Menu("Nuovo utente");
        Menu aperturaMenu = new Menu("Apri profilo");
        Menu nonRegistrato = new Menu("Menu non registrato");
        Menu chiusuraMenu = new Menu("Chiusura Menu");
        fileMenu.getItems().addAll(nuovoMenu, aperturaMenu, nonRegistrato, chiusuraMenu);

        Menu aiuto = new Menu("autori");
        Menu infoAutori = new Menu("autori: ");
        aiuto.getItems().addAll(infoAutori);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, aiuto);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);

        Scene prova = new Scene(layout, 300, 500);
        stage.setTitle("menù principale");
        stage.setScene(prova);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
