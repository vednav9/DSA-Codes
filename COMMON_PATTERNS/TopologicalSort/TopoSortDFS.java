package TopologicalSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopoSortDFS {
    public List<Integer> topoSortDFS(int n, List<List<Integer>> graph) {

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i])
                dfs(i, graph, visited, stack);
        }

        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty())
            result.add(stack.pop());

        return result;
    }

    private void dfs(int node,
            List<List<Integer>> graph,
            boolean[] visited,
            Stack<Integer> stack) {

        visited[node] = true;

        for (int nei : graph.get(node)) {
            if (!visited[nei])
                dfs(nei, graph, visited, stack);
        }

        stack.push(node);
    }
}
