package com.atguigu.linkedlist.exer;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/12    18:47
 * @Version:1.0
 */
public class HeroNode {


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


