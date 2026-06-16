**Two Heaps Pattern is NOT used in Quicksort.**
It is mainly used for **median problems and balancing two halves of data**.

Now let’s properly learn the **Two Heaps Pattern** step-by-step so the concept becomes very clear.

---

# PART 1 — WHY TWO HEAPS ARE NEEDED

Some problems require:

• continuously inserting numbers
• quickly getting the **median**

Example stream:

```
5, 15, 1, 3
```

After each insertion we want the **median**.

Sorting each time:

```
O(n log n) each step ❌
```

Two Heaps solution:

```
O(log n) insert
O(1) median
```

---

# PART 2 — WHAT IS THE CORE IDEA

We divide numbers into **two halves**.

```
smaller half | larger half
```

Example:

```
[1,3,5] | [7,9,11]
```

Then median is between these halves.

---

# PART 3 — WHAT HEAPS DO WE USE?

| Heap     | Purpose             |
| -------- | ------------------- |
| Max Heap | stores smaller half |
| Min Heap | stores larger half  |

Why?

Max heap lets us quickly access the **largest of the smaller half**.

Min heap lets us access the **smallest of the larger half**.

```
maxHeap.peek() <= minHeap.peek()
```

---

# PART 4 — HEAP STRUCTURE

Example numbers inserted:

```
5, 15, 1, 3
```

Heaps become:

```
maxHeap = [3,1]
minHeap = [5,15]
```

Median:

```
(3 + 5) / 2 = 4
```

---

# PART 5 — TWO HEAP RULES (VERY IMPORTANT)

We must maintain two rules:

### Rule 1

```
All elements in maxHeap <= elements in minHeap
```

---

### Rule 2

Heap sizes differ by **at most 1**

```
|maxHeap - minHeap| <= 1
```

---

# PART 6 — INSERTION PROCESS

When inserting new number:

### Step 1

Insert into maxHeap first.

```
maxHeap.add(num)
```

---

### Step 2

Move largest element of maxHeap to minHeap.

```
minHeap.add(maxHeap.poll())
```

---

### Step 3

Balance heaps.

If minHeap becomes bigger:

```
maxHeap.add(minHeap.poll())
```

---

# PART 7 — FINDING MEDIAN

### Case 1: Even elements

```
median = (maxHeap.peek + minHeap.peek) / 2
```

---

### Case 2: Odd elements

```
median = maxHeap.peek
```

Because maxHeap keeps one extra element.

---

# PART 8 — VISUAL DRY RUN

Stream:

```
5
```

```
maxHeap = [5]
minHeap = []
median = 5
```

---

Insert 15

```
maxHeap = [5]
minHeap = [15]
median = (5+15)/2 = 10
```

---

Insert 1

```
maxHeap = [5,1]
minHeap = [15]
median = 5
```

---

Insert 3

```
maxHeap = [3,1]
minHeap = [5,15]
median = 4
```

---

# PART 9 — JAVA IMPLEMENTATION

This is the **LeetCode 295 solution**.

```java
class MedianFinder {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {

        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        return maxHeap.peek();
    }
}
```

---

# PART 10 — TIME COMPLEXITY

Insert:

```
O(log n)
```

Median query:

```
O(1)
```

---

# PART 11 — WHEN TO USE TWO HEAPS

Use Two Heaps when problem involves:

| Problem Type           |
| ---------------------- |
| Running median         |
| Continuous stream      |
| Balanced halves        |
| Sliding window median  |
| Percentile calculation |

---

# PART 12 — REAL INTERVIEW PROBLEMS

Common questions:

1️⃣ **Median of Data Stream**
2️⃣ **Sliding Window Median**
3️⃣ **IPO problem (LeetCode)**
4️⃣ **Find median in stream**

---

# FINAL CHEAT RULE

If problem says:

```
stream
median
continuous insert
balance two halves
```

👉 **Think Two Heaps Pattern**