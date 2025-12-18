package COMMON_PATTERNS.DFS;

import java.util.*;

public class CycleDetectionDirected {
    boolean hasCycleDirected(int node,
            List<List<Integer>> graph,
            boolean[] visited,
            boolean[] inStack) {
        visited[node] = true;
        inStack[node] = true;

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                if (hasCycleDirected(nei, graph, visited, inStack))
                    return true;
            } else if (inStack[nei]) {
                return true;
            }
        }

        inStack[node] = false;
        return false;
    }
}