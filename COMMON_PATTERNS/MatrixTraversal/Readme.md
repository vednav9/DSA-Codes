This is a **very high-frequency pattern** and connects multiple things you already learned (**DFS, BFS, boundaries, grids**).

We’ll cover:

1. **Core Matrix Traversal Mindset**
2. **Spiral Order**
3. **Rotate Matrix**
4. **DFS/BFS on Matrix**
5. **Connected Components**
6. **Island Problems**

---

# PART 1 — CORE MATRIX TRAVERSAL MINDSET

A matrix is just a **grid graph**.

Each cell = a node
Neighbors = up, down, left, right (sometimes diagonal)

---

## 2 Golden Rules

### 1️⃣ Boundary Check (MOST IMPORTANT)

```java
if (r >= 0 && c >= 0 && r < m && c < n)
```

---

### 2️⃣ Directions Array (MEMORIZE)

```java
int[][] dirs = {
    {1, 0},   // down
    {-1, 0},  // up
    {0, 1},   // right
    {0, -1}   // left
};
```

---

# 1️⃣ SPIRAL ORDER

## 🎯 Problem

Traverse matrix in spiral:

```
→ → →
      ↓
← ← ←
↑
```

---

## IDEA

Use **4 boundaries**:

```java
top, bottom, left, right
```

Shrink boundaries after each traversal.

---

## 🪜 Steps

1. Traverse top row → move `top++`
2. Traverse right column → `right--`
3. Traverse bottom row → `bottom--`
4. Traverse left column → `left++`

Repeat.

---

## ✅ Code

```java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();

    int top = 0, bottom = matrix.length - 1;
    int left = 0, right = matrix[0].length - 1;

    while (top <= bottom && left <= right) {

        for (int j = left; j <= right; j++)
            res.add(matrix[top][j]);
        top++;

        for (int i = top; i <= bottom; i++)
            res.add(matrix[i][right]);
        right--;

        if (top <= bottom) {
            for (int j = right; j >= left; j--)
                res.add(matrix[bottom][j]);
            bottom--;
        }

        if (left <= right) {
            for (int i = bottom; i >= top; i--)
                res.add(matrix[i][left]);
            left++;
        }
    }

    return res;
}
```

---

# 2️⃣ ROTATE MATRIX (90°)

## 🎯 Problem

Rotate matrix by 90° clockwise.

---

## KEY INSIGHT

Two steps:

### Step 1: Transpose

```java
matrix[i][j] ↔ matrix[j][i]
```

---

### Step 2: Reverse each row

---

## WHY IT WORKS

Transpose converts rows → columns
Reverse aligns them correctly

---

## ✅ Code

```java
public void rotate(int[][] matrix) {
    int n = matrix.length;

    // transpose
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
        }
    }

    // reverse rows
    for (int[] row : matrix) {
        int l = 0, r = n - 1;
        while (l < r) {
            int temp = row[l];
            row[l] = row[r];
            row[r] = temp;
            l++;
            r--;
        }
    }
}
```

---

# 3️⃣ DFS / BFS ON MATRIX

Matrix problems = graph traversal.

---

## DFS TEMPLATE

```java
void dfs(int[][] grid, int r, int c) {
    if (out of bounds OR invalid) return;

    mark visited;

    for (each direction) {
        dfs(next cell);
    }
}
```

---

## BFS TEMPLATE

```java
Queue<int[]> q = new LinkedList<>();
q.offer(start);

while (!q.isEmpty()) {
    int[] cell = q.poll();

    for (dir) {
        add neighbors
    }
}
```

---

# 4️⃣ CONNECTED COMPONENTS

## 🎯 Problem

Count groups of connected cells.

---

## IDEA

* Traverse entire matrix
* When unvisited cell found → run DFS/BFS
* That forms one component

---

## ✅ Code

```java
public int countComponents(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n];

    int count = 0;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (!visited[i][j] && grid[i][j] == 1) {
                dfs(grid, visited, i, j);
                count++;
            }
        }
    }
    return count;
}

void dfs(int[][] grid, boolean[][] vis, int r, int c) {
    if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
        || vis[r][c] || grid[r][c] == 0)
        return;

    vis[r][c] = true;

    dfs(grid, vis, r + 1, c);
    dfs(grid, vis, r - 1, c);
    dfs(grid, vis, r, c + 1);
    dfs(grid, vis, r, c - 1);
}
```

---

# 5️⃣ ISLAND PROBLEMS (VERY IMPORTANT)

## 🎯 Problem (Number of Islands — LC 200)

Grid:

```
1 = land
0 = water
```

Count islands.

---

## IDEA

* Each island = one DFS
* Mark visited by:

  * boolean array OR
  * modifying grid

---

## ✅ Code

```java
public int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    int count = 0;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '1') {
                dfs(grid, i, j);
                count++;
            }
        }
    }
    return count;
}

void dfs(char[][] grid, int r, int c) {
    if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
        || grid[r][c] == '0')
        return;

    grid[r][c] = '0'; // mark visited

    dfs(grid, r + 1, c);
    dfs(grid, r - 1, c);
    dfs(grid, r, c + 1);
    dfs(grid, r, c - 1);
}
```

---

# KEY VARIATIONS OF ISLAND PROBLEMS

| Problem            | Variation    |
| ------------------ | ------------ |
| Number of Islands  | count        |
| Max Area of Island | max size     |
| Surrounded Regions | boundary DFS |
| Rotting Oranges    | BFS          |
| Flood Fill         | recolor      |

---

# HOW TO IDENTIFY MATRIX PATTERN

If problem says:

```
grid / matrix
neighbors
island
path
region
connected
```

👉 Think:

```
DFS / BFS on matrix
```

---

# MASTER SUMMARY

| Pattern              | Technique           |
| -------------------- | ------------------- |
| Spiral               | boundaries          |
| Rotate               | transpose + reverse |
| DFS/BFS              | directions array    |
| Connected components | DFS loop            |
| Islands              | DFS + mark visited  |

---

# ONE-LINE MEMORY RULE

> **Matrix problems = graph problems in disguise**