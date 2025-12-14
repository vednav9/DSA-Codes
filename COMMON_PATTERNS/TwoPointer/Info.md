# WHAT IS THE TWO POINTERS PATTERN?

Two pointers = two indices moving in a sequence:
- either **towards each other**
- or together in **same direction**

We use it when:
- array/string is **sorted**, or
- we need **continuous regions**, or
- we detect something like duplications, partitions, palindromes.

Time complexity improves from ```O(n²) → O(n)```.

# 1. Opposite Direction Pointers

(Left at start, right at end: move toward center)

Used when:

- Array/string is sorted
- You want pairs, triplets, or check palindrome

# 2. Same-Direction Pointers (Slow/Fast)

This is the Linked List version of two pointers but also appears in arrays.

Use-cases:

- Detect cycle (Floyd’s algorithm)
- Find middle of linked list
- Remove N-th node from end
- Partition array / remove duplicates

# 3. Skip Duplicates Pattern

Used in:
- 2Sum / 3Sum / 4Sum
- Removing duplicates
- Counting unique elements
- Sorted datasets

# 4. In-Place Operations

- Two pointers is GREAT for modifying array in place:
- Partitioning (like Quicksort)
- Removing elements (filtering)
- Moving zeros
- Sorting colors (Dutch National Flag)

