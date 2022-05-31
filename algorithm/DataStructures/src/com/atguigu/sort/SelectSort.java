package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/17    10:35
 * @Version:1.0
 * 选择排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出来某个元素，再依规定交换位置后达到排序的目的。
 * 选择排序就是每次选择出后面遍历的最小的元素放在最前面
 *
 *
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {1, 10, 2, -1, 8, 4};
        selectSort(arr);

        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }

        long begin = System.currentTimeMillis();

        long end = System.currentTimeMillis();
        System.out.println("花费的时间是" + (end - begin));


    }


    @Test
    public void test01() {
        int[] arr = {101, 99, 34, 1, -1, 90, 123};
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        SelectSort.selectSort(arr1);
        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("selectSort花费" + (end - begin));

    }


    /**
     * 这个是最终的完善，推导过程在下面
     * @param arr
     */
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minValue = arr[i];
            // 假定当前数的索引的最小值就是i
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    // 说明假定的最小值，并不是最小，重置minValue
                    minValue = arr[j];
                    // 重置minIndex
                    minIndex = j;
                }
            }

            // 如果minIndex没有发生变化还是i的话就不用交换
            // 说明minValue就是那个最小值
            if (minIndex != i) {
                // 将最小值，放在arr[0]，即交换
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }

//        System.out.println(Arrays.toString(arr));


    }


    /**
     * 推导每一步的演变过程，便于理解
     *
     *   这是一个很重要的思想：
     *      一个算法：先简单  -->  做复杂：
     *      就是可以把一个复杂的算法，拆分成简单的问题  -> 逐步解决
     *      这下面是逐步推到的 从第一轮===>第二轮===>第三轮
     *
     */
    public static void processSelectSort(int[] arr) {
        /**
         * 原始数组：[101, 34, 119, 1]
         * 第 1 轮排序后：[1, 34, 119, 101]
         * 第 2 轮排序后：[1, 34, 119, 101]
         * 第 3 轮排序后：[1, 34, 101, 119]
         */

        // 假定最小值是arr[0]

        int minValue = arr[0];
        // 假定当前数的索引的最小值就是0
        int minIndex = 0;
        // 让最小的数和下标为1的进行比较
        for (int i = 0 + 1; i < arr.length; i++) {
            if (minValue > arr[i]) {
                // 说明假定的最小值，并不是最小，重置minValue
                minValue = arr[i];
                // 重置minIndex
                minIndex = i;
            }
        }
        if (minIndex != 0) {
            // 将将最小值，放在arr[0]，即交换
            arr[minIndex] = arr[0];
            arr[0] = minValue;
        }


        System.out.println("第一轮后"); // 1, 34, 119, 101
        System.out.println(Arrays.toString(arr));

        // 第2轮
        minIndex = 1;
        minValue = arr[1];
        for (int i = 2; i < arr.length; i++) {
            if (minValue > arr[i]) {
                minIndex = i;
                minValue = arr[i];
            }

        }

        if (minIndex != 1) {
            // 然后将minValue的值和arr[1]对换
            arr[minIndex] = arr[1];
            arr[1] = minValue;
        }
        System.out.println("第二趟之后");
        System.out.println(Arrays.toString(arr));


        // 第3轮
        minIndex = 2;
        minValue = arr[2];
        for (int i = 3; i < arr.length; i++) {
            if (minValue > arr[i]) {
                minIndex = i;
                minValue = arr[i];
            }

        }

        if (minIndex != 2) {
            // 然后将minValue的值和arr[1]对换
            arr[minIndex] = arr[2];
            arr[2] = minValue;
        }
        System.out.println("第三趟之后");
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 2022年4月18日  第二次写选择排序
     * 选择排序的基本思路：就是选择出最小的元素放在首位
     * @param arr
     */
    public static void selectSort1(int[] arr) {
        // 记录数组中的最小值
        int minValue = 0;
        // 记录数组中的最小值的下标
        int minIndex = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            minValue = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            // 小优化
            if (minIndex != i) {
                // !=i就说明被被交换了

                // 交换最小值和当前的第i个元素的位置
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }

        }
    }


    @Test
    public void test02() {
        int[] arr = {101, 99, 34, 1, -1, 90, 123};
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        SelectSort.selectSort1(arr1);
        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("花费" + (end - begin));

    }


}
