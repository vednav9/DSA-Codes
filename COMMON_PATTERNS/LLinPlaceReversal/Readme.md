Excellent choice.
**Linked List In-Place Reversal** is a **CORE interview pattern** and appears again and again in different disguises.

I’ll teach you this **from zero → master level**, covering **all 4 subpatterns**, with **clean intuition + reusable Java templates**.

---

# LINKED LIST IN-PLACE REVERSAL — CORE IDEA

“In-place” means:

* **No extra list**
* Only change **next pointers**
* Use **O(1) extra space**

The **fundamental operation** behind ALL problems here is:

```
curr.next = prev
```

If you master this pointer dance, everything else is just variations.

---

## BASIC REVERSAL TEMPLATE (MEMORIZE THIS)

```java
ListNode prev = null;
ListNode curr = head;

while (curr != null) {
    ListNode next = curr.next; // save next
    curr.next = prev;          // reverse link
    prev = curr;               // move prev
    curr = next;               // move curr
}
return prev; // new head
```

This is the **engine** for every problem below.

---

# 1️⃣ Reverse Entire Linked List

### 🎯 Problem

Reverse the whole linked list.

### Intuition

Walk forward, reverse links one by one.

---

## ✔ Java Code — Reverse List (LC 206)

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(1)**

---

# 2️⃣ Reverse Sublist (Between m and n)

### 🎯 Problem

Reverse nodes from position `left` to `right` (1-indexed).

Example:

```
1 → 2 → 3 → 4 → 5
reverse 2 to 4
1 → 4 → 3 → 2 → 5
```

---

## Strategy

1. Move to node **before left**
2. Reverse exactly `(right - left + 1)` nodes
3. Reconnect three parts

---

## ✔ Java Code — Reverse Sublist (LC 92)

```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null || left == right) return head;

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;

    // Step 1: move prev to node before 'left'
    for (int i = 1; i < left; i++) {
        prev = prev.next;
    }

    // Step 2: reverse sublist
    ListNode curr = prev.next;
    ListNode next = null;

    for (int i = 0; i < right - left; i++) {
        next = curr.next;
        curr.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }

    return dummy.next;
}
```

### 🔑 Key Insight

This uses **head insertion** technique.

---

# 3️⃣ Reverse Nodes in K-Group

### 🎯 Problem

Reverse nodes in groups of size `k`.

Example:

```
1 → 2 → 3 → 4 → 5
k = 2
2 → 1 → 4 → 3 → 5
```

---

## Strategy

1. Check if there are at least `k` nodes
2. Reverse k nodes
3. Recurse/iterate on remaining list

---

## ✔ Java Code — Reverse K-Group (LC 25)

```java
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;

    // Step 1: check if we have k nodes
    while (curr != null && count < k) {
        curr = curr.next;
        count++;
    }

    // Step 2: reverse k nodes
    if (count == k) {
        curr = reverseKGroup(curr, k); // reverse rest

        while (count-- > 0) {
            ListNode next = head.next;
            head.next = curr;
            curr = head;
            head = next;
        }
        head = curr;
    }
    return head;
}
```

### ⏱ Complexity

* Time: **O(n)**
* Space: **O(n/k)** recursion stack (or O(1) iterative version)

---

# 4️⃣ Reorder List (First + Last + Second + Second-Last…)

### 🎯 Problem

Reorder list as:

```
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 …
```

Example:

```
1 → 2 → 3 → 4 → 5
→ 1 → 5 → 2 → 4 → 3
```

---

## Strategy (3 Steps)

1. **Find middle** (fast & slow pointer)
2. **Reverse second half**
3. **Merge two halves alternately**

---

## ✔ Java Code — Reorder List (LC 143)

```java
public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;

    // Step 1: find middle
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    // Step 2: reverse second half
    ListNode second = reverse(slow.next);
    slow.next = null;

    // Step 3: merge two halves
    ListNode first = head;
    while (second != null) {
        ListNode temp1 = first.next;
        ListNode temp2 = second.next;

        first.next = second;
        second.next = temp1;

        first = temp1;
        second = temp2;
    }
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

# MASTER CHEATSHEET (VERY IMPORTANT)

| Subpattern          | Key Technique              |
| ------------------- | -------------------------- |
| Reverse entire list | `prev, curr, next`         |
| Reverse sublist     | dummy + head insertion     |
| Reverse k-group     | count + recursive reversal |
| Reorder list        | middle + reverse + merge   |

---

# HOW TO IDENTIFY THIS PATTERN IN INTERVIEWS

If problem says:

* “Reverse linked list”
* “Reverse in groups”
* “Reverse between indices”
* “Modify list without extra space”
* “Rearrange list”

**Think: In-Place Reversal Pattern**
