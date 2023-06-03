package ImageProcessing.Contours;

import ImageProcessing.Lineaire.FiltrageLineaireLocal;

/**
 * Effectue une convolution par rapport au filtre
 */
public class ContoursLineaire {

    public static int[][] gradientPrewitt(int[][] image,int dir) {
        double[][] masque = dir == 1 ? new double[][] {
                {1,0,-1},
                {1,0,-1},
                {1,0,-1}
        } : new double[][] {
                {1,1,1},
                {0,0,0},
                {-1,-1,-1}
        };

        int [][] resultat = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);

        return resultat;
    }

    public static int[][] gradientSobel(int[][] image,int dir) {
        double[][] masque = dir == 1 ? new double[][] {
                {1,0,-1},
                {2,0,-2},
                {1,0,-1}
        } : new double[][] {
                {1,2,1},
                {0,0,0},
                {-1,-2,-1}
        };

        int [][] resultat = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);

        return resultat;
    }
    public static int[][] laplacien4(int[][] image) {
        double[][] masque = new double[][] {
                {0,1,0},
                {1,-4,1},
                {0,1,0}
        };

        int [][] resultat = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);

        return resultat;
    }

    public static int[][] laplacien8(int[][] image) {
        double[][] masque = new double[][] {
                {1,1,1},
                {1,-8,1},
                {1,1,1}
        };

        int [][] resultat = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);

        return resultat;
    }
}
