package com.atguigu.test.spare_array.double_linkedlist;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/15    10:46
 * @Version:1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        // 测试，创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "无用", "智多星");
        HeroNode hero4 = new HeroNode(6, "林冲", "豹子头");

        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);
        linkedList.list();
        linkedList.removeNode(1);
        linkedList.list();
        int length = linkedList.getLength();
        System.out.println("链表的长度length =是 " + length);

    }


}

/**
 * 定义双向链表
 */
class HeroNode {

    // 编号
    private int no;
    // 名字
    private String name;
    // 昵称
    private String nicName;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    // 前指针
    private HeroNode pre;

    // 后指针
    private HeroNode next;

    public HeroNode(int no, String name, String nicName) {
        this.no = no;
        this.name = name;
        this.nicName = nicName;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nicName='" + nicName + '\'' +
                '}';
    }
}

class DoubleLinkedList {

    // 头指针
    HeroNode head = new HeroNode(0, "", "");


    /**
     * 删除结点的操作
     * @param no 表示要删除的结点的编号
     */
    public void removeNode(int no) {
        // 用一个临时变量去接收头节点的下一个结点
        HeroNode tempNode = head;
        // 标志位判断是否找到了要移除的结点
        boolean flag = false;
        while (true) {
            if (tempNode.getNext() == null) {
                // 这里说明，已经遍历到最后了直接退出循环
                if (tempNode.getNo() == no) {
                    flag = true;
                }
                break;
            }
            if (tempNode.getNo() == no) {
                flag = true;
                break;
            }
            // 指针后移
            tempNode = tempNode.getNext();
        }

        if (flag) {
            System.out.println("你要移除的结点是" + tempNode);
            // 说明找到了该结点
            // 先判断要移除的节点是不是最后一个，这里有点区别
            if (tempNode.getNext() == null) {
                //这里表示已经到达了链表的最后
                tempNode.getPre().setNext(null);
            } else {
                // 这里表明是在中间进行移除
                tempNode.getPre().setNext(tempNode.getNext());
                tempNode.getNext().setPre(tempNode.getPre());
            }
        }else {
            System.out.println("没有找到你要移除的结点的编号" + no);
        }

    }

    /**
     * 对链表进行遍历输出的方法
     */
    public void list() {
        // 用一个临时变量去接收头节点
        HeroNode tempNode = head.getNext();
        while (true) {
            if (tempNode == null) {
                break;
            }
            System.out.println(tempNode);
            // 指针后移
            tempNode = tempNode.getNext();

        }


    }

    /**
     * 双向链表的添加方法
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 用一个临时变量去接收head头节点
        HeroNode tempNode = head;

        // 对链表进行遍历，直到链表到达最后一个结点
        while (true) {
            if (tempNode.getNext() == null) {
                //  说明已经到达链表的最后一个元素
                tempNode.setNext(heroNode);
                heroNode.setPre(tempNode);
                break;
            }
            tempNode = tempNode.getNext();
        }


    }

    // 获取双向链表的头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 获取双向链表的长度
     *
     * @return
     */
    public int getLength() {
        // 用一个临时变量去接收头节点
        HeroNode tempNode = head;
        int length = 0;
        while (tempNode != null) {
            length++;
            tempNode = tempNode.getNext();
        }
        // 这里要去除头节点
        return length - 1;
    }


}