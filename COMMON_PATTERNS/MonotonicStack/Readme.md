**Monotonic Stack** is one of the **highest-ROI interview patterns**.

We’ll go in this order (this order matters):

1. **What is a stack & why normal stack fails**
2. **What “monotonic” really means**
3. **Core monotonic stack template**
4. **Next Greater Element**
5. **Next Smaller Element**
6. **Daily Temperatures**
7. **Largest Rectangle in Histogram (hardest, but logical)**
8. **How to recognize monotonic stack instantly**

---

# PART 0 — WHY MONOTONIC STACK EXISTS

## The core problem it solves

You are given an array, and for each element you want to know:

* the **next greater**
* the **next smaller**
* the **previous greater/smaller**
* how far it can extend until blocked

Brute force = **O(n²)**
Monotonic stack = **O(n)**

---

## Example brute force problem

```
arr = [2, 1, 5, 6, 2, 3]

For 5 → next smaller?
→ need to scan right → slow
```

We need a smarter structure.

---

# PART 1 — WHAT IS A MONOTONIC STACK?

A **monotonic stack** is a stack that maintains elements in a **specific order**:

### Types:

* **Monotonic Increasing Stack**
* **Monotonic Decreasing Stack**

---

## 🔑 Very Important Rule

We **do not care about the stack itself**.
We care about **when elements get popped**.

> 💡 *The answer for an element is found when it gets popped.*

---

# PART 2 — TYPES OF MONOTONIC STACKS

## 1️⃣ Monotonic Increasing Stack

* Stack elements are in **increasing order**
* Used for:

  * **Next Smaller Element**
  * **Previous Smaller Element**

Example stack:

```
[1, 3, 5, 8]
```

---

## 2️⃣ Monotonic Decreasing Stack

* Stack elements are in **decreasing order**
* Used for:

  * **Next Greater Element**
  * **Previous Greater Element**

Example stack:

```
[8, 5, 3, 1]
```

---

# PART 3 — CORE TEMPLATE (MEMORIZE THIS)

```java
Stack<Integer> st = new Stack<>();

for (int i = 0; i < n; i++) {
    while (!st.isEmpty() && condition) {
        int idx = st.pop();
        // answer for idx is i
    }
    st.push(i);
}
```

* Stack stores **indices**, not values
* Condition depends on problem

---

# 1️⃣ NEXT GREATER ELEMENT

## 🎯 Problem

For each element, find the **next element to the right that is greater**.

Example:

```
[2, 1, 2, 4, 3]
→ [4, 2, 4, -1, -1]
```

---

## THINKING

* We want **next greater**
* Use **monotonic decreasing stack**
* When a bigger element appears → it resolves previous smaller ones

---

## CONDITION

```
while stack not empty AND current > stackTop
```

---

## ✅ Code

```java
public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    Arrays.fill(res, -1);

    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && nums[i] > nums[st.peek()]) {
            res[st.pop()] = nums[i];
        }
        st.push(i);
    }
    return res;
}
```

---

## WHY THIS WORKS

* Each element waits in stack until a greater element appears
* Each element is pushed and popped **once**
* Total time = **O(n)**

---

# 2️⃣ NEXT SMALLER ELEMENT

## 🎯 Problem

For each element, find next smaller element to the right.

Example:

```
[4, 5, 2, 10, 8]
→ [2, 2, -1, 8, -1]
```

---

## DIFFERENCE FROM NGE

Now we want:

* **next smaller**
* Use **monotonic increasing stack**

---

## CONDITION

```
while stack not empty AND current < stackTop
```

---

## ✅ Code

```java
public int[] nextSmallerElements(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    Arrays.fill(res, -1);

    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && nums[i] < nums[st.peek()]) {
            res[st.pop()] = nums[i];
        }
        st.push(i);
    }
    return res;
}
```

---

# 3️⃣ DAILY TEMPERATURES

## 🎯 Problem

For each day, find how many days you must wait for a **warmer temperature**.

Example:

```
[73,74,75,71,69,72,76,73]
→ [1,1,4,2,1,1,0,0]
```

---

## THINKING

This is just **Next Greater Element**, but instead of value:

* return **distance**

---

## CONDITION

```
while currentTemp > stackTopTemp
```

---

## ✅ Code

```java
public int[] dailyTemperatures(int[] temps) {
    int n = temps.length;
    int[] res = new int[n];
    Stack<Integer> st = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && temps[i] > temps[st.peek()]) {
            int idx = st.pop();
            res[idx] = i - idx;
        }
        st.push(i);
    }
    return res;
}
```

---

## IMPORTANT OBSERVATION

* This is **monotonic decreasing stack**
* Almost identical to Next Greater Element

---

# 4️⃣ LARGEST RECTANGLE IN HISTOGRAM (MOST IMPORTANT)

## 🎯 Problem

Given heights of bars, find **largest rectangle area**.

Example:

```
[2,1,5,6,2,3]
→ 10
```

---

## KEY INSIGHT (VERY IMPORTANT)

For each bar:

* treat it as **minimum height**
* extend **left and right** until a smaller bar blocks it

So we need:

* **Previous Smaller**
* **Next Smaller**

---

## WHY STACK?

We want to know:

> “When does this bar get blocked?”

Answer:

* when a smaller bar appears → pop

---

## ALGORITHM IDEA

* Maintain **monotonic increasing stack**
* When current height < top:

  * pop height
  * calculate area using popped bar as height

---

## AREA FORMULA

```
height = h
width = rightIndex - leftIndex - 1
area = height × width
```

---

## ✅ Code (VERY IMPORTANT)

```java
public int largestRectangleArea(int[] heights) {
    Stack<Integer> st = new Stack<>();
    int maxArea = 0;
    int n = heights.length;

    for (int i = 0; i <= n; i++) {
        int currHeight = (i == n) ? 0 : heights[i];

        while (!st.isEmpty() && currHeight < heights[st.peek()]) {
            int height = heights[st.pop()];
            int right = i;
            int left = st.isEmpty() ? -1 : st.peek();
            int width = right - left - 1;

            maxArea = Math.max(maxArea, height * width);
        }
        st.push(i);
    }
    return maxArea;
}
```

---

## WHY WE ADD A ZERO AT END?

To:

* force pop all remaining bars
* finalize their areas

---

# HOW TO IDENTIFY MONOTONIC STACK IN INTERVIEWS

If problem says:

* next greater / smaller
* nearest element
* span
* histogram
* temperatures
* stock span
* max area / width

👉 **Think Monotonic Stack**

---

# MASTER SUMMARY (MEMORIZE THIS)

| Problem           | Stack Type |
| ----------------- | ---------- |
| Next Greater      | decreasing |
| Next Smaller      | increasing |
| Daily Temps       | decreasing |
| Largest Rectangle | increasing |

---

# ONE-LINE MEMORY RULE

> **Monotonic Stack = resolve elements when they get blocked**