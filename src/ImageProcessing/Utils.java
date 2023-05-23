package ImageProcessing;

public class Utils {
    public static double[][] intToDouble(int[][] arr) {
        int height = arr.length;
        int width = arr[0].length;

        double[][] result = new double[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = (double) arr[y][x];
            }
        }

        return result;
    }

    public static int[][] doubleToInt(double[][] arr) {
        int height = arr.length;
        int width = arr[0].length;

        int[][] result = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result[y][x] = (int) Math.round(arr[y][x]);
            }
        }

        return result;
    }

}
