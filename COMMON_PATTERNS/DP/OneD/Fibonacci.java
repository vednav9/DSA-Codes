package DP.OneD;

public class Fibonacci {
    public static void main(String[] args) {
        Solution s=new Solution();
        int a=s.fib(5);
        System.out.println(a);
    }
}
// DP Solution — Memoization
class Solution {
    int[] memo;

    public int fib(int n) {
        memo = new int[n+1];
        return solve(n);
    }

    int solve(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];

        memo[n] = solve(n-1) + solve(n-2);
        return memo[n];
    }
}
// DP Solution — Tabulation (Bottom-Up)
class Solution1 {
    public int fib(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }
}
// Space Optimized DP
class Solution3 {
    public int fib(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0, prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
