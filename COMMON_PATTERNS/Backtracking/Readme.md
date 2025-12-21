Backtracking is used when:

* we want to **build results step-by-step**
* explore all combinations/paths/solutions
* make decisions → recurse → undo decisions

This pattern powers:

* Subsets
* Permutations
* Combination Sums
* N-Queens
* Grid/Path search

We will cover each subpattern with:

* **intuition**
* **template**
* **Java implementation**
* **when to choose this pattern**

---

# ⭐ CORE IDEA OF BACKTRACKING

Backtracking uses:

```
choose → explore → unchoose
```

We try a choice, explore deeper, and revert.

---

# ⭐ UNIVERSAL BACKTRACKING TEMPLATE (MEMORIZE THIS)

```java
void backtrack(state) {
    if (solution found) {
        add to results;
        return;
    }

    for (choice in choices) {
        make choice;
        backtrack(new state);
        undo choice;
    }
}
```

---

# 1️⃣ SUBSETS PATTERN (POWERSET)

Used when:

* need ALL subsets
* include/exclude choice

---

## Intuition

For every element:

* include OR skip

---

## Example: Subsets (LC 78)

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
}

void backtrack(List<List<Integer>> result,
               List<Integer> temp,
               int[] nums,
               int start) {

    result.add(new ArrayList<>(temp));

    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        backtrack(result, temp, nums, i + 1);
        temp.remove(temp.size() - 1);
    }
}
```

---

# 2️⃣ PERMUTATIONS PATTERN

Used when:

* order matters
* need arrangement of all items

---

## Key Idea

Track visited elements.

---

## Example: Permutations (LC 46)

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    boolean[] used = new boolean[nums.length];

    backtrack(result, new ArrayList<>(), nums, used);
    return result;
}

void backtrack(List<List<Integer>> result,
               List<Integer> temp,
               int[] nums,
               boolean[] used) {

    if (temp.size() == nums.length) {
        result.add(new ArrayList<>(temp));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;

        used[i] = true;
        temp.add(nums[i]);

        backtrack(result, temp, nums, used);

        used[i] = false;
        temp.remove(temp.size() - 1);
    }
}
```

---

# 3️⃣ COMBINATION SUMS PATTERN

Used when:

* combinations must sum to target
* avoid duplicates
* unlimited picks allowed (sometimes)

---

## Example: Combination Sum (LC 39)

### Intuition

* choose candidate → recurse with same index
* skip candidate → move forward

---

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), candidates, target, 0);
    return result;
}

void backtrack(List<List<Integer>> result,
               List<Integer> temp,
               int[] nums,
               int remain,
               int start) {

    if (remain < 0) return;
    if (remain == 0) {
        result.add(new ArrayList<>(temp));
        return;
    }

    for (int i = start; i < nums.length; i++) {
        temp.add(nums[i]);
        backtrack(result, temp, nums, remain - nums[i], i); // allow reuse
        temp.remove(temp.size() - 1);
    }
}
```

---

## Combination Sum II (No reuse, skip duplicates)

Use sorting + skip duplicates.

---

# 4️⃣ N-QUEENS (ULTRA-IMPORTANT)

A classic backtracking challenge.

Challenge:
Place 8 queens on 8×8 chessboard → no attack.

---

## Core Constraints

Queens attack:

* same row
* same column
* same diagonals

---

## Optimization Trick

Track sets:

```
col used
diag (r + c) used
anti-diag (r - c) used
```

---

### Java Code: N-Queens (LC 51)

```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];

    for (char[] row : board) Arrays.fill(row, '.');

    Set<Integer> cols = new HashSet<>();
    Set<Integer> diag = new HashSet<>();
    Set<Integer> antiDiag = new HashSet<>();

    backtrack(0, n, board, cols, diag, antiDiag, result);

    return result;
}

void backtrack(int row, int n,
               char[][] board,
               Set<Integer> cols,
               Set<Integer> diag,
               Set<Integer> antiDiag,
               List<List<String>> result) {

    if (row == n) {
        result.add(build(board));
        return;
    }

    for (int col = 0; col < n; col++) {

        if (cols.contains(col) ||
            diag.contains(row + col) ||
            antiDiag.contains(row - col)) continue;

        board[row][col] = 'Q';

        cols.add(col);
        diag.add(row + col);
        antiDiag.add(row - col);

        backtrack(row + 1, n, board, cols, diag, antiDiag, result);

        board[row][col] = '.';

        cols.remove(col);
        diag.remove(row + col);
        antiDiag.remove(row - col);
    }
}

List<String> build(char[][] board) {
    List<String> res = new ArrayList<>();
    for (char[] row : board) {
        res.add(new String(row));
    }
    return res;
}
```

---

# 5️⃣ PATH SEARCH PATTERN

Used for:

* word search
* grid paths
* robot moves
* island exploration

Usually:

* DFS + backtracking

---

## Example: Word Search (LC 79)

```java
public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (backtrack(board, word, i, j, 0))
                return true;
        }
    }
    return false;
}

boolean backtrack(char[][] board, String word,
                  int i, int j, int idx) {

    if (idx == word.length()) return true;
    if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
        return false;
    if (board[i][j] != word.charAt(idx))
        return false;

    char temp = board[i][j];
    board[i][j] = '#';

    boolean found =
        backtrack(board, word, i + 1, j, idx + 1) ||
        backtrack(board, word, i - 1, j, idx + 1) ||
        backtrack(board, word, i, j + 1, idx + 1) ||
        backtrack(board, word, i, j - 1, idx + 1);

    board[i][j] = temp;
    return found;
}
```

---

# ⭐ BACKTRACKING — IDENTIFICATION TRICKS

If problem asks:

* “return all”
* “find all unique”
* “generate”
* “enumerate”
* “count”
* “path”
* “arrangements”
* “choices at each step”

→ **Backtracking is the correct tool.**

---

# ⭐ COMPLEXITY NOTE

Backtracking often has:

```
O(b^d)
```

Where:

* `b` = branching factor
* `d` = depth

But that’s expected — no polynomial solution exists.

---

# ⭐ CHEATSHEET SUMMARY

| Pattern         | Key Idea           |
| --------------- | ------------------ |
| Subsets         | include/skip       |
| Permutations    | visited[]          |
| Combination sum | start index, reuse |
| N-Queens        | constraint pruning |
| Path search     | DFS + mark cells   |
