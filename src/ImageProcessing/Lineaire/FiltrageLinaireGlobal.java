package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utils;

public class FiltrageLinaireGlobal {

    public static int[][] filtrePasseBasIdeal(int[][] image,int frequenceCoupure) {
        int hauteur = image.length;
        int largeur = image[0].length;
        double[][] imageInDouble = Utils.intToDouble(image);
        int centreHauteur = (hauteur - 1) / 2 ;
        int centreLargeur = (largeur - 1) / 2 ;


        // 1. Fourier
        MatriceComplexe fourier = Fourier.Fourier2D(imageInDouble);
        // 2. Decroire
        MatriceComplexe decroise1 = Fourier.decroise(fourier);
        // 3. Fonction de transfert du filtre H
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                double distance = Math.sqrt(Math.pow(i - centreHauteur, 2) + Math.pow(j - centreLargeur, 2));
                if (distance > frequenceCoupure)
                    decroise1.set(i, j, new Complexe(0,0));
            }
        }
        // 4. Decroire
        MatriceComplexe decroise2 = Fourier.decroise(decroise1);
        // 5. Fourier inverse
        MatriceComplexe fourierInverse = Fourier.InverseFourier2D(decroise2);
        double[][] partieReel = fourierInverse.getPartieReelle();
        int[][] resultat = Utils.doubleToInt(partieReel);

        return resultat;
    }

    public static int[][] filtrePasseHautIdeal(int[][] image, int frequenceCoupure) {
        int hauteur = image.length;
        int largeur = image[0].length;
        double[][] imageInDouble = Utils.intToDouble(image);
        int centreHauteur = (hauteur - 1) / 2 ;
        int centreLargeur = (largeur - 1) / 2 ;

        // 1. Fourier
        MatriceComplexe fourier = Fourier.Fourier2D(imageInDouble);
        // 2. Decroire
        MatriceComplexe decroise1 = Fourier.decroise(fourier);
        // 3. Fonction de transfert du filtre H
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                double distance = Math.sqrt(Math.pow(i - centreHauteur, 2) + Math.pow(j - centreLargeur, 2));
                if (distance < frequenceCoupure)
                    decroise1.set(i, j, new Complexe(0,0));
            }
        }
        // 4. Decroire
        MatriceComplexe decroise2 = Fourier.decroise(decroise1);
        // 5. Fourier inverse
        MatriceComplexe fourierInverse = Fourier.InverseFourier2D(decroise2);
        double[][] partieReel = fourierInverse.getPartieReelle();
        int[][] resultat = Utils.doubleToInt(partieReel);

        return resultat;
    }

    public static int[][] filtrePasseBasButterworth(int[][] image,int frequenceCoupure,int ordre) {

        //FREQUENCE DE COUPURE :
            //  1 /  1 + ( (Vu²+v²) / frequenceCoupure) ²*ordre

        int hauteur = image.length;
        int largeur = image[0].length;
        double[][] imageInDouble = Utils.intToDouble(image);
        int centreHauteur = (hauteur - 1) / 2 ;
        int centreLargeur = (largeur - 1) / 2 ;

        // 1. Fourier
        MatriceComplexe fourier = Fourier.Fourier2D(imageInDouble);
        // 2. Decroire
        MatriceComplexe decroise1 = Fourier.decroise(fourier);
        // 3. Fonction de transfert du filtre H
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                double distance = Math.sqrt(Math.pow(i - centreHauteur, 2) + Math.pow(j - centreLargeur, 2));
                double val = 1 / (1 + Math.pow((distance / frequenceCoupure), 2*ordre));
                Complexe complexe = decroise1.get(i,j);
                complexe.multiplie(new Complexe(val, 0));
                decroise1.set(i,j, complexe);
            }
        }
        // 4. Decroire
        MatriceComplexe decroise2 = Fourier.decroise(decroise1);
        // 5. Fourier inverse
        MatriceComplexe fourierInverse = Fourier.InverseFourier2D(decroise2);
        double[][] partieReel = fourierInverse.getPartieReelle();
        int[][] resultat = Utils.doubleToInt(partieReel);

        return resultat;

    }
}
