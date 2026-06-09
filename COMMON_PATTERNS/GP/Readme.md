Greedy algorithms look simple but require **deep intuition about optimal choices**.

We’ll cover:

1. **What Greedy really means**
2. **How to recognize greedy problems**
3. **Activity Selection**
4. **Interval Scheduling**
5. **Fractional Knapsack**
6. **Huffman Coding**
7. **Jump Game**

---

# 1️⃣ What is a Greedy Algorithm?

A **Greedy algorithm** makes the **best local choice at each step** hoping it leads to the **global optimum**.

Instead of exploring all possibilities (like DP), it **commits immediately**.

Example thinking:

```
At this moment, what is the best decision?
Take it.
Move forward.
```

---

# Greedy vs Dynamic Programming

| Greedy                | Dynamic Programming  |
| --------------------- | -------------------- |
| Local optimal choice  | explores many states |
| Usually sorting based | uses DP tables       |
| Faster                | sometimes slower     |

Greedy works only if the problem has:

### 1️⃣ Greedy Choice Property

Local best choice leads to global optimum.

### 2️⃣ Optimal Substructure

Optimal solution can be built from smaller optimal solutions.

---

# 2️⃣ Activity Selection Problem

## Problem

You have activities with:

```
start time
finish time
```

Goal:

```
Select maximum number of non-overlapping activities
```

Example:

```
start:  [1,3,0,5,8,5]
finish: [2,4,6,7,9,9]
```

---

## Key Insight

Always choose the activity that **finishes earliest**.

Why?

Because it leaves **maximum room for future activities**.

---

## Algorithm

1. Sort activities by **finish time**
2. Select the first activity
3. Pick next activity whose start ≥ last finish

---

## Java Code

```java
import java.util.*;

class ActivitySelection {

    static int maxActivities(int[] start, int[] finish) {

        int n = start.length;

        int[][] activities = new int[n][2];

        for(int i=0;i<n;i++){
            activities[i][0] = start[i];
            activities[i][1] = finish[i];
        }

        Arrays.sort(activities, (a,b) -> a[1]-b[1]);

        int count = 1;
        int lastFinish = activities[0][1];

        for(int i=1;i<n;i++){

            if(activities[i][0] >= lastFinish){
                count++;
                lastFinish = activities[i][1];
            }

        }

        return count;
    }
}
```

---

# 3️⃣ Interval Scheduling

This is essentially the **same concept** as activity selection.

Example:

```
[1,3]
[2,5]
[4,6]
```

Sort by **end time**, then select intervals that don't overlap.

This appears in problems like:

```
Maximum number of meetings
Maximum non-overlapping intervals
```

---

# 4️⃣ Fractional Knapsack

## Problem

You have items with:

```
weight
value
```

But unlike 0/1 knapsack:

```
You can take fractions of items.
```

Example:

```
capacity = 50

item1: weight 10 value 60
item2: weight 20 value 100
item3: weight 30 value 120
```

---

## Key Insight

Take items with **maximum value per weight** first.

```
value / weight ratio
```

Sort items by:

```
value/weight descending
```

---

## Java Code

```java
import java.util.*;

class FractionalKnapsack {

    static double getMaxValue(int[] wt, int[] val, int capacity){

        int n = wt.length;

        double[][] items = new double[n][2];

        for(int i=0;i<n;i++){
            items[i][0] = (double) val[i] / wt[i];
            items[i][1] = i;
        }

        Arrays.sort(items, (a,b)-> Double.compare(b[0],a[0]));

        double totalValue = 0;

        for(int i=0;i<n;i++){

            int index = (int) items[i][1];

            if(capacity >= wt[index]){

                capacity -= wt[index];
                totalValue += val[index];

            } else {

                totalValue += items[i][0] * capacity;
                break;

            }

        }

        return totalValue;
    }
}
```

---

# 5️⃣ Huffman Coding (Greedy + Heap)

## Problem

Used in **data compression**.

Goal:

```
Build optimal prefix codes minimizing total encoding length.
```

---

## Key Idea

Always combine **two smallest frequencies first**.

Use **min heap**.

Example frequencies:

```
a:5
b:9
c:12
d:13
e:16
f:45
```

Steps:

```
1. Pick two smallest
2. Merge them
3. Push back to heap
4. Repeat
```

---

## Java Code (Simplified)

```java
import java.util.*;

class HuffmanCoding {

    static int huffmanCost(int[] freq){

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int f : freq)
            pq.offer(f);

        int cost = 0;

        while(pq.size() > 1){

            int a = pq.poll();
            int b = pq.poll();

            int merged = a + b;

            cost += merged;

            pq.offer(merged);
        }

        return cost;
    }
}
```

---

# 6️⃣ Jump Game

## Problem

Array represents maximum jump length.

Example:

```
[2,3,1,1,4]
```

Can you reach the last index?

---

## Greedy Insight

Track the **farthest reachable index**.

If at any index:

```
i > farthestReach
```

Then it's impossible.

---

## Java Code

```java
class JumpGame {

    public boolean canJump(int[] nums){

        int farthest = 0;

        for(int i=0;i<nums.length;i++){

            if(i > farthest)
                return false;

            farthest = Math.max(farthest, i + nums[i]);

        }

        return true;
    }
}
```

---

# 7️⃣ How to Recognize Greedy Problems

Greedy often appears when problems mention:

```
maximize
minimize
schedule
select
interval
profit
ratio
```

Often the solution involves:

```
sort + single pass
```

---

# Greedy Pattern Summary

| Problem             | Greedy Strategy      |
| ------------------- | -------------------- |
| Activity selection  | earliest finish time |
| Interval scheduling | sort by end          |
| Fractional knapsack | max value/weight     |
| Huffman coding      | merge smallest       |
| Jump game           | track farthest reach |

---

# Final Mental Model

Greedy algorithm means:

```
Make the best choice now
Don't reconsider
Move forward
```