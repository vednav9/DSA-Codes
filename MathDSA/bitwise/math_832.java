package MathDSA.bitwise;

import java.util.Arrays;

public class math_832 {
    public static void main(String[] args) {
        // [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
        // [[1,1,0],[1,0,1],[0,0,0]]
        // [[1,0,0],[0,1,0],[1,1,1]]
        int[][] image = { { 1, 1, 0, 0 }, { 1, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 0, 1, 0 } };
        System.out.println(Arrays.deepToString(flip(image)));

    }

    static int[][] flip(int[][] image) {

        for (int[] row : image) {
            for (int i = 0; i < (row.length + 1) / 2; i++) {
                // insted of (row.length) we can take (image[0].length)
                // but it would be assume length of row[0] only
                int temp = row[i] ^ 1;
                row[i] = row[row.length - i - 1] ^ 1;
                row[row.length - i - 1] = temp;
            }
        }

        return image;
    }
}
