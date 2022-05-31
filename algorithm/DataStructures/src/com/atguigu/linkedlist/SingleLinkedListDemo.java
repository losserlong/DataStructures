package com.atguigu.linkedlist;


/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/11    21:09
 * @Version:1.0
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        // 测试，创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero2_2 = new HeroNode(4, "小罗翔", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "无用", "智多星");
        HeroNode hero4 = new HeroNode(6, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();
        list.add(hero1);
        list.add(hero2);
        list.add(hero3);
        list.add(hero4);
        list.list();

        System.out.println("---------------------------------------------------");
        SingleLinkedList list1 = new SingleLinkedList();
        list1.addByOrder(hero1);
        list1.addByOrder(hero2);
        list1.addByOrder(hero3);
        list1.addByOrder(hero4);

        list1.list();

        System.out.println("修改过后的代码");
        boolean result = list1.modifyNode(hero2_2);
        if (result) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
        list.list();


        HeroNode heroNode = list
                .queryByNo(4);
        System.out.println("heroNode = " + heroNode);

   /*     list.removeNode(hero2_2.no);
        list.removeNode(hero1.no);
        list.removeNode(hero3.no);
        list.removeNode(hero4.no);
        list.list();*/


    }
}

class HeroNode {


    // 英雄编号
    public int no;
    // 英雄名字
    public String name;
    // 英雄昵称
    public String nickName;
    // 下一个节点
    public HeroNode next;

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
                ", nickName=" + nickName +
                '}';
    }
}


class SingleLinkedList {

    // 先初始化一个头节点，头节点不要动，不存放具体的数据，用于指向下一个节点
    private HeroNode head = new HeroNode(0, "", "");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 单链表通过编号进行查询
     * @param no
     */
    public HeroNode queryByNo(int no) {

        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            return temp;
        } else {
            // 当单项链表中没有存在这个no的节点
            throw new RuntimeException("抱歉！没有查询到您输入编号对应的节点！");
        }


    }


    // 添加节点到单向链表
    // 思路，当不考虑编号顺序时
    // 1.遍历链表找到当前链表的最后一个节点
    // 2.将最后一个节点的next指向新增的节点

    /**
     * 第一种添加英雄的方式，没有管顺序进行添加
     * @param newHeroNode
     */
    public void add(HeroNode newHeroNode) {

        // 这里头节点不能动，用临时变量temp进行接收head头节点
        HeroNode temp = head;

        while (true) {
            // 死循环，找到链表的最后一个节点
            if (temp.next == null) {
                break;
            }
            // 如果没有找到，就将temp进行后移，知道找到最后一个节点
            temp = temp.next;
        }
        // 当退出循环时，temp指向的就是链表的最后一个节点
        // 将最后这个节点的next指向新插入的节点
        temp.next = newHeroNode;
    }

    /**
     * 第二种添加英雄的方式
     * 根据排名将英雄插入到指定的位置
     * 比如说有排名1，4，8 ，9。那么如果要插入2的话就得插入到1和4中间
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {

        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的节点的位置
        // 因为是单链表，因此我们要找的temp是位于要插入的节点的前一个节点，否则插入不了
        HeroNode temp = head;
        // flag是作为标志，判断添加的编号是否存在，默认是false
        boolean flag = false;

        // 对单链表进行遍历
        while (true) {
            if (temp.next == null) {
                // 说明temp已经是最后一个元素
                break;
            }
            if (temp.next.no > heroNode.no) {
                // 如果temp的后一个元素的编号大于heroNode的编号，那就是heroNode要插入的位置
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明编号已经存在了
                // 就将flag值设为true，表示这个编号已经存在节点了
                flag = true;
                break;
            }
            // 将temp进行后移,遍历当前链表
            temp = temp.next;
        }
        if (!flag) {
            // 新的节点.next = temp.next
            // 将temp.next = 新的节点
            heroNode.next = temp.next;

            temp.next = heroNode;

        } else {
            // 已经村存在，不能进行添加
            System.out.println("准备插入的编号已经存在，编号为" + heroNode.no);
        }


    }


    /**
     * 显示链表（通过遍历来实现）
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
            if (temp.next == null) {
                // 表示已经到达了最后一个元素，退出循环
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 说明找到了休要修改的元素
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


            if (temp.next == null) {
                // 最后一个元素了，退出循环
                break;
            }
            if (temp.next.no == no) {
                flag = true; // 表示找到这个temp节点是待删除节点的前一个节点
                break;
            }
            // 对temp进行遍历
            temp = temp.next;
        }

        if (flag) {
            System.out.println("移除成功，移除掉的节点是" + temp.next);
            // 这一步是精髓
            temp.next = temp.next.next;
        } else {
            System.out.println("您要删除的节点不存在于这个单项链表中！！！");
        }
    }


}

