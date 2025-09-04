/*
 * Construct Graph
 * BFS
 * DFS
 * All Path from source to desctination
 */

package Graph;

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

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(1, 2));
        graph[0].add(new Edge(1, 3));
        graph[0].add(new Edge(2, 0));
        graph[0].add(new Edge(2, 1));
        graph[0].add(new Edge(2, 3));
    }

    public static void bfs(ArrayList<Edge> graph[], int V){
        Queue<Integer> queue=new LinkedList<>();
        boolean vis[]=new boolean[V];
        queue.add(0);

        while (queue!=null) {
            int curr=queue.remove();
            if (vis[curr]==false) {
                System.out.println(curr+" ");
                vis[curr]=true;

                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e=graph[curr].get(i);
                    queue.add(e.dest);
                }
            }
        }
    }

    public static void bfsDisjoint(ArrayList<Edge> graph[], int V, boolean[] vis, int start){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);

        while (queue!=null) {
            int curr=queue.remove();
            if (vis[curr]==false) {
                System.out.println(curr+" ");
                vis[curr]=true;

                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e=graph[curr].get(i);
                    queue.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] vis){
        System.out.println(curr+" ");
        vis[curr]=true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e=graph[curr].get(i);
            dfs(graph, e.dest, vis);
        }
    }

    public static void findAllPath(ArrayList<Edge>[] graph, boolean[] vis, int curr, String path, int target){
        if(curr==target){
            System.out.println(path);
            return;
        }

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e=graph[curr].get(i);
            if (!vis[e.dest]) {
                vis[curr]=true;
                findAllPath(graph, vis, e.dest, path+e.dest, target);
                vis[curr]=false;
            }
        }
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);

        // Print
        for (int i = 0; i < graph[2].size(); i++) {
            Edge e = graph[2].get(i);
            System.out.println(e.dest + "");
        }
        createGraph(graph);

        bfs(graph, V);

        // For disjoint

        boolean[] vis=new boolean[V];
        for (int i = 0; i < vis.length; i++) {
            if (vis[i]==false) {
                bfsDisjoint(graph, V, vis, i);                
            }
        }
        
        boolean[] visited=new boolean[V];
        // Disjoint for loop
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]==false) {
                dfs(graph, 0, visited);
            }
        }

        // Find All Path

        findAllPath(graph, new boolean[V], 0, "0", 5);

    }
}