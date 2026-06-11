**Heap / Priority Queue** is the last **high-impact core pattern** in interviews.

We’ll go step-by-step in this exact order:

1. **What is a Heap & why we need it**
2. **Min-Heap vs Max-Heap (VERY IMPORTANT)**
3. **Core Heap template in Java**
4. **Top K pattern**
5. **Kth smallest / largest**
6. **Merge K sorted lists**
7. **Running Median (2 Heaps pattern)**
8. **Frequency sorting**
9. **How to recognize Heap problems instantly**

---

# PART 0 — WHY HEAP / PRIORITY QUEUE EXISTS

## The core problem it solves

You want to:

* always get the **smallest** or **largest** element
* while data keeps changing (insert / remove)

Sorting every time → **O(n log n)** (slow)
Heap → **O(log n)** per operation (fast)

---

## Real-life analogy

* Hospital emergency queue
* CPU scheduling
* Trending videos
* Top scorers

👉 “Always give me the most important item first”

That’s a **priority queue**.

---

# PART 1 — WHAT IS A HEAP?

A heap is a **complete binary tree** with ordering property.

### Types:

| Type     | Property                |
| -------- | ----------------------- |
| Min Heap | smallest element on top |
| Max Heap | largest element on top  |

---

## Java Heap (VERY IMPORTANT)

Java **PriorityQueue** is:

* **Min Heap by default**

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
```

Max Heap:

```java
PriorityQueue<Integer> maxHeap =
    new PriorityQueue<>(Collections.reverseOrder());
```

---

# PART 2 — CORE HEAP TEMPLATE (MEMORIZE)

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(x);   // insert
pq.poll();     // remove top
pq.peek();     // see top
```

All heap problems are **variations of this**.

---

# 1️⃣ TOP K ELEMENTS PATTERN

## 🎯 Problem

Find **Top K largest** (or smallest) elements.

---

## THINKING (MOST IMPORTANT)

If we want **Top K largest**:

* keep a **min-heap of size K**
* smallest among top K stays on top
* if new element > heap top → replace

---

## 🧩 Why Min-Heap for Top K Largest?

Because:

* smallest of the K best is easiest to remove

---

## ✅ Code — Top K Largest Elements

```java
public int[] topKFrequent(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }

    int[] res = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        res[i] = minHeap.poll();
    }
    return res;
}
```

---

## Concept takeaway

* **Top K largest → Min Heap**
* **Top K smallest → Max Heap**

---

# 2️⃣ Kth SMALLEST / LARGEST ELEMENT

## 🎯 Problem

Find **Kth largest** element in array.

---

## THINKING

Same as Top K:

* Maintain heap of size K
* Final top is answer

---

## ✅ Code — Kth Largest (LC 215)

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }
    return minHeap.peek();
}
```

---

## Difference from Top K?

None — same pattern, different output.

---

# 3️⃣ MERGE K SORTED LISTS (VERY IMPORTANT)

## 🎯 Problem

You are given K sorted linked lists.
Merge them into one sorted list.

---

## THINKING

* We always want the **smallest current node**
* Push the head of each list into min-heap
* Pop smallest, push its next

---

## 🧩 Heap contains:

* Node value
* Node reference

---

## ✅ Code — Merge K Sorted Lists (LC 23)

```java
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq =
        new PriorityQueue<>((a, b) -> a.val - b.val);

    for (ListNode node : lists) {
        if (node != null) pq.offer(node);
    }

    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    while (!pq.isEmpty()) {
        ListNode node = pq.poll();
        curr.next = node;
        curr = curr.next;

        if (node.next != null) {
            pq.offer(node.next);
        }
    }
    return dummy.next;
}
```

---

## Complexity

* Time: `O(N log K)`
* Space: `O(K)`

---

# 4️⃣ RUNNING MEDIAN (2 HEAPS PATTERN)

## 🎯 Problem

Stream of numbers → after each insertion, return median.

---

## KEY IDEA (VERY IMPORTANT)

Split numbers into **two halves**:

| Heap     | Stores       |
| -------- | ------------ |
| Max Heap | smaller half |
| Min Heap | larger half  |

---

## Invariant

* Size difference ≤ 1
* MaxHeap top ≤ MinHeap top

---

## Median Rules

* Even size → avg of two tops
* Odd size → top of larger heap

---

## ✅ Code — Running Median

```java
class MedianFinder {
    PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap =
        new PriorityQueue<>();

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // balance
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        if (minHeap.size() > maxHeap.size()) {
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

## WHY THIS WORKS

* Left heap controls lower half
* Right heap controls upper half
* Median always at the boundary

---

# 5️⃣ FREQUENCY SORTING

## 🎯 Problem

Sort elements by frequency.

---

## THINKING

1. Count frequency using map
2. Push entries into heap ordered by frequency

---

## ✅ Code — Frequency Sort (LC 347 / 451)

```java
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums)
        freq.put(n, freq.getOrDefault(n, 0) + 1);

    PriorityQueue<Integer> pq =
        new PriorityQueue<>(
            (a, b) -> freq.get(a) - freq.get(b)
        );

    for (int num : freq.keySet()) {
        pq.offer(num);
        if (pq.size() > k) {
            pq.poll();
        }
    }

    int[] res = new int[k];
    for (int i = k - 1; i >= 0; i--) {
        res[i] = pq.poll();
    }
    return res;
}
```

---

# MASTER HEAP CHEATSHEET

| Problem           | Heap Used  |
| ----------------- | ---------- |
| Top K largest     | Min Heap   |
| Top K smallest    | Max Heap   |
| Kth largest       | Min Heap   |
| Merge K lists     | Min Heap   |
| Running median    | Two Heaps  |
| Frequency sorting | Heap + Map |

---

# HOW TO IDENTIFY HEAP PROBLEMS IN INTERVIEWS

If problem says:

* “top”
* “kth”
* “smallest / largest”
* “stream”
* “merge sorted”
* “priority”
* “frequent”

👉 **Think Heap immediately**

---

# ONE-LINE MEMORY RULE

> **Heap = Always keep the best candidate at the top**