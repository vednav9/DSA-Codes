package DP.OneD;

public class HouseRobber {
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 2, 7, 9, 3, 1 };
        System.out.println(rob(arr));
    }

    class Solution {
        public int rob(int[] nums) {
            int prev2 = 0;
            int prev1 = 0;

            for (int n : nums) {
                int curr = Math.max(prev1, prev2 + n);
                prev2 = prev1;
                prev1 = curr;
            }
            return prev1;
        }
    }
}