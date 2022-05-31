package com.atguigu.tree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/27    15:46
 * @Version:1.0
 */
public class BinaryTree1 {

    public static void main(String[] args) {
        // 创建需要的节点
        HeroNode1 root = new HeroNode1(1, "宋江");
        HeroNode1 node2 = new HeroNode1(2, "吴用");
        HeroNode1 node3 = new HeroNode1(3, "卢俊义");
        HeroNode1 node4 = new HeroNode1(4, "林冲");
        HeroNode1 node5 = new HeroNode1(5, "关胜");

        // 说明，我们先手动创建二叉树，后买你递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        BinaryTree1 tree1 = new BinaryTree1();
        tree1.root = root;
        tree1.preOrder();
        HeroNode1 heroNode1 = tree1.delNode(4);
        System.out.println("heroNode1 = " + heroNode1);
        tree1.preOrder();


    }


    HeroNode1 root;


    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null){
            this.root.preSort();
        }
    }

    /**
     * 删除节点
     * @param no
     */
    public HeroNode1 delNode(int no) {
        HeroNode1 resNode = null;
        if (this.root.no == no) {
            resNode = root;
            root = null;
            return resNode;
        }

        return this.root.delNode(no);

    }


}
