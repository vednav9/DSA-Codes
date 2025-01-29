// Google, Microsoft, Apple, Amazon

public class Reverse_206{
    private ListNode head;
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

    // In place
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return head;
        }

        ListNode prev = null;
        ListNode present = head;
        ListNode next = present.next;

        while (present != null) {
            present.next = prev;
            prev = present;
            present = next;
            if (next != null) {
                next = next.next;
            }
        }
        head = prev;
        return prev;
    }

    // Recursion

    // private void recRev(ListNode node){
    //     if(node==tail){
    //         head=tail;
    //         return;
    //     }
    //     recRev(node.next);

    //     tail.next=node;
    //     tail=node;
    //     tail.next=null;
    // }
}