// Linked List Cycle

package COMMON_PATTERNS.FastSlow;

public class FloydCycleDetection {
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

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // 1 step
            fast = fast.next.next; // 2 steps

            if (slow == fast) { // cycle detected
                return true;
            }
        }
        return false; // fast reached null → no cycle
    }

}