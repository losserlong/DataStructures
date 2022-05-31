package com.atguigu.test.spare_array.linkedlist;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/12    9:41
 * @Version:1.0
 */
public class SingleLinkedListDemo1 {
    public static void main(String[] args) {
        HeroNode1 h2 = new HeroNode1(2, "吴用", "智多星");

        HeroNode1 h1 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 h3 = new HeroNode1(3, "李逵", "黑旋风");
        HeroNode1 h4 = new HeroNode1(4, "林冲", "豹子头");


        SingleLined1 singleLined1 = new SingleLined1();
        singleLined1.addNode(h2);
        singleLined1.addNode(h1);

        singleLined1.addNode(h3);
        singleLined1.addNode(h4);
        singleLined1.showLinkedList();
        System.out.println(" 反转之后的链表 1111111111111111111111111111111111111111111111111111");
        singleLined1.reverse(singleLined1.head);
        singleLined1.showLinkedList();


    }

}

/**
 * 创建英雄的类
 */
class HeroNode1 {

    // 英雄编号
    public int no;

    // 英雄姓名
    public String name;

    // 英雄昵称
    public String nicName;

    // 指向下个一英雄
    public HeroNode1 next;

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

    public HeroNode1 getNext() {
        return next;
    }

    public void setNext(HeroNode1 next) {
        this.next = next;
    }

    public HeroNode1() {
    }

    public HeroNode1(int no, String name, String nicName) {
        this.no = no;
        this.name = name;
        this.nicName = nicName;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nicName='" + nicName + '\'' +
                '}';
    }
}


/**
 *
 */
class SingleLined1 {
    HeroNode1 head = new HeroNode1(0, "", "");


    public boolean isEmpty() {
        return this == null;
    }


    /**
     * 思路:
     * 1. 先定义一个节点 reverseHead = new HeroNode();
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端.
     * 3. 原来的链表的head.next = reverseHead.next
     */

    // 2022年1月16日  再写一遍反转的方法
    public void reverse(HeroNode1 headNode) {
        // 先用一个临时变量去接收一个头节点的下一个结点
        HeroNode1 tempNode = headNode.next;
        // 再创建一个反转的链表
        HeroNode1 reverse = new HeroNode1(0, "", "");
        // 这个用于保存当前遍历的结点的下一个结点
        HeroNode1 nextNode = null;
        if (tempNode == null) {
            throw new RuntimeException("链表为空不能反转");
        }

        // 对链表进行遍历
        while (true) {
            if (tempNode == null) {
                break;
            }
            // 用nextNode去接收tempNode的下一个结点,防止链表断裂
            nextNode = tempNode.next;
            // tempNode的下一个结点永远指向reverse的头节点
            tempNode.next = reverse.next;
            //
            reverse.next = tempNode;
            // 直接将后面一个结点的值，赋值给当前结点
            tempNode = nextNode;
        }

        head.next = reverse.next;


    }


    /**
     * 添加节点的方法
     * @param heroNode1
     */
    public void addNode(HeroNode1 heroNode1) {

        HeroNode1 temp = head;
        if (isEmpty()) {
            head = heroNode1;
        } else {
            // 对单链表进行遍历
            while (true) {
                if (temp.getNext() == null) {
                    // 最后一个节点的next是为null的，到了这一步，就说明已经是最后一个节点了
                    temp.setNext(heroNode1);
                    break;
                }
                // temp进行遍历
                temp = temp.getNext();
            }
        }


    }


    /**
     * 显示所有的单向链表
     */
    public void showLinkedList() {

        if (isEmpty()) {
            throw new RuntimeException("此时链表为空不能进行显示！！！！");
        }
        // 这里还是用一个临时变量接收
        HeroNode1 temp = head.next;

        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }


    }


}