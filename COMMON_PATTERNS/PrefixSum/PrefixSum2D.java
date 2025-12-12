package COMMON_PATTERNS.PrefixSum;

public class PrefixSum2D {
    public int[][] buildPrefix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] prefix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                prefix[i][j] = mat[i][j]
                        + (i > 0 ? prefix[i - 1][j] : 0)
                        + (j > 0 ? prefix[i][j - 1] : 0)
                        - (i > 0 && j > 0 ? prefix[i - 1][j - 1] : 0);
            }
        }
        return prefix;
    }
}