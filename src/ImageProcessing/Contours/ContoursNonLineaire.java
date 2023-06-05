package ImageProcessing.Contours;

import ImageProcessing.NonLineaire.MorphoElementaire;

public class ContoursNonLineaire {


    public static int[][] gradientErosion(int[][] image)
    {
        int[][] erosionResult = MorphoElementaire.erosion(image, 3);
        int rows = image.length;
        int cols = image[0].length;
        int[][] gradient = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gradient[i][j] = image[i][j] - erosionResult[i][j];
            }
        }

        return gradient;
    }

    public static int[][] gradientDilatation(int[][] image)
    {
        int[][] dilatationResult = MorphoElementaire.dilatation(image, 3);
        int rows = image.length;
        int cols = image[0].length;
        int[][] gradient = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gradient[i][j] = dilatationResult[i][j] - image[i][j];
            }
        }
        return gradient;
    }

    public static int[][] gradientBeucher(int[][] image)
    {
        int[][] erosionResult = MorphoElementaire.erosion(image, 3);
        int[][] dilatationResult = MorphoElementaire.dilatation(image, 3);
        int rows = image.length;
        int cols = image[0].length;
        int[][] gradient = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gradient[i][j] = dilatationResult[i][j] - erosionResult[i][j];
            }
        }

        return gradient;
    }

    public static int[][] laplacienNonLineaire(int[][] image)
    {
        int[][] gradientDilatationResult = gradientDilatation(image);
        int[][] gradientErosionResult = gradientErosion(image);
        int rows = image.length;
        int cols = image[0].length;
        int[][] laplacien = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                laplacien[i][j] = gradientDilatationResult[i][j] - gradientErosionResult[i][j];
            }
        }

        return laplacien;
    }
}
