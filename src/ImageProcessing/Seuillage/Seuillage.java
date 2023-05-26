package ImageProcessing.Seuillage;

public class Seuillage {


    /*
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

    /*
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



}
