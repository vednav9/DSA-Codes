package OptimiseCodes;
import java.util.*;

class CycleDirected {
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Directed edges
        addEdge(graph, 0, 1);
        addEdge(graph, 1, 2);
        addEdge(graph, 2, 0); // forms a cycle
        addEdge(graph, 2, 3);

        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        boolean hasCycle = false;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclic(graph, i, visited, recStack)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) System.out.println("Cycle found");
        else System.out.println("No cycle");
    }

    public static boolean isCyclic(ArrayList<ArrayList<Integer>> graph, int curr, boolean[] visited, boolean[] recStack) {
        visited[curr] = true;
        recStack[curr] = true;  // mark current node in recursion path

        for (int neighbor : graph.get(curr)) {
            if (!visited[neighbor]) {
                if (isCyclic(graph, neighbor, visited, recStack)) {
                    return true;
                }
            } else if (recStack[neighbor]) {
                // visited and still in recursion stack → cycle
                return true;
            }
        }

        recStack[curr] = false; // done exploring current node
        return false;
    }

    public static void addEdge(ArrayList<ArrayList<Integer>> graph, int u, int v) {
        graph.get(u).add(v);
    }
}
