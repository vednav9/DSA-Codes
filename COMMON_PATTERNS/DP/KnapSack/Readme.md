Perfect.

We’ll go in this exact order (do NOT skip):

1. **What Knapsack DP really is (mental model)**
2. **0/1 Knapsack (foundation of everything)**
3. **Subset Sum (boolean knapsack)**
4. **Partition Equal Subset Sum**
5. **Unbounded Knapsack**
6. **How to instantly recognize which one to use**

---

# PART 0 — THE CORE IDEA OF KNAPSACK DP (VERY IMPORTANT)

### Knapsack DP is about **CHOICE**

At every item, you ask:

> **Do I take this item or not?**

That’s it.
Every knapsack problem is just this question repeated smartly.

---

## 🎒 Why is it called “Knapsack”?

You have:

* A bag with **limited capacity**
* Items with:

  * weight
  * value

Goal:

* **Maximize value** without exceeding capacity

---

## The MOST IMPORTANT Insight

Knapsack DP is about **items** and **capacity/target**.

So DP almost always looks like:

```
dp[i][w]
```

Meaning:

> Using **first i items**, can I achieve something with **capacity w**?

---

# 1️⃣ 0/1 KNAPSACK (MOST IMPORTANT)

### Meaning of 0/1

* Each item can be taken **once (1)** or **not taken (0)**
* No repetition

---

## 🎯 Problem

You are given:

* weights[]
* values[]
* capacity W

Find **maximum value**.

---

## THINKING (DO NOT JUMP TO CODE)

At item `i`, with capacity `w`:

### Choice 1: DON’T take item i

```
value = dp[i-1][w]
```

### Choice 2: TAKE item i (only if weight allows)

```
value = value[i] + dp[i-1][w - weight[i]]
```

Take the **maximum**.

---

## 🧩 DP Definition

```
dp[i][w] = max value using first i items with capacity w
```

---

## 🧱 Base Cases

```
dp[0][w] = 0   // no items
dp[i][0] = 0   // zero capacity
```

---

## Transition (THE HEART)

```
if weight[i-1] <= w:
    dp[i][w] = max(
        dp[i-1][w],                         // skip
        value[i-1] + dp[i-1][w - weight[i-1]] // take
    )
else:
    dp[i][w] = dp[i-1][w]
```

---

## ✅ Code (After understanding)

```java
public int knapsack01(int[] wt, int[] val, int W) {
    int n = wt.length;
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {
        for (int w = 1; w <= W; w++) {
            if (wt[i - 1] <= w) {
                dp[i][w] = Math.max(
                    dp[i - 1][w],
                    val[i - 1] + dp[i - 1][w - wt[i - 1]]
                );
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    return dp[n][W];
}
```

---

## WHY `i-1`?

Because:

* dp[i] means first **i items**
* current item index = `i-1`

This is a common confusion — now it should be clear.

---

# 2️⃣ SUBSET SUM (BOOLEAN KNAPSACK)

Now we change the **question**, not the structure.

---

## 🎯 Problem

Given array nums[], is there a subset with **sum = target**?

---

## Key Insight

This is still **0/1 knapsack**, but instead of **max value**, we ask:

> **Is it possible or not?**

So dp becomes **boolean**.

---

## 🧩 DP Meaning

```
dp[i][s] = true if using first i elements,
           we can form sum s
```

---

## 🧱 Base Case

```
dp[i][0] = true   // sum 0 always possible (pick nothing)
dp[0][s>0] = false
```

---

## Transition

### If current number ≤ sum:

```
dp[i][s] = dp[i-1][s]           // skip
        OR dp[i-1][s - nums[i-1]] // take
```

---

## ✅ Code

```java
public boolean subsetSum(int[] nums, int target) {
    int n = nums.length;
    boolean[][] dp = new boolean[n + 1][target + 1];

    for (int i = 0; i <= n; i++)
        dp[i][0] = true;

    for (int i = 1; i <= n; i++) {
        for (int s = 1; s <= target; s++) {
            if (nums[i - 1] <= s) {
                dp[i][s] = dp[i - 1][s] ||
                           dp[i - 1][s - nums[i - 1]];
            } else {
                dp[i][s] = dp[i - 1][s];
            }
        }
    }
    return dp[n][target];
}
```

---

## KEY TAKEAWAY

| 0/1 Knapsack   | Subset Sum       |
| -------------- | ---------------- |
| maximize value | check possible   |
| int DP         | boolean DP       |
| same structure | same transitions |

---

# 3️⃣ PARTITION EQUAL SUBSET SUM

### 🎯 Problem

Can you split array into **two subsets with equal sum**?

---

## Insight (VERY IMPORTANT)

Let total sum = S

To split equally:

```
Find subset with sum = S / 2
```

So this becomes:

> **Subset Sum Problem**

---

## Early Exit

If total sum is odd → impossible.

---

## ✅ Code

```java
public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int n : nums) sum += n;

    if (sum % 2 != 0) return false;

    return subsetSum(nums, sum / 2);
}
```

This reuse is intentional — interviews LOVE this.

---

# 4️⃣ UNBOUNDED KNAPSACK

Now comes the **only difference**.

---

## 🎯 Meaning

Each item can be taken **unlimited times**.

Examples:

* Coin Change
* Rod Cutting
* Infinite supply problems

---

## Key Difference from 0/1 Knapsack

| 0/1         | Unbounded           |
| ----------- | ------------------- |
| use dp[i-1] | use dp[i]           |
| take once   | take multiple times |

---

## Transition (IMPORTANT)

```
dp[i][w] = max(
    dp[i-1][w],                // skip
    val[i-1] + dp[i][w - wt[i-1]] // take again
)
```

Notice:

* We stay on **same item i** after taking.

---

## ✅ Code

```java
public int unboundedKnapsack(int[] wt, int[] val, int W) {
    int n = wt.length;
    int[][] dp = new int[n + 1][W + 1];

    for (int i = 1; i <= n; i++) {
        for (int w = 1; w <= W; w++) {
            if (wt[i - 1] <= w) {
                dp[i][w] = Math.max(
                    dp[i - 1][w],
                    val[i - 1] + dp[i][w - wt[i - 1]]
                );
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    return dp[n][W];
}
```

---

# ONE-LINE MEMORY TRICK (VERY POWERFUL)

* **0/1 Knapsack** → move to `i-1`
* **Unbounded Knapsack** → stay on `i`
* **Subset Sum** → same as 0/1, but boolean
* **Partition** → subset sum of total/2

---

# HOW TO IDENTIFY KNAPSACK IN INTERVIEWS

If the problem mentions:

* subset
* target sum
* partition
* maximize profit
* minimize difference
* choose items
* include / exclude
* capacity / weight / sum

👉 **Think Knapsack DP**

---

# FINAL CONCEPT SUMMARY

| Problem            | DP Type       |
| ------------------ | ------------- |
| 0/1 Knapsack       | maximize      |
| Subset Sum         | boolean       |
| Partition          | subset sum    |
| Unbounded Knapsack | reuse allowed |
