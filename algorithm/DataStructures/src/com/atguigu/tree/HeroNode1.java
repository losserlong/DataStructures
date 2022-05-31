package com.atguigu.tree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/26    22:54
 * @Version:1.0
 * 复习之前的树的遍历删除查找等
 */
public class HeroNode1 {

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
        root.preSort();
        System.out.println("中序遍历");
        root.infixOrder();
        System.out.println("后序遍历");
        root.postOrder();
/*

        HeroNode1 result = root.preOrderSearch(4);
        System.out.println("前序遍历查找返回的结果是");
        System.out.println(result);
        System.out.println("中序遍历查找返回的结果是");
        HeroNode1 resNode1 = root.infixOrderSearch(5);
        System.out.println("resNode1 = " + resNode1);
*/

        // 删除节点
        HeroNode1 remNode = root.delNode(1);
        System.out.println("remNode = ===============" + remNode);
        root.postOrder();


    }


    public int no;
    public String name;
    // 指向左边的索引
    public HeroNode1 left; // 一开始left和right不知道指向左边还是指向右边,默认都为null
    // 指向右边的索引
    public HeroNode1 right;

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }


    /**
     * 删除指定节点
     */
    public HeroNode1 delNode(int no) {


        HeroNode1 target = null;

        if (this != null) {

            // 如果左节点的no等于no,就将this.left置为空
            if (this.left != null && this.left.no == no) {
                target = this.left;
                this.left = null;
                return target;
            }


            // 如果右节点的no等于no,就将this.left置为空
            if (this.right != null && this.right.no == no) {
                target = this.right;
                this.right = null;

                return target;
            }


            // 向左边递归删除
            if (this.left != null) {
                // 先左边递归
                target = this.left.delNode(no);
            }

            // 向右边递归删除
            if (this.right != null) {
                target = this.right.delNode(no);
            }


        }

        return target;

    }


    /**
     * 先序遍历 中-左-右
     */
    public void preSort() {
        if (this != null) {
            System.out.println(this);
            if (this.left != null) {
                this.left.preSort();
            }

            if (this.right != null) {
                this.right.preSort();
            }
        }
    }

    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public HeroNode1 preOrderSearch(int no) {


        if (this.no == no) {
            return this;
        }

        HeroNode1 resNode = null;
        if (this.left != null) {
            // 向左递归查找
            resNode = this.left.preOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.left != null) {
            //  向左递归查找
            resNode = this.left.preOrderSearch(no);
        }

        if (this.right != null) {
            // 向右递归查找
            resNode = this.right.preOrderSearch(no);
        }


        return resNode;
    }


    /**
     * 中序遍历 左-中-右
     */
    public void infixOrder() {
        if (this != null) {
            //
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);

            if (this.right != null) {
                this.right.infixOrder();
            }
        }
    }

    /**
     * 中序遍历查找
     * @return
     */
    public HeroNode1 infixOrderSearch(int no) {
        HeroNode1 resNode = null;

        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            resNode = this;
        }

        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }

        return resNode;


    }


    /**
     * 后序遍历
     *
     */
    public void postOrder() {

        if (this != null) {
            if (this.left != null) {
                this.left.postOrder();
            }

            if (this.right != null) {
                this.right.postOrder();
            }

            System.out.println(this);


        }


    }


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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
