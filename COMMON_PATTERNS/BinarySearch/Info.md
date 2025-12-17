# right=mid
# Binary Search is not only for finding a number.

It is a power technique used to search on:

values

answers

ranges

peaks

distances

time

capacity


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

# 1. First / Last Occurrence

(Searching boundaries using binary search)

Problem

Given sorted array with duplicates, find:

- First position of target

- Last position of target

This is widely used in:

- frequency queries

- range queries

- "count how many times X appears"

- LeetCode 34

# 2. Binary Search on Answer

(One of the MOST IMPORTANT patterns in coding interviews)

Key Insight

- You are not searching an array.
- You are searching for the answer (a number) that satisfies a condition.

Examples:

- Minimum capacity to ship packages

- Minimum time to paint boards

- Minimum largest subarray sum

- Koko eating bananas

- Allocate books

General pattern:

Template
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

Always:

```isPossible(mid)``` checks if mid is a valid answer

- If mid works → shrink right

- If mid fails → increase left

Binary search continues until both meet.

# 3. Search in Rotated Sorted Array

# 4. Peak Element (Unimodal Search)
A peak is an element where:

```java
nums[i] > nums[i-1] and nums[i] > nums[i+1]
```

# 5. K-th Smallest Pair Distance

(Hard problem using binary search on answer)

Goal:

Given a sorted array, find K-th smallest absolute difference between any pair.

Example:
```java
nums = [1,3,6]
pair distances = [2,5,3] → sorted: [2,3,5]
```

## Trick

Search distance ```d``` using binary search.

Let:

- left = 0

- right = max(nums) - min(nums)

For each mid (candidate distance), count pairs with distance ≤ mid.

Use two pointers to count in O(n).

# 6. Minimize Maximum (Painters / Books allocation)

Famous problems:

- Split array into “m” subarrays minimizing largest sum

- Allocate books to students

- Painters partition problem

- Ship packages

## Trick

Binary search on maximum allowed workload.

Let:
```java
min = max element
max = sum of all elements
```

Binary search this range.


---

| Subpattern                  | Key Idea                        | When to Use              |
| --------------------------- | ------------------------------- | ------------------------ |
| First/Last Occurrence       | Keep searching even after found | duplicates               |
| Binary Search on Answer     | Search in value range           | capacity, distance, time |
| Rotated Sorted Array        | One half is always sorted       | rotated arrays           |
| Peak Element                | Compare mid with mid+1          | unimodal arrays          |
| K-th Smallest Pair Distance | Count pairs ≤ mid               | sorted + distances       |
| Minimize Maximum            | isPossible(mid) check           | load balancing           |
