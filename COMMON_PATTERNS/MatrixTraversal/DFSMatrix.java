package MatrixTraversal;

public class DFSMatrix {
    static int rows = 4, cols = 4;

    static int[][] directions = {
        {1, 0},   // down
        {-1, 0},  // up
        {0, 1},   // right
        {0, -1}   // left
    };

    public static void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        // Boundary check
        if (r < 0 || c < 0 || r >= rows || c >= cols || visited[r][c])
            return;

        // Mark visited
        visited[r][c] = true;

        // System.out.println("Visited: " + r + "," + c);
        System.out.println(grid[r][c]);

        // Explore neighbors
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            dfs(grid, visited, newRow, newCol);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };

        boolean[][] visited = new boolean[rows][cols];

        dfs(grid, visited, 0, 0);
    }
}