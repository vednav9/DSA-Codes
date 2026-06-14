package MatrixTraversal;
import java.util.*;

public class BFSMatrix {
    static int rows = 4, cols = 4;

    static int[][] directions = {
        {1, 0},   // down
        {-1, 0},  // up
        {0, 1},   // right
        {0, -1}   // left
    };

    public static void bfs(int[][] grid, boolean[][] visited, int startRow, int startCol) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // System.out.println("Visited: " + r + "," + c);
            System.out.println(grid[r][c]);

            for (int[] dir : directions) {
                int newRow = r + dir[0];
                int newCol = c + dir[1];

                if (newRow >= 0 && newCol >= 0 && newRow < rows && newCol < cols
                        && !visited[newRow][newCol]) {

                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
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

        bfs(grid, visited, 0, 0);
    }
}