We’ll cover **all three subpatterns** you listed:

1. **Include / Exclude DP (MOST IMPORTANT)**
2. **Diameter DP**
3. **Subtree DP (returning info from children)**

---

# PART 0 — HOW TREE DP IS DIFFERENT FROM NORMAL DP

## Key Difference

In arrays / grids:

* You know the order of states (index, row, column)

In trees:

* There is **no fixed order**
* A node depends on its **children**

So we use:

> **POSTORDER DFS (children first, then parent)**

That is the golden rule of Tree DP.

---

## Universal Tree DP Template

```java
int dfs(TreeNode node) {
    if (node == null) return base;

    int left = dfs(node.left);
    int right = dfs(node.right);

    // compute answer using left & right
    return something;
}
```

Everything you’ll see is a variation of this.

---

# 1️⃣ INCLUDE / EXCLUDE DP (House Robber on Tree)

### MOST IMPORTANT TREE DP PATTERN

---

## 🎯 Problem (House Robber III – LC 337)

* You are given a binary tree
* Each node has money
* You **cannot rob two directly connected nodes**
* Maximize money

---

## THINKING (THIS IS THE KEY)

At every node, you have **two choices**:

### Choice 1: INCLUDE this node

* You **cannot include its children**
* You can include **grandchildren**

### Choice 2: EXCLUDE this node

* You are free to include or exclude children

---

## 🧩 DP STATE (VERY IMPORTANT)

For every node, store **two values**:

```
[0] = max money if we EXCLUDE this node
[1] = max money if we INCLUDE this node
```

So DFS returns an array of size 2.

---

## TRANSITION LOGIC

Let:

* `left[0], left[1]` → result from left child
* `right[0], right[1]` → result from right child

### INCLUDE current node

```
include = node.val + left[0] + right[0]
```

(children must be excluded)

---

### EXCLUDE current node

```
exclude = max(left[0], left[1]) +
          max(right[0], right[1])
```

(children choose best option)

---

## WHY THIS WORKS

We are **locally optimal** at each node while respecting constraints.
Tree structure guarantees no overlap.

---

## ✅ CODE — Include / Exclude DP

```java
class Solution {
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // returns [exclude, include]
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int include = node.val + left[0] + right[0];
        int exclude = Math.max(left[0], left[1]) +
                      Math.max(right[0], right[1]);

        return new int[]{exclude, include};
    }
}
```

---

## CONCEPT TAKEAWAY

| State   | Meaning                            |
| ------- | ---------------------------------- |
| include | take node, skip children           |
| exclude | skip node, choose best of children |

This pattern appears in **many tree problems**.

---

# 2️⃣ DIAMETER DP (Longest Path in Tree)

---

## 🎯 Problem (Diameter of Binary Tree – LC 543)

* Diameter = **longest path between any two nodes**
* Path may or may not pass through root

---

## KEY OBSERVATION

For any node:

* Longest path **through** that node =

```
height(left) + height(right)
```

We compute heights bottom-up.

---

## 🧩 DP MEANING

DFS returns:

```
height of subtree rooted at this node
```

And we maintain a **global maximum diameter**.

---

## TRANSITION

```
leftHeight = dfs(left)
rightHeight = dfs(right)

diameter = max(diameter, leftHeight + rightHeight)

return 1 + max(leftHeight, rightHeight)
```

---

## ✅ CODE — Diameter DP

```java
class Solution {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}
```

---

## WHY GLOBAL VARIABLE?

Because:

* Height is returned upward
* Diameter is a **side effect** computed at each node

This is common in tree DP.

---

## CONCEPT TAKEAWAY

| Return value | Used for           |
| ------------ | ------------------ |
| height       | parent computation |
| diameter     | global answer      |

---

# 3️⃣ SUBTREE DP (Return Multiple Values)

---

## 🎯 What is Subtree DP?

Each node:

* computes some information about its subtree
* returns it to parent
* parent combines child results

This is **the most general form of Tree DP**.

---

## 🎯 Example Problem: Maximum Path Sum (LC 124)

* Path can start & end anywhere
* Maximize sum

---

## THINKING

At each node:

* We want the **best path going downward**
* But also update **global max including both children**

---

## 🧩 DP MEANING

DFS returns:

```
max downward path sum starting from this node
```

---

## TRANSITION

```
left = max(0, dfs(left))
right = max(0, dfs(right))

maxPath = node.val + left + right
update global max

return node.val + max(left, right)
```

---

## ✅ CODE — Subtree DP

```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        // update global answer
        maxSum = Math.max(maxSum, node.val + left + right);

        // return best downward path
        return node.val + Math.max(left, right);
    }
}
```

---

## WHY `max(0, ...)`?

Because:

* Negative paths reduce sum
* We are free to **ignore a subtree**

---

# HOW TO IDENTIFY TREE DP IN INTERVIEWS

If problem says:

* tree
* maximize / minimize
* path
* include / exclude
* subtree
* diameter
* distance between nodes

👉 **Think: DFS + return value**

---

# MASTER SUMMARY (VERY IMPORTANT)

| Pattern           | Core Idea           |
| ----------------- | ------------------- |
| Include / Exclude | return two states   |
| Diameter DP       | height + global max |
| Subtree DP        | return info upward  |

---

# ONE-LINE MEMORY RULE

> **Tree DP = Postorder DFS + combine children results**
