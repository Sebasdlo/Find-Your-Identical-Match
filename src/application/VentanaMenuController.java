package application;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La clase VentanaMenuController es el controlador de la ventana del menú principal del juego.
 */
public class VentanaMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * El método cambio_escena_MENU se llama cuando se hace clic en el botón para volver al menú principal.
     *
     * @param event el evento del botón.
     * @throws IOException si ocurre un error al cargar el archivo FXML del menú principal.
     */
    public void cambio_escena_MENU(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VentanaMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El método cambio_escena_Juego se llama cuando se hace clic en el botón para iniciar el juego.
     *
     * @param event el evento del botón.
     * @throws IOException si ocurre un error al cargar el archivo FXML del juego.
     */
    public void cambio_escena_Juego(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VentanaJuego.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El método cambio_escena_Ganador se llama cuando se hace clic en el botón para mostrar la ventana de ganador.
     *
     * @param event el evento del botón.
     */
    public void cambio_escena_Ganador(ActionEvent event) {
        // Aquí puedes agregar la lógica para mostrar la ventana de ganador
    }

    /**
     * El método setStage establece el objeto Stage actual para el controlador.
     *
     * @param stage el objeto Stage actual.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
