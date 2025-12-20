package COMMON_PATTERNS.BFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPath {
    public int shortestPath(int n, List<List<Integer>> graph, int src, int dest) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        q.offer(src);
        visited[src] = true;
        dist[src] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == dest)
                return dist[node];

            for (int nei : graph.get(node)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    dist[nei] = dist[node] + 1;
                    q.offer(nei);
                }
            }
        }
        return -1; // unreachable
    }
}