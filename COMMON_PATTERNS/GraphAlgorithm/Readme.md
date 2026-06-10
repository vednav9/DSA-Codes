This is the **final advanced graph layer** — once you master this, you’re at **top-tier interview level**.

---

# PART 0 — BIG PICTURE (VERY IMPORTANT)

All these algorithms solve **two types of problems**:

## 1️⃣ Shortest Path Problems

* Dijkstra
* Bellman-Ford
* Floyd-Warshall

## 2️⃣ Minimum Spanning Tree (MST)

* Kruskal
* Prim

## 3️⃣ Connectivity / Components

* Union Find (DSU)

---

# PART 1 — DIJKSTRA (Shortest Path, Non-negative weights)

## 🎯 Problem

Find shortest distance from source → all nodes.

---

## KEY IDEA

Always expand the **closest node first**.

👉 Greedy + Min Heap

---

## 🪜 Steps

1. Initialize distance array

```java
dist[src] = 0
others = ∞
```

2. Use min heap:

```
(node, distance)
```

3. Pop smallest distance node
4. Relax edges:

```
if newDist < oldDist → update
```

---

## ❗ Works only for:

```
non-negative weights
```

---

## ✅ Code

```java
public int[] dijkstra(int n, List<List<int[]>> graph, int src) {

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
    pq.offer(new int[]{src, 0});

    while(!pq.isEmpty()){

        int[] curr = pq.poll();
        int node = curr[0], d = curr[1];

        if(d > dist[node]) continue;

        for(int[] nei : graph.get(node)){
            int next = nei[0];
            int weight = nei[1];

            if(dist[node] + weight < dist[next]){
                dist[next] = dist[node] + weight;
                pq.offer(new int[]{next, dist[next]});
            }
        }
    }

    return dist;
}
```

---

# PART 2 — BELLMAN-FORD

## 🎯 Problem

Handles **negative weights**

---

## KEY IDEA

Relax all edges **n-1 times**

Why?
Because longest path can have **n-1 edges**

---

## 🪜 Steps

1. Initialize distances
2. Repeat `n-1` times:

   * relax all edges
3. Detect negative cycle:

   * if still improves → cycle exists

---

## ✅ Code

```java
public int[] bellmanFord(int n, int[][] edges, int src){

    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    for(int i=0;i<n-1;i++){
        for(int[] e : edges){
            int u=e[0], v=e[1], w=e[2];

            if(dist[u]!=Integer.MAX_VALUE &&
               dist[u]+w < dist[v]){
                dist[v] = dist[u]+w;
            }
        }
    }

    // check negative cycle
    for(int[] e : edges){
        int u=e[0], v=e[1], w=e[2];
        if(dist[u]!=Integer.MAX_VALUE &&
           dist[u]+w < dist[v]){
            System.out.println("Negative cycle detected");
        }
    }

    return dist;
}
```

---

# PART 3 — FLOYD-WARSHALL

## 🎯 Problem

Find shortest path **between all pairs**

---

## KEY IDEA

Try every node as an intermediate:

```
dist[i][j] = min(
    dist[i][j],
    dist[i][k] + dist[k][j]
)
```

---

## 🪜 Steps

3 nested loops:

```java
for k
  for i
    for j
```

---

## ✅ Code

```java
public void floydWarshall(int[][] dist){

    int n = dist.length;

    for(int k=0;k<n;k++){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){

                if(dist[i][k] != Integer.MAX_VALUE &&
                   dist[k][j] != Integer.MAX_VALUE){

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }
    }
}
```

---

# PART 4 — MINIMUM SPANNING TREE (MST)

## 🎯 Goal

Connect all nodes with:

```
minimum total edge weight
```

---

# 4️⃣ KRUSKAL’S ALGORITHM

## KEY IDEA

* Sort edges by weight
* Add smallest edge if it doesn’t form cycle

👉 Uses **Union Find**

---

## ✅ Code

```java
public int kruskal(int n, int[][] edges){

    Arrays.sort(edges, (a,b)->a[2]-b[2]);

    UnionFind uf = new UnionFind(n);
    int cost = 0;

    for(int[] e: edges){
        int u=e[0], v=e[1], w=e[2];

        if(uf.find(u)!=uf.find(v)){
            uf.union(u,v);
            cost += w;
        }
    }

    return cost;
}
```

---

# 5️⃣ PRIM’S ALGORITHM

## KEY IDEA

* Start from any node
* Always pick smallest edge expanding tree

👉 Similar to Dijkstra

---

## ✅ Code

```java
public int prim(int n, List<List<int[]>> graph){

    boolean[] visited = new boolean[n];
    PriorityQueue<int[]> pq =
        new PriorityQueue<>((a,b)->a[1]-b[1]);

    pq.offer(new int[]{0,0});
    int cost = 0;

    while(!pq.isEmpty()){

        int[] curr = pq.poll();
        int node = curr[0], w = curr[1];

        if(visited[node]) continue;

        visited[node] = true;
        cost += w;

        for(int[] nei: graph.get(node)){
            if(!visited[nei[0]])
                pq.offer(nei);
        }
    }

    return cost;
}
```

---

# PART 6 — UNION FIND (DSU)

## 🎯 Problem

Track connected components efficiently.

---

## KEY IDEA

Each node has a **parent**

Operations:

```
find(x) → root
union(x,y) → merge sets
```

---

## Optimizations

* Path compression
* Union by rank

---

## ✅ Code

```java
class UnionFind {

    int[] parent, rank;

    public UnionFind(int n){
        parent = new int[n];
        rank = new int[n];

        for(int i=0;i<n;i++)
            parent[i] = i;
    }

    public int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y){
        int px = find(x), py = find(y);

        if(px == py) return;

        if(rank[px] < rank[py])
            parent[px] = py;
        else if(rank[px] > rank[py])
            parent[py] = px;
        else{
            parent[py] = px;
            rank[px]++;
        }
    }
}
```

---

# FINAL MASTER SUMMARY

| Algorithm      | Use Case                    |
| -------------- | --------------------------- |
| Dijkstra       | shortest path (no negative) |
| Bellman-Ford   | handles negative            |
| Floyd-Warshall | all-pairs shortest path     |
| Kruskal        | MST using edges             |
| Prim           | MST using nodes             |
| Union Find     | connectivity                |

---

# HOW TO IDENTIFY WHICH TO USE

| Problem says                   | Use            |
| ------------------------------ | -------------- |
| Shortest path                  | Dijkstra       |
| Negative weights               | Bellman-Ford   |
| All pairs                      | Floyd-Warshall |
| Minimum spanning tree          | Kruskal / Prim |
| Cycle detection / connectivity | Union Find     |

---

# ONE-LINE MEMORY RULE

> **Graphs = choose shortest path or minimum connection strategy**