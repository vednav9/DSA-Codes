package MatrixTraversal;

public class CountComponents {
    public int countComponents(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfs(int[][] grid, boolean[][] vis, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
                || vis[r][c] || grid[r][c] == 0)
            return;

        vis[r][c] = true;

        dfs(grid, vis, r + 1, c);
        dfs(grid, vis, r - 1, c);
        dfs(grid, vis, r, c + 1);
        dfs(grid, vis, r, c - 1);
    }
}