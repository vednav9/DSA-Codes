# 1. Sliding Window

- Fixed-size window → use size condition: right - left + 1 == k

- Variable-size window → expand right, shrink while invalid

- Longest substring/subarray with condition → often "while invalid → shrink" (LC3)

- At most K distinct → use frequency + distinct counter

- Anagram / permutation window → fixed-size + frequency comparison

- Dynamic expand + shrink mindset → generic mental template

- Sliding Window Maximum → use Deque to track max in O(n)

## Key Problems - 
- Longest Substring Without Repeating Characters (LC 3)
- Sliding Window Maximum (LC 239)
- Minimum Window Substring (LC 76)
- Permutation in String (LC 567)


# 2. Two Pointers Pattern

- Opposite direction pointers

- Same direction (slow/fast)

- Skip duplicates

- In-place operations

- Pair sum problems

## Key Problems -
- Two Sum (sorted)
- 3Sum (LC 15)
- Valid Palindrome
- Remove Duplicates from Sorted Array (LC 26)


# 3. Fast & Slow Pointer Pattern
- Floyd cycle detection

- Find cycle start

- Middle of linked list

- Palindrome linked list

- Happy number

## Key Problems -
- Detect Cycle in Linked List (LC 141)
- Happy Number (LC 202)
- Middle of Linked List (LC 876)


# 4. Linked List In-Place Reversal Pattern

- Reverse entire list

- Reverse sublist

- Reverse k-group

- Reorder list

## Key Problems -
- Reverse Linked List (LC 206)
- Reverse Nodes in k-Group (LC 25)
- Remove Nth Node From End (LC 19)


# 5. Merge Intervals Pattern

- Merge overlapping intervals

- Insert interval

- Erase interval

- Sweep line technique (meeting rooms)

## Key Problems -
- Merge Intervals (LC 56)
- Insert Interval (LC 57)
- Meeting Rooms II (LC 253)

# 6. Prefix Sum Pattern

- Basic prefix sum

- Hashmap prefix sum

- Parity prefix

- 2D prefix sum

- Difference array (range update)

## Key Problems
- Subarray Sum Equals K (LC 560)
- Product of Array Except Self (LC 238)
- Range Sum Query


# 7. Binary Search Pattern
## Subpatterns
- First/last occurrence

- Binary Search on Answer

- Search rotated array

- Peak element

- K-th smallest pair distance

- Minimize maximum (painters, books)

## Key Problems
- Kth Largest (Quickselect + BS)
- Find Peak Element (LC 162)
- Search in Rotated Sorted Array (LC 33)
