package LinkedList.day01;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/13    9:14
 * @Version:1.0
 *
 * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val的节点。如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index有效，则删除链表中的第index个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        System.out.println(listNode.removeElements(listNode, 2));
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {



    public static ListNode removeElements(ListNode head, int val) {

        // 先用一个临时变量接收这个头节点
        ListNode tempNode = head;

        if (tempNode == null) {
            return null;
        }
        while (true) {
            // 这表明到达了链表的最后
            if (tempNode.next != null) {
                break;
            }
            if (tempNode.next.val == val) {
                tempNode.next = tempNode.next.next;
            }
            // 将head进行后移
            head = head.next;
        }

        return tempNode;

    }
}

