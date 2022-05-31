package com.atguigu.huffmancode;

import org.junit.Test;

import java.io.*;
import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/03    19:02
 * @Version:1.0
 * ==========================================哈夫曼编码====================================================
 * 注意赫夫曼编码的非叶子节点的data为null,因为它的非叶子节点的weight值是有叶子结点的weigh之和设置的
 * 赫夫曼编码是前缀编码，是无二义性的
 *
 *
 *
 */
public class HuffmanCode {
    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        // 将传入的字符串转换成字节数组
        byte[] contentBytes = content.getBytes();
        System.out.println("还未压缩前的内容的字节数组的长度是" + contentBytes.length);

        List<Node> list = getNodes(contentBytes);
        System.out.println(list);

        Node root = createHuffmanTree(list);

        System.out.println("前序遍历");
        root.preSort();

        // 测试是否生成了对应的哈夫曼编码
        System.out.println("测试是否生成了对应的哈夫曼编码");
        getCodes(root);
        System.out.println("生成的哈夫曼编码表是" + huffmanCodes);

        byte[] zip = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(zip)); // 17

        byte[] bs = huffmanZip(zip);
    }

    @Test
    public void testHuffman() {
        String content = "i like like like java do you like a java";

        byte[] huffmanZip = HuffmanCode.huffmanZip(content.getBytes());
        System.out.println(Arrays.toString(huffmanZip) + "\n长度为" + huffmanZip.length);


    }

    @Test
    public void testHuffman2() {
        String content = "i like like like java do you like a java";
        List<Node> nodes = HuffmanCode.getNodes(content.getBytes());
        Node huffmanTree = HuffmanCode.createHuffmanTree(nodes);
        System.out.println("huffmanTree = " + huffmanTree);
        huffmanTree.preSort();

    }


    @Test
    public void testHuffman1() {
//        String str = HuffmanCode.byteToBitString((byte) 1);
//        System.out.println("str = " + str);
        String content = "i like like like java do you like a java";

        // 赫夫曼编码过后的数组
        byte[] huffmanBytes = huffmanZip(content.getBytes());
        System.out.println("huffmanBytes = " + Arrays.toString(huffmanBytes));
        byte[] sourceBytes = HuffmanCode.decode(huffmanCodes, huffmanBytes);
        System.out.println(Arrays.toString(sourceBytes));
        System.out.println(new String(sourceBytes));


    }


    /**
     * 文件压缩测试
     */
    @Test
    public void testZipFile() {
        String srcFile = "D://workspaces//aaa.txt";
        String desFile = "./dest.zip";

        HuffmanCode.zipFile(srcFile, desFile);
        System.out.println("压缩文件成功");

    }


    /**
     * 编写一个将文件压缩的方法
     * @param srcFile 待压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录下
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);

            // 创建一个和源文件一样大小的byte[]
            byte[] bytes = new byte[is.available()];
            // 读取文件
            // 将src中的内容读取到bytes中
            is.read(bytes);
            // 获取到文件对应的赫夫曼编码表
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            // 创建文件的输出流用于存放压缩文件
            fos = new FileOutputStream(dstFile);

            // 创建一个和文件输出流相关联的ObjectOutputStream
            oos = new ObjectOutputStream(fos);

            // 把赫夫曼编码后的字节数组写入到压缩文件
            oos.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入赫夫曼编码,是为了恢复源文件
            // 注意一定要把赫夫曼编码表写入压缩文件
            oos.writeObject(huffmanCodes);


            fos.write(1024);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }


    /**
     * 编写一个方法,完成对压缩数据的解码
     * @param huffmanCodes 原先的哈夫曼编码表
     * @param huffmanBytes 就是哈夫曼编码处理后的二进制所对应的十进制的数组  [-88, -65, -56, -65, ....]
     * @return 返回的就是原来的字符串对应的数组  数据 "i like java..."对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到huffmanBytes对应的二进制字符串,形式1010100010
        StringBuilder sb = new StringBuilder();

        // 2. 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(!flag, huffmanBytes[i]));
        }

//        System.out.println("赫夫曼解码后的字节数组对应的二进制字符串=" + sb.toString());

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换,因为要反向查询 原先是 a ==> 100  现在是 100 ==> a
        Map<String, Byte> map = new HashMap<>();
        Iterator<Map.Entry<Byte, String>> iterator = huffmanCodes.entrySet().iterator();
        // 反向拿到将key和value反过来加到,之前的哈夫曼编码表是拿到单个单词对应的ASCII码值所对应的哈夫曼编码的二进制数值
        // 现在反过来得到二进制数值对应的ASCII码值
        while (iterator.hasNext()) {
            Map.Entry<Byte, String> entry = iterator.next();

            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map = " + map);

        // 创建一个集合,存放byte
        List<Byte> list = new ArrayList<>();
        // i不断扫描sb(二进制字符串)
        for (int i = 0; i < sb.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 递增的取出 key,就是用滑动窗口取匹配(双指针,它的左指针不东,右指针移动去匹配)
                //
                String key = sb.substring(i, i + count);
                if (map.containsKey(key)) {
                    // 如果匹配到了
                    flag = false;
                    list.add(map.get(key));
                    break;
                } else {
                    // 如果没有匹配到
                    // count右指针后移
                    count++;
                }
            }
            // 将左边的指针移动到count的位置
            i += count;
        }


        // 当for循环结束后,我们的list就存放了所有的字符 "i like like ...." 所对应的二进制数组
        // 所有我们要将list中的数据放入到byte[]中并返回
//        System.out.println(list);

        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }


        return bytes;
    }

    /**
     * 完成数据的解压操作,其实就是压缩的逆过程
     * 思路：
     * 1. 先将huffmanCodeBytes数组[-88, -65, -56,...] 哈夫曼编码对应的二进制字符串"10101000101111111..."
     * 2. 赫夫曼编码对应的二进制的字符串"10101000101111111"  ==>对照赫夫曼编码 ==> "i like like like java do you like a java"
     *
     * 将一个byte转成一个二进制的字符串
     * @param flag 表示标识是否需要补高位,如果是true,表示需要补高位,如果是false表示不补,如果是最后一个字节不用补充高位
     * @param b 传入byte
     * @return 返回b所对应的二进制字符串(注意返回的是补码)
     */
    public static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存b
        int temp = b; // 将b转成int

        // 如果是正数我们还存在补高位的
        // 按位或
        // 256==> 1 0000 0000 |  0000 0001  ==> 1 0000 0001
        if (flag) {
            temp |= 256;
        }

        // 返回的是temp对应的补码
        String str = Integer.toBinaryString(temp);
//        System.out.println("str = " + str);
        if (flag) {               // 取我们的后面8位
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 将下面的将原来的byte数组转化成哈夫曼编码之后的数组
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes) {
        // 将原来的字节数组转化成List<Node> Node里面 存放的是这个字符和出现的次数
        List<Node> nodes = getNodes(bytes);

        // 创建nodes传入创建赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);

        // 根据赫夫曼树创建对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);

        byte[] zip = zip(bytes, huffmanCodes);


        return zip;

    }


    /**
     * 编写一个方法,将字符串对应的byte[]数组,通过生成赫夫曼编码表,返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes 这是原始的字符串对应的byte[]
     * @param huffmanCodes 返回赫夫曼编码的map数组
     * @return 返回赫夫曼编码处理之后的byte[]
     * 举例：String content="i like like like java do you like a java";==>byte[] contentBytes=content.getBytes();
     *  返回的字符串是"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     *  ==>对应的byte[] huffmanCodeBytes , 即8位对应一个byte,放到huffmanCodeBytes
     *  huffmanCodeBytes[0] =10101000(补码) ==> byte [推导 10101000==>10101000 -1 = 10100111(反码) ==> 11011000]
     *  huffmanCodeBytes[0]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 其实吧,说了这么多,就是将原来的byte数组,通过赫夫曼编码表之后转成二进制的byte数组,
        StringBuilder sb = new StringBuilder();
        // 遍历bytes数组
        for (byte value : bytes) {
            sb.append(huffmanCodes.get(value));
        }
        System.out.println(sb.toString());

        // System.out.println(sb.toString());
        // 将"010100010111111110010..."转成byte[]
        // 统计返回的哈夫曼编码所对应的长度 byte[] huffmanCodeBytes长度
        // 下面判断分支对应的一行高效代码为：len = contentHuffmanCodeStr.length() + 7 / 8,
        // 原理是让它大于等于 8 ，无论是刚好整除还是有余数，都和下面的结果一致

        int len;

        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            // 除不尽就加1个长度
            len = sb.length() / 8 + 1;
        }

        // 创建压缩后的byte[]数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;

        // 每八位放一个byte,所以步长就是8
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;

            if (i + 8 > sb.length()) {
                // 说明最后这一位的长度不够八位了，直接从i取到最后
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            // 将二进制字符串转成byte加入到哈夫曼编码的byte数组中
            // 01010001 ==> 转换成  byte
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }


        return huffmanCodeBytes;
    }


    /**
     * 对下面的获得赫夫曼编码表的方法进行重载
     * @param node
     */
    public static Map<Byte, String> getCodes(Node node) {
        return getCodes(node, "", builder);
    }


    // 生成赫夫曼树对应的赫夫曼编码
    // 思路：
    // 1. 将赫夫曼编码表存放在Map<Byte , String>形式
    // 32 => 01 , 97 => 100 100 =>11000 等,赫夫曼编码是前缀编码，是无二义性的
    // 2. 在生成赫夫曼编码表的时候,需要去拼接路径,定义一个StringBuilder,存储某个叶子节点的路径
    public static Map<Byte, String> huffmanCodes = new HashMap<>();
    public static StringBuilder builder = new StringBuilder();

    /**
     * 将传入的node结点的所有叶子节点的哈夫曼编码得到,并放到huffmanCodes集合中
     * @param node 赫夫曼树的根节点
     * @param code 路径：左子节点是0 , 右子节点是1
     * @param sb StringBuilder用于拼接路径的(也就是01010的那个编码)
     */
    public static Map<Byte, String> getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        // 将code加入到sb2中
        sb2.append(code);
        // 如果node为空不进行处理
        if (node != null) {
            // 判断当前node是叶子节点还是非叶子节点
            if (node.date == null) {
                // 表明是非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", sb2);

                // 向右递归
                getCodes(node.right, "1", sb2);

            } else {
                // 说明当前这个结点是叶子节点
                // 就表示找到了叶子节点的最后
                huffmanCodes.put(node.date, sb2.toString());
            }

        }

        return huffmanCodes;

    }


    /**
     * 创建赫夫曼树
     * @param list
     * @return
     */
    private static Node createHuffmanTree(List<Node> list) {

        while (list.size() > 1) {
            // 先对list进行排序,从小到大排序
            Collections.sort(list);

            // 取出第一棵最小的二叉树
            Node leftNode = list.get(0);
            // 取出第二小的二叉树
            Node rightNode = list.get(1);
            // 根节点的二叉树没有data只有权值
            Node parent = new Node();
            parent.weight = leftNode.weight + rightNode.weight;
            // 将两个最小值和次小值的父节点的左右节点指向leftNode和rightNode
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            // 将parent加入到list中
            list.add(parent);

        }

        // 返回最后的结点nodes
        return list.get(0);
    }

    /**
     * 将传入的字节数组返回node集合
     * @param contentBytes
     * @return node集合
     */
    private static List<Node> getNodes(byte[] contentBytes) {
        List<Node> nodes = new ArrayList<>();

        // 存储每个byte出现的次数-->用map[key , value]进行存储
        Map<Byte, Integer> hashMap = new HashMap<>();
        for (byte b : contentBytes) {
            // 表示当hashMap中之前没有加入这个元素就将它的值设置为1
            if (!hashMap.containsKey(b)) {
                hashMap.put(b, 1);
            } else {
                // 出现过了就将它原来的值+1
                hashMap.put(b, hashMap.get(b) + 1);
            }
        }

        // 把每一个键值对转换成一个Node对象,并加入到List中
        Iterator<Map.Entry<Byte, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Byte, Integer> entry = iterator.next();
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }


        return nodes;
    }


}

/**
 * 创建Node,带数据和权值
 */
class Node implements Comparable<Node> {

    //存放数据本身,比如'a' => 97 , ' ' => 32
    Byte date;
    // 代表的是权值,表示字符出现的次数
    int weight;
    // 指向左子树
    Node left;
    // 指向右子树
    Node right;


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

    public Node() {
    }

    public Node(Byte date, int weight) {
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
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}




