package GraphAlgorithm;
import java.util.*;

public class Dijkstra {
    public int[] dijkstra(int n, List<List<int[]>> graph, int src) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] { src, 0 });

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();
            int node = curr[0], d = curr[1];

            if (d > dist[node])
                continue;

            for (int[] nei : graph.get(node)) {
                int next = nei[0];
                int weight = nei[1];

                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.offer(new int[] { next, dist[next] });
                }
            }
        }

        return dist;
    }
}