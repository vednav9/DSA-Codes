BFS — CORE IDEA

**BFS (Breadth-First Search)** explores:

> **Level by level**, starting from a source.

Key properties:

* Uses a **Queue**
* Guarantees **shortest path** in **unweighted graphs**
* Perfect for **levels, distances, minimum steps**

---

## UNIVERSAL BFS TEMPLATE (MEMORIZE)

```java
Queue<Integer> q = new LinkedList<>();
boolean[] visited = new boolean[n];

q.offer(start);
visited[start] = true;

while (!q.isEmpty()) {
    int node = q.poll();

    for (int nei : graph[node]) {
        if (!visited[nei]) {
            visited[nei] = true;
            q.offer(nei);
        }
    }
}
```

Everything else is a variation of this.

---

# 1️⃣ LEVEL ORDER TRAVERSAL (TREES)

Used when problem says:

* “level by level”
* “minimum depth”
* “print nodes per level”

---

## ✔ Example: Binary Tree Level Order Traversal (LC 102)

### Trick

Use queue size to separate levels.

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
        int size = q.size();
        List<Integer> level = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            TreeNode node = q.poll();
            level.add(node.val);

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

---

## ✔ Variants

* Zigzag traversal
* Level order bottom-up
* Average of levels

---

# 2️⃣ SHORTEST PATH IN UNWEIGHTED GRAPH

**This is where BFS beats DFS.**

Used when:

* “minimum steps”
* “minimum edges”
* “shortest distance (unweighted)”

---

## ✔ Example: Shortest Path from Source

```java
public int shortestPath(int n, List<List<Integer>> graph, int src, int dest) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[n];
    int[] dist = new int[n];

    q.offer(src);
    visited[src] = true;
    dist[src] = 0;

    while (!q.isEmpty()) {
        int node = q.poll();
        if (node == dest) return dist[node];

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                visited[nei] = true;
                dist[nei] = dist[node] + 1;
                q.offer(nei);
            }
        }
    }
    return -1; // unreachable
}
```

---

## ✔ Key Interview Line

> “Since the graph is unweighted, BFS guarantees the shortest path.”

---

# 3️⃣ MULTI-SOURCE BFS (VERY IMPORTANT)

Used when:

* Multiple starting points exist
* Spread problems
* Distance from nearest source

Examples:

* Rotting Oranges
* Walls and Gates
* 01 Matrix

---

## ✔ Example: Rotting Oranges (LC 994)

### Trick

* Push **ALL sources** into queue first
* BFS expands outward from all simultaneously

```java
public int orangesRotting(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> q = new LinkedList<>();
    int fresh = 0;

    // Step 1: Add all rotten oranges
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 2) q.offer(new int[]{i, j});
            if (grid[i][j] == 1) fresh++;
        }
    }

    int minutes = 0;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    // Step 2: BFS
    while (!q.isEmpty() && fresh > 0) {
        int size = q.size();
        minutes++;

        for (int i = 0; i < size; i++) {
            int[] cell = q.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];

                if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                    grid[r][c] = 2;
                    fresh--;
                    q.offer(new int[]{r, c});
                }
            }
        }
    }

    return fresh == 0 ? minutes : -1;
}
```

---

# 4️⃣ BIDIRECTIONAL BFS (ADVANCED)

Used when:

* Single source → single target
* Search space is huge
* Graph is unweighted

Classic example:

* **Word Ladder (LC 127)**

---

## Idea

Run BFS:

* one from **start**
* one from **end**
  Stop when frontiers meet → faster than normal BFS.

---

## ✔ Simplified Java Example (Conceptual)

```java
public int ladderLength(String begin, String end, Set<String> dict) {
    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    Set<String> visited = new HashSet<>();

    beginSet.add(begin);
    endSet.add(end);
    int level = 1;

    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
        // Always expand smaller frontier
        if (beginSet.size() > endSet.size()) {
            Set<String> temp = beginSet;
            beginSet = endSet;
            endSet = temp;
        }

        Set<String> next = new HashSet<>();

        for (String word : beginSet) {
            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char old = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String newWord = new String(arr);

                    if (endSet.contains(newWord)) return level + 1;
                    if (!visited.contains(newWord) && dict.contains(newWord)) {
                        visited.add(newWord);
                        next.add(newWord);
                    }
                }
                arr[i] = old;
            }
        }
        beginSet = next;
        level++;
    }
    return 0;
}
```

---

## ✔ When to mention in interview?

> “Since this is shortest path and the state space is large, I’ll optimize using bidirectional BFS.”

---

# 5️⃣ GRID BFS (2D MATRIX BFS)

Used for:

* Islands
* Shortest path in matrix
* Fire spread
* Chessboard problems

---

## ✔ Template — Grid BFS

```java
int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
Queue<int[]> q = new LinkedList<>();
boolean[][] visited = new boolean[m][n];

q.offer(new int[]{sr, sc});
visited[sr][sc] = true;

while (!q.isEmpty()) {
    int[] cell = q.poll();

    for (int[] d : dirs) {
        int r = cell[0] + d[0];
        int c = cell[1] + d[1];

        if (r >= 0 && c >= 0 && r < m && c < n && !visited[r][c]) {
            visited[r][c] = true;
            q.offer(new int[]{r, c});
        }
    }
}
```

---

## ✔ Example: Shortest Path in Binary Matrix (LC 1091)

```java
public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;
    if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{0, 0});
    grid[0][0] = 1;

    int[][] dirs = {
        {1,0},{-1,0},{0,1},{0,-1},
        {1,1},{1,-1},{-1,1},{-1,-1}
    };

    int steps = 1;

    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] cell = q.poll();
            if (cell[0] == n-1 && cell[1] == n-1) return steps;

            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && c >= 0 && r < n && c < n && grid[r][c] == 0) {
                    grid[r][c] = 1;
                    q.offer(new int[]{r, c});
                }
            }
        }
        steps++;
    }
    return -1;
}
```

---

# ⭐ BFS MASTER CHEATSHEET

| Subpattern        | Use BFS Because       |
| ----------------- | --------------------- |
| Level order       | process per level     |
| Shortest path     | unweighted graph      |
| Multi-source BFS  | multiple start points |
| Bidirectional BFS | reduce search space   |
| Grid BFS          | matrix traversal      |

---

# ⭐ HOW TO IDENTIFY BFS IN INTERVIEWS

If problem says:

* “minimum steps / moves”
* “shortest path”
* “levels”
* “nearest”
* “spread / infection”
* “simultaneous expansion”

**Think BFS first.**