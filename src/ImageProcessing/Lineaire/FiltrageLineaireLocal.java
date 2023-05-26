package ImageProcessing.Lineaire;

public class FiltrageLineaireLocal {
    public static int[][] filtreMasqueConvolution(int[][] image, double [][] masque) {
        int[][] resultat = new int[image.length][image[0].length];
        int masqueOffset = masque.length / 2;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                double somme = 0;

                for (int k = 0; k < masque.length; k++) {
                    for (int l = 0; l < masque[0].length; l++) {
                        int imageI = i + k - masqueOffset;
                        int imageJ = j + l - masqueOffset;
                        if (imageJ >= 0 && imageJ < image[0].length && imageI >= 0 && imageI < image.length) {
                            somme += image[imageI][imageJ] * masque[k][l];
                        } else {
                            // sert a rien mais au cas on veut prolonger l'image a la place de mettre de 0 autour
                            somme += 0 * masque[k][l];
                        }
                    }
                }
                resultat[i][j] = (int) Math.round(somme);
            }
        }

        return resultat;
    }

    public static int[][] filtreMoyenneur(int[][] image, int tailleMasque) {
        double[][] masque = new double[tailleMasque][tailleMasque];
        double masqueCoeff = 1.0 / (tailleMasque * tailleMasque);
        for (int i = 0; i < tailleMasque; i++) {
            for (int j = 0; j < tailleMasque; j++) {
                masque[i][j] = masqueCoeff;
            }
        }
        int [][] resultat = filtreMasqueConvolution(image, masque);
        return resultat;
    }
}
