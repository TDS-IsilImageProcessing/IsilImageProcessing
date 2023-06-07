/*
 * Histogramme.java
 *
 * Created on 23 septembre 2007, 20:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ImageProcessing.Histogramme;

/**
 *
 * @author Jean-Marc
 */
public class Histogramme 
{
    public static int[] Histogramme256(int mat[][])
    {
        int M = mat.length;
        int N = mat[0].length;
        int histo[] = new int[256];
        
        for(int i=0 ; i<256 ; i++) histo[i] = 0;
        
        for(int i=0 ; i<M ; i++)
            for(int j=0 ; j<N ; j++)
                if ((mat[i][j] >= 0) && (mat[i][j]<=255)) histo[mat[i][j]]++;
        
        return histo;
    }

    public static int minimum(int[][] image)
    {
        int Min = image[0][0];

        int lignes = image.length; //lignes
        int colonnes = image[0].length; //colonnes

        for(int x=0; x< lignes; x++)
            for(int y=0; y< colonnes; y++)
                if(image[x][y]<Min)
                    Min = image[x][y];

        return Min;
    }


    public static int maximum(int[][] image)
    {
        int Max = image[0][0];

        int lignes = image.length; //lignes
        int colonnes = image[0].length; //colonnes

        for(int x=0; x< lignes; x++)
            for(int y=0; y< colonnes; y++)
                if(image[x][y]>Max)
                    Max = image[x][y];

        return Max;
    }

    public static int luminance(int[][] image)
    {
        int M = image.length;
        int N = image[0].length;
        int[] histo = Histogramme256(image);

        double somme = 0;
        for (int i = 0; i < 256; i++) {
            somme += i * histo[i];
        }

        return (int) somme/(M * N) ;
    }

    public static double contraste1(int[][] image)
    {
        int M = image.length;
        int N = image[0].length;
        int luminance = luminance(image);

        double sommeCarres = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                double dif = image[i][j] - luminance;
                sommeCarres += Math.pow(dif, 2);
            }
        }

        return Math.sqrt(sommeCarres / (M * N));
    }
    public static double contraste2(int[][] image)
    {
        int min = minimum(image);
        int max = maximum(image);
        return (double)(max - min) / (max + min);
    }

    public static int[][] rehaussement(int[][] image, int[] courbeTonale)
    {
        int M = image.length;
        int N = image[0].length;

        int[][] imageRehaussee = new int[M][N];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int niveauGris = image[i][j];
                imageRehaussee[i][j] = courbeTonale[niveauGris];
            }
        }
        return imageRehaussee;
    }

    public static int[] creerCourbeTonaleLineaireSaturation(int smin, int smax) {

        int[] courbeTonale = new int[256];
        for (int i = 0; i < 256; i++) {
            if (i <= smin) {
                courbeTonale[i] = 0;
            } else if (i >= smax) {
                courbeTonale[i] = 255;
            } else {
                courbeTonale[i] = 255 * (i - smin) / (smax - smin);
            }
        }

        return courbeTonale;
    }

    public static int[] creerCourbeTonaleGamma(double gamma) {
        int[] courbeTonale = new int[256];

        for (int i = 0; i < 256; i++) {
            int nouveauNiveauGris = (int) (255 * Math.pow(i / 255.0, 1.0 / gamma));
            courbeTonale[i] = Math.min(Math.max(nouveauNiveauGris, 0), 255);
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleNegatif()
    {
        int[] courbeTonale = new int[256];

        for (int i = 0; i < 256; i++) {
            courbeTonale[i] = 255 - i;
        }

        return courbeTonale;
    }

    public static int[] creeCourbeTonaleEgalisation(int[][] image)
    {
        int M = image.length;
        int N = image[0].length;

        int[] courbeTonaleEgalisation = new int[256];
        // Calcul de l'histogramme de l'image originale hist(k), k ? [0, 255]
        int[] histogramme = Histogramme256(image);

        double[] histogrammeNormalise = new double[256];
        double[] histogrammeCumule = new double[256];

        // Normalisation de l'histogramme
        int totalPixels = M * N;
        for (int i = 0; i < 256; i++) {
            histogrammeNormalise[i] = (double) histogramme[i] / totalPixels;
        }

        // Calcul de l'histogramme des frequences cumul�es
        histogrammeCumule[0] = histogrammeNormalise[0];
        for (int i = 1; i < 256; i++) {
            histogrammeCumule[i] = histogrammeCumule[i - 1] + histogrammeNormalise[i];
        }


        // Transformation des niveaux de gris en utilisant la loi
        for (int i = 0; i < 256; i++) {
            courbeTonaleEgalisation[i] = (int) (255 * histogrammeCumule[i]);
        }

        return courbeTonaleEgalisation;
    }

}
