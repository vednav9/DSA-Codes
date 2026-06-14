package MatrixTraversal;

public class Rotate {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // reverse rows
        for (int[] row : matrix) {
            int l = 0, r = n - 1;
            while (l < r) {
                int temp = row[l];
                row[l] = row[r];
                row[r] = temp;
                l++;
                r--;
            }
        }
    }

    // OR

    public void rotate2(int[][] matrix) {
        //flip
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - i][j];
                matrix[length - 1 - i][j] = temp;
            }
        }
        // transpose
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }
}