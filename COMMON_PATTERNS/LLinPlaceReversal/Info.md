# LINKED LIST IN-PLACE REVERSAL — CORE IDEA

“In-place” means:
- No extra list
- Only change next pointers
- Use O(1) extra space

The fundamental operation behind ALL problems here is:
```bash
curr.next = prev
```

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

| Subpattern          | Key Technique              |
| ------------------- | -------------------------- |
| Reverse entire list | `prev, curr, next`         |
| Reverse sublist     | dummy + head insertion     |
| Reverse k-group     | count + recursive reversal |
| Reorder list        | middle + reverse + merge   |
