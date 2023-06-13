package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;


class VentanaJuegoControllerTest {
	
    private VentanaJuegoController ventanaJuegoController;

    @BeforeEach
    void setUp() {
        ventanaJuegoController = new VentanaJuegoController();
    }

    @Test
    void testCheckIfMatchingPairWasFound_WhenCardsMatched_ShouldUpdatePoints() throws FileNotFoundException {
        ventanaJuegoController.initialize();
        ventanaJuegoController.checkIfMatchingPairWasFound(0, 0);
        ventanaJuegoController.checkIfMatchingPairWasFound(0, 1);
        int expectedPuntos = 2;
        assertEquals(expectedPuntos, ventanaJuegoController.getPuntos());
    }

    @Test
    void testCheckIfMatchingPairWasFound_WhenCardsDoNotMatch_ShouldResetCards() throws FileNotFoundException {
        ventanaJuegoController.initialize();
        ventanaJuegoController.checkIfMatchingPairWasFound(0, 0);
        ventanaJuegoController.checkIfMatchingPairWasFound(1, 0);
        assertNull(ventanaJuegoController.getFirstCard());
        assertNull(ventanaJuegoController.getSecondCard());
    }

    @Test
    void testRestartGame_ShouldResetGame() throws FileNotFoundException {
        ventanaJuegoController.initialize();
        ventanaJuegoController.checkIfMatchingPairWasFound(0, 0);
        ventanaJuegoController.checkIfMatchingPairWasFound(0, 1);
        ventanaJuegoController.restartGame(null);
        assertNull(ventanaJuegoController.getFirstCard());
        assertNull(ventanaJuegoController.getSecondCard());
        int expectedPuntos = 0;
        assertEquals(expectedPuntos, ventanaJuegoController.getPuntos());
    }
}