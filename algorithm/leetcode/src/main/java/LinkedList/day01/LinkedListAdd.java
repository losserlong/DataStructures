package LinkedList.day01;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/12    19:31
 * @Version:1.0
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedListAdd {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = 0;
        int length2 = 0;
        // 这个表示的是进位
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            // 这里表示如果l1不为空的话，就拿到l1的值，反之就为0，将其赋值给l1Val
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;




        }


        return null;

    }
}


