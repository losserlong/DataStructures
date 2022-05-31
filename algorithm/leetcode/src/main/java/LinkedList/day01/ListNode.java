package LinkedList.day01;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/13    9:30
 * @Version:1.0
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode removeElements1(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return header.next;
    }

    public static ListNode removeElements(ListNode head, int val) {

        // 先创建一个head头节点之前的节点，它的下一个节点是head节点
        ListNode tempNode = new ListNode(-1, head);
        ListNode cur=head;


        if (head == null) {
            return null;
        }
        while (true) {
            if (cur.next == null) {
                // 表明已经到达了链表最后
                break;
            }
            if (tempNode.next.val == val) {
                tempNode.next = tempNode.next.next;
            }
            tempNode = tempNode.next;
            cur=cur.next;
        }

        return tempNode.next;

    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
