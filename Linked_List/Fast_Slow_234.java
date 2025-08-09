// Google, Microsoft, Amazon, Linkedin, Apple

public class Fast_Slow_234 {
    // private ListNode head;
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

    public boolean isPalindrome(ListNode head) {
        ListNode mid=middleNode(head);
        ListNode headSecond=reverseList(mid);
        ListNode reReverseHead=headSecond;

        // compare
        while (head!=null&&headSecond!=null) {
            if (head.val!=headSecond.val) {
                break;
            }
            head=head.next;
            headSecond=headSecond.next;
        }
        reverseList(reReverseHead);
        
        return head==null||headSecond==null;
    }

    public ListNode middleNode(ListNode head) {
        ListNode s = head;
        ListNode f = head;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

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
}