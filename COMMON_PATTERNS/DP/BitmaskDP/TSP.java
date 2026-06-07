package DP.BitmaskDP;
import java.util.*;

public class TSP {
    int n;  // 4
    int[][] dist;
    int[][] dp;

    public int tsp(int[][] dist) {
        this.dist = dist;
        this.n = dist.length;
        int maxMask = 1 << n;   // 16

        dp = new int[maxMask][n];
        for (int i = 0; i < maxMask; i++) {
            Arrays.fill(dp[i], -1);
        }

        // start from city 0, mask = 1 (only city 0 visited)
        return dfs(1, 0);
    }

    private int dfs(int mask, int pos) {
        // if all cities visited
        if (mask == (1 << n) - 1) { // 15 (1111)
            return dist[pos][0]; // return to start
        }

        // check memo
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int ans = Integer.MAX_VALUE;

        // try visiting next city
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newMask = mask | (1 << city);   // mark visited
                int cost = dist[pos][city] + dfs(newMask, city);
                ans = Math.min(ans, cost);
            }
        }

        dp[mask][pos] = ans;
        return ans;
    }
}