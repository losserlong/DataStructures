package com.atguigu.huffmancode.file_zip;

import com.atguigu.huffmancode.HuffmanCode;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/05    21:48
 * @Version:1.0
 * 文件压缩和解压缩
 *
 */
public class FileZipAndUnzip {


    /**
     * 文件压缩测试
     */
    @Test
    public void testZipFile() {
//        String srcFile = "D://workspaces//aaa.txt";
        String srcFile = "./dariary.txt";
        String desFile = "./dariary.zip";

        HuffmanCode.zipFile(srcFile, desFile);
        System.out.println("压缩文件成功");


    }

    /**
     * 测试文件解压
     */
    @Test
    public void testunZipFile() {
        String srcFile = "D://workspaces";
        String desFile = "./dest.zip";

//        HuffmanCode.zipFile(srcFile, desFile);
//        System.out.println("压缩文件成功");
        try {
            FileZipAndUnzip.unZipFile(desFile, "./aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解压文件成功");


    }

    /**
     * 解压文件测试
     */
    @Test
    public void unZipFile(){
        String ziFile="./dest.zip";
        String dstFile="./destFile";
        FileZipAndUnzip.unZipFile(ziFile,dstFile);



    }


    /**
     * 编写一个方法对压缩文件的解压
     * @param zipFile 待解压的文件
     * @param dstFile 将文件解压到哪个位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义一个文件输出流
        OutputStream os = null;
        try {

            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组和huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表huffmanCodes
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 原始的byte
            byte[] res = HuffmanCode.decode(huffmanCodes, huffmanBytes);

            // 将bytes写入到目标文件
            os = new FileOutputStream(dstFile);
            os.write(res);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭流资源,和打开的顺序相反
            try {
                if (os != null) {
                    os.close();
                }
                // 关闭流资源
                if (ois != null) {
                    ois.close();
                }
                // 关闭流资源
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }



    }


    /**
     * 编写一个将文件压缩的方法,这里读和写的时候都是用对象流的方式进行操作的
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
            byte[] huffmanBytes = HuffmanCode.huffmanZip(bytes);
            // 创建文件的输出流用于存放压缩文件
            fos = new FileOutputStream(dstFile);

            // 创建一个和文件输出流相关联的ObjectOutputStream
            oos = new ObjectOutputStream(fos);

            // 把赫夫曼编码后的字节数组写入到压缩文件
            oos.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入赫夫曼编码,是为了恢复源文件
            // 注意一定要把赫夫曼编码表写入压缩文件
            oos.writeObject(HuffmanCode.huffmanCodes);


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
}
