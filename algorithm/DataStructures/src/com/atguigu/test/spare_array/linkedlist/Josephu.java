package com.atguigu.test.spare_array.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/15    11:39
 * @Version:1.0
 * 约瑟夫问题
 * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，
 * 由此产生一个出队编号的序列。
 *
 * n = 5 , 即有5个人
 * k = 1, 从第一个人开始报数
 * m = 2, 数2下
 *
 * 这里的思路是构建一个循环单向链表
 *
 */
public class Josephu {

    public static void main(String[] args) {
        // 测试环形链表和遍历是否可以
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        // 加入5个小孩
        list.addBoy(5);
        list.showBoy();
        list.remove(1, 2, 5);

    }


}

/**
 * 创建一个单向的环形链表
 */
class CircleSingleLinkedList {
    // 先构建一个first为空
    Boy first = null;


    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空，没有任何小孩~！~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        // 先让curBoy指向first
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号：" + curBoy.getNo());
            if (curBoy.getNext() == first) {
                // 说明已经遍历完毕了
                break;
            }
            // 如果没有，指针后移
            curBoy = curBoy.getNext();
        }
    }


    /**
     *
     * @param startNo 从第几个小孩开始
     * @param countNum 总共数几下
     * @param nums 小孩的数量
     */
    public void remove(int startNo, int countNum, int nums) {
        // 检验这些数据是否合法
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("您输入的数据有误！请重新输入！！！");
            return;
        }
        // 现在是数据合法的情况下
        // 这个cur先指向first，然后要将cur指向最后一个结点，也就是first的前一个结点
        Boy cur = first;
        while (true) {
            if (cur.getNext() == first) {
                break;
            }
            // 将指针后移
            cur = cur.getNext();
        }
        // 这表明已经到达cur的最后一个节点了
        // 就将cur指针和first指针移动startNo-1个位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            cur = cur.getNext();
        }


        // 对链表进行遍历
        while (true) {
            // 最后一个元素的条件是当cur和first结点是同一个的时候
            if (first == cur) {
                System.out.println("最后退出的小孩是" + first.getNo());
                break;
            }

            // 数几下
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                cur = cur.getNext();
            }
            System.out.println("要出圈的小孩是" + first.getNo());
            cur.setNext(first.getNext());
            first = first.getNext();
        }
    }


    /**
     *
     * @param nums 这里表示创建nums个男孩结点
     */
    public void addBoy(int nums) {
        // 先判断传入的nums值是否合理
        if (nums < 1) {
            System.out.println("您输入的数据不合法！！！");
            return;
        }
        // 这个是辅助指针
        Boy cur = null;

        for (int i = 1; i <= nums; i++) {
            // 当是第一个小孩的时候
            // 让他自己指向他自己
            Boy boy = new Boy(i);
            if (i == 1) {
                // 将第一个小孩赋值给boy
                first = boy;
                first.setNext(first);
                // 这里用这个临时变量cur去接收first
                cur = first;
            } else {
                // 其实这里就是用cur去遍历添加
                //  先将cur下一个结点指向boy
                // 然后将boy的下一个指针first，
                // 又将cur移动到boy，这里就构成了环
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }


        }


    }


}

/**
 * 创建Boy类表示一个结点
 */
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }


}
