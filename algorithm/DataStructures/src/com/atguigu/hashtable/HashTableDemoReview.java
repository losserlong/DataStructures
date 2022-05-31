package com.atguigu.hashtable;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/26    10:11
 * @Version:1.0
 * 复习之前所学的HashTable
 */
public class HashTableDemoReview {

    public static void main(String[] args) {
        HashTableReview review = new HashTableReview(5);
        HeroNode n1 = new HeroNode(1,"张三");
        HeroNode n6= new HeroNode(6,"张6");
        HeroNode n2 = new HeroNode(2,"李四");
        HeroNode n3 = new HeroNode(3,"王五");
        HeroNode n4 = new HeroNode(4,"赵六");
        review.addNode(n1);
        review.addNode(n2);
        review.addNode(n3);
        review.addNode(n4);
        review.addNode(n6);
        review.listNode();

    }

}

/**
 * hashtable由数组加链表组成
 */
class HashTableReview {
    private LinkedListReview[] listReviews;
    private int size;

    public HashTableReview(int size) {
        this.listReviews = new LinkedListReview[size];
        this.size = size;
        // 给listReviews各个数组初始化
        for (int i = 0; i < size; i++) {
            listReviews[i] = new LinkedListReview();
        }
    }


    /**
     * 添加数组
     * @param heroNode
     */
    public void addNode(HeroNode heroNode) {
        // 获得要插入的数组
        int index = hashFun(heroNode.no);
        listReviews[index].addNode(heroNode);

    }

    public void listNode(){
        for (int i = 0; i < size; i++) {
            listReviews[i].listNode();
        }

    }



    /**
     * 定义一个hash函数
     * @param n
     * @return
     */
    public int hashFun(int n) {
        return n % size;
    }


}

class LinkedListReview {
    HeroNode head;


    /**
     * 增加英雄
     */
    public void addNode(HeroNode heroNode) {
        if (head == null) {
            head = heroNode;
            return;
        }
        // 如果head不为空,就遍历到链表最后,然后再加入
        HeroNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        // 加在链表的最后
        cur.next = heroNode;


    }

    /**
     * 遍历所有的英雄
     */
    public void listNode() {
        if (head == null) {
            System.out.println("说明链表为空！");
            return;
        }
        HeroNode cur = head;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }


    }


}

/**
 * 英雄节点
 */
class HeroNode {
    int no;
    String name;
    HeroNode next;


    public HeroNode() {
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public HeroNode(int no, String name, HeroNode next) {
        this.no = no;
        this.name = name;
        this.next = next;
    }

/*    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }*/


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}










































