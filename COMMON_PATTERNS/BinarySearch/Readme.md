Let’s **MASTER the Binary Search Pattern** — not just basic search, but the **advanced forms actually used in interviews**.

Binary Search is *not* only for finding a number.
It is a **power technique** used to search on:

* values
* answers
* ranges
* peaks
* distances
* time
* capacity

We will cover **ALL 6 MASTER subpatterns**:

1. **First/Last Occurrence**
2. **Binary Search on Answer**
3. **Search in Rotated Sorted Array**
4. **Peak Element (Unimodal Array Search)**
5. **K-th Smallest Pair Distance**
6. **Minimize Maximum (Painters Partition / Allocate Books)**

Including:
✔ intuition
✔ clean Java templates
✔ interview tricks
✔ small dry-runs

---

# CORE BINARY SEARCH TEMPLATE (VERY IMPORTANT)

```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == target)
            return mid;
        else if (arr[mid] < target)
            left = mid + 1;
        else
            right = mid - 1;
    }
    return -1;
}
```

Now let’s go into **advanced patterns**.

---

# 1. First / Last Occurrence

(Searching boundaries using binary search)

## Problem

Given sorted array with duplicates, find:

* **First** position of target
* **Last** position of target

This is widely used in:

* frequency queries
* range queries
* "count how many times X appears"
* LeetCode 34

---

## ✔ Trick:

**Do not stop when you find the target.
Keep searching left or right.**

---

## ✔ Java Code — First Occurrence

```java
int firstOccurrence(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            ans = mid;
            right = mid - 1; // search on left
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return ans;
}
```

---

## ✔ Java Code — Last Occurrence

```java
int lastOccurrence(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            ans = mid;
            left = mid + 1; // search on right
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return ans;
}
```

---

# 2. Binary Search on Answer

(One of the MOST IMPORTANT patterns in coding interviews)

## Key Insight

You are **not searching an array**.
You are searching for the **answer** (a number) that satisfies a condition.

Examples:

* Minimum capacity to ship packages
* Minimum time to paint boards
* Minimum largest subarray sum
* Koko eating bananas
* Allocate books

General pattern:

### ✔ Template

```java
int left = minimum_possible_answer;
int right = maximum_possible_answer;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (isPossible(mid)) {
        right = mid;  // try smaller answer
    } else {
        left = mid + 1;  // need larger value
    }
}
return left; // smallest feasible answer
```

### ✔ Always:

* `isPossible(mid)` checks if mid is a **valid answer**
* If mid works → shrink right
* If mid fails → increase left

Binary search continues until both meet.

---

# Example: Koko Eating Bananas (LC 875)

Goal: find minimum eating speed `k`.

```java
public int minEatingSpeed(int[] piles, int h) {
    int left = 1, right = 0;
    for (int p : piles) right = Math.max(right, p);

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (canEat(piles, h, mid))
            right = mid;
        else
            left = mid + 1;
    }
    return left;
}

boolean canEat(int[] piles, int h, int k) {
    long time = 0;
    for (int p : piles) {
        time += (p + k - 1) / k; // ceil(p/k)
    }
    return time <= h;
}
```

---

# 3. Search in Rotated Sorted Array

Classic question.

### Example:

`[4,5,6,7,0,1,2]`

The array is sorted but rotated.

---

## Trick

At any moment, **one half is sorted**.
Use this fact to decide where to search.

---

## ✔ Java Code

```java
int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) return mid;

        // Left side sorted
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        // Right side sorted
        else {
            if (nums[mid] < target && target <= nums[right])
                left = mid + 1;
            else
                right = mid - 1;
        }
    }
    return -1;
}
```

---

# 4. Peak Element (Unimodal Search)

A peak is an element where:

```
nums[i] > nums[i-1] and nums[i] > nums[i+1]
```

Even without a fully sorted array, binary search works!

---

## Trick

Compare mid with mid+1:

* if `nums[mid] < nums[mid+1]` → peak is on right
* else → peak is on left

---

## ✔ Java Code

```java
public int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < nums[mid + 1])
            left = mid + 1;   // go right
        else
            right = mid;     // go left
    }
    return left;
}
```

This works because **at least one peak always exists**.

---

# 5. K-th Smallest Pair Distance

(Hard problem using binary search on answer)

### Goal:

Given a sorted array, find K-th smallest absolute difference between any pair.

Example:

```
nums = [1,3,6]
pair distances = [2,5,3] → sorted: [2,3,5]
```

---

## Trick

Search distance `d` using binary search.

Let:

* left = 0
* right = max(nums) - min(nums)

For each mid (candidate distance), count pairs with distance ≤ mid.

Use two pointers to count in O(n).

---

## ✔ Java Code

```java
public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);

    int left = 0;
    int right = nums[nums.length - 1] - nums[0];

    while (left < right) {
        int mid = (left + right) / 2;
        int count = countPairs(nums, mid);

        if (count >= k)
            right = mid;
        else
            left = mid + 1;
    }
    return left;
}

int countPairs(int[] nums, int maxDist) {
    int count = 0, left = 0;

    for (int right = 0; right < nums.length; right++) {
        while (nums[right] - nums[left] > maxDist)
            left++;
        count += right - left;
    }
    return count;
}
```

---

# 6. Minimize Maximum (Painters / Books allocation)

Famous problems:

* Split array into “m” subarrays minimizing largest sum
* Allocate books to students
* Painters partition problem
* Ship packages

---

## Trick

Binary search on maximum allowed workload.

Let:

```
min = max element
max = sum of all elements
```

Binary search this range.

---

## ✔ Java Code (Split Array Largest Sum – LC 410)

```java
public int splitArray(int[] nums, int m) {
    int left = 0, right = 0;
    for (int n : nums) {
        left = Math.max(left, n);
        right += n;
    }

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (canSplit(nums, m, mid))
            right = mid;
        else
            left = mid + 1;
    }
    return left;
}

boolean canSplit(int[] nums, int m, int maxAllowed) {
    int count = 1;
    int sum = 0;

    for (int n : nums) {
        if (sum + n > maxAllowed) {
            count++;
            sum = n;

            if (count > m) return false;
        } else {
            sum += n;
        }
    }
    return true;
}
```

---

# BINARY SEARCH PATTERN MASTER SUMMARY

| Subpattern                  | Key Idea                        | When to Use              |
| --------------------------- | ------------------------------- | ------------------------ |
| First/Last Occurrence       | Keep searching even after found | duplicates               |
| Binary Search on Answer     | Search in value range           | capacity, distance, time |
| Rotated Sorted Array        | One half is always sorted       | rotated arrays           |
| Peak Element                | Compare mid with mid+1          | unimodal arrays          |
| K-th Smallest Pair Distance | Count pairs ≤ mid               | sorted + distances       |
| Minimize Maximum            | isPossible(mid) check           | load balancing           |
