package DP.KnapSack;

public class UnboundedKnapsack {
    public int unboundedKnapsack(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            val[i - 1] + dp[i][w - wt[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Initialize with large value (amount + 1 works as infinity)
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= amount; w++) {
                dp[i][w] = amount + 1;
            }
        }

        // Base case: 0 coins needed for amount 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= amount; w++) {

                if (coins[i - 1] <= w) {
                    dp[i][w] = Math.min(
                            dp[i - 1][w], // don't take
                            1 + dp[i][w - coins[i - 1]] // take (unbounded)
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][amount] > amount ? -1 : dp[n][amount];
    }
}
