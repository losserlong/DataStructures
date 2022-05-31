package com.atguigu.SparseArray;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/16    9:50
 * @Version:1.0
 *
 * 稀疏数组
 * 获取行长度 : int rowlength=array.length;
 *
 * 获取列长度:int colength=array[0].length;
 *
 *
 *
 */
public class SparseArray {

    public static void main(String[] args) throws Exception {
        // 创建原始的二维数组 11 * 11
        // 0：表示没有棋子 ,1 表示黑子  ，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[5][6] = 2;
        // 输出原始的二维数组
        // 取出每一行
        for (int[] row : chessArr1) {
            // 将每一行进行遍历
            for (int item : row) {
                System.out.print(item + "  ");
            }
            // 遍历完每一行之后进行换行
            System.out.println();

        }

        // 将二维数组转成稀疏数组
        // 1.先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1[0].length; i++) {
            for (int j = 0; j < chessArr1[1].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }

        }

        System.out.println("sum=" + sum);
        // 2.创建对应的稀疏数组  sum+1是因为在原先的基础上加了个 row  col val 原始数组的行列
        int sparseArray[][] = new int[sum + 1][3];

        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 遍历二维数组，将非0的值放到sparseArray中
        int count = 0; // count 用于记录是第几个非0数据
        for (int i = 0; i < chessArr1[0].length; i++) {
            for (int j = 0; j < chessArr1[1].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i; // 存放行
                    sparseArray[count][1] = j; // 存放列
                    sparseArray[count][2] = chessArr1[i][j]; // 存放值
                }
            }

        }

        // 输出稀疏数组的形式
        System.out.println("-------------------------得到的稀疏数组的形式为-------------------------");
        for (int i = 0; i < sparseArray.length; i++) {
            // 这里的第一行第三列的数字是原始数组中含有的非0数字的个数
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
            System.out.println();
        }


        /**
         * 将稀疏数组转成二维数组
         * 1.先读取稀疏数组中的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2  = int [11] [11]
         * 2.在读取稀疏数组后的几行数据，并且赋值给二维数组即可
         */
        // 创建原来的二维数组
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 遍历稀疏数组 从稀疏数组的第二行开始遍历
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];

        }

        System.out.println("----------------------------------------分割线");
        for (int[] row : chessArr2) {
            for (int item : row) {
                System.out.print(item + "  ");
            }
            System.out.println();
        }
        ObjectOutputStream oos = null;
        try {
            // 使用对象流水将数组存入
            // 1.造流，造文件
            oos = new ObjectOutputStream(new FileOutputStream("object.txt"));


            // 2.将文件写入
            oos.writeObject(sparseArray);
            oos.flush();


            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));
            int[][] arr = (int[][]) ois.readObject();
            for (int [] row:arr){
                for(int item:row){
                    System.out.print(item+"  ");
                }
                System.out.println();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
        }


    }
}
