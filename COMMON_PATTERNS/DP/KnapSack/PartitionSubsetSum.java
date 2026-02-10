package DP.KnapSack;

public class PartitionSubsetSum {
    public boolean canPartition(int[] nums) {
        
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // If total sum is odd, cannot partition
        if (totalSum % 2 != 0) return false;
        
        int halfSum = totalSum / 2;
        int n = nums.length;
        
        boolean[][] dp = new boolean[n + 1][halfSum + 1];
        
        // Sum 0 is always possible
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= halfSum; s++) {
                
                if (nums[i - 1] <= s) {
                    dp[i][s] = dp[i - 1][s] || 
                               dp[i - 1][s - nums[i - 1]];
                } else {
                    dp[i][s] = dp[i - 1][s];
                }
            }
        }
        
        return dp[n][halfSum];
    }
}