# ⭐ SECTION 1 — What is Dynamic Programming?

DP solves problems using:

```
big problem = smaller overlapping subproblems
```

If smaller subproblems repeat again & again → DP reduces work by storing answers.

---

## ⭐ 3 conditions to use DP:

### 1️⃣ Overlapping subproblems

Same recursive state appears repeatedly.

### 2️⃣ Optimal substructure

Best answer depends on best choices of smaller problems.

### 3️⃣ State + Transition

You can define:

* a dp state
* a recurrence relation

If these exist → DP is the weapon.

---

# ⭐ DP vs Recursion vs Memoization vs Tabulation

| Concept     | Meaning                      |
| ----------- | ---------------------------- |
| Recursion   | top-down brute force         |
| Memoization | add caching to recursion     |
| Tabulation  | iterative DP array bottom-up |

---

# ⭐ IMPORTANT DP THINKING

Before coding DP, ask:

1️⃣ What is the **state**?
2️⃣ What is the **transition**?
3️⃣ What are the **base cases**?

---

---

# ⭐ PART A — Fibonacci DP (1D DP)

Classic for understanding DP.

---

## 🎯 Problem:

Return nth Fibonacci number:

```
f(n) = f(n-1) + f(n-2)
```

---

## ❌ Brute Recursive Time (Exponential)

```
f(50) → 2^50 operations
```

Reason?

Repeated computation:

```
f(5)
|- f(4)
|   |- f(3)
|       |- f(2)
|           ...
|- f(3)
    |- f(2)
```

---

## ⭐ DP Solution — Memoization

Top-down recursion + cache.

```java
class Solution {
    int[] memo;

    public int fib(int n) {
        memo = new int[n+1];
        return solve(n);
    }

    int solve(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];

        memo[n] = solve(n-1) + solve(n-2);
        return memo[n];
    }
}
```

Time complexity: **O(n)**
Space complexity: **O(n recursion + memo)**

---

## ⭐ DP Solution — Tabulation (Bottom-Up)

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }
}
```

Time: O(n)
Space: O(n)

---

## ⭐ Space Optimized DP

```java
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0, prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

This is the most optimal.

---

# ⭐ PART B — House Robber DP (1D DP)

---

## 🎯 Problem:

Given array of money in houses:

```
cannot rob two adjacent houses
maximize stolen amount
```

Array:

```
[2,7,9,3,1]
→ answer = 12 (2 + 9 + 1)
```

---

## Recurrence Idea

At index i:

Option 1: **Rob house i**
Gain: nums[i] + rob(i-2)

Option 2: **Skip house i**
Gain: rob(i-1)

So recurrence:

```
dp[i] = max(dp[i-1], dp[i-2] + nums[i])
```

This is DP.

---

## ⭐ Bottom-Up Solution

```java
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++)
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);

        return dp[n-1];
    }
}
```

---

## ⭐ Space Optimization

```java
class Solution {
    public int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int n : nums) {
            int curr = Math.max(prev1, prev2 + n);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
```

This is the perfect form.

---

# ⭐ PART C — Min Jumps / Climb Stairs DP

---

## 🎯 Climbing Stairs

At each step:

* you can jump 1 step
* or jump 2 steps

Find number of ways to reach top.

Equivalent recurrence:

```
dp[i] = dp[i-1] + dp[i-2]
```

This is Fibonacci again.

---

## ⭐ Code

```java
public int climbStairs(int n) {
    int prev2 = 1; // ways to reach step -1 (empty)
    int prev1 = 1; // ways to reach step 0

    for (int i = 2; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }
    return prev1;
}
```

---

# ⭐ Min Jumps (Greedy + DP)

---

## 🎯 Problem

Given array, you can jump to next k based on nums[i].

Goal: min jumps to reach end.

---

## ⭐ DP Recurrence

```java
dp[i] = 1 + min(dp[j]) for all j < i and j + nums[j] >= i
```

Time DP: O(n²)

---

## ⭐ Optimized: Greedy O(n)

Interview prefers:

```java
public int jump(int[] nums) {
    int jumps = 0, end = 0, far = 0;

    for (int i = 0; i < nums.length - 1; i++) {
        far = Math.max(far, i + nums[i]);

        if (i == end) {
            jumps++;
            end = far;
        }
    }
    return jumps;
}
```

Although DP exists, greedy is preferred.

---

# ⭐ Key Takeaways: 1D DP

| Problem         | Recurrence                       | Type              |
| --------------- | -------------------------------- | ----------------- |
| Fibonacci       | f[n] = f[n-1] + f[n-2]           | linear DP         |
| House Robber    | dp[i]=max(dp[i-1], dp[i-2]+a[i]) | linear DP         |
| Climbing Stairs | f[n] = f[n-1] + f[n-2]           | linear DP         |
| Min Jumps       | dp or greedy                     | linear DP variant |

---

# ⭐ Common Structure of 1D DP

1. Define dp[i] meaning
2. Base cases for dp[0], dp[1]
3. Transition formula
4. Build from small → big

---

# ⭐ IDENTIFYING 1D DP QUESTIONS

If you hear:

* maximize sum in array
* count ways to reach end
* optimal path in linear structure
* avoid adjacent
* jump forward
* take/skip last

**Think 1D DP.**