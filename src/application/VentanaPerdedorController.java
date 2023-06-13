package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * La clase VentanaPerdedorController es el controlador de la ventana mostrada cuando el jugador pierde en el juego.
 */
public class VentanaPerdedorController {
    @FXML
    private ImageView ImagenPerdedor;
    @FXML
    private Button Menu;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * El método cambio_escena_Perdedor se llama cuando se hace clic en el botón para mostrar la ventana de perdedor.
     *
     * @param event el evento del botón.
     * @throws IOException si ocurre un error al cargar el archivo FXML de la ventana de perdedor.
     */
    public void cambio_escena_Perdedor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VentanaPerdedor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
