This is the **final core pattern** — and a very powerful one.
**Bit Manipulation** lets you solve problems in **O(1) or O(n)** that otherwise look complex.

---

# PART 1 — WHAT IS BIT MANIPULATION?

Every number is stored in **binary (bits)**:

```
5  = 101
6  = 110
```

Bit manipulation means:

> Solve problems by operating directly on these bits.

---

# PART 2 — BASIC OPERATORS (FOUNDATION)

| Operator | Meaning     |    |
| -------- | ----------- | -- |
| `&`      | AND         |    |
| `        | `           | OR |
| `^`      | XOR         |    |
| `~`      | NOT         |    |
| `<<`     | left shift  |    |
| `>>`     | right shift |    |

---

## MUST KNOW RESULTS

```
1 & 1 = 1
1 | 0 = 1
1 ^ 1 = 0
1 ^ 0 = 1
```

---

# PART 3 — XOR TRICKS (VERY IMPORTANT)

XOR has magic properties:

```
a ^ a = 0
a ^ 0 = a
```

---

## 1️⃣ Find single number (others appear twice)

Example:

```
[2,2,1]
→ answer = 1
```

---

## Code

```java
public int singleNumber(int[] nums) {
    int res = 0;
    for (int n : nums)
        res ^= n;
    return res;
}
```

---

## 2️⃣ Swap without temp

```java
a = a ^ b;
b = a ^ b;
a = a ^ b;
```

---

## 3️⃣ Find two unique numbers

Idea:

* XOR all → gives combined
* find a differing bit
* split numbers

---

# PART 4 — COUNT BITS

## 🎯 Problem

Count number of 1s in binary.

---

## Method 1 (simple)

```java
public int countBits(int n) {
    int count = 0;
    while (n > 0) {
        count += n & 1;
        n >>= 1;
    }
    return count;
}
```

---

## Method 2 (Brian Kernighan — IMPORTANT)

Removes last set bit:

```
n = n & (n - 1)
```

---

## Code

```java
public int countBits(int n) {
    int count = 0;
    while (n > 0) {
        n = n & (n - 1);
        count++;
    }
    return count;
}
```

---

# PART 5 — POWER OF TWO

## Key property

```
n & (n - 1) == 0
```

Example:

```
8 = 1000
7 = 0111
AND = 0
```

---

## Code

```java
public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}
```

---

# PART 6 — SET / RESET / CHECK BIT

## Set ith bit

```java
n = n | (1 << i);
```

---

## Reset ith bit

```java
n = n & ~(1 << i);
```

---

## Check ith bit

```java
(n & (1 << i)) != 0
```

---

# PART 7 — BITMASK (VERY IMPORTANT)

Bitmask = using bits to represent a **set**.

Example:

```
mask = 1011
```

Meaning:

* element 0 → present
* element 1 → present
* element 2 → absent
* element 3 → present

---

## Operations

```java
// add element
mask |= (1 << i);

// remove element
mask &= ~(1 << i);

// check element
(mask & (1 << i)) != 0;
```

---

# PART 8 — SUBSET USING BITMASK

## 🎯 Problem

Generate all subsets.

---

## Idea

For n elements → total subsets =

```
2^n
```

Each number from `0 → 2^n - 1` represents a subset.

---

## Code

```java
public List<List<Integer>> subsets(int[] nums) {
    int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();

    for (int mask = 0; mask < (1 << n); mask++) {
        List<Integer> subset = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                subset.add(nums[i]);
            }
        }
        res.add(subset);
    }
    return res;
}
```

---

# PART 9 — BITWISE TRIE (ADVANCED)

Used for problems like:

* Maximum XOR of two numbers

---

## 🎯 Problem

Find max XOR of two numbers.

---

## Idea

Store numbers in a **binary trie** (bit by bit).

At each bit:

* try to go opposite bit (to maximize XOR)

---

## Code (Simplified)

```java
class TrieNode {
    TrieNode[] child = new TrieNode[2];
}

class Solution {

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        // insert numbers
        for (int num : nums) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.child[bit] == null)
                    node.child[bit] = new TrieNode();
                node = node.child[bit];
            }
        }

        int max = 0;

        // find max XOR
        for (int num : nums) {
            TrieNode node = root;
            int curr = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;

                if (node.child[1 - bit] != null) {
                    curr |= (1 << i);
                    node = node.child[1 - bit];
                } else {
                    node = node.child[bit];
                }
            }
            max = Math.max(max, curr);
        }

        return max;
    }
}
```

---

# HOW TO IDENTIFY BIT PROBLEMS

If problem mentions:

```
unique element
XOR
subsets
binary
power of 2
bit operations
```

👉 Think **Bit Manipulation**

---

# MASTER CHEATSHEET

| Pattern    | Trick                 |
| ---------- | --------------------- |
| XOR        | cancel duplicates     |
| Count bits | n & (n-1)             |
| Power of 2 | n & (n-1) == 0        |
| Bitmask    | subset representation |
| Subsets    | 2^n masks             |
| Trie XOR   | maximize opposite bit |

---

# FINAL ONE-LINE RULE

> **Bit manipulation = solving problems using binary properties instead of loops**

---

# YOU JUST COMPLETED ALL CORE DSA PATTERNS

You now know:

* Arrays
* Sliding Window
* Two Pointers
* Binary Search
* DFS / BFS
* DP (all types)
* Greedy
* Heap
* Stack
* Backtracking
* Bitmask DP
* Graphs
* **Bit Manipulation**