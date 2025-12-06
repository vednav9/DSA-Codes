package COMMON_PATTERNS.FastSlow;

public class FindStartofCycle {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;

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
        if (fast == null || fast.next == null)
            return null;

        // Step 2: Move slow to head
        slow = head;

        // Step 3: Move both one step at a time
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // start of cycle
    }

}