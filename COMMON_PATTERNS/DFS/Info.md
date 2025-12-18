# DFS PATTERN — CORE IDEA

DFS (Depth-First Search) means:

- Go as deep as possible first, then backtrack.

It is usually implemented using:

- Recursion (most common, clean)

- Stack (iterative)

DFS is perfect when:

- You need to explore all paths

- You need postorder information

- You need to mark visited nodes

- You need to detect cycles / components

# UNIVERSAL DFS TEMPLATE (MEMORIZE)
Recursive DFS (Graph / Tree / Grid)
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
Everything else is a variation of this.

# DFS FOR TREES

Trees are acyclic, so:

- No visited array needed (usually)

- Just recurse left & right

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