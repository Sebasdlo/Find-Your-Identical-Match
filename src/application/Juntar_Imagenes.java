package application;
import java.util.Random;

/**
 * La clase Juntar_Imagenes se encarga de generar una matriz de objetos Celdas con imágenes aleatorias.
 */
public class Juntar_Imagenes {
    public Celdas[][] Juntar_Imagenes = new Celdas[6][6];

    /**
     * El método populateMatrix se utiliza para poblar la matriz Juntar_Imagenes con imágenes aleatorias.
     */
    public void populateMatrix() {
        Juntar_Imagenes = new Celdas[6][6];
        String[] images = {"compu", "cartel", "altavoz", "Corona", "gorro"};
        Random randomGenerator = new Random();

        while (!isBoardFull()) {
            int randomImageIndex = randomGenerator.nextInt(images.length);
            String randomImageSelected = images[randomImageIndex];

            int randomRow1 = randomGenerator.nextInt(6);
            int randomCol1 = randomGenerator.nextInt(6);
            while (Juntar_Imagenes[randomRow1][randomCol1] != null) {
                randomRow1 = randomGenerator.nextInt(6);
                randomCol1 = randomGenerator.nextInt(6);
            }

            int randomRow2 = randomGenerator.nextInt(6);
            int randomCol2 = randomGenerator.nextInt(6);
            while ((randomRow1 == randomRow2 && randomCol1 == randomCol2)
                    || Juntar_Imagenes[randomRow2][randomCol2] != null) {
                randomRow2 = randomGenerator.nextInt(6);
                randomCol2 = randomGenerator.nextInt(6);
            }

            Juntar_Imagenes[randomRow1][randomCol1] = new Celdas(randomImageSelected, randomRow1, randomCol1);
            Juntar_Imagenes[randomRow2][randomCol2] = new Celdas(randomImageSelected, randomRow2, randomCol2);
        }
    }

    /**
     * El método isBoardFull verifica si la matriz Juntar_Imagenes está completamente llena.
     *
     * @return true si la matriz está llena, false de lo contrario.
     */
    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (Juntar_Imagenes[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}

