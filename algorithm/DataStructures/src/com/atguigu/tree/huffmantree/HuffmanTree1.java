package com.atguigu.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/03    11:18
 * @Version:1.0
 * 赫夫曼树复习
 *
 */
public class HuffmanTree1 {


    public static void main(String[] args) {

        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preSort();


    }


    /**
     *
     * 创建一棵赫夫曼树
     * @param arr
     * @return 返回哈夫曼树的头节点
     */
    public static Node createHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList<>();

        for (int value : arr) {
            // 将新构建的赫夫曼树加到list中
            list.add(new Node(value));
        }

        // 先将赫夫曼树的每一个子树(每一个节点)进行排序
        Collections.sort(list);
        while (list.size() > 1) {
            // 左子结点
            Node leftNode = list.get(0);
            // 右子结点
            Node rightNode = list.get(1);
            // 将最小和次小的两个结点的值组成一个新节点也就是他们的父节点
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 然后将两个结点进行移除
            list.remove(leftNode);
            list.remove(rightNode);

            // 再将parent加入到list集合中，然后再排序，重复上述的操作，
            list.add(parent);
            // 直到list中只剩下一个结点，也就是Node的根节点
            Collections.sort(list);
        }

        // 返回哈夫曼树的头节点
        return list.get(0);

    }


}
