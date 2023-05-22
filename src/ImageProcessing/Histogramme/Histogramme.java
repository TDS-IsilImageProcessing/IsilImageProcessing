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

        int hauteur = image.length; //Donne le nombre de lignes
        int largeur = image[0].length; //Donne le nombre d'éléments de la ligne 0, donc la largeur

        for(int x=0; x< largeur; x++)
            for(int y=0; y< hauteur; y++)
                if(image[x][y]<Min)
                    Min = image[x][y];

        return Min;
    }


    public static int maximum(int[][] image)
    {
        int Max = image[0][0];

        int hauteur = image.length; //Donne le nombre de lignes
        int largeur = image[0].length; //Donne le nombre d'éléments de la ligne 0, donc la largeur

        for(int x=0; x< largeur; x++)
            for(int y=0; y< hauteur; y++)
                if(image[x][y]>Max)
                    Max = image[x][y];

        return Max;
    }

    public static int luminance(int[][] image)
    {
        return 0;
    }

    public static double contraste1(int[][] image)
    {
        return 0;
    }
}
