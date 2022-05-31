package com.atguigu.stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/15    19:04
 * @Version:1.0
 * 用链表来模拟实现栈
 */
public class LinkedListToStackDemo {

    public static void main(String[] args) {

        LinkedListToStack list = new LinkedListToStack(5);
        list.push(new Stack_Linked(1));
        list.push(new Stack_Linked(2));
        list.push(new Stack_Linked(2));
        list.push(new Stack_Linked(4));
        list.push(new Stack_Linked(5));
        list.show();
        System.out.println(" ----------------------");
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(list.pop());
        System.out.println(" ----------------------");
        list.show();

    }
}

class LinkedListToStack {
    // 表示栈的长度
    private int nums;

    // 计数器
    private static int count = 0;
    private static int count1 = 0;

    public LinkedListToStack() {
    }

    public LinkedListToStack(int nums) {
        this.nums = nums;
    }

    // 头节点
    Stack_Linked head = new Stack_Linked(-1);


    /**
     * 对链表进行遍历
     */
    public void show() {
        // 用一个临时变量去接收头节点
        Stack_Linked tempNode = head.next;
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }
    }

    /**
     * 弹出链表的第一个元素
     * @return
     */
    public Stack_Linked pop() {
        // 表明只有一个头节点
        if (head.next == null) {
            return null;
        }
        // 存储头节点
        Stack_Linked tempNode = head;
        Stack_Linked node = null;
        count--;
        if (count < 0) {
            return null;

        } else {
            if (tempNode.next.next != null) {
                node = tempNode.next;
                tempNode.next = tempNode.next.next;
                return node;
            } else {
                node = tempNode.next;
                tempNode.next = null;
                return node;
            }


        }

    }


    /**
     * 单链表的添加
     * @param node
     */
    public void push(Stack_Linked node) {

        // 用一个临时变量去接收头节点
        Stack_Linked tempNode = head;
        while (true) {
            if (tempNode.next == null) {
                break;
            }
            // 指针后移
            tempNode = tempNode.next;
        }
        if (count++ < nums) {
            tempNode.next = node;
        } else {
            throw new RuntimeException("已经超过链表的长度了！！！");
        }

    }


}

class Stack_Linked {
    public int data;
    // 后面的指针
    public Stack_Linked next;

    public Stack_Linked() {
    }

    public Stack_Linked(int data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Stack_Linked{" +
                "data=" + data +
                '}';
    }
}