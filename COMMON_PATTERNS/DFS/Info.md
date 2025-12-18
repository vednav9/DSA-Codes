# DFS PATTERN — CORE IDEA

**DFS (Depth-First Search)** means:

> Go as deep as possible first, then backtrack.

It is usually implemented using:

* **Recursion** (most common, clean)
* **Stack** (iterative)

DFS is perfect when:

* You need to **explore all paths**
* You need **postorder information**
* You need to **mark visited nodes**
* You need to **detect cycles / components**

---

# UNIVERSAL DFS TEMPLATE (MEMORIZE)

### Recursive DFS (Graph / Tree / Grid)

```java
void dfs(int node) {
    visited[node] = true;

    for (int nei : graph[node]) {
        if (!visited[nei]) {
            dfs(nei);
        }
    }
}
```

Everything else is a **variation of this**.

---

## 1️⃣ DFS FOR TREES

Trees are **acyclic**, so:

* No visited array needed (usually)
* Just recurse left & right

---

### Tree Traversals (DFS)

```java
void dfs(TreeNode root) {
    if (root == null) return;

    // PREORDER
    dfs(root.left);

    // INORDER
    dfs(root.right);

    // POSTORDER
}
```

---

### Example: Maximum Depth of Binary Tree (LC 104)

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);

    return 1 + Math.max(left, right);
}
```

---

## 2️⃣ DFS FOR GRAPHS

Graphs **may contain cycles**, so we MUST track `visited`.

---

### Graph Representation (Adjacency List)

```java
List<List<Integer>> graph = new ArrayList<>();
boolean[] visited = new boolean[n];
```

---

### DFS Traversal

```java
void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;

    for (int nei : graph.get(node)) {
        if (!visited[nei]) {
            dfs(nei, graph, visited);
        }
    }
}
```

---

## 3️⃣ CONNECTED COMPONENTS (VERY IMPORTANT)

Used when problem says:

* “How many groups?”
* “How many islands?”
* “Disconnected graph?”

---

### Example: Number of Connected Components

```java
public int countComponents(int n, int[][] edges) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

    for (int[] e : edges) {
        graph.get(e[0]).add(e[1]);
        graph.get(e[1]).add(e[0]);
    }

    boolean[] visited = new boolean[n];
    int components = 0;

    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i, graph, visited);
            components++;
        }
    }
    return components;
}
```

Each DFS call = **one component**.

---

## 4️⃣ CYCLE DETECTION (DIRECTED & UNDIRECTED)

### 🔹 A) Cycle Detection — Undirected Graph

Key idea:

* Pass **parent**
* If you see a visited node that’s NOT the parent → cycle

---

```java
boolean hasCycleUndirected(int node, int parent,
                           List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;

    for (int nei : graph.get(node)) {
        if (!visited[nei]) {
            if (hasCycleUndirected(nei, node, graph, visited))
                return true;
        } else if (nei != parent) {
            return true;
        }
    }
    return false;
}
```

---

### 🔹 B) Cycle Detection — Directed Graph

Key idea:

* Use **recursion stack**
* If you revisit a node in current path → cycle

---

```java
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
```

Used in:

* Course Schedule
* Dependency problems
* Deadlock detection

---

## 5️⃣ TREE DFS (POSTORDER DP) — VERY IMPORTANT

This is **DFS + Dynamic Programming on Trees**.

Used when:

* Parent depends on children
* You need values from subtrees

---

### Example: Diameter of Binary Tree (LC 543)

```java
int diameter = 0;

public int diameterOfBinaryTree(TreeNode root) {
    dfs(root);
    return diameter;
}

int dfs(TreeNode node) {
    if (node == null) return 0;

    int left = dfs(node.left);
    int right = dfs(node.right);

    diameter = Math.max(diameter, left + right);

    return 1 + Math.max(left, right);
}
```

📌 Pattern:

* DFS returns a value
* Global variable tracks answer

---

### Example: Binary Tree Maximum Path Sum (LC 124)

```java
int maxSum = Integer.MIN_VALUE;

public int maxPathSum(TreeNode root) {
    dfs(root);
    return maxSum;
}

int dfs(TreeNode node) {
    if (node == null) return 0;

    int left = Math.max(0, dfs(node.left));
    int right = Math.max(0, dfs(node.right));

    maxSum = Math.max(maxSum, node.val + left + right);

    return node.val + Math.max(left, right);
}
```

---

## 6️⃣ WORD SEARCH (GRID DFS + BACKTRACKING)

Used in:

* Matrix traversal
* Word search
* Island problems

---

### Problem: Word Search (LC 79)

Key ideas:

* DFS in 4 directions
* Mark visited (temporarily)
* Backtrack

---

```java
public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (dfs(board, word, i, j, 0))
                return true;
        }
    }
    return false;
}

boolean dfs(char[][] board, String word, int i, int j, int idx) {
    if (idx == word.length()) return true;
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
        return false;
    if (board[i][j] != word.charAt(idx))
        return false;

    char temp = board[i][j];
    board[i][j] = '#'; // mark visited

    boolean found =
        dfs(board, word, i + 1, j, idx + 1) ||
        dfs(board, word, i - 1, j, idx + 1) ||
        dfs(board, word, i, j + 1, idx + 1) ||
        dfs(board, word, i, j - 1, idx + 1);

    board[i][j] = temp; // backtrack
    return found;
}
```

---

# HOW TO IDENTIFY DFS IN INTERVIEWS

If problem mentions:

* “Explore all paths”
* “Connected components”
* “Tree traversal”
* “Grid / island”
* “Cycle detection”
* “Backtracking”

Think **DFS** first.

---

# DFS MASTER CHEATSHEET

| Subpattern           | Key Tool           |
| -------------------- | ------------------ |
| Tree DFS             | recursion          |
| Graph DFS            | visited[]          |
| Connected components | DFS count          |
| Cycle (undirected)   | parent             |
| Cycle (directed)     | recursion stack    |
| Tree DP              | postorder DFS      |
| Word search          | DFS + backtracking |
