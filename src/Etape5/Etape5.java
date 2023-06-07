package Etape5;

import ImageProcessing.Lineaire.FiltrageLinaireGlobal;
import ImageProcessing.NonLineaire.MorphoComplexe;
import ImageProcessing.Seuillage.Seuillage;

public class Etape5 {

    public static int[][] Etape5_1_LenaBruit(int[][] Matrice)
    {

        Matrice = MorphoComplexe.filtreMedian(Matrice, 3);
        Matrice = FiltrageLinaireGlobal.filtrePasseBasButterworth(Matrice, 50, 1);

        return Matrice;



    }

    public static int[][] Etape5_2_LenaAEgaliser(int[][] Matrice)
    {
        return Matrice;
    }


    public static int[][] Etape5_3_PetitsPois(int[][] Matrice)
    {

        Matrice = Seuillage.seuillageSimple(Matrice, 1);

        return Matrice;
    }

    public static int[][] garderComposanteRouge(int[][] rouge, int[][] vert, int[][] bleu) {
        int[][] resultat = new int[rouge.length][rouge[0].length];

        for (int i = 0; i < rouge.length; i++) {
            for (int j = 0; j < rouge[0].length; j++) {
                if (rouge[i][j] == 255 && vert[i][j] == 255 && bleu[i][j] == 255) {
                    resultat[i][j] = 255;  // Garder la composante rouge
                } else {
                    resultat[i][j] = 0;    // Supprimer les autres composantes
                }
            }
        }

        return resultat;
    }


}
