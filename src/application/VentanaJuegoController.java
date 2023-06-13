package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class VentanaJuegoController {
    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private HBox menuPanel;
    @FXML
    private Button start;
    @FXML
    private Label tiempoLabel;
    @FXML
    private Label puntosLabel;

    @FXML
    private GridPane gameMatrix;
    private Stage currentStage; // Variable para almacenar la instancia de la ventana actual
    private Stage perdedorStage; // Variable para almacenar la instancia de la ventana de perdedor
    private Stage ganadorStage; // Variable para almacenar la instancia de la ventana de ganador

    private Juntar_Imagenes Juntar_Imagenes = new Juntar_Imagenes();
    private Celdas firstCard = null;
    private Celdas secondCard = null;

    private int tiempoRestante = 420; // 420 segundos = 7 minutos
    private Timer timer;
    private int puntos = 0;
    private int intentos = 0;

    /**
     * Inicializa el controlador después de que su elemento raíz haya sido completamente procesado.
     *
     * @throws FileNotFoundException si no se encuentra el archivo de imagen.
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        Juntar_Imagenes.populateMatrix();

        iniciarContadorTiempo();

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                String imagePath = "logo1.jpg";
                Image image = loadImage(imagePath);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(90);
                imageView.setFitHeight(90);

                CardInfo cardInfo = new CardInfo(row, col);
                imageView.getProperties().put("cardInfo", cardInfo);

                imageView.setOnMouseClicked(event -> {
                    try {
                        cardListener(event);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                gameMatrix.add(imageView, row, col);
            }
        }
    }

    /**
     * Devuelve la primera tarjeta seleccionada.
     *
     * @return la primera tarjeta seleccionada.
     */
    public Celdas getFirstCard() {
        return firstCard;
    }

    /**
     * Devuelve la segunda tarjeta seleccionada.
     *
     * @return la segunda tarjeta seleccionada.
     */
    public Celdas getSecondCard() {
        return secondCard;
    }

    /**
     * Devuelve el puntaje actual del juego.
     *
     * @return el puntaje actual.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Método que se ejecuta cuando se hace clic en una tarjeta.
     *
     * @param event el evento del mouse.
     * @throws FileNotFoundException si no se encuentra el archivo de imagen.
     */
    public void cardListener(MouseEvent event) throws FileNotFoundException {
        Node sourceComponent = (Node) event.getSource();
        CardInfo cardInfo = (CardInfo) sourceComponent.getProperties().get("cardInfo");
        int rowSelected = cardInfo.getRow();
        int colSelected = cardInfo.getCol();

        String image = Juntar_Imagenes.Juntar_Imagenes[rowSelected][colSelected].value;
        String imagePath = image + ".png";
        Image selectedImage = loadImage(imagePath);
        ((ImageView) sourceComponent).setImage(selectedImage);
        checkIfMatchingPairWasFound(rowSelected, colSelected);
    }

    /**
     * Verifica si se encontró un par coincidente.
     *
     * @param rowSelected la fila seleccionada.
     * @param colSelected la columna seleccionada.
     * @throws FileNotFoundException si no se encuentra el archivo de imagen.
     */
    public void checkIfMatchingPairWasFound(int rowSelected, int colSelected) throws FileNotFoundException {
        if (firstCard == null) {
            firstCard = Juntar_Imagenes.Juntar_Imagenes[rowSelected][colSelected];
        } else if (secondCard == null) {
            secondCard = Juntar_Imagenes.Juntar_Imagenes[rowSelected][colSelected];
        } else {
            if (firstCard.value.equals(secondCard.value)) {
                Juntar_Imagenes.Juntar_Imagenes[firstCard.row][firstCard.col].wasGuessed = true;
                Juntar_Imagenes.Juntar_Imagenes[secondCard.row][secondCard.col].wasGuessed = true;
                puntos += 2; // Sumar 2 puntos por cada par encontrado
                actualizarPuntosLabel(); // Actualizar la etiqueta de puntos

                if (puntos >= 34) {
                    finalizarJuego();
                    return; // Salir del método para evitar continuar el juego
                }
            } else {
                int indexFirstCardInList = (firstCard.row * 6) + firstCard.col;
                Image questionImage = loadImage("logo1.jpg");
                ((ImageView) gameMatrix.getChildren().get(indexFirstCardInList)).setImage(questionImage);

                int indexSecondCardInList = (secondCard.row * 6) + secondCard.col;
                ((ImageView) gameMatrix.getChildren().get(indexSecondCardInList)).setImage(questionImage);
            }

            firstCard = Juntar_Imagenes.Juntar_Imagenes[rowSelected][colSelected];
            secondCard = null;
        }
    }

    /**
     * Reinicia el juego, restableciendo las tarjetas y el puntaje.
     *
     * @param event el evento del botón.
     * @throws FileNotFoundException si no se encuentra el archivo de imagen.
     */
    public void restartGame(ActionEvent event) throws FileNotFoundException {
        firstCard = null;
        secondCard = null;
        Juntar_Imagenes.populateMatrix();
        puntos = 0; // Restablecer los puntos a 0

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                String imagePath = "logo1.jpg";
                Image image = loadImage(imagePath);
                int index = (row * 6) + col;
                ((ImageView) gameMatrix.getChildren().get(index)).setImage(image);
            }
        }

        reiniciarContadorTiempo();
        actualizarPuntosLabel(); // Actualizar la etiqueta de puntos
    }

    /**
     * Actualiza la etiqueta de puntos con el puntaje actual.
     */
    private void actualizarPuntosLabel() {
        puntosLabel.setText("Puntos: " + puntos);
    }

    /**
     * Carga una imagen desde el archivo de imagen especificado.
     *
     * @param imagePath la ruta de la imagen.
     * @return la imagen cargada.
     * @throws FileNotFoundException si no se encuentra el archivo de imagen.
     */
    private Image loadImage(String imagePath) throws FileNotFoundException {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/application/imagenes/" + imagePath);
            if (inputStream == null) {
                throw new FileNotFoundException("Image file not found: " + imagePath);
            }
            return new Image(inputStream);
        } catch (Exception e) {
            throw new FileNotFoundException("Image file not found: " + imagePath);
        }
    }

    /**
     * Inicia el contador de tiempo para el juego.
     */
    private void iniciarContadorTiempo() {
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(() -> {
                    tiempoRestante--;
                    actualizarTiempoLabel();
                    if (tiempoRestante <= 0) {
                        finalizarJuego();
                    }
                });
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, 1000, 1000); // Ejecutar la tarea cada segundo
    }

    /**
     * Actualiza la etiqueta de tiempo con el tiempo restante.
     */
    private void actualizarTiempoLabel() {
        int minutos = tiempoRestante / 60;
        int segundos = tiempoRestante % 60;
        String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
        tiempoLabel.setText(tiempoFormateado);
    }

    /**
     * Reinicia el contador de tiempo.
     */
    private void reiniciarContadorTiempo() {
        timer.cancel();
        tiempoRestante = 420; // Reiniciar el tiempo a 7 minutos
        tiempoLabel.setText("Tiempo: " + formatTime(tiempoRestante));
        iniciarContadorTiempo();
    }

    /**
     * Finaliza el juego, mostrando la ventana de ganador o perdedor según corresponda.
     */
    private void finalizarJuego() {
        timer.cancel();
        Platform.runLater(() -> {
            try {
                if (puntos >= 34) {
                    cambio_escena_Ganador(); // Llamar al método cambioEscenaGanador si se alcanzan los 36 puntos
                } else {
                    cambio_escena_Perdedor(); // Llamar al método cambioEscenaPerdedor si se acaba el tiempo
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Cambia a la escena de la ventana de perdedor.
     *
     * @throws IOException si ocurre un error al cargar la escena.
     */
    public void cambio_escena_Perdedor() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.hide();

        Parent root = FXMLLoader.load(getClass().getResource("VentanaPerdedor.fxml"));
        perdedorStage = new Stage();
        Scene scene = new Scene(root);
        perdedorStage.setScene(scene);
        perdedorStage.show();
    }

    /**
     * Cambia a la escena de la ventana de ganador.
     *
     * @throws IOException si ocurre un error al cargar la escena.
     */
    public void cambio_escena_Ganador() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.hide();

        Parent root = FXMLLoader.load(getClass().getResource("VentanaGanador.fxml"));
        ganadorStage = new Stage();
        Scene scene = new Scene(root);
        ganadorStage.setScene(scene);
        ganadorStage.show();
    }

    /**
     * Formatea el tiempo en minutos y segundos.
     *
     * @param timeInSeconds el tiempo en segundos.
     * @return el tiempo formateado en minutos y segundos.
     */
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

