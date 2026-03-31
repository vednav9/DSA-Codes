Perfect.
**DP on Grids** is where DP becomes *visual* and intuitive.

We’ll cover **all four problems** you listed in a logical learning order:

1. **Core Grid DP mindset (MOST IMPORTANT)**
2. **Unique Paths** (counting DP)
3. **Minimum Path Sum** (cost DP)
4. **Gold Mine** (directional DP)
5. **Cherry Pickup** (advanced multi-state DP)

---

# PART 0 — CORE GRID DP MINDSET (READ CAREFULLY)

## What is a Grid DP problem?

You are given a **2D grid (matrix)** and:

* you start at some cell
* you move only in **allowed directions**
* you want to **count paths**, **minimize cost**, or **maximize reward**

---

## 🔑 The MOST IMPORTANT Rule

In grid DP:

```
dp[i][j] = answer for cell (i, j)
```

And the value at `(i, j)` depends ONLY on:

* cells you could have come from

So the question is always:

> “From which cells can I reach (i, j)?”

---

## Movement → DP transition

| Allowed movement | Transition comes from |
| ---------------- | --------------------- |
| Right, Down      | top, left             |
| Right only       | left                  |
| Down only        | top                   |
| Diagonals        | multiple cells        |

---

# 1️⃣ UNIQUE PATHS (COUNTING DP)

## 🎯 Problem

You are at top-left `(0,0)`
You want to reach bottom-right `(m-1, n-1)`
You can move:

* Right
* Down

👉 Count total number of unique paths.

---

## THINK FIRST (NO CODE)

To reach cell `(i, j)`:

* last step must be from:

  * `(i-1, j)` **(from top)**
  * `(i, j-1)` **(from left)**

So:

```
paths to (i, j) =
paths to (i-1, j) + paths to (i, j-1)
```

---

## 🧩 DP Definition

```
dp[i][j] = number of ways to reach cell (i, j)
```

---

## 🧱 Base Cases

* First row → only **one way** (keep moving right)
* First column → only **one way** (keep moving down)

```
dp[0][*] = 1
dp[*][0] = 1
```

---

## Transition

```
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

---

## ✅ Code

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];

    // base cases
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    return dp[m-1][n-1];
}
```

---

## CONCEPT TAKEAWAY

* This is **counting DP**
* No costs, no min/max
* Just number of ways

---

# 2️⃣ MINIMUM PATH SUM (COST DP)

## 🎯 Problem

Each cell has a cost.
Move right or down.
Find **minimum sum path** from top-left to bottom-right.

---

## THINK FIRST

To reach `(i, j)`:

* you must come from:

  * top `(i-1, j)`
  * left `(i, j-1)`

But now you want the **minimum cost**.

So:

```
min cost to (i, j) =
grid[i][j] + min(cost from top, cost from left)
```

---

## 🧩 DP Definition

```
dp[i][j] = minimum cost to reach cell (i, j)
```

---

## 🧱 Base Cases

* Start cell:

```
dp[0][0] = grid[0][0]
```

* First row: only from left
* First column: only from top

---

## Transition

```
dp[i][j] = grid[i][j] +
           min(dp[i-1][j], dp[i][j-1])
```

---

## ✅ Code

```java
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];

    dp[0][0] = grid[0][0];

    for (int i = 1; i < m; i++)
        dp[i][0] = dp[i-1][0] + grid[i][0];

    for (int j = 1; j < n; j++)
        dp[0][j] = dp[0][j-1] + grid[0][j];

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = grid[i][j] +
                       Math.min(dp[i-1][j], dp[i][j-1]);
        }
    }
    return dp[m-1][n-1];
}
```

---

## CONCEPT TAKEAWAY

* Same grid as Unique Paths
* Only change: **sum → min sum**
* Counting DP → Cost DP

---

# 3️⃣ GOLD MINE PROBLEM (DIRECTIONAL DP)

## 🎯 Problem

You are in a gold mine grid.
You can start from **any row in first column**.
From `(i, j)` you can move:

* right `(i, j+1)`
* right-up `(i-1, j+1)`
* right-down `(i+1, j+1)`

Maximize gold collected.

---

## KEY OBSERVATION

Movement is only **to the right**, so:

👉 DP must be filled **from right to left**.

---

## 🧩 DP Definition

```
dp[i][j] = max gold collectable starting from cell (i, j)
```

---

## 🧱 Base Case

Last column:

```
dp[i][lastCol] = grid[i][lastCol]
```

---

## Transition

From `(i, j)` you can go to:

```
dp[i][j] = grid[i][j] +
           max(
             dp[i][j+1],
             dp[i-1][j+1],
             dp[i+1][j+1]
           )
```

(check bounds)

---

## ✅ Code

```java
public int getMaxGold(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];

    // last column
    for (int i = 0; i < m; i++)
        dp[i][n-1] = grid[i][n-1];

    for (int j = n-2; j >= 0; j--) {
        for (int i = 0; i < m; i++) {
            int right = dp[i][j+1];
            int rightUp = (i > 0) ? dp[i-1][j+1] : 0;
            int rightDown = (i < m-1) ? dp[i+1][j+1] : 0;

            dp[i][j] = grid[i][j] +
                       Math.max(right,
                       Math.max(rightUp, rightDown));
        }
    }

    int ans = 0;
    for (int i = 0; i < m; i++)
        ans = Math.max(ans, dp[i][0]);

    return ans;
}
```

---

## CONCEPT TAKEAWAY

* Direction of movement decides DP order
* Maximize instead of minimize
* Start position may be multiple

---

# 4️⃣ CHERRY PICKUP (ADVANCED GRID DP)

## 🎯 Problem (Simplified understanding)

Two people start at top row and move to bottom row:

* both can move:

  * down-left
  * down
  * down-right
* if both land on same cell → count cherry once
* maximize total cherries

---

## WHY THIS IS HARD

State is NOT just one position.

You must track **both players at same time**.

---

## 🧩 DP State

```
dp[row][col1][col2] =
max cherries from this row
when:
player1 at col1
player2 at col2
```

---

## Transition

From `(row, col1, col2)`:

* player1 has 3 moves
* player2 has 3 moves
  → total 9 combinations

Add:

* grid[row][col1]
* grid[row][col2] (only if col1 != col2)

---

## Core Concept (NO CODE FIRST)

This is:

* **Grid DP**
* **Multi-agent DP**
* **3D DP**
* same ideas as before, just more states

---

## CONCEPT TAKEAWAY

* Cherry Pickup is NOT new DP
* It’s just **grid DP with more dimensions**
* If you understand Unique Paths + Gold Mine → this is extension

---

# 🎯 FINAL CONCEPT SUMMARY (VERY IMPORTANT)

| Problem       | DP Type        | Key Idea              |
| ------------- | -------------- | --------------------- |
| Unique Paths  | Counting DP    | sum of ways           |
| Min Path Sum  | Cost DP        | min of costs          |
| Gold Mine     | Directional DP | max profit            |
| Cherry Pickup | Multi-state DP | track multiple agents |

---

# HOW TO IDENTIFY GRID DP IN INTERVIEWS

If problem says:

* matrix
* grid
* robot
* move directions
* path
* collect / minimize / maximize

👉 **Think DP on Grids**
