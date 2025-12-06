# 1. Sliding Window

- Fixed-size window → use size condition: right - left + 1 == k

- Variable-size window → expand right, shrink while invalid

- Longest substring/subarray with condition → often "while invalid → shrink" (LC3)

- At most K distinct → use frequency + distinct counter

- Anagram / permutation window → fixed-size + frequency comparison

- Dynamic expand + shrink mindset → generic mental template

- Sliding Window Maximum → use Deque to track max in O(n)

## LC - 
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

## LC -
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