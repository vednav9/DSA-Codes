# WHAT IS THE TWO POINTERS PATTERN?

Two pointers = **two indices moving in a sequence**:
- either **towards each other**  
- or **together in same direction**  

We use it when:
- array/string is **sorted**, or  
- we need **continuous regions**, or  
- we detect something like duplications, partitions, palindromes.

Time complexity improves from **O(n²) → O(n)**.

---

# 1. Opposite Direction Pointers  
(Left at start, right at end: move toward center)

Used when:
- Array/string is **sorted**
- You want pairs, triplets, or check palindrome

---

## ✔ Example: Pair Sum in Sorted Array (Two Sum II)

**Problem:** Given sorted array, find two numbers that add up to target.

### Java Code (Template)

```java
public int[] twoSumSorted(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left < right) {
        int sum = arr[left] + arr[right];

        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++; // need larger number
        } else {
            right--; // need smaller number
        }
    }

    return new int[]{-1, -1};
}
```

### Why this works?
- Sorted array lets you adjust pointers.
- Moves in O(n), instead of O(n²).

---

## ✔ Example: Check Palindrome

```java
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;

    while (left < right) {
        if (s.charAt(left) != s.charAt(right)) return false;
        left++;
        right--;
    }
    return true;
}
```

---

# 2. Same-Direction Pointers (Slow/Fast)

This is the **Linked List** version of two pointers but also appears in arrays.

Use-cases:
- Detect cycle (Floyd’s algorithm)
- Find middle of linked list
- Remove N-th node from end
- Partition array / remove duplicates

---

## ✔ Example: Floyd’s Cycle Detection

```java
public boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) return true;
    }
    return false;
}
```

---

## ✔ Example: Middle of Linked List

```java
public ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

---

# 3. Skip Duplicates Pattern

Used in:
- 2Sum / 3Sum / 4Sum
- Removing duplicates
- Counting unique elements
- Sorted datasets

### ✔ Example: Remove Duplicates from Sorted Array

```java
public int removeDuplicates(int[] nums) {
    int left = 0;

    for (int right = 1; right < nums.length; right++) {
        if (nums[right] != nums[left]) {
            left++;
            nums[left] = nums[right];
        }
    }

    return left + 1; // new length
}
```

Time = O(n), inplace.

---

### ✔ Example: 3Sum (skip duplicates)

```java
public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates

        int left = i + 1;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum == 0) {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                left++;
                right--;

                while (left < right && nums[left] == nums[left - 1]) left++;  // skip
                while (left < right && nums[right] == nums[right + 1]) right--; // skip
            }
            else if (sum < 0) left++;
            else right--;
        }
    }

    return result;
}
```

---

# 4. In-Place Operations

Two pointers is GREAT for modifying array in place:

- Partitioning (like Quicksort)
- Removing elements (filtering)
- Moving zeros
- Sorting colors (Dutch National Flag)

---

## ✔ Example: Move Zeroes (LeetCode 283)

```java
public void moveZeroes(int[] nums) {
    int left = 0; // pointer for non-zero

    for (int right = 0; right < nums.length; right++) {
        if (nums[right] != 0) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
        }
    }
}
```

---

## ✔ Example: Sort Colors (Dutch National Flag) — BEST example of in-place two pointers

```java
public void sortColors(int[] nums) {
    int low = 0, mid = 0, high = nums.length - 1;

    while (mid <= high) {
        if (nums[mid] == 0) {
            swap(nums, low, mid);
            low++;
            mid++;
        } else if (nums[mid] == 1) {
            mid++;
        } else {
            swap(nums, mid, high);
            high--;
        }
    }
}
```

---

# 5. Pair Sum Problems

Extremely common category.

### ✔ Example: Count pairs with sum = target

```java
public int countPairs(int[] nums, int target) {
    Arrays.sort(nums);
    int left = 0, right = nums.length - 1;
    int count = 0;

    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
            count++;
            left++;
            right--;
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }

    return count;
}
```

---

# TWO POINTERS — MASTER TEMPLATE

This template gives you 80% of solutions:

### Opposite direction (sorted array)

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];

    if (sum == target) { /* found */ }
    else if (sum < target) left++;
    else right--;
}
```

---

### Same direction (slow/fast)

```java
int slow = 0, fast = 0;
while (fast < arr.length) {
    // move fast pointer
    // move slow pointer conditionally
    fast++;
}
```

---

### Skip duplicates

```java
while (left < right && arr[left] == arr[left - 1]) left++;
while (left < right && arr[right] == arr[right + 1]) right--;
```

---

### In-place partitioning

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    if (condition) left++;
    else swap(left, right--);
}
```

---

# QUICK SUMMARY CHEATSHEET

| Subpattern | When to Use | Key Idea |
|-----------|-------------|----------|
| Opposite pointers | Sorted array / palindrome | Move toward center |
| Same direction slow/fast | Linked lists, cycle detection | fast moves 2x |
| Skip duplicates | Sorted arrays | Avoid duplicate results |
| In-place operations | Move zeros, Dutch flag | swap left/right |
| Pair sum problems | Two-sum, 3-sum | Adjust based on sum |
