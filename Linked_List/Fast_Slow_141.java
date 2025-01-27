// Used for Linked List Cycle
// Amazon and Microsoft
public class Fast_Slow_141 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // Another problem
    
    // Count Node in Cycle | Length Cycle

    public int lengthCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // calculate the length

                ListNode temp=slow;
                int length=0;

                do {
                    temp=temp.next;
                    length++;
                } while (temp!=slow);

                return length;
            }
        }
        return 0;
    }
}