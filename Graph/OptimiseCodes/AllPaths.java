package OptimiseCodes;

import java.util.*;

class AllPaths {
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // create empty lists for each vertex
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // add edges
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(3);

        int src = 0, tar = 3;
        ArrayList<Integer> path = new ArrayList<>();

        printAllPaths(graph, src, tar, path);
    }

    public static void printAllPaths(ArrayList<ArrayList<Integer>> graph, int src, int tar, ArrayList<Integer> path) {
        // add current node to path
        path.add(src);

        // base case: if reached target
        if (src == tar) {
            System.out.println(path);
            path.remove(path.size() - 1);
            return;
        }

        // visit all neighbors
        for (int neighbor : graph.get(src)) {
            printAllPaths(graph, neighbor, tar, path);
        }

        // backtrack (remove current node)
        path.remove(path.size() - 1);
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans=new ArrayList<>();
        return helper(graph, 0, ans);
    }

    public static List<List<Integer>> helper(int[][] graph, int src, List<List<Integer>> ans){
        
        return ans;
    }
}