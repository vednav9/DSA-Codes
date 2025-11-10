import java.util.*;

public class Graph {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    // Create graph
    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 2));
    }

    // BFS (single component)
    public static void bfs(ArrayList<Edge>[] graph, int start, boolean[] vis) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        vis[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.remove();
            System.out.print(curr + " ");

            for (Edge e : graph[curr]) {
                if (!vis[e.dest]) {
                    vis[e.dest] = true;
                    queue.add(e.dest);
                }
            }
        }
    }

    // BFS for disconnected graph
    public static void bfsDisjoint(ArrayList<Edge>[] graph, int V) {
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) bfs(graph, i, vis);
        }
    }

    // DFS (recursive)
    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] vis) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) dfs(graph, e.dest, vis);
        }
    }

    // DFS for disconnected graph
    public static void dfsDisjoint(ArrayList<Edge>[] graph, int V) {
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) dfs(graph, i, vis);
        }
    }

    // Find all paths from source to destination
    public static void findAllPaths(ArrayList<Edge>[] graph, boolean[] vis, int curr, String path, int target) {
        if (curr == target) {
            System.out.println(path);
            return;
        }

        vis[curr] = true;
        for (Edge e : graph[curr]) {
            if (!vis[e.dest]) {
                findAllPaths(graph, vis, e.dest, path + " -> " + e.dest, target);
            }
        }
        vis[curr] = false; // backtrack
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        System.out.println("Adjacency List:");
        for (int i = 0; i < V; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e.dest + " ");
            }
            System.out.println();
        }

        System.out.println("\nBFS traversal:");
        bfsDisjoint(graph, V);

        System.out.println("\n\nDFS traversal:");
        dfsDisjoint(graph, V);

        System.out.println("\n\nAll paths from 0 to 3:");
        findAllPaths(graph, new boolean[V], 0, "0", 3);
    }
}