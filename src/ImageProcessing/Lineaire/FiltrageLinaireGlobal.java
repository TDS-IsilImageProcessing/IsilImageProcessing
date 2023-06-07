package ImageProcessing.Lineaire;

import ImageProcessing.Complexe.Complexe;
import ImageProcessing.Complexe.MatriceComplexe;
import ImageProcessing.Fourier.Fourier;
import ImageProcessing.Utils;

public class FiltrageLinaireGlobal {
    /**
     * Attenuer les variations rapide d'intensité de l'image, certaines impression de flou,
     * frequence est le niveau de luminance
     * supprimer le bruit en Haute frequence et garde base frequence
     * Resultat : image lisse, detail fin, zone réguliere pas changé, du flou est visible vu la degradation
     * @param image
     * @param frequenceCoupure
     * @return
     */
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

    /**
     * Accentue les detail, image plus nette avec des contours plus marqué
     * supprimer le bruit en base frequence et garde haute frequence
     * Resultat: contours et detail fin sont accentués, bords sont plus nettes
     * @param image
     * @param frequenceCoupure
     * @return
     */
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

    /**
     * Resultat : image lissé ou les detail fin et bruit a haute frequence sont attenués tout en preservant les les countours
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return
     */
    public static int[][] filtrePasseBasButterworth(int[][] image,int frequenceCoupure,int ordre) {

        //FREQUENCE DE COUPURE :  4.12
            //  1 /  1 + ( (Vu²+v²) / frequenceCoupure) ^2*ordre

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

    /**
     * Met en évide ce les detail fin et variation rapides de l'image
     * Resultat : detail fin et variations rapide sont accentué, contours plus nettes
     * @param image
     * @param frequenceCoupure
     * @param ordre
     * @return
     */
    public static int[][] filtrePasseHautButterworth(int[][] image,int frequenceCoupure, int ordre) {
        //FREQUENCE DE COUPURE :  4.13
            // 1 / 1 + (frequenceCoupure / Vu²+v²) ^2*ordre


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
                double val = 1 / (1 + Math.pow((frequenceCoupure / distance), 2*ordre));
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
