# FAST & SLOW POINTER — CORE IDEA

You have two pointers:

* **slow** → moves **1 step**
* **fast** → moves **2 steps**

If there is a cycle:

* fast eventually “laps” slow → they meet
  If there is no cycle:
* fast reaches null → no cycle

This works because moving at different speeds creates a guaranteed meeting point inside a cycle.

---

# 1. Floyd Cycle Detection (Linked List Cycle)

### 🎯 Problem:

Check if a linked list has a cycle.

### Intuition:

If fast moves twice as fast as slow, and they are running on a circular track, fast MUST meet slow.

---

## 🔹 Java Code (Cycle Detection)

```java
public boolean hasCycle(ListNode head) {
    if (head == null) return false;

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;         // 1 step
        fast = fast.next.next;    // 2 steps

        if (slow == fast) {       // cycle detected
            return true;
        }
    }
    return false; // fast reached null → no cycle
}
```

---

# 2. Find Start of Cycle (Cycle Entry Point)

After detecting a cycle, find the node where the cycle begins.

### Key Insight (Proven by math):

When slow = fast inside cycle:

* Reset slow to head
* Keep fast where it is
* Move both **one step at a time**
* The node where they meet again = **start of cycle**

---

## 🔹 Java Code (Find cycle start)

```java
public ListNode detectCycle(ListNode head) {
    if (head == null) return null;

    ListNode slow = head;
    ListNode fast = head;

    // Step 1: Find meeting point
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) { // collision point
            break;
        }
    }

    // If no cycle
    if (fast == null || fast.next == null) return null;

    // Step 2: Move slow to head
    slow = head;

    // Step 3: Move both one step at a time
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }

    return slow; // start of cycle
}
```

---

# Why does this work?

This is the mathematical insight interviewers expect:

Let:

* `L` = distance from head to cycle start
* `C` = length of cycle
* `x` = distance from cycle start to meeting point

Fast moves: `2d`
Slow moves: `d`

Their difference is a multiple of cycle length:

```
2d − d = kC  →  d = kC
```

After resetting slow to head:

* slow moves L steps to cycle start
* fast moves (cycle - x) steps to cycle start

They meet exactly at the start.

---

# 3. Middle of Linked List

Classic easy interview question:
Return the **middle node** of a linked list.

### Trick:

* slow moves 1 step
* fast moves 2 steps
* when fast reaches null, slow is in the middle

---

## 🔹 Java Code (Middle of list)

```java
public ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;      
        fast = fast.next.next;
    }
    return slow;
}
```

### ✔ For even-length lists:

* This returns the **second middle** (the correct LeetCode answer)

---

# 4. Palindrome Linked List

(using slow/fast + reverse)

### 🎯 Goal:

Check if a linked list is a palindrome.

### Trick:

1. Use slow/fast to find middle
2. Reverse **second half**
3. Compare both halves
4. (Optional) Restore list (not required for LC)

---

## 🔹 Java Code (Palindrome Linked List)

```java
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;

    // Step 1: Find middle
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Step 2: Reverse second half
    ListNode secondHalf = reverse(slow);

    // Step 3: Compare both halves
    ListNode firstHalf = head;
    while (secondHalf != null) {
        if (firstHalf.val != secondHalf.val) {
            return false;
        }
        firstHalf = firstHalf.next;
        secondHalf = secondHalf.next;
    }

    return true;
}

private ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

---

# 5. Happy Number (Fast & Slow in number iteration)

A “happy number” repeatedly replaces n with sum of square of digits:

* If process ends in 1 → happy
* If it loops forever → not happy

Cycle detection works perfectly here.

---

## 🔹 Java Code (Happy Number)

```java
public boolean isHappy(int n) {
    int slow = n;
    int fast = next(n);

    while (fast != 1 && slow != fast) {
        slow = next(slow);
        fast = next(next(fast));
    }

    return fast == 1;
}

private int next(int n) {
    int sum = 0;
    while (n > 0) {
        int digit = n % 10;
        sum += digit * digit;
        n /= 10;
    }
    return sum;
}
```

### Why this works?

The digit-square process behaves like a linked list with cycles.

---

# FAST & SLOW POINTER MASTER SUMMARY

| Subpattern            | Use Case                       | Condition                 |
| --------------------- | ------------------------------ | ------------------------- |
| Cycle Detection       | Check if LL has cycle          | slow == fast              |
| Find Cycle Start      | Detect cycle entry point       | Reset slow to head        |
| Middle of Linked List | Find mid node                  | fast jumps 2 steps        |
| Palindrome LL         | Compare halves                 | reverse + compare         |
| Happy Number          | Cycle in number transformation | fast == slow or fast == 1 |

---

# CHEATSHEET (REMEMBER FOR INTERVIEWS)

```
slow = head
fast = head

while (fast != null && fast.next != null):
    slow = slow.next
    fast = fast.next.next

// For cycle start:
reset slow to head
while (slow != fast):
    slow = slow.next
    fast = fast.next
return slow
```
