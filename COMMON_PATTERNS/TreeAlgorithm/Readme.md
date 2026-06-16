This is the **last major structural pattern** — and it ties together **trees + recursion + range queries + optimization**.

---

# PART 0 — BIG PICTURE

Tree algorithms fall into 3 categories:

## 1️⃣ Basic Tree Logic

* BST operations
* Path Sum
* Diameter

## 2️⃣ Advanced Tree Queries

* LCA (Lowest Common Ancestor)

## 3️⃣ Range Query Trees

* Segment Tree
* Fenwick Tree (BIT)

## 4️⃣ Balanced Trees

* AVL Tree

---

# 1️⃣ BST (Binary Search Tree)

## 🎯 Property

```text
Left < Root < Right
```

---

## Why BST?

* Fast search: `O(log n)`
* Sorted structure

---

## Operations

### Search

```java
public TreeNode search(TreeNode root, int key) {
    if (root == null || root.val == key) return root;

    if (key < root.val)
        return search(root.left, key);
    else
        return search(root.right, key);
}
```

---

### Insert

```java
public TreeNode insert(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);

    if (val < root.val)
        root.left = insert(root.left, val);
    else
        root.right = insert(root.right, val);

    return root;
}
```

---

### Delete (important)

3 cases:

1. Leaf → delete
2. One child → replace
3. Two children → replace with inorder successor

```java
public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;

    if (key < root.val)
        root.left = deleteNode(root.left, key);
    else if (key > root.val)
        root.right = deleteNode(root.right, key);
    else {
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        TreeNode min = findMin(root.right);
        root.val = min.val;
        root.right = deleteNode(root.right, min.val);
    }
    return root;
}

TreeNode findMin(TreeNode node) {
    while (node.left != null) node = node.left;
    return node;
}
```

---

# 2️⃣ AVL TREE (SELF-BALANCING BST)

## 🎯 Problem

BST can become skewed:

```text
1 → 2 → 3 → 4
```

→ becomes O(n)

---

## AVL Fix

Maintain **balance factor**:

```text
balance = height(left) - height(right)
```

Must be:

```text
-1, 0, 1
```

---

## 🔄 Rotations

| Case | Rotation     |
| ---- | ------------ |
| LL   | Right rotate |
| RR   | Left rotate  |
| LR   | Left + Right |
| RL   | Right + Left |

---

## Example Rotation (Right Rotate)

```java
TreeNode rightRotate(TreeNode y) {
    TreeNode x = y.left;
    TreeNode T2 = x.right;

    x.right = y;
    y.left = T2;

    return x;
}
```

---

# 3️⃣ SEGMENT TREE (VERY IMPORTANT)

## 🎯 Problem

Efficient:

* Range queries
* Range updates

Example:

```text
sum of range [l, r]
```

---

## Idea

Divide array into segments:

```text
[0-7]
 /   \
[0-3] [4-7]
```

---

## Build Tree

```java
class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] nums) {
        n = nums.length;
        tree = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
        } else {
            int mid = (start + end) / 2;
            build(nums, 2*node+1, start, mid);
            build(nums, 2*node+2, mid+1, end);
            tree[node] = tree[2*node+1] + tree[2*node+2];
        }
    }
}
```

---

## Query

```java
int query(int node, int start, int end, int l, int r) {
    if (r < start || l > end) return 0;

    if (l <= start && end <= r) return tree[node];

    int mid = (start + end) / 2;

    return query(2*node+1, start, mid, l, r)
         + query(2*node+2, mid+1, end, l, r);
}
```

---

# 4️⃣ FENWICK TREE (BIT)

## 🎯 Faster alternative to segment tree

Supports:

```text
prefix sum
updates
```

---

## Trick

Uses binary representation:

```text
i & (-i)
```

---

## Code

```java
class Fenwick {

    int[] bit;
    int n;

    Fenwick(int n) {
        this.n = n;
        bit = new int[n+1];
    }

    void update(int i, int val) {
        while (i <= n) {
            bit[i] += val;
            i += i & (-i);
        }
    }

    int sum(int i) {
        int s = 0;
        while (i > 0) {
            s += bit[i];
            i -= i & (-i);
        }
        return s;
    }
}
```

---

# 5️⃣ LCA (Lowest Common Ancestor)

## 🎯 Problem

Find lowest node that has both nodes as descendants.

---

## Idea (Binary Tree)

```java
public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q)
        return root;

    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);

    if (left != null && right != null)
        return root;

    return left != null ? left : right;
}
```

---

# 6️⃣ TREE DIAMETER

## 🎯 Longest path between any two nodes

---

## Idea

Same as earlier DP:

```java
int dfs(node) {
    left = dfs(left)
    right = dfs(right)

    diameter = max(diameter, left + right)

    return 1 + max(left, right)
}
```

---

# 7️⃣ PATH SUM

## 🎯 Problem

Check if root → leaf sum equals target

---

## Code

```java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;

    if (root.left == null && root.right == null)
        return sum == root.val;

    return hasPathSum(root.left, sum - root.val) ||
           hasPathSum(root.right, sum - root.val);
}
```

---

# FINAL MASTER SUMMARY

| Pattern      | Use                    |
| ------------ | ---------------------- |
| BST          | search, insert, delete |
| AVL          | balanced BST           |
| Segment Tree | range query            |
| Fenwick      | prefix sum             |
| LCA          | ancestor queries       |
| Diameter     | longest path           |
| Path Sum     | path checking          |

---

# HOW TO IDENTIFY

If problem says:

```text
tree
ancestor
range query
sum range
balanced
height
path
```

👉 Think tree algorithms.

---

# FINAL ONE-LINE RULE

> **Tree problems = recursion + combine results from children**