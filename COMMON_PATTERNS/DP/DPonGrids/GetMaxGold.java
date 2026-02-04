package DP.DPonGrids;

public class GetMaxGold {
    public int getMaxGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        // last column
        for (int i = 0; i < m; i++)
            dp[i][n - 1] = grid[i][n - 1];

        for (int j = n - 2; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                int right = dp[i][j + 1];
                int rightUp = (i > 0) ? dp[i - 1][j + 1] : 0;
                int rightDown = (i < m - 1) ? dp[i + 1][j + 1] : 0;

                dp[i][j] = grid[i][j] +
                        Math.max(right,
                                Math.max(rightUp, rightDown));
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++)
            ans = Math.max(ans, dp[i][0]);

        return ans;
    }
}
