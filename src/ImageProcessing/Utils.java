package ImageProcessing;

import CImage.Exceptions.CImageNGException;

public class Utils {
    public static double[][] intToDouble(int[][] arr) {
        int height = arr.length;
        int width = arr[0].length;

        double[][] result = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = (double) arr[y][x];
            }
        }

        return result;
    }

    public static int[][] doubleToInt(double[][] arr) {
        int height = arr.length;
        int width = arr[0].length;

        int[][] result = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = (int) Math.round(arr[y][x]);
            }
        }

        return result;
    }
    public static int[][] MiseAJourCImage(int   matrice[][])
    {
        int M = matrice.length;
        int N = matrice[0].length;
        double max = matrice[0][0];
        double min = matrice[0][0];
        int[][] matrice_int = new int[M][N];
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++) {
                if (matrice[i][j] > max) max = matrice[i][j];
                if (matrice[i][j] < min ) min = matrice[i][j];
            }
        double blanc = max;
        double noir = min;
        int val;

        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
            {
                if (matrice[i][j] >= blanc)
                {
                    matrice_int[i][j] = 255;
                }
                else
                {
                    if (matrice[i][j] <= noir)
                    {
                        matrice_int[i][j] = 0;
                    }
                    else
                    {
                        val = (int)((matrice[i][j] - noir)/(blanc-noir)*255+0.5);
                        if (val > 255) val = 255;
                        if (val < 0) val = 0;
                        matrice_int[i][j] = val;
                    }
                }
            }
        return matrice_int;
    }

}
