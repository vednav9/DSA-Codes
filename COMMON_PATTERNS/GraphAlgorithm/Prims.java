package GraphAlgorithm;

import java.util.List;
import java.util.PriorityQueue;

public class Prims {
    public int prim(int n, List<List<int[]>> graph) {

        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[] { 0, 0 });
        int cost = 0;

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();
            int node = curr[0], w = curr[1];

            if (visited[node])
                continue;

            visited[node] = true;
            cost += w;

            for (int[] nei : graph.get(node)) {
                if (!visited[nei[0]])
                    pq.offer(nei);
            }
        }

        return cost;
    }
}