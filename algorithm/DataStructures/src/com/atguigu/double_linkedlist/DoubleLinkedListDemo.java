package com.atguigu.double_linkedlist;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/14    15:38
 * @Version:1.0
 * 双向链表代码
 * 双向链表有两个指针，前驱指针pre和后继指针next
 *
 *
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        // 测试，创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "无用", "智多星");
        HeroNode hero4 = new HeroNode(6, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.add(hero3);
        list.add(hero4);
        list.list();
        list.removeNode(6);
        System.out.println(list.modifyNode(new HeroNode(2, "有用", "智少星星")));
        list.list();


    }

    @Test
    public void test01() {
        // 测试，创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "无用", "智多星");
        HeroNode hero4 = new HeroNode(6, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.list();

    }


}

/**
 * 这个是对单向链表进行增删改，初始化等操作的类
 */
class DoubleLinkedList {
    // 构造一个带头结点双向链表
    private HeroNode head = new HeroNode(0, "", "");

    // 返回双向链表的头节点
    public HeroNode getHeadNode() {
        return this.head;
    }


    /**
     * 删除节点操作
     * 传入节点，然后通过节点的编号对链表进行遍历，然后删除节点
     * @param no
     */
    public void removeNode(int no) {
        // 将头节点的值赋值给临时变量，头节点不能进行更改
        HeroNode temp = head;

        // 标志位，判断是否找到了要删除的节点的元素
        boolean flag = false;
        // 遍历链表
        while (true) {


            if (temp == null) {
                // 最后一个元素了，退出循环
                // 这里如果用temp.next来判断的话，那么当他到了最后一个结点的时候，到这一步就退出了，不会走下一步了
                break;
            }
            if (temp.no == no) {
                flag = true; // 表示找到这个temp节点是待删除的节点
                break;
            }
            // 对temp进行遍历
            temp = temp.next;
        }

        if (flag) {
            System.out.println("移除成功，移除掉的节点是" + temp);
            // 这一步是精髓 ，temp是待删除的结点
            // 这两步是将temp的前驱结点的后指针指向temp的下一个结点
            // 将temp的后继结点的前指针指向temp的前一个结点
            temp.pre.next = temp.next;
            // 这里代码有点问题，temp不能是最后一个结点，不然temp.next就是空了
            if (temp.next == null) {
                // 如果待删除的节点是最后一个的话，就将倒数第二个的后继节点制空即可
                temp.pre.next = null;
            } else {
                temp.next.pre = temp.pre;
            }

        } else {
            System.out.println("您要删除的节点不存在于这个单项链表中！！！");
        }
    }


    /**
     * 双向链表的修改和单向链表结点的修改一样
     * 根据编号进行修改
     * 编号不能修改
     * @param
     * @return 找到了返回true   没有找到返回false
     */
    public boolean modifyNode(HeroNode newHeroNode) {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，不能进行修改！！！");
            return false;
        }

        // 用一个临时变量去接收头节点head的下一个元素
        HeroNode temp = head.next;

        // 定一个flag标志，判断链表中是否有这个节点
        boolean flag = false;

        // 链表不为空，将链表进行遍历
        while (true) {
            if (temp == null) {
                // 表示已经到达了最后一个元素，退出循环
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 说明找到了要修改的元素
                flag = true;
                // 退出循环
                break;
            }
            // 这里要将temp进行遍历
            temp = temp.next;

        }
        if (flag) {
            // 对节点进行修改
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
            return true;
        } else {
            return false;
        }
    }


    /**
     * 遍历双向链表的方法
     */
    public void list() {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("此时链表为空，不能进行遍历");
            return;
        }
        // 将head的值赋值给一个临时变量
        // 然后通过这个临时变量进行遍历
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                // 当temp是最后那个节点的时候，就退出循环
                break;
            }
            // 将temp进行后移，使得能够全部遍历完
            temp = temp.next;
            System.out.println(temp);
        }
    }


    /**
     * 按照顺序进行添加
     * 第二种添加英雄的方式
     * 根据排名将英雄插入到指定的位置
     * 比如说有排名1，4，8 ，9。那么如果要插入2的话就得插入到1和4中间
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {

        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的节点的位置
        // 因为是单链表，因此我们要找的temp是位于要插入的节点的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;
        boolean flag1 = false;
        while (true) {
            if (temp.no > heroNode.no) {
                flag1 = true;
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.println(temp.no + "这个编号已经存在");
        } else if (flag1) {
            temp.next = heroNode;
            heroNode.pre = temp;
        } else {
            heroNode.next = temp;
            temp.pre.next = heroNode;
            heroNode.pre = temp.pre;
            temp.pre = heroNode;
        }


    }

    /**
     * 添加双向链表的方法
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
/*        // 链表为空
        if (head.next == null) {
            return;
        }*/
        // 用一个临时变量接收这个双向链表的头节点
        HeroNode tempNode = head;
        while (tempNode.next != null) {
            // 对tempNode进行迭代
            tempNode = tempNode.next;
        }

        // 这里说明已经到达最后一个节点了
        // 将tempNode的后继指针指向将要添加的结点
        tempNode.next = heroNode;
        // 将要添加的前驱指针指向tempNode
        heroNode.pre = tempNode;

    }


}


/**
 * 定义双向链表
 */
class HeroNode {


    // 英雄编号
    public int no;
    // 英雄名字
    public String name;
    // 英雄昵称
    public String nickName;
    // 下一个节点
    public HeroNode next;
    // 指向前一个节点 默认为null
    public HeroNode pre;

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

