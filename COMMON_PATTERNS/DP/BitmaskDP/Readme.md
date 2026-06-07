**Bitmask DP** is the **final boss of DP**, but if taught correctly, it becomes logical—not magical.

We’ll cover:

1. **What is Bitmask DP & why we need it**
2. **State Compression (the core idea)**
3. **How a bitmask represents choices**
4. **TSP (Travelling Salesman Problem) — classic Bitmask DP**
5. **General Bitmask DP template**
6. **How to recognize Bitmask DP in interviews**

---

# PART 0 — WHY BITMASK DP EXISTS

## The problem DP couldn’t solve easily

Some problems ask:

* Visit **each city exactly once**
* Choose a **subset** of elements
* Order matters
* Constraints are small (N ≤ 15–20)

Examples:

* Travelling Salesman Problem (TSP)
* Assign jobs to people
* Match tasks optimally
* Visit all nodes once

Normal DP fails because:

* State depends on **which items are already used**
* That’s exponential information

👉 **Bitmask = compact way to store “used / not used” information**

---

# PART 1 — WHAT IS A BITMASK?

A **bitmask** is just an integer where each bit represents a state.

### Example: N = 4 cities

```
mask = 0101 (binary)
```

Meaning:

* city 0 → visited
* city 1 → not visited
* city 2 → visited
* city 3 → not visited

So:

```
mask & (1 << i) != 0  → city i is visited
```

---

## Common Bitmask Operations (MEMORIZE)

```java
// check if ith bit is set
(mask & (1 << i)) != 0

// set ith bit
mask | (1 << i)

// remove ith bit
mask & ~(1 << i)

// total states = 2^n
```

This is **state compression**.

---

# PART 2 — WHAT IS STATE COMPRESSION?

Instead of:

```
boolean visited[20]
```

We store:

```
int mask
```

That reduces:

* memory
* DP state complexity
* recursion parameters

---

# PART 3 — BITMASK DP STATE (VERY IMPORTANT)

Bitmask DP almost always looks like:

```
dp[mask][pos]
```

Meaning:

> Minimum / maximum cost
> when we have visited cities in `mask`
> and we are currently at city `pos`

This definition is **the heart of Bitmask DP**.

---

# 1️⃣ TSP (TRAVELLING SALESMAN PROBLEM)

---

## 🎯 Problem

* Given N cities
* Distance matrix `dist[i][j]`
* Start from city 0
* Visit every city **exactly once**
* Return to city 0
* Minimize total cost

---

## THINKING (NO CODE YET)

At any point:

* You are at city `pos`
* You have visited cities in `mask`

Your options:

* Go to any city **not yet visited**

---

## 🧩 DP Definition

```
dp[mask][pos] = minimum cost to:
                visit all cities in mask
                and end at city pos
```

---

## 🧱 Base Case

When:

```
mask == (1 << n) - 1   // all cities visited
```

You must return to start city (0):

```
return dist[pos][0]
```

---

## Transition

From `(mask, pos)`:
Try visiting a new city `city` not in mask:

```
newMask = mask | (1 << city)

cost = dist[pos][city] + dp[newMask][city]
```

Choose minimum.

---

# THIS IS JUST KNAPSACK + DFS

* mask → subset of cities
* pos → current position
* choice → which city to visit next

---

# 2️⃣ FULL JAVA CODE — TSP (Top-Down DP)

```java
class Solution {
    int n;
    int[][] dist;
    int[][] dp;

    public int tsp(int[][] dist) {
        this.dist = dist;
        this.n = dist.length;
        int maxMask = 1 << n;

        dp = new int[maxMask][n];
        for (int i = 0; i < maxMask; i++) {
            Arrays.fill(dp[i], -1);
        }

        // start from city 0, mask = 1 (only city 0 visited)
        return dfs(1, 0);
    }

    private int dfs(int mask, int pos) {
        // if all cities visited
        if (mask == (1 << n) - 1) {
            return dist[pos][0]; // return to start
        }

        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int ans = Integer.MAX_VALUE;

        // try visiting next city
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newMask = mask | (1 << city);
                int cost = dist[pos][city] + dfs(newMask, city);
                ans = Math.min(ans, cost);
            }
        }

        dp[mask][pos] = ans;
        return ans;
    }
}
```

---

## Complexity (IMPORTANT)

* States: `2^n * n`
* Transitions: `n`
* Time: `O(n^2 * 2^n)`
* Space: `O(n * 2^n)`

This is why:

```
n ≤ 15–20
```

---

# PART 4 — HOW TO THINK BITMASK DP STEP-BY-STEP

When you see a problem:

### Step 1: Ask

> Do I need to track **which items are used**?

If YES → bitmask.

---

### Step 2: Define state

```
dp[mask][something]
```

---

### Step 3: Define base case

```
mask == all_used
```

---

### Step 4: Define transitions

```
try adding one unused element
```

---

# PART 5 — STATE COMPRESSION DP (GENERAL TEMPLATE)

```java
int dfs(int mask, int pos) {
    if (mask == ALL_USED)
        return baseAnswer;

    if (dp[mask][pos] != -1)
        return dp[mask][pos];

    int ans = INF;

    for (each option not in mask) {
        ans = min/max(ans,
              cost + dfs(mask | option, newPos));
    }

    return dp[mask][pos] = ans;
}
```

---

# COMMON BITMASK DP PROBLEMS

| Problem             | State            |
| ------------------- | ---------------- |
| TSP                 | dp[mask][pos]    |
| Assignment Problem  | dp[mask][worker] |
| Job Scheduling      | dp[mask]         |
| Minimum XOR pairing | dp[mask]         |
| Team formation      | dp[mask]         |

---

# HOW TO IDENTIFY BITMASK DP IN INTERVIEWS

If you hear:

* “visit all exactly once”
* “choose subset”
* “n ≤ 15”
* “optimal arrangement”
* “assignment / matching”
* “state explosion”

👉 **Think Bitmask DP**

---

# ONE-LINE MEMORY RULE

> **Bitmask DP = DP where the state is “which things are already used”**