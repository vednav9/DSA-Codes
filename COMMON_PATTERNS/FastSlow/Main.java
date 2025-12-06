/*

| Subpattern            | Use Case                       | Condition                 |
| --------------------- | ------------------------------ | ------------------------- |
| Cycle Detection       | Check if LL has cycle          | slow == fast              |
| Find Cycle Start      | Detect cycle entry point       | Reset slow to head        |
| Middle of Linked List | Find mid node                  | fast jumps 2 steps        |
| Palindrome LL         | Compare halves                 | reverse + compare         |
| Happy Number          | Cycle in number transformation | fast == slow or fast == 1 |

*/

package COMMON_PATTERNS.FastSlow;

public class Main {
    /*
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
     */
}