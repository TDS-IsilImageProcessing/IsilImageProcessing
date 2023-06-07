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

}
