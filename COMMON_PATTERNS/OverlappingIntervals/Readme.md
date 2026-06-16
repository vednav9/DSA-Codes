It is a **separate greedy + sorting pattern** used for interval problems.

Now I’ll teach it **from the ground up**, so you fully understand **why sorting works**, **how merging works**, and **how different interval problems are variations of the same logic**.

---

# PART 1 — WHAT IS AN INTERVAL?

An interval represents a **range**.

Example:

```
[1,3]
[2,6]
[8,10]
[15,18]
```

Each interval has:

```
start
end
```

---

# PART 2 — WHAT DOES “OVERLAPPING” MEAN?

Two intervals overlap if:

```
start2 <= end1
```

Example:

```
[1,3]
[2,6]
```

Since:

```
2 <= 3
```

They overlap.

---

# PART 3 — WHY SORTING IS REQUIRED

Sorting helps us **process intervals in order**.

We sort by **start time**.

Example before sorting:

```
[8,10]
[1,3]
[2,6]
```

After sorting:

```
[1,3]
[2,6]
[8,10]
```

Now overlaps become easy to detect.

---

# PART 4 — MERGING OVERLAPPING INTERVALS

Example:

```
[1,3]
[2,6]
```

These merge into:

```
[1,6]
```

Because:

```
start = min(1,2)
end = max(3,6)
```

---

# PART 5 — MERGE INTERVALS ALGORITHM

Steps:

### Step 1

Sort intervals by start.

### Step 2

Keep a result list.

### Step 3

For each interval:

Case 1: **No overlap**

```
current.start > previous.end
```

Add new interval.

Case 2: **Overlap**

```
previous.end = max(previous.end, current.end)
```

---

# VISUAL EXAMPLE

Input:

```
[1,3]
[2,6]
[8,10]
[15,18]
```

Step 1: sort

```
[1,3]
[2,6]
[8,10]
[15,18]
```

---

Process:

```
merge [1,3] + [2,6] → [1,6]
```

Result:

```
[1,6]
[8,10]
[15,18]
```

---

# PART 6 — JAVA CODE (MERGE INTERVALS)

```java
public int[][] merge(int[][] intervals) {

    Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

    List<int[]> result = new ArrayList<>();

    int[] current = intervals[0];
    result.add(current);

    for(int[] interval : intervals){

        if(interval[0] <= current[1]){
            current[1] = Math.max(current[1], interval[1]);
        }
        else{
            current = interval;
            result.add(current);
        }
    }

    return result.toArray(new int[result.size()][]);
}
```

---

# PART 7 — MEETING ROOMS PROBLEM

### Problem

Given meeting times:

```
[0,30]
[5,10]
[15,20]
```

Find **minimum number of meeting rooms required**.

---

## Key idea

Two meetings overlap if:

```
start < previous end
```

We track **active meetings using a min heap of end times**.

---

# ALGORITHM

1. Sort meetings by start time
2. Use min heap for end times
3. If meeting start ≥ earliest ending meeting → reuse room
4. Otherwise allocate new room

---

# JAVA CODE

```java
public int minMeetingRooms(int[][] intervals) {

    Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    pq.offer(intervals[0][1]);

    for(int i=1;i<intervals.length;i++){

        if(intervals[i][0] >= pq.peek()){
            pq.poll();
        }

        pq.offer(intervals[i][1]);
    }

    return pq.size();
}
```

---

# PART 8 — MINIMUM ARROWS TO BURST BALLOONS

### Problem

Balloons represented as:

```
[start,end]
```

One arrow at position `x` bursts all balloons where:

```
start <= x <= end
```

Goal: **minimum arrows**.

---

## Key Insight

Sort by **end coordinate**.

Always shoot arrow at **earliest end**.

Greedy logic works because earlier arrow bursts more balloons.

---

# EXAMPLE

```
[10,16]
[2,8]
[1,6]
[7,12]
```

Sort by end:

```
[1,6]
[2,8]
[7,12]
[10,16]
```

Arrow at `6` bursts first two.

Second arrow at `12`.

Answer = **2**

---

# JAVA CODE

```java
public int findMinArrowShots(int[][] points) {

    Arrays.sort(points, (a,b) -> Integer.compare(a[1], b[1]));

    int arrows = 1;
    int end = points[0][1];

    for(int i=1;i<points.length;i++){

        if(points[i][0] > end){
            arrows++;
            end = points[i][1];
        }
    }

    return arrows;
}
```

---

# PART 9 — MASTER INTERVAL TEMPLATE

Most interval problems follow this structure:

```
1. Sort intervals
2. Traverse once
3. Apply greedy merge / count logic
```

---

# PART 10 — HOW TO RECOGNIZE INTERVAL PROBLEMS

If problem mentions:

```
interval
meeting
schedule
range
start/end
merge
overlap
timeline
```

👉 Think **Overlapping Interval Pattern**

---

# MASTER CHEAT TABLE

| Problem         | Technique          |
| --------------- | ------------------ |
| Merge intervals | Sorting + merging  |
| Meeting rooms   | Sorting + min heap |
| Burst balloons  | Sorting + greedy   |
| Insert interval | merge pattern      |

---

# TIME COMPLEXITY

Sorting dominates:

```
O(n log n)
```

Traversal:

```
O(n)
```

Total:

```
O(n log n)
```

---

# ONE-LINE MEMORY RULE

> **Interval problems = sort first, then process overlaps**