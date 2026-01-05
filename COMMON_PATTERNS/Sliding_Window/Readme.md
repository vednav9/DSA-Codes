## 0. Sliding Window – Core Idea

You have an array/string and you’re interested in **continuous segments** (subarrays / substrings).

Instead of:

* brute force = check all subarrays → O(n²)

You:

* maintain a “window” `[left…right]`
* expand `right` to include more
* move `left` to remove from the front
* keep updating your answer on the fly → O(n)

---

## 1. Fixed-Size Window

> Window size is fixed (e.g., subarray of size k).

**Classic example:**
Max sum of any subarray of size `k`.

### Pattern

```java
int maxSumSubarrayOfSizeK(int[] arr, int k) {
    int n = arr.length;
    int windowSum = 0;
    int maxSum = Integer.MIN_VALUE;

    int left = 0;
    for (int right = 0; right < n; right++) {
        windowSum += arr[right];              // add new element

        // once window reaches size k
        if (right - left + 1 == k) {
            maxSum = Math.max(maxSum, windowSum);

            windowSum -= arr[left];          // remove element going out
            left++;                          // shrink from left
        }
    }
    return maxSum;
}
```

**Key things:**

* `right - left + 1` always tracks size
* Only one loop → O(n)
* Very useful for averages, sums, counts when size is fixed.

---

## 2. Variable-Size Window (Generic Template)

> Window grows and shrinks depending on some condition (sum, distinct count, etc.)

**General template (VERY IMPORTANT):**

```java
int slidingWindowGeneric(int[] arr) {
    int n = arr.length;
    int left = 0;
    // some state: sum, freq map, etc.
    
    for (int right = 0; right < n; right++) {
        // 1) include arr[right] into window
        
        // 2) while window is INVALID → shrink from left
        while (/* condition invalid using current state */) {
            // remove arr[left] from state
            left++;
        }
        
        // 3) here window [left..right] is VALID
        // update answer if needed (length, sum, etc.)
    }
    return 0; // or answer
}
```

---

## 3. Longest Substring/Subarray with Condition

Typical pattern: **“Longest … such that condition holds”**

You:

* expand `right`
* if condition violated → move `left` until valid again
* keep track of max length when valid

### Example: Longest Substring Without Repeating Characters (LC 3)

**Condition:** all chars unique.

```java
int lengthOfLongestSubstring(String s) {
    int n = s.length();
    int left = 0, maxLen = 0;

    int[] freq = new int[256]; // frequency of chars

    for (int right = 0; right < n; right++) {
        char ch = s.charAt(right);
        freq[ch]++;

        // if we have duplicate of current char, shrink
        while (freq[ch] > 1) {
            char leftChar = s.charAt(left);
            freq[leftChar]--;
            left++;
        }

        // now window [left..right] has all unique chars
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

**Pattern:**

* **Add** `right`
* While **invalid** (duplicate appears) → **shrink** from `left`
* Update max when valid.

---

## 4. “At Most K Distinct” Pattern

Super important pattern; used for:

* “at most K distinct”
* “exactly K distinct” (by trick: atMost(K) - atMost(K-1))

### A. Template: Longest substring with **at most K distinct characters**

```java
int longestSubstringAtMostKDistinct(String s, int k) {
    int n = s.length();
    int left = 0, maxLen = 0;
    int[] freq = new int[256];
    int distinct = 0;

    for (int right = 0; right < n; right++) {
        char ch = s.charAt(right);
        if (freq[ch] == 0) distinct++;
        freq[ch]++;

        // shrink while more than k distinct
        while (distinct > k) {
            char leftChar = s.charAt(left);
            freq[leftChar]--;
            if (freq[leftChar] == 0) distinct--;
            left++;
        }

        // window [left..right] has at most k distinct
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

### B. Exactly K Distinct Trick

`longestSubstringWithExactlyKDistinct = atMost(K) - atMost(K-1)`

```java
int longestSubstringExactlyKDistinct(String s, int k) {
    return atMostKDistinct(s, k) - atMostKDistinct(s, k - 1);
}

int atMostKDistinct(String s, int k) {
    int n = s.length();
    int left = 0, maxLen = 0;
    int[] freq = new int[256];
    int distinct = 0;

    for (int right = 0; right < n; right++) {
        char ch = s.charAt(right);
        if (freq[ch] == 0) distinct++;
        freq[ch]++;

        while (distinct > k) {
            char leftChar = s.charAt(left);
            freq[leftChar]--;
            if (freq[leftChar] == 0) distinct--;
            left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
```

This “at most” pattern repeats in many hard problems.

---

## 5. Anagram / Permutation Window Pattern

> Given a pattern `p` and string `s`, find all substrings of `s` that are a permutation/anagram of `p`.

Key ideas:

* Fixed-size window (length = p.length())
* Character frequency comparison
* Use `matches` counter to avoid full compare each time

### Example: Find All Anagrams (LC 438 style)

```java
List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (s.length() < p.length()) return result;

    int[] freqP = new int[26];
    int[] freqS = new int[26];

    int m = p.length();
    for (int i = 0; i < m; i++) {
        freqP[p.charAt(i) - 'a']++;
        freqS[s.charAt(i) - 'a']++;
    }

    if (Arrays.equals(freqP, freqS)) {
        result.add(0);
    }

    for (int right = m; right < s.length(); right++) {
        // add new char
        freqS[s.charAt(right) - 'a']++;
        // remove left char of previous window
        int left = right - m;
        freqS[s.charAt(left) - 'a']--;

        if (Arrays.equals(freqP, freqS)) {
            result.add(left + 1);
        }
    }

    return result;
}
```

You can optimize by maintaining a `matchCount` instead of `Arrays.equals`, but this is easier to understand first.

**Pattern:**

* Fixed-size window
* Maintain frequency arrays / maps
* Compare or track match count.

---

## 6. “Dynamic Expand + Shrink” Mindset (Core Mental Model)

Almost every variable-size sliding window follows:

1. `right` loops from 0 → n - 1
2. We **add** `arr[right]` / `s.charAt(right)` to some state:

   * sum
   * map
   * count
3. While window **invalid** → move `left` forward and **remove** things from state
4. When window **valid** → use `[left..right]` to update ans

General Java template again:

```java
int solve(int[] arr) {
    int left = 0, n = arr.length;
    // state: sum, map, etc.
    
    for (int right = 0; right < n; right++) {
        // expand window: include arr[right]

        // shrink while invalid
        while (/* invalid condition */) {
            // remove arr[left] from state
            left++;
        }

        // now window is valid
        // update answer using [left..right]
    }
    return 0; // or ans
}
```

Once this is in your brain, you can look at almost any sliding window problem and adapt this skeleton.

---

## 7. Sliding Window Maximum (Deque Pattern)

This is a bit special: we use a **Deque** to maintain candidates for max.

**Problem:**
Given array `nums` and integer `k`, find max of each subarray of size `k`.

**Key idea:**

* Store **indices** in deque
* Maintain deque **decreasing** by value (front = max)
* Remove from:

  * back: all smaller elements than current (they’re useless)
  * front: if they move out of window

### Java solution:

```java
int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) return new int[0];

    int[] result = new int[n - k + 1];
    Deque<Integer> dq = new LinkedList<>(); // will store indices

    int resIdx = 0;

    for (int i = 0; i < n; i++) {
        // 1) remove indices that are out of this window
        while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
            dq.pollFirst();
        }

        // 2) maintain decreasing order: remove smaller elements from back
        while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
            dq.pollLast();
        }

        // 3) add current index
        dq.offerLast(i);

        // 4) starting from i >= k-1, window is valid; record max
        if (i >= k - 1) {
            result[resIdx++] = nums[dq.peekFirst()];
        }
    }

    return result;
}
```

**Complexity:**
Each index is pushed and popped at most once → **O(n)** time.

---

## 🔁 Quick Recap of Sliding Window Patterns in Java

1. **Fixed-size window** → use size condition: `right - left + 1 == k`
2. **Variable-size window** → expand right, shrink while invalid
3. **Longest substring/subarray** → often “while invalid → shrink”
4. **At most K distinct** → use frequency + distinct counter
5. **Anagram / permutation** → fixed-size + frequency comparison
6. **Dynamic expand/shrink** → generic mental template
7. **Sliding window max** → use **Deque** to track max in O(n)