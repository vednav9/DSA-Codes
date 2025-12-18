package COMMON_PATTERNS.DFS;
import java.util.List;
import java.util.ArrayList;

public class CycleDetectionUndirected {
    static boolean hasCycleUndirected(int node, int parent,
                                      List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                if (hasCycleUndirected(nei, node, graph, visited))
                    return true;
            } else if (nei != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes (0 to 4)

        // Initialize graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add undirected edges
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0); // creates a cycle
        addEdge(graph, 3, 4); // separate component, no cycle

        boolean[] visited = new boolean[n];
        boolean hasCycle = false;

        // Check all components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycleUndirected(i, -1, graph, visited)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        System.out.println("Graph has cycle: " + hasCycle);
    }

    static void addEdge(List<List<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
}
