package ImageProcessing.Contours;

import ImageProcessing.NonLineaire.MorphoElementaire;

public class ContoursNonLineaire {


    public static int[][] gradientErosion(int[][] image)
    {
        int[][] erosionResult = MorphoElementaire.erosion(image, 3);
        int lignes = image.length;
        int colonnes = image[0].length;
        int[][] gradient = new int[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                gradient[i][j] = image[i][j] - erosionResult[i][j];
            }
        }
        return gradient;
    }

    public static int[][] gradientDilatation(int[][] image)
    {
        int[][] dilatationResult = MorphoElementaire.dilatation(image, 3);
        int lignes = image.length;
        int colonnes = image[0].length;
        int[][] gradient = new int[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                gradient[i][j] = dilatationResult[i][j] - image[i][j];
            }
        }
        return gradient;
    }

    public static int[][] gradientBeucher(int[][] image)
    {
        int[][] erosionResult = MorphoElementaire.erosion(image, 3);
        int[][] dilatationResult = MorphoElementaire.dilatation(image, 3);
        int lignes = image.length;
        int colonnes = image[0].length;
        int[][] gradient = new int[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                gradient[i][j] = dilatationResult[i][j] - erosionResult[i][j];
            }
        }
        return gradient;
    }

    public static int[][] laplacienNonLineaire(int[][] image)
    {
        int[][] gradientDilatationResult = gradientDilatation(image);
        int[][] gradientErosionResult = gradientErosion(image);
        int lignes = image.length;
        int colonnes = image[0].length;
        int[][] laplacien = new int[lignes][colonnes];

        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                laplacien[i][j] = Math.abs(gradientDilatationResult[i][j] - gradientErosionResult[i][j]);
            }
        }
        return laplacien;
    }
}
