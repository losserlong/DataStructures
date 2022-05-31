package com.atguigu.huffmancode;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/05    10:12
 * @Version:1.0
 * 哈夫曼树的复习
 *
 *
 */
public class HuffmanCodeReview {


    @Test
    public void huffmanTest() {
        String str = "i like like like java do you like a java";
        List<Node1> nodes = HuffmanCodeReview.getNodes(str.getBytes());
        System.out.println("nodes = " + nodes);

        Node1 huffmanTree = HuffmanCodeReview.createHuffmanTree(nodes);
        System.out.println("huffmanTree = " + huffmanTree);
        huffmanTree.preSort();
    }

    @Test
    public void testHuffmanCode() {
        String str = "i like like like java do you like a java";
        System.out.println("长度是 = " + str.length());
        List<Node1> nodes = HuffmanCodeReview.getNodes(str.getBytes());
        Node1 huffmanRoot = HuffmanCodeReview.createHuffmanTree(nodes);

        Map<Byte, String> huffmanCodes = HuffmanCodeReview.getHuffmanCodes(huffmanRoot, "", builder);
        System.out.println(huffmanCodes);
        byte[] zip = zip(str.getBytes(), huffmanCodes);
        System.out.println("哈夫曼编码之后的数组是~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(Arrays.toString(zip)+"长度是~~~~~~~~~~~~~~~~~"+zip.length);
    }


    /**
     *
     * @param bytes 原始字符串对应的ASCII 码的byte数组
     * @param huffmanCode 哈夫曼编码表
     * @return  返回压缩后的哈夫曼编码
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        StringBuilder sb = new StringBuilder();
        for (byte value : bytes) {
            // 将ASCII码对应的哈夫曼编码加入到sb中
            sb.append(huffmanCode.get(value));
        }

        System.out.println(sb);

        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            // 除不尽就 + 1
            len = sb.length() / 8 + 1;
        }

        // 创建压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;
        // 步长是8,也就是每次截取八个
        for (int i = 0; i < sb.length(); i+=8) {

            String strByte;
            if (i + 8 > sb.length()) {
                // 直接全部加入
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }

            // 将strByte转成二进制
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }

        // 返回哈夫曼
        return huffmanCodeBytes;


    }


    // huffmanCode的哈夫曼编码
    public static Map<Byte, String> huffmanCode = new HashMap<>();
    static StringBuilder builder = new StringBuilder();


    /**
     * 重载一下
     */
    public static Map<Byte, String> getHuffmanCodes(Node1 node) {
        return getHuffmanCodes(node, "", builder);
    }

    /**
     * 返回哈夫曼编码表,
     * @param node 哈夫曼树的根节点
     * @param code 路径 0 ：表示左子节点 1：表示右子节点
     * @param sb 用于拼接10001011
     * @return 哈夫曼编码表
     */
    public static Map<Byte, String> getHuffmanCodes(Node1 node, String code, StringBuilder sb) {
        StringBuilder builder = new StringBuilder(sb);

        // 将code加入到builder中
        builder.append(code);
        if (node != null) {
            // 判断当前节点是叶子节点还是非叶子结点
            if (node.date == null) {
                // 非叶子结点
                // 向左边递归
                getHuffmanCodes(node.left, "0", builder);
                getHuffmanCodes(node.right, "1", builder);
            } else {
                // 说明已经到达了最后(非叶子节点)
                // 就将builder字符串加入到哈夫曼编码表中,key是node中的data也就是byte
                huffmanCode.put(node.date, new String(builder));
            }
        }

        return huffmanCode;
    }


    /**
     * 传入的Node结点构建一颗赫夫曼树,
     * @param list
     * @return
     */
    public static Node1 createHuffmanTree(List<Node1> list) {
        while (list.size() > 1) {
            // 先对集合中的数据进行排序
            Collections.sort(list);

            Node1 leftNode = list.get(0);
            Node1 rightNode = list.get(1);
            // 父节点
            Node1 parentNode = new Node1(leftNode.weight + rightNode.weight);
            // 设置parent的左右结点
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 在list中移除leftNode和rightNode
            list.remove(leftNode);
            list.remove(rightNode);
            // 加入parentNode
            list.add(parentNode);

        }


        return list.get(0);
    }


    /**
     * 传入bytes数组返回list集合,已经返回Node1的所有结点了,哈夫曼编码树的一个结点的data是一个字节数组中的一个值,weight是出现的次数
     * @param bytes
     * @return
     */
    public static List<Node1> getNodes(byte[] bytes) {
        List<Node1> list = new ArrayList<>();

        Map<Byte, Integer> hashMap = new HashMap<>();

        // 先统计各个字节出现的次数
        for (byte b : bytes) {
            if (hashMap.containsKey(b)) {
                // 之前已经存在的时候,就将原先的值+1
                hashMap.put(b, hashMap.get(b) + 1);

            } else {
                // 当是第一次加入的时候
                hashMap.put(b, 1);
            }
        }

        // 遍历hashMap,将Node装入到list中
        Iterator<Map.Entry<Byte, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Byte, Integer> entry = iterator.next();
            list.add(new Node1(entry.getKey(), entry.getValue()));
        }

        return list;


    }


}

/**
 * 创建Node1,带数据和权值
 */
class Node1 implements Comparable<Node1> {

    //存放数据本身,比如'a' => 97 , ' ' => 32
    Byte date;
    // 代表的是权值,表示字符出现的次数
    int weight;
    // 指向左子树
    Node1 left;
    // 指向右子树
    Node1 right;


    /**
     * 树的前序遍历方法
     */
    public void preSort() {


        if (this == null) {
            return;
        }
        System.out.println(this);
        if (this.left != null) {
            this.left.preSort();
        }
        if (this.right != null) {
            this.right.preSort();
        }


    }

    public Node1() {
    }

    public Node1(int weight) {
        this.weight = weight;
    }

    public Node1(Byte date, int weight) {
        this.date = date;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + date +
                ", weight=" + weight +
                '}';
    }

    /**
     * 因为要将Node的值参与比较所以重写compareTo方法
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node1 o) {
        return this.weight - o.weight;
    }
}




