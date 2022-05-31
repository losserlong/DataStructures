package com.atguigu.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/02    20:58
 * @Version:1.0
 * =========================================赫夫曼树================================================
 *叶子节点，构造一颗二叉树，若该树的带权路径长度（WPL）达到最小，称
 * 这样的二叉树为 最优二叉树，也称为 哈夫曼树（Huffman Tree），还有的叫 霍夫曼树
 *
 *
 * 节点的权 及 带权路径长度
 *若将树中节点赋给一个有着某种函数的数值，则这个数值称为该节点的 权。
 *节点的带权路径长度为：从根节点到该节点之间的路径长度与该节点的权的乘积。
 *
 * 树的带权路径长度
 * 所有叶子节点的带权路径长度之和，记为 WPL（weighted path length），权值越大的节点离根节点越近的二叉树才是最优二叉树
 *
 *============================================构建赫夫曼树的步骤
 * 构成赫夫曼树的步骤:
 * 1)从小到大进行排序,将每一个数据，每个数据都是一个节点，每个节点可以看成是—颗最简单的二叉树
 * 2)取出根节点权值最小的两颗二叉树
 * 3)组成一颗新的二叉树,该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4)再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4的步骤，
 *
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preSort();

    }

    /**
     * 创建赫夫曼树的方法
     * 传入数组返回赫夫曼树
     * @param arr
     * @return 赫夫曼树的头节点
     */
    private static Node createHuffmanTree(int[] arr) {
        // 为了操作方便
        // 1. 遍历arr数组
        // 2. 将arr的每一个元素构建成一个Node
        // 3. 将Node放入到ArrayList中便于排序
        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }

        // 先对集合中的数从小到大排序
        Collections.sort(list);

        System.out.println(list);

        // !!!最后不断的增加删除之后,ArrayList集合中最终只会剩下一个根节点了
        // !!!就说明已经构建好了哈夫曼树
        while (list.size() > 1) {


            // 先对集合中的数从小到大排序
            Collections.sort(list);


            // 取出根节点权值最小的两颗二叉树
            // (1) 取出权值最小的结点(二叉树)
            Node leftNode = list.get(0);

            // (2) 取出次小的结点(二叉树)
            Node rightNode = list.get(1);

            // (3) 构建一颗新的二叉树
            // 将左孩子的value值+右孩子的value值赋值给父节点
            Node parent = new Node(rightNode.value + leftNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // (4) 从ArrayList中删除已经加入过的二叉树
            list.remove(leftNode);
            list.remove(rightNode);

            // (5) 将parent加入到list中
            list.add(parent);
        }

        System.out.println("第一轮处理完成" + list);

        return list.get(0);

    }


}

/**
 * 创建结点类
 * 为了让Node类能实现排序,我们将Node实现Comparable接口
 */
class Node implements Comparable<Node> {
    // 结点的权值
    int value;

    // 指向左子节点
    Node left;

    // 指向右子结点
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public static void preSort(Node node) {
        if (node != null) {
            node.preSort();
        }else {
            System.out.println("空树不能遍历");
        }


    }


    /**
     * 前序遍历，用来测试哈夫曼树的正确性
     */
    public void preSort() {
        System.out.println(this);

        if (this.left != null) {
            this.left.preSort();
        }
        if (this.right != null) {
            this.right.preSort();
        }


    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

/*
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
*/

    @Override
    public int compareTo(Node node) {
        // 从小到大排序
        return this.value - node.value;
    }
}