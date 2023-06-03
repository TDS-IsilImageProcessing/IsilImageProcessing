package ImageProcessing.Contours;

import ImageProcessing.Lineaire.FiltrageLineaireLocal;

/**
 * Effectue une convolution par rapport au filtre
 */
public class ContoursLineaire {


    /**
     * Parcout l'image et effectue un masque de convolution avec le filtre selon la direction.
     * Technique utilis� pour detecter les contours. La direction permet de mettre en �vidence les variations d'intensit� dans les directions sp�cifiques
     * Resultat : les pixels sur les contours ou les bord de l'images ont des valeurs �lev�es dans la direction du gradient, les r�gions lisses ont des valeurs faible
     * @param image
     * @param dir
     * @return
     */
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

    /**
     * Parcout l'image et effectue un masque de convolution avec le filtre selon la direction.
     * meme chose que le prewitt mais le masque est diff�rents
     * plus sensible pour les contours, meilleur pr�cision des contours
     * Resultat : les contours et les transition d'intensit� sont mis en �vidence
     * @param image
     * @param dir
     * @return
     */
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

    /**
     * Parcout l'image et effectue un masque de convolution avec le filtre selon la direction.
     * Detecte les r�gions de l'image ou l'intensit� varie rapidement (contours, bord, detail)
     * Met en evidence les transitions d'intensite rapide et les regions ou l'intensit� change brusquement
     * Permet de detecter les contours et bords precis
     * a utiliser avec d'autres techniques de filtrage ou seuillage
     *
     * @param image
     * @return
     */
    public static int[][] laplacien4(int[][] image) {
        double[][] masque = new double[][] {
                {0,1,0},
                {1,-4,1},
                {0,1,0}
        };

        int [][] resultat = FiltrageLineaireLocal.filtreMasqueConvolution(image, masque);

        return resultat;
    }

    /**
     * Parcout l'image et effectue un masque de convolution avec le filtre selon la direction.
     * meme chose que le 4 mais prend en compte les voisins diagonaux et plus des voisions horizontaux et verticaux
     * plus sensible au detail et au coutours diagonaux
     * a utiliser avec d'autres techniques de filtrage ou seuillage
     *
     * @param image
     * @return
     */
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
