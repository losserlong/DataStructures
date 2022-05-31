package com.atguigu.linkedlist.exer;


/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/12    17:50
 * @Version:1.0
 *  1.判断链表中有效节点的个数
 *
 */
public class ExerDemo {


    public static void main(String[] args) {

        // 测试，创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hero2_2 = new HeroNode(4, "小罗翔", "玉麒麟");
        HeroNode hero3 = new HeroNode(2, "无用", "智多星");
        HeroNode hero4 = new HeroNode(6, "林冲", "豹子头");
  /*
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
        int length = getLength(list.getHead());
        System.out.println("这个链表的有效长度为" + length);


        HeroNode heroNode = list
                .queryByNo(4);
        System.out.println("查询到的heroNode = " + heroNode);
*/

        SingleLinkedList list3 = new SingleLinkedList();
        list3.add(hero1);
        list3.add(hero2);
        list3.add(hero3);
        list3.add(hero4);
        list3.list();
        HeroNode returnHeroNode = returnHeroNode(list3.getHead(), 8);
        System.out.println("返回的链表中的节点为" + returnHeroNode);

        list3.reverseLinkedList(list3.getHead());
        list3.list();

        System.out.println("反转过后的链表为");
        list3.reverseLinkedList(list3.getHead());
        list3.list();

        // 这里的逆序打印没有改变链表原来的结构
        System.out.println("对链表进行逆序打印");
        list3.reversePrint(list3.getHead());


        System.out.println("\"进行移除操作~~~~~~~~~~~~~~~~~~~\" = " + "进行移除操作~~~~~~~~~~~~~~~~~~~");

        //   移除节点操作
        list3.removeNode(hero4.no);
//        list3.removeNode(hero3.no);
//        list3.removeNode(hero2.no);
        list3.list();


    }


    /**
     * 求单链表中有效节点的个数
     * 对链表进行遍历即可
     * @param heroNode
     * @return
     */
    public static int getLength(HeroNode heroNode) {
        HeroNode temp = heroNode;


        // 1、先判断链表是否为空注意这里不包括头节点
        if (temp.next == null) {
            // 这里表示只有头节点这一个元素
            return 0;
        }
        int num = 0;
        // 从开始节点的后一个节点开始遍历，因为这里的长度不包括头节点
        while (temp.next != null) {
            num++;
            // 将temp节点进行后移一个
            temp = temp.next;
        }
        return num;
    }

    /**
     * 查找单链表中的倒数第k个结点【新浪面试题】
     *
     * 获取单链表中倒数第index个节点
     * @param headNode
     * @param index
     * @return
     */

    public static HeroNode returnHeroNode(HeroNode headNode, int index) {

        // 判断链表的长度是是否为空，为空就返回空
        if (headNode.next == null) {
            return null;
        }


        // 用一个临时变量temp接收headNode头节点的值
        HeroNode temp = headNode;
        // 1.先遍历单链表，获取链表的长度
        // 直接调用上面的方法就行了
        int length = getLength(headNode);
        System.out.println("length = " + length);
        // 做一个index数据的合法校验
        if (index < 0 || index > length) {
            return null;
        }

        // 因为是倒数第index个，所以就要换成顺数第i个,因为我们这个是从headNode开始的，所以要+1
        int i = length - index + 1;

        // 用作标志位，当标志位和i相匹配的时候就返回
        int flag = 0;
        // 然后再去得到正数第i个节点的值
        while (true) {
            // 每遍历一次就将flag进行加一
            flag++;
            // 因为没有把头节点算进去，所以这里flag是从一开始
            if (flag == i) {
                return temp.next;
            }
            temp = temp.next;
        }
    }


}
