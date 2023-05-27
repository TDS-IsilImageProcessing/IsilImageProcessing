package ImageProcessing.Seuillage;

import java.util.ArrayList;
import java.util.List;

public class Seuillage {


    /**
    * UTILITE : transformer une image NG en image binaire pour s�parer les objets fonc�s des fonds clairs
    *
    *
    * COMMENT :
    * On donne un "seuil" (int)
    * On parcourt tous les pixels de l'image
    *   Si la valeur du pixel est inf�rieure au seuil -> Ce pixel vaut 0 dans l'image binaire de sortie
    *   Si la valeur du pixel est sup�rieure au seuil -> Ce pixel vaut 255 dans l'image binaire de sortie
    *
    * */
    public static int[][] seuillageSimple(int[][] image, int seuil) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageBinaire = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (image[y][x] <= seuil) {
                    imageBinaire[y][x] = 0; // Pixel noir (valeur 0) pour les pixels inf�rieurs ou �gaux au seuil
                } else {
                    imageBinaire[y][x] = 255; // Pixel blanc (valeur 255) pour les pixels sup�rieurs au seuil
                }
            }
        }

        return imageBinaire;
    }

    /**
    * Comme seuillage simple, mais avec 2 seuils
    * Permet de d�finir des r�gions
    *
    * */
    public static int[][] seuillageDouble(int[][] image, int seuil1, int seuil2) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageSegmentee = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                int niveauGris = image[y][x];

                if (niveauGris < seuil1) {
                    imageSegmentee[y][x] = 0; // Niveau de gris 0 pour les pixels inf�rieurs au premier seuil
                } else if (niveauGris >= seuil1 && niveauGris < seuil2) {
                    imageSegmentee[y][x] = 127; // Niveau de gris 127 pour les pixels entre les deux seuils
                } else {
                    imageSegmentee[y][x] = 255; // Niveau de gris 255 pour les pixels sup�rieurs ou �gaux au deuxi�me seuil
                }
            }
        }

        return imageSegmentee;
    }


    public static int[][] seuillageAutomatique(int[][] image) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageSegmentee = new int[hauteur][largeur];

        // �tape 1: Initialisation du seuil T avec la moyenne des niveaux de gris de l'image
        int seuil = calculerMoyenne(image);

        // Boucle de convergence
        int NbTour = 0;
        boolean hasConverged = false;
        while (!hasConverged) {
            // �tape 2: S�paration des pixels en deux groupes G1 et G2
            List<Integer> groupe1 = new ArrayList<>();
            List<Integer> groupe2 = new ArrayList<>();

            for (int y = 0; y < hauteur; y++) {
                for (int x = 0; x < largeur; x++) {
                    int niveauGris = image[y][x];
                    if (niveauGris > seuil) {
                        groupe1.add(niveauGris);
                    } else {
                        groupe2.add(niveauGris);
                    }
                }
            }

            // �tape 3: Calcul des moyennes des niveaux de gris des deux groupes
            double moyenneGroupe1 = calculerMoyenne(groupe1);
            double moyenneGroupe2 = calculerMoyenne(groupe2);

            // �tape 4: Mise � jour du seuil
            int nouveauSeuil = (int) Math.round((moyenneGroupe1 + moyenneGroupe2) / 2.0);

            // �tape 5: V�rification de la convergence
            if (nouveauSeuil == seuil) {
                hasConverged = true;
            } else {
                seuil = nouveauSeuil;
            }

            NbTour ++;
        }

        // �tape finale: Application du seuillage parfait trouv� sur l'image de d�part
        imageSegmentee = seuillageSimple(image, seuil);

        System.out.println("Seuillage automatique, nombre d'itt�rations : " + NbTour);
        System.out.println("Seuillage automatique, seuil id�al trouv� : " + seuil);
        return imageSegmentee;
    }

    /**
     * UTILITE : Calculer la moyenne d'une image NG
     *
     * COMMENT :
     * Parcourir tous les pixels et ajouter leur valeur dans "somme"
     * Diviser la somme par le nombre d'�l�ments
     */
    private static int calculerMoyenne(int [][] MatriceImage) {

        int somme = 0;
        int hauteur = MatriceImage.length;
        int largeur = MatriceImage[0].length;

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                somme = somme + MatriceImage[y][x];
            }
        }

        int moyenne = somme / (hauteur * largeur);
        System.out.println("Seuillage automatique, moyenne T de d�part : " + moyenne);

        return moyenne;
    }

    /**
     * M�me que la pr�c�dente, mais avec une liste de int
     */
    private static double calculerMoyenne(List<Integer> ListNiveauxGris) {

        int somme = 0;
        for (int ValPixelNG : ListNiveauxGris) {
            somme = somme + ValPixelNG;
        }

        int moyenne = somme / ListNiveauxGris.size();

        return moyenne;
    }


}
