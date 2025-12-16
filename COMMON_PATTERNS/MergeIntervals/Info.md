# CORE IDEA OF INTERVALS PATTERN

Intervals are always in the form:

    [start, end]


The key idea behind all problems:

✔ First: Sort intervals by start time
✔ Then: compare current interval with previous merged interval
✔ If overlapping → merge
✔ If not → add as new interval

Almost ALL interval problems reduce to this.

# 1. Merge Overlapping Intervals
Problem

Given intervals, merge all overlapping ones.

Intuition

If two intervals overlap:
```
[a, b]
   [c, d]
```

They overlap when:
```
c ≤ b
```

Merged interval becomes:
```
[start = min(a, c), end = max(b, d)]
```

# 2. Insert Interval
Problem

Given sorted intervals and a new interval, insert it and then merge if needed.

Intuition

We divide into 3 phases:
1. Add all intervals before new interval
2. Merge all overlapping intervals with new interval
3. Add remaining intervals

# 3. Erase Interval (Interval removal)
Problem

Given intervals and one interval to remove, return the intervals after removing/remapping the overlap.

Hard part:

We don’t remove fully, we may need to split:

Example: Remove ```[3,5] from [1,7]``` → becomes:
```
[1,3), (5,7]
or in integers:
[1,3] and [6,7]
```

# 4. Sweep Line Technique (Meeting Rooms)

This is EXTREMELY COMMON in interviews.

Problem

Given a set of meeting intervals, find:

- number of rooms needed (Meeting Rooms II)

    OR

- if you can attend all meetings (Meeting Rooms I)

## Sweep Line Intuition

Instead of comparing intervals directly:

1. Create two arrays:
- one for start times
- one for end times
2. Sort both
3. Walk through them together
4. Count active meetings

- Meeting room count increases when meeting starts

- Meeting room count decreases when meeting ends

###
| Subpattern      | Key Action                               | Time Complexity |
| --------------- | ---------------------------------------- | --------------- |
| Merge intervals | sort + merge sequential                  | O(n log n)      |
| Insert interval | add before, merge overlapping, add after | O(n)            |
| Remove interval | split left/right around removal          | O(n)            |
| Sweep line      | separate start/end arrays & sort         | O(n log n)      |
