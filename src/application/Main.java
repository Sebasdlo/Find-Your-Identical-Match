package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * La clase Main es la clase principal que inicia la aplicación JavaFX y muestra la ventana principal del juego.
 */
public class Main extends Application {

    /**
     * El método start se llama cuando se inicia la aplicación y muestra la ventana principal del juego.
     *
     * @param stage el objeto Stage utilizado para mostrar la ventana principal.
     * @throws IOException si ocurre un error al cargar el archivo FXML de la ventana principal.
     */
    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("VentanaMenu.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setTitle("Memory Game");
        stage.setScene(new Scene(root));

        stage.show(); // IMPORTANT
    }

    /**
     * El método main es el punto de entrada de la aplicación.
     *
     * @param args los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
