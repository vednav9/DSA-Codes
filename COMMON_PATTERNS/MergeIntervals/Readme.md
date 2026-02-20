This pattern shows up in Google, Meta, Stripe, Uber, Microsoft **all the time**.

We will cover all 4 subpatterns:

1. **Merge overlapping intervals**
2. **Insert interval**
3. **Erase interval**
4. **Sweep-line technique (Meeting Rooms)**

Every subpattern will have:
✔ Intuition
✔ Rules
✔ Diagram explanation
✔ Java code template
✔ How to identify the pattern during an interview

---

# CORE IDEA OF INTERVALS PATTERN

Intervals are always in the form:

```
[start, end]
```

The **key idea** behind all problems:

### ✔ First: **Sort intervals by start time**

### ✔ Then: compare current interval with previous merged interval

### ✔ If overlapping → merge

### ✔ If not → add as new interval

Almost ALL interval problems reduce to this.

---

# 1. Merge Overlapping Intervals

### Problem

Given intervals, merge all overlapping ones.

### Intuition

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

---

## ✔ Java Code — Merge Intervals

```java
public int[][] merge(int[][] intervals) {
    if (intervals.length <= 1) return intervals;

    // Step 1: Sort intervals by start
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    List<int[]> result = new ArrayList<>();

    // Start with the first interval
    int[] current = intervals[0];

    for (int i = 1; i < intervals.length; i++) {
        int[] next = intervals[i];

        // Check overlap: next.start <= current.end
        if (next[0] <= current[1]) {
            // Merge
            current[1] = Math.max(current[1], next[1]);
        } else {
            // No overlap → push current
            result.add(current);
            current = next; // move to next interval
        }
    }

    // Add last interval
    result.add(current);

    return result.toArray(new int[result.size()][]);
}
```

---

# 2. Insert Interval

### Problem

Given sorted intervals and a new interval, insert it and then merge if needed.

### Intuition

We divide into 3 phases:

1. **Add all intervals before new interval**
2. **Merge all overlapping intervals with new interval**
3. **Add remaining intervals**

---

## ✔ Java Code — Insert Interval

```java
public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();

    int i = 0;
    int n = intervals.length;

    // 1. Add all intervals ending before newInterval starts
    while (i < n && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i]);
        i++;
    }

    // 2. Merge overlaps: intervals starting before newInterval ends
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    result.add(newInterval);

    // 3. Add remaining intervals
    while (i < n) {
        result.add(intervals[i]);
        i++;
    }

    return result.toArray(new int[result.size()][]);
}
```

### ✔ When to use this pattern?

Whenever you see:

* “Insert a new interval”
* “Add event and merge”
* “Add time slot”

This exact logic applies.

---

# 3. Erase Interval (Interval removal)

### Problem

Given intervals and one interval to remove, return the intervals after removing/remapping the overlap.

### Hard part:

We **don’t remove fully**, we may need to split:

Example: Remove `[3,5]` from `[1,7]` → becomes:

```
[1,3), (5,7]
or in integers:
[1,3] and [6,7]
```

---

## ✔ Java Code — Erase Interval

```java
public List<int[]> removeInterval(int[][] intervals, int[] toRemove) {
    List<int[]> result = new ArrayList<>();

    int rStart = toRemove[0];
    int rEnd = toRemove[1];

    for (int[] interval : intervals) {
        int start = interval[0];
        int end = interval[1];

        // No overlap
        if (end <= rStart || start >= rEnd) {
            result.add(interval);
        }
        else {
            // Left part remaining
            if (start < rStart) {
                result.add(new int[]{start, rStart});
            }
            // Right part remaining
            if (end > rEnd) {
                result.add(new int[]{rEnd, end});
            }
        }
    }

    return result;
}
```

### ✔ Used in:

* Removing timeout slot
* Cutting a time range out of existing schedule
* Calendar modifications

---

# 4. Sweep Line Technique (Meeting Rooms)

This is EXTREMELY COMMON in interviews.

### Problem

Given a set of meeting intervals, find:

* number of rooms needed (Meeting Rooms II)
  OR
* if you can attend all meetings (Meeting Rooms I)

---

## Sweep Line Intuition

Instead of comparing intervals directly:

1. Create two arrays:

   * one for start times
   * one for end times
2. Sort both
3. Walk through them together
4. Count active meetings

### ✔ Meeting room count increases when meeting starts

### ✔ Meeting room count decreases when meeting ends

---

## ✔ Java Code — Meeting Rooms II (Minimum Rooms)

```java
public int minMeetingRooms(int[][] intervals) {
    int n = intervals.length;
    int[] start = new int[n];
    int[] end = new int[n];

    for (int i = 0; i < n; i++) {
        start[i] = intervals[i][0];
        end[i] = intervals[i][1];
    }

    Arrays.sort(start);
    Arrays.sort(end);

    int rooms = 0;
    int endPtr = 0;

    for (int i = 0; i < n; i++) {
        // New meeting starts before earliest one ends → need a room
        if (start[i] < end[endPtr]) {
            rooms++;
        } else {
            // else one meeting freed up a room
            endPtr++;
        }
    }

    return rooms;
}
```

### ✔ What this gives you?

* A scalable way to track overlapping intervals
* Works for many problems beyond meeting rooms

---

# IDENTIFYING INTERVAL PATTERN IN INTERVIEWS

If you hear any of these phrases, THINK “Intervals Pattern”:

✔ “merge time ranges”
✔ “find free time”
✔ “overlapping tasks”
✔ “scheduling problem”
✔ “insert into sorted list of ranges”
✔ “find minimum rooms / maximum overlaps”
✔ “calendar bookings”

Always start by saying:

> “I will sort the intervals by start time first.”

This shows interviewers you understand the correct approach.

---

# CHEATSHEET SUMMARY

| Subpattern      | Key Action                               | Time Complexity |
| --------------- | ---------------------------------------- | --------------- |
| Merge intervals | sort + merge sequential                  | O(n log n)      |
| Insert interval | add before, merge overlapping, add after | O(n)            |
| Remove interval | split left/right around removal          | O(n)            |
| Sweep line      | separate start/end arrays & sort         | O(n log n)      |
