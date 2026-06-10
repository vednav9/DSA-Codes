package GraphAlgorithm;
import java.util.*;

// Edge class
class Edge implements Comparable<Edge> {
    int src, dest, weight;

    // Constructor
    Edge(int s, int d, int w) {
        this.src = s;
        this.dest = d;
        this.weight = w;
    }

    // Sort edges by weight
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

// Disjoint Set (Union-Find)
class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find with path compression
    int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    // Union by rank
    void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}

public class Kruskals {

    public static void kruskal(int vertices, List<Edge> edges) {
        Collections.sort(edges); // sort edges by weight

        DisjointSet ds = new DisjointSet(vertices);

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;

            // Check if adding this edge forms a cycle
            if (ds.find(u) != ds.find(v)) {
                mst.add(edge);
                totalWeight += edge.weight;
                ds.union(u, v);
            }
        }

        // Print MST
        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
        }

        System.out.println("Total Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        int vertices = 4;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        kruskal(vertices, edges);
    }
}