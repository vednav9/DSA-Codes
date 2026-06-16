package TopologicalSort;
import java.util.*;

public class KahnsAlgo {
    public int[] topoSort(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int[] indegree = new int[n];

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] result = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {

            int node = q.poll();
            result[idx++] = node;

            for (int nei : graph.get(node)) {
                indegree[nei]--;

                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        }

        return result;
    }
}