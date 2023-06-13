package application;

/**
 * La clase CardInfo representa la información de una carta en el juego.
 * Contiene el número de fila y columna de la carta en la matriz del juego.
 */
public class CardInfo {
    private final int row;
    private final int col;

    /**
     * Crea una nueva instancia de CardInfo con la fila y columna especificadas.
     *
     * @param row la fila de la carta.
     * @param col la columna de la carta.
     */
    public CardInfo(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Obtiene el número de fila de la carta.
     *
     * @return el número de fila.
     */
    public int getRow() {
        return row;
    }

    /**
     * Obtiene el número de columna de la carta.
     *
     * @return el número de columna.
     */
    public int getCol() {
        return col;
    }
}