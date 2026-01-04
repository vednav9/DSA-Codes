# Sliding Window – Core Idea

You have an array/string and you’re interested in continuous segments (subarrays / substrings).

Instead of:
- brute force = check all subarrays → O(n²)

You:
- maintain a “window” [left…right]
- expand right to include more
- move left to remove from the front
- keep updating your answer on the fly → O(n)

# 1. Fixed-Size Window

Window size is fixed (e.g., subarray of size k).

Classic example:
Max sum of any subarray of size k.

Key things:

- right - left + 1 always tracks size
- Only one loop → O(n)
- Very useful for averages, sums, counts when size is fixed.

# 2. Variable-Size Window (Generic Template)
    
Window grows and shrinks depending on some condition (sum, distinct count, etc.)

# 3. Longest Substring/Subarray with Condition

Typical pattern: “Longest … such that condition holds”

You:

- expand right
- if condition violated → move left until valid again
- keep track of max length when valid

Example: Longest Substring Without Repeating Characters (LC 3)

Condition: all chars unique.

Pattern:

- Add right
- While invalid (duplicate appears) → shrink from left
- Update max when valid.

# 4. “At Most K Distinct” Pattern

Super important pattern; used for:

- “at most K distinct”
- “exactly K distinct” (by trick: ```atMost(K) - atMost(K-1)```)

# 5. Anagram / Permutation Window Pattern

Given a pattern p and string s, find all substrings of s that are a permutation/anagram of p.

Key ideas:

- Fixed-size window (length = p.length())
- Character frequency comparison
- Use matches counter to avoid full compare each time

# 6. “Dynamic Expand + Shrink” Mindset (Core Mental Model)

Almost every variable-size sliding window follows:

1. right loops from 0 → n - 1
2. We add arr[right] / s.charAt(right) to some state:
- sum
- map
- count
3. While window invalid → move left forward and remove things from state
4. When window valid → use [left..right] to update ans