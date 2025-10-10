package OptimiseCodes;

import java.util.*;

class Main {
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // create edges
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0);  // forms a cycle
        addEdge(graph, 2, 3);

        boolean[] visited = new boolean[V];
        boolean hasCycle = false;

        // check each component (for disconnected graphs)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCycle(graph, i, visited, -1)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) System.out.println("Cycle found");
        else System.out.println("No cycle");
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> graph, int curr, boolean[] visited, int parent) {
        visited[curr] = true;

        for (int neighbor : graph.get(curr)) {
            if (!visited[neighbor]) {
                if (isCycle(graph, neighbor, visited, curr)) {
                    return true;
                }
            } else if (neighbor != parent) {
                // visited neighbor that is NOT parent → cycle
                return true;
            }
        }
        return false;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }
}