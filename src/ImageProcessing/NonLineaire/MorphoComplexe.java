package ImageProcessing.NonLineaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MorphoComplexe {


    public static int[][] dilatationGeodesique(int[][] image,int[][] masqueGeodesique, int nbIter)
    {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageDilatee = new int[hauteur][largeur];

        // Copie de l image initiale dans l image dilatee
        for (int y = 0; y < hauteur; y++) {
            System.arraycopy(image[y], 0, imageDilatee[y], 0, largeur);
        }

        // Realisation de la dilatation geodesique
        for (int iter = 0; iter < nbIter; iter++) {
            int[][] imageTemp = new int[hauteur][largeur];

            // Copie de l image dilatee precedente dans l image temporaire
            for (int y = 0; y < hauteur; y++) {
                System.arraycopy(imageDilatee[y], 0, imageTemp[y], 0, largeur);
            }

            // Parcours de chaque pixel de l'image
            for (int y = 0; y < hauteur; y++) {
                for (int x = 0; x < largeur; x++) {
                    // Verification de l appartenance au masque geodesique
                    if (masqueGeodesique[y][x] == 255) {    //255 car ces une image NG, c'est 0 ou 255
                        // Recherche de la valeur maximale parmi les voisins du pixel
                        int valeurMax = imageTemp[y][x];

                        for (int j = y - 1; j <= y + 1; j++) {
                            for (int i = x - 1; i <= x + 1; i++) {
                                if (j >= 0 && i >= 0 && j < hauteur && i < largeur) {
                                    if (imageTemp[j][i] > valeurMax) {
                                        valeurMax = imageTemp[j][i];
                                    }
                                }
                            }
                        }

                        // Mise à jour du pixel dans l'image dilatée
                        //Peut etre qu on ne change rien si aucun pixel plus grand n existe dans les voisins
                        imageDilatee[y][x] = valeurMax;
                    }
                }
            }
        }

        return imageDilatee;
    }

    public static int[][] reconstructionGeodesique(int[][] image, int[][] masqueGeodesique) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageReconstruite = new int[hauteur][largeur];

        //Mon Etape 1 : recopier l image de base dans l image reconstruite si le masque geodesique = 255
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                imageReconstruite[y][x] = image[y][x];
            }
        }

        // Étape 2: Répétition de la dilatation conditionnelle jusqu'à convergence
        boolean hasChanged;
        int CompteurNbIterations = 0;
        do {
            hasChanged = false;

            // Étape 3: Dilatation conditionnelle de l'image reconstruite
            for (int y = 0; y < hauteur; y++) {
                for (int x = 0; x < largeur; x++) {
                    if (masqueGeodesique[y][x] == 255) {
                        // Recherche du maximum parmi les pixels voisins dans l'image reconstruite

                        int maximumVoisin = imageReconstruite[y][x];
                        //int maximumVoisin = image[y][x];
                        for (int j = y - 1; j <= y + 1; j++) {
                            for (int i = x - 1; i <= x + 1; i++) {
                                if (j >= 0 && i >= 0 && j < hauteur && i < largeur)
                                {
                                    //if (imageReconstruite[j][i] > maximumVoisin) {
                                    if (image[j][i] > maximumVoisin) {
                                        //maximumVoisin = imageReconstruite[j][i];
                                        maximumVoisin = image[j][i];
                                    }

                                }
                            }
                        }

                        // Mise à jour de l'image reconstruite si le maximum des voisins est supérieur au pixel courant
                        if (maximumVoisin > imageReconstruite[y][x]) {
                            imageReconstruite[y][x] = maximumVoisin;
                            hasChanged = true;
                            CompteurNbIterations ++;
                        }
                    }
                }
            }
        } while (hasChanged);

        System.out.println("Reconstruction geodesique, nombre d iterations : " + CompteurNbIterations);
        return imageReconstruite;
    }

    public static int[][] filtreMedian(int[][] image, int tailleMasque) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageFiltree = new int[hauteur][largeur];

        // Parcours de chaque pixel de l'image
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                // Création d une liste pour stocker les valeurs des pixels dans le voisinage
                List<Integer> voisinage = new ArrayList<>();

                // Parcours du voisinage autour du pixel
                for (int j = y - tailleMasque / 2; j <= y + tailleMasque / 2; j++) {
                    for (int i = x - tailleMasque / 2; i <= x + tailleMasque / 2; i++) {
                        // Verification des limites de l image
                        if (j >= 0 && i >= 0 && j < hauteur && i < largeur) {
                            // Ajout de la valeur du pixel dans le voisinage
                            voisinage.add(image[j][i]);
                        }
                    }
                }

                // Tri du voisinage pour obtenir les valeurs dans l ordre croissant
                Collections.sort(voisinage);

                // Calcul de la mediane
                int valeurMedian = voisinage.get(voisinage.size() / 2);

                // Mise à jour du pixel dans l image filtree
                imageFiltree[y][x] = valeurMedian;
            }
        }

        return imageFiltree;
    }


}
