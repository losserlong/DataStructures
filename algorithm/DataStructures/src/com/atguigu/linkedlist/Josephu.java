package com.atguigu.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/14    20:46
 * @Version:1.0
 * 约瑟夫问题
 */
public class Josephu {

    public static void main(String[] args) {
        // 测试环形链表和遍历是否可以
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        // 加入5个小孩
        list.addBoy(5);
        list.showBoy();
        list.countBoy(1,2,5);

    }
}


/**
 * 创建一个单向的环形链表
 */
class CircleSingleLinkedList {
    // 创建一个first结点，但是当前没有编号
    private Boy first = null;


    /**
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum  表示数几下
     * @param nums  表示有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误！请重新输入参数");
            return;
        }
        // 创建一个辅助指针，最先开始的时候，指向first
        // 最后要将helper指向链表的最后
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                // 说明helper指向了链表的最后
                break;
            }
            // 对helper进行遍历
            helper = helper.getNext();
        }

        // 小孩报数前，先让first移动到那个小孩的位置
        // 先让first和helper移动k - 1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        // 这是一个循环操作，直到圈中只有一个结点的时候
        while (true) {
            // 说明只有一个结点了
            if (first == helper) {

                break;
            }
            // 否则就将first和helper进行移动countNum-1次，然后出圈
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的结点就是要出圈的小孩
            System.out.println("小孩" + first.getNo() + "出圈");
            // 出圈完之后，要将first指针和helper执行进行移动
            // 这里就是删除小孩的操作了
            first = first.getNext();
            helper.setNext(first);
/*
            // 自己写的
            helper.setNext(first.getNext());
            first = helper.getNext();
*/
        }
        // 最后留在圈中的小孩是
        System.out.println("最后留在圈中的小孩是"+helper.getNo());

    }

    /**
     *
     *       添加小孩结点，构成一个环形的连接
     *
     * @param nums  这个表示添加多少个小孩
     */
    public void addBoy(int nums) {
        // 对nums进行校验
        if (nums < 1) {
            System.out.println("num的值不合法");
            return;
        }

        Boy curBoy = null;  // 辅助指针，帮助构建辅助变量
        // 使用for循环创建我们的环形连接
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 当是第一个小孩的时候，自己和自己构成环状
            if (i == 1) {
                first = boy;
                // 这里表示的是自己和自己构成环状
                first.setNext(first);
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                // 其实这里就是通过cur去遍历
                // 将cur的下一个结点指向新的boy
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }


    }

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
