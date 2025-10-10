package OptimiseCodes;

import java.util.*;

public class DFSExample {

    // Method to perform DFS
    public static void dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) return;

        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            dfs(graph, neighbor, visited);
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph as an adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3, 4));
        graph.put(2, Arrays.asList(0, 4));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1, 2));

        Set<Integer> visited = new HashSet<>();
        System.out.println("DFS starting from node 0:");
        dfs(graph, 0, visited);
    }
}
