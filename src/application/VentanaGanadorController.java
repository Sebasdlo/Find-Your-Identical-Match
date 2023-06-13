package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La clase VentanaGanadorController es el controlador de la ventana de la pantalla de ganador del juego.
 */
public class VentanaGanadorController {
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
}
