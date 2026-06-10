package GraphAlgorithm;
import java.util.*;

public class BellmanFord {
    public int[] bellmanFord(int n, int[][] edges, int src) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];

                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // check negative cycle
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != Integer.MAX_VALUE &&
                    dist[u] + w < dist[v]) {
                System.out.println("Negative cycle detected");
            }
        }

        return dist;
    }
}