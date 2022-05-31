package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/17    13:37
 * @Version:1.0
 *
 * ===========================================================简单插入排序===========================================================
 * 基本介绍
 * 插入排序属于内部排序法，是对于欲排序的元素以 插入的方式寻找该元素的适当位置，以达到排序的目的。
 *该 插入排序又被称为 直接插入排序 或 简单插入排序。
 *
 * 基本思想
 * 插入排序（Insertion Sorting）的基本思想是：
 * 1. 把 n 个待排序的元素 看成 为一个 有序表 和 无序表
 * 2. 开始时，有序表中只包含一个元素，无序表中包含有 n - 1 个 元素
 * 排序过程中每次 从无序表中取出第一个元素，把它的排序码 依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，
 * 使之成为新的有序表。
 *
 *
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {46, 34, 10, 1, -1, 89};
        System.out.println("原始数组" + Arrays.toString(arr));
        insertSort(arr);

    }


    @Test
    public void test01() {

        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        insertSort(arr1);
        long end = System.currentTimeMillis();
        // 花费477
        System.out.println("insertSort花费" + (end - begin));
        System.out.println(Arrays.toString(arr1));

    }


    /**
     * 推到过程在下面
     * 这个是最终的写法
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int insertIndex = 0;
        int insertVal = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            insertIndex = i;
            insertVal = arr[i + 1];
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }


            arr[insertIndex + 1] = insertVal;


            System.out.println(Arrays.toString(arr));

        }

    }


    /**
     * 这个是推导过程
     * 插入排序
     * 说是说分成 两个表一个有序表一个无序表，
     * 但是实际上只是分开了，一段用来表示有序表，另一段表示无序表
     *
     * @param arr
     */
    public static void processInsertSort(int[] arr) {
        // 使用逐步推导的方式讲解，便于理解
        // 第一次插入的结果
        // int[] arr = {101, 34, 119, 1};  => {34 , 101 , 119 ,1}

        // =================================第一轮======================================

        // 定义待插入的数
        int insertVal = arr[1];
        // 待插入数的索引
        int insertIndex = 1 - 1; //要插入的数与有序表中最后一个开始比较（存储的是下标）。也就是当前数的上一个开始比较

        // 给insertVal找到插入的位置
        // 说明
        // 1. insertIndex  >= 0 保证在insertVal找插入位置，不越界
        // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        // 3. 就需要将arr[index]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            // 101  101  119  1
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        // 当退出循环，表示位置找到了，就是insertIndex+1
        // 34  101  119  1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮后");

        System.out.println(Arrays.toString(arr));

        // =================================第二轮======================================
        insertVal = arr[2];
        insertIndex = 2 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第二轮后");

        System.out.println(Arrays.toString(arr));

        // =================================第三轮======================================
        insertVal = arr[3];
        insertIndex = 3 - 1;
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;


        System.out.println("第三轮后");

        System.out.println(Arrays.toString(arr));


    }


    /**
     * 2022年4月22日
     * 插入排序复习
     * @param arr
     */
    public void insertSort3(int[] arr) {
        // 插入值在有序表中的索引
        int insertIndex = 0;

        // 插入的值
        int insertValue = 0;

        // 从下标为1的开始往前面插入
        // 这里要 < arr.length-1 因为后面待插入的用了 i + 1不然就会溢出
        for (int i = 0; i < arr.length - 1; i++) {
            insertIndex = i;
            insertValue = arr[i + 1];

            // 小到大排列
            while (insertIndex >= 0 && insertValue < arr[i]) {

                // 就将正序列中index的值赋值给它的下一个
                arr[insertIndex + 1] = arr[insertIndex];
                // 索引下移
                insertIndex--;
            }

            // 说明找到了待插入的位置
            arr[insertIndex + 1] = insertValue;


        }


    }


    /**
     * 2022年4月18日
     * 第二次写插入排序
     */
    public void insertSort1(int[] arr) {

        // 插入的值
        int insertValue = 0;
        // 插入的位置
        int insertPos = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            // i+1 位的元素插入到i之前的元素的合适位置
            insertValue = arr[i + 1];
            insertPos = i;
            // 这里模拟第一次
            // int[] arr = {101, 34, 119, 1};  => {34 , 101 , 119 ,1}
            while (insertPos >= 0 && insertValue < arr[insertPos]) {
                // 101 101 119 1
                // 把插入的位置的前一个赋值给后一个
                arr[insertPos + 1] = arr[insertPos];
                insertPos--;
            }
            // insert表示就找到了位置
            // 这里就表示insertValue <= arr[insertPos]
            // 就插入到arr[insertPost]前面
            // 34 101 119 1
            arr[insertPos + 1] = insertValue;

        }
    }

    @Test
    public void test2() {

        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        insertSort1(arr1);
        long end = System.currentTimeMillis();
        // 花费477
        System.out.println("花费" + (end - begin));
        System.out.println(Arrays.toString(arr1));
        int[] arr = {46, 34, 10, 1, -1, 89};
        insertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 2022年4月19日
     *  基本介绍
     *  插入排序属于内部排序法，是对于欲排序的元素以 插入的方式寻找该元素的适当位置，以达到排序的目的。
     * 该 插入排序又被称为 直接插入排序 或 简单插入排序。
     * 插入排序复习
     * @param arr
     */
    public void insertSort2(int[] arr) {
        // 插入的值
        int insertValue = 0;
        // 插入的位置
        int pos = 0;
        // int[] arr = {101, 34, 119, 1};  => {34 , 101 , 119 ,1}
        for (int i = 0; i < arr.length - 1; i++) {
            insertValue = arr[i + 1];
            pos = i;
            while (pos >= 0 && insertValue < arr[pos]) {
                // 101 101 119 1
                arr[pos + 1] = arr[pos];
                pos--; // -1
            }
            // pos 0
            // 就说明pos+1是将要插入的位置
            arr[pos + 1] = insertValue;

        }


    }


}
