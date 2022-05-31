package com.atguigu.tree;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/27    17:05
 * @Version:1.0
 * 顺序存储二叉树的代码实现
 * 顺序存储二叉树
 * 顺序存储二叉树的概念：
 *     顺序存储二叉树的特点：
 *         （1）顺序存储二叉树通常只考虑完全二叉树
 *         （2）第n个元素的左子节点为2*n+1
 *         （3）第n个元素的右子节点为2*n+2
 *         （4）第n个元素的父节点是(n-1)/2
 *         （5）n：表示二叉树中的第几个元素(按照0开始编号如图所示)
 *         [1,2,3,4,5,6,7] (想那个二叉树的图)
 *
 *
 *
 */
public class ArrBinaryTreeDemo {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个ArrayBinaryTree
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder(0);
        System.out.println();
        tree.preOrder();
        System.out.println("中序遍历");
        tree.infixOrder(0);

        tree.postOrder(0);


        tree.preOrder1(0);


    }


}

// 编写一个ArrayBinaryTree,实现顺序存储二叉树的遍历
class ArrBinaryTree {


    // 存储数据节点的数组
    private int[] arr;


    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }


    /**
     * 重载preOrder方法
     */
    public void preOrder() {
        this.preOrder(0);
    }


    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * 顺序存储二叉树的概念：
     *     顺序存储二叉树的特点：
     *         （1）顺序存储二叉树通常只考虑完全二叉树
     *         （2）第n个元素的左子节点为2*n+1
     *         （3）第n个元素的右子节点为2*n+2
     *         （4）第n个元素的父节点是(n-1)/2
     *         （5）n：表示二叉树中的第几个元素(按照0开始编号如图所示)
     *         [1,2,3,4,5,6,7] (想那个二叉树的图)
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        // 输出当前的元素
        System.out.print(arr[index] + "\t\t");
        // 向左边递归遍历
        // 因为左边的数是当前的下标*2+1
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }
        // 右边的数是当前的小标*2+2
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }


    /**
     * 顺序存储二叉树的中序遍历
     * @param index
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照完全二叉树进行遍历");
            return;
        }
        // 中序遍历,也就是先左,在中,在右
        if (2 * index + 1 < arr.length) {
            // 就左中序遍历
            infixOrder(2 * index + 1);
        }
        System.out.println("输出当前元素" + arr[index]);
        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }


    }


    /**
     * 后续遍历顺序存储二叉树
     */
    public void postOrder(int index) {
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }

        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }

        System.out.println("输出当前元素\t\t" + arr[index]);


    }


    /**
     * 复习顺序存储二叉树
     */
    public void preOrder1(int index) {

        if (arr == null || arr.length == 0) {
            return;
        }

        // 中序遍历
        if (index * 2 + 1 < arr.length) {
            preOrder1(index * 2 + 1);
        }

        System.out.println("中序遍历结果为\t" + arr[index]);


        // 右边节点
        if (index * 2 + 2 < arr.length) {
            preOrder1(index * 2 + 2);
        }


    }


    /**
     * 顺序化左子结点是index * 2 + 1
     * 顺序化右子结点是index * 2 + 2
     * 复习顺序存储二叉树
     * @param index 传入的是开始顺序化的下标
     */
    public void preOrder2(int index) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // 前序遍历,先输出它自己
        System.out.println(arr[index]);

        // 查找左子结点
        if (index * 2 + 1 < arr.length) {
            preOrder2(index * 2 + 1);
        }
        // 查找右子节点
        if (index * 2 + 2 < arr.length) {
            preOrder2(index * 2 + 2);
        }


    }


}






