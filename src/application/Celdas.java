package application;

/**
 * La clase Celdas representa una celda en el juego y almacena información relacionada con ella.
 */
public class Celdas {
    public String value;
    public int row;
    public int col;
    public boolean wasGuessed;

    /**
     * Crea una nueva instancia de Celdas con el valor, fila y columna especificados.
     *
     * @param value      el valor de la celda.
     * @param row        la fila de la celda.
     * @param col        la columna de la celda.
     */
    public Celdas(String value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.wasGuessed = false;
    }
}
