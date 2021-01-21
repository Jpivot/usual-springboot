package algorithm;

public class ReverseListTest {
    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode listNode = new ListNode();
        traverse(head, listNode);
        return listNode.next;
    }

    public ListNode traverse(ListNode head, ListNode listNode) {
        if (head != null && head.next != null) {
            ListNode tmp = traverse(head.next, listNode);
            tmp.next = head;
            listNode = tmp.next;
            head.next = null;
        } else if (head != null) {
            listNode.next = head;
            listNode = listNode.next;
        }
        return listNode;
    }
}
