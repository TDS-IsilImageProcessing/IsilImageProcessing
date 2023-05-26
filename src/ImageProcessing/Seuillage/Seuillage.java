package ImageProcessing.Seuillage;

public class Seuillage {


    /*
    * UTILITE : transformer une image NG en image binaire pour séparer les objets foncés des fonds clairs
    *
    *
    * COMMENT :
    * On donne un "seuil" (int)
    * On parcourt tous les pixels de l'image
    *   Si la valeur du pixel est inférieure au seuil -> Ce pixel vaut 0 dans l'image binaire de sortie
    *   Si la valeur du pixel est supérieure au seuil -> Ce pixel vaut 255 dans l'image binaire de sortie
    *
    * */
    public static int[][] seuillageSimple(int[][] image, int seuil) {
        int hauteur = image.length;
        int largeur = image[0].length;
        int[][] imageBinaire = new int[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                if (image[y][x] <= seuil) {
                    imageBinaire[y][x] = 0; // Pixel noir (valeur 0) pour les pixels inférieurs ou égaux au seuil
                } else {
                    imageBinaire[y][x] = 255; // Pixel blanc (valeur 255) pour les pixels supérieurs au seuil
                }
            }
        }

        return imageBinaire;
    }

    /*
    * Comme seuillage simple, mais avec 2 seuils
    * Permet de définir des régions
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
                    imageSegmentee[y][x] = 0; // Niveau de gris 0 pour les pixels inférieurs au premier seuil
                } else if (niveauGris >= seuil1 && niveauGris < seuil2) {
                    imageSegmentee[y][x] = 127; // Niveau de gris 127 pour les pixels entre les deux seuils
                } else {
                    imageSegmentee[y][x] = 255; // Niveau de gris 255 pour les pixels supérieurs ou égaux au deuxième seuil
                }
            }
        }

        return imageSegmentee;
    }



}
