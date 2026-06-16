e’ll go in this order (don’t skip):

1. **Why Quickselect exists**
2. **How partitioning really works**
3. **Quickselect vs Quicksort**
4. **Kth largest / smallest logic**
5. **Step-by-step dry run**
6. **Clean Java code (interview-ready)**
7. **When to use / not use Quickselect**

---

# PART 0 — WHY QUICKSELECT EXISTS

## The problem

You want:

* **Kth largest** or **Kth smallest** element

### Options:

| Method          | Time               |
| --------------- | ------------------ |
| Sort array      | O(n log n)         |
| Heap (size k)   | O(n log k)         |
| **Quickselect** | **O(n) average** ✅ |

👉 Quickselect gives **selection without full sorting**.

---

# PART 1 — CORE IDEA (VERY IMPORTANT)

Quickselect is based on **partitioning** (same as Quicksort).

### Key insight:

> After partitioning, **the pivot is in its final sorted position**.

So:

* If pivot index == target index → **answer found**
* If pivot index > target → search **left**
* If pivot index < target → search **right**

You **discard half the array** every time.

---

# PART 2 — PARTITIONING TECHNIQUE (THE HEART)

Let’s understand partitioning **deeply**, because everything depends on this.

---

## 🎯 Goal of Partition

Given a pivot:

* All elements **≤ pivot** go left
* All elements **> pivot** go right
* Pivot ends in its **correct position**

---

## Lomuto Partition (Easiest to Understand)

### Steps:

1. Choose last element as pivot
2. Maintain index `i` = boundary of smaller elements
3. Traverse with `j`
4. Swap when `nums[j] <= pivot`

---

### Example

```
nums = [3, 2, 1, 5, 4]
pivot = 4
```

After partition:

```
[3, 2, 1, 4, 5]
pivot index = 3
```

---

## Lomuto Partition Code (MEMORIZE)

```java
private int partition(int[] nums, int left, int right) {
    int pivot = nums[right];
    int i = left;

    for (int j = left; j < right; j++) {
        if (nums[j] <= pivot) {
            swap(nums, i, j);
            i++;
        }
    }
    swap(nums, i, right);
    return i;
}
```

---

# PART 3 — QUICKSELECT VS QUICKSORT

| Quicksort                | Quickselect                  |
| ------------------------ | ---------------------------- |
| Sorts both sides         | Goes to **one side only**    |
| Recursive on both halves | Recursive on **needed half** |
| O(n log n)               | **O(n) average**             |

Quickselect = *partial Quicksort*.

---

# PART 4 — KTH LARGEST VS KTH SMALLEST

This is where people get confused. Let’s make it **crystal clear**.

---

## Array Indexing Trick

If array were sorted ascending:

* Kth **smallest** → index `k - 1`
* Kth **largest** → index `n - k`

Example:

```
nums = [1,2,3,4,5]
k = 2

2nd largest → index = 5 - 2 = 3 → value 4
```

---

# PART 5 — QUICKSELECT LOGIC (STEP BY STEP)

### Function:

```
quickSelect(left, right)
```

1. Partition array → get `pivotIndex`
2. Compare with `targetIndex`
3. Recurse only on relevant side

---

# 1️⃣ KTH LARGEST ELEMENT — FULL JAVA CODE

### 🎯 Problem: LeetCode 215

---

## ✅ Code (INTERVIEW-READY)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int target = n - k; // kth largest index
        return quickSelect(nums, 0, n - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int target) {
        if (left == right) return nums[left];

        int pivotIndex = partition(nums, left, right);

        if (pivotIndex == target) {
            return nums[pivotIndex];
        } else if (pivotIndex > target) {
            return quickSelect(nums, left, pivotIndex - 1, target);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, target);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

---

# PART 6 — DRY RUN (IMPORTANT)

```
nums = [3,2,1,5,6,4], k = 2
target = 6 - 2 = 4
```

Sorted array:

```
[1,2,3,4,5,6]
index 4 → 5
```

Quickselect finds it **without sorting fully**.

---

# PART 7 — TIME & SPACE COMPLEXITY

| Case    | Time         |
| ------- | ------------ |
| Average | **O(n)**     |
| Worst   | O(n²) (rare) |

Worst case happens when pivot is always smallest/largest.

---

## How to avoid worst case?

* Randomize pivot (optional in interviews)

```java
int randomIndex = left + rand.nextInt(right - left + 1);
swap(nums, randomIndex, right);
```

---

# WHEN TO USE QUICKSELECT

Use Quickselect when:

* You need **only one order statistic**
* Kth smallest / largest
* Median
* Percentile
* No need for full sorting

---

# WHEN NOT TO USE IT

Avoid when:

* You need full sorted order
* Worst-case performance must be guaranteed
* Stable ordering required

---

# QUICKSELECT CHEATSHEET (MEMORIZE)

| Task         | Index   |
| ------------ | ------- |
| Kth smallest | `k - 1` |
| Kth largest  | `n - k` |

---

# ONE-LINE MEMORY RULE

> **Quickselect = Quicksort that only explores the side you need**