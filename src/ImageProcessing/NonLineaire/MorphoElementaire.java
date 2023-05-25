package ImageProcessing.NonLineaire;
import CImage.*;
import CImage.Exceptions.CImageNGException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MorphoElementaire {


    public static int[][] erosion(int [][] image,int tailleMasque)
    {


        int hauteur = image.length; //Donne le nombre de lignes
        int largeur = image[0].length;
        // Donne le nombre d'elements de la ligne 0. Sachant que toutes les lignes ont le
        //meme nombre d'elements, c'est la longueur
        int[][] imageOut = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                List<Integer> listetmp = new ArrayList<Integer>();

                for (int j = y - tailleMasque / 2; j <= y + tailleMasque / 2; j++) {
                    for (int i = x - tailleMasque / 2; i <= x + tailleMasque / 2; i++) {
                        if (j >= 0 && i >= 0 && j < hauteur && i < largeur) {
                            listetmp.add(image[j][i]);
                        }
                    }
                }

                Collections.sort(listetmp);
                int minimum = listetmp.get(0); //Car la valeur minimum se trouve en tete de liste
                imageOut[y][x] = minimum;
            }
        }

        return imageOut;
    }

    public static int[][] dilatation(int [][] image,int tailleMasque)
    {


        int hauteur = image.length; //Donne le nombre de lignes
        int largeur = image[0].length;
        // Donne le nombre d'elements de la ligne 0. Sachant que toutes les lignes ont le
        //meme nombre d'elements, c'est la longueur
        int[][] imageOut = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                List<Integer> listetmp = new ArrayList<Integer>();

                for (int j = y - tailleMasque / 2; j <= y + tailleMasque / 2; j++) {
                    for (int i = x - tailleMasque / 2; i <= x + tailleMasque / 2; i++) {
                        if (j >= 0 && i >= 0 && j < hauteur && i < largeur) {
                            listetmp.add(image[j][i]);
                        }
                    }
                }

                Collections.sort(listetmp);
                int minimum = listetmp.get(listetmp.size()-1); //Car la valeur maximum se trouve en fin de liste
                imageOut[y][x] = minimum;
            }
        }

        return imageOut;
    }

    public static int[][] ouverture(int [][] image,int tailleMasque)
    {
        int[][] imageErodee = erosion(image, tailleMasque); // Appel de la fonction d'erosion
        int[][] imageOut = dilatation(imageErodee, tailleMasque); // Appel de la fonction de dilatation avec l'image erodee

        return imageOut;
    }

    public static int[][] fermeture(int [][] image,int tailleMasque)
    {
        int[][] imageDilatee = dilatation(image, tailleMasque); // Appel de la fonction de dilatation
        int[][] imageOut = erosion(imageDilatee, tailleMasque); // Appel de la fonction d erosion avec l'image dilatee

        return imageOut;
    }

}
