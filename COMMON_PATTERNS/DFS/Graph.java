package COMMON_PATTERNS.DFS;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // List<List<Integer>> graph = new ArrayList<>();
    // boolean[] visited = new boolean[n];

    void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                dfs(nei, graph, visited);
            }
        }
    }
}
