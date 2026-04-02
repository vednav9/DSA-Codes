Perfect.
We will follow this order (very intentional):

1. **Core Subsequence DP mindset (MOST IMPORTANT)**
2. **LCS – the foundation**
3. **Edit Distance – cost-based DP**
4. **Longest Palindromic Subsequence**
5. **Distinct Subsequences (counting DP)**
6. **Shortest Common Supersequence**
7. **Delete Operation / Min Insertions & Deletions**
8. **Wildcard Matching**
9. **Regex Matching**
10. **Unifying mental templates**

I’ll explain **WHY** each transition exists before showing code.

---

# PART 0 — THE SUBSEQUENCE DP MINDSET (READ CAREFULLY)

## What is a subsequence?

* Characters **in order**
* Not necessarily continuous

Example:

```
"abcde" → "ace" is a subsequence
```

---

## Why DP is needed?

Because at **each character**, you face a decision:

```
USE this character
OR
SKIP this character
```

This creates **overlapping subproblems**.

---

## Universal DP State for String DP

### For 2 strings:

```
dp[i][j] = answer considering:
           s1[0..i-1] and s2[0..j-1]
```

This definition is **non-negotiable**.
Once you accept it, everything becomes logical.

---

## Two fundamental decisions

At `s1[i-1]` and `s2[j-1]`:

### Case 1: Characters MATCH

```
We can safely use both characters
→ move diagonally
```

### Case 2: Characters DON'T MATCH

```
We must skip something
→ either skip s1[i-1] or skip s2[j-1]
```

This **match vs mismatch** logic powers almost ALL string DP problems.

---

# 1️⃣ LONGEST COMMON SUBSEQUENCE (LCS)

## Problem

Find the **longest subsequence common to both strings**.

---

## DP Meaning

```
dp[i][j] = length of LCS between
           s1[0..i-1] and s2[0..j-1]
```

---

## Transition (THIS IS THE CORE)

### If characters match:

```
s1[i-1] == s2[j-1]
→ dp[i][j] = 1 + dp[i-1][j-1]
```

Why?
Because we **use this character** in the subsequence.

---

### If characters don't match:

```
We must skip one character
→ max(dp[i-1][j], dp[i][j-1])
```

Why?
Because skipping either may lead to a longer subsequence.

---

## Base Case

```
dp[0][*] = dp[*][0] = 0
```

Empty string has no subsequence.

---

## Code (after understanding)

```java
public int longestCommonSubsequence(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n+1][m+1];

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                dp[i][j] = 1 + dp[i-1][j-1];
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    return dp[n][m];
}
```

---

# 2️⃣ EDIT DISTANCE (COST-BASED DP)

## Problem

Convert string A → string B using:

* Insert
* Delete
* Replace

Minimize operations.

---

## DP Meaning

```
dp[i][j] = min operations to convert
           s1[0..i-1] → s2[0..j-1]
```

---

## Base Cases

```
dp[0][j] = j   // insert all
dp[i][0] = i   // delete all
```

---

## Transition Logic

### If characters match:

```
No operation needed
→ dp[i][j] = dp[i-1][j-1]
```

---

### If mismatch:

You try ALL 3 operations:

| Operation | Result           |
| --------- | ---------------- |
| Insert    | 1 + dp[i][j-1]   |
| Delete    | 1 + dp[i-1][j]   |
| Replace   | 1 + dp[i-1][j-1] |

Choose minimum.

---

## Code

```java
public int minDistance(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n+1][m+1];

    for (int i = 0; i <= n; i++) dp[i][0] = i;
    for (int j = 0; j <= m; j++) dp[0][j] = j;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1];
            } else {
                dp[i][j] = 1 + Math.min(
                    dp[i-1][j-1],
                    Math.min(dp[i-1][j], dp[i][j-1])
                );
            }
        }
    }
    return dp[n][m];
}
```

---

# 3️⃣ LONGEST PALINDROMIC SUBSEQUENCE (LPS)

## Key Insight

A palindrome reads same forward & backward.

So:

```
LPS(s) = LCS(s, reverse(s))
```

This is NOT a trick — it is logically sound.

---

## Why this works?

A palindromic subsequence must appear **in order** in both the string and its reverse.

---

## Implementation

```java
public int longestPalindromeSubseq(String s) {
    String rev = new StringBuilder(s).reverse().toString();
    return longestCommonSubsequence(s, rev);
}
```

---

# 4️⃣ DISTINCT SUBSEQUENCES (COUNTING DP)

## Problem

Count how many times `t` appears as a subsequence in `s`.

---

## DP Meaning

```
dp[i][j] = number of ways
           t[0..j-1] appears in s[0..i-1]
```

---

## Transition

### If characters match:

You have two choices:

* use it
* skip it

```
dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
```

---

### If not match:

You must skip from s

```
dp[i][j] = dp[i-1][j]
```

---

## Base Case

```
dp[i][0] = 1   // empty string is subsequence once
dp[0][j>0] = 0
```

---

## Code

```java
public int numDistinct(String s, String t) {
    int n = s.length(), m = t.length();
    long[][] dp = new long[n+1][m+1];

    for (int i = 0; i <= n; i++) dp[i][0] = 1;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (s.charAt(i-1) == t.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return (int) dp[n][m];
}
```

---

# 5️⃣ SHORTEST COMMON SUPERSEQUENCE (SCS)

## Problem

Smallest string that contains both strings as subsequences.

---

## Key Formula

```
SCS length = n + m − LCS
```

Why?
Common characters are counted twice otherwise.

---

## Construction idea:

* Use LCS DP table
* Backtrack to build string

(This is advanced, but conceptually tied to LCS.)

---

# 6️⃣ DELETE OPERATION / MIN INSERTIONS & DELETIONS

## Delete Operation (LC 583)

Only deletions allowed.

### Insight

```
Min deletions = (len1 - LCS) + (len2 - LCS)
```

Why?
Keep LCS, delete everything else.

---

## Minimum Insertions to make Palindrome

```
Min insertions = n − LPS
```

---

# 7️⃣ WILDCARD MATCHING

Pattern contains:

* `?` → matches one char
* `*` → matches any sequence

---

## DP Meaning

```
dp[i][j] = does s[0..i-1] match p[0..j-1]
```

---

## Key Transitions

| Pattern char | Logic                                           |
| ------------ | ----------------------------------------------- |
| exact / ?    | dp[i-1][j-1]                                    |
| *            | dp[i][j-1] (empty) OR dp[i-1][j] (consume char) |

---

# 8️⃣ REGEX MATCHING

Pattern contains:

* `.` any char
* `*` zero or more of previous

This is **harder**, but still same DP grid.

Key idea:

* `*` can eliminate previous char or consume current

---

# UNIFYING DP SUBSEQUENCE TEMPLATE

```
dp[i][j] = answer for s1[0..i-1], s2[0..j-1]

if match:
    dp[i][j] = diagonal
else:
    dp[i][j] = skip options
```

---

# 🎯 FINAL TAKEAWAY (VERY IMPORTANT)

| Problem Type    | DP Behavior      |
| --------------- | ---------------- |
| LCS             | maximize         |
| Edit Distance   | minimize         |
| Distinct Subseq | count            |
| LPS             | LCS with reverse |
| Delete Ops      | LCS based        |
| Matching        | boolean DP       |
