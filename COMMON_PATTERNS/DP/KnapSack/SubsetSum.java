package DP.KnapSack;

public class SubsetSum {
    public boolean subsetSum(int[] nums, int target) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= target; s++) {
                if (nums[i - 1] <= s) {
                    dp[i][s] = dp[i - 1][s] ||
                            dp[i - 1][s - nums[i - 1]];
                } else {
                    dp[i][s] = dp[i - 1][s];
                }
            }
        }
        return dp[n][target];
    }
}