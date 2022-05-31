package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/20    12:08
 * @Version:1.0
 * 归并排序
 * 归并排序（merge sort）是利用 归并 的思想实现的排序方法，该算法采用经典的 分治（divide-and-conquer）策略 ：
 *
 * 分（divide）：将问题分成一些小的问题，然后递归求解
 * 治（conquer）：将分的阶段得到的各答案「修补」在一起
 * 即：分而治之
 *
 * 归并排序(MERGE-SORT）是利用归并的思想实现的排序方法，
 * 该算法采用经典的分治(divide-and-conquer〉策略（分治法将问题分(divide)成一些小的问题然后递归求解，
 * 而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

    }


    @Test
    public void test01() {
        int[] arr = {101, 99, 34, 1, -1, 90, 123};
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr1.length];

        long begin = System.currentTimeMillis();

        mergeSort(arr1, 0, arr1.length - 1, temp);

        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("MergeSort花费" + (end - begin));


    }

    /**
     * 分 + 合方法
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间的索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            // 到合并
            merge(arr, left, mid, right, temp);
        }

    }


    /**
     *
     * 合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序列的初始索引
     * @param mid 中间索引
     * @param right 最右边的索引
     * @param temp 做中转的临时数组b
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i,左边有序序列的初始索引
        int j = mid + 1; // 初始化j,右边有序序列的初始化索引
        int t = 0; // 指向temp数组的当前的索引

        // ==========================第一步===========================
        // 两个有序序列
        // 先把左右两边(有序)数据按照规则填充到temp数组,就是比较左右两边的数值,然后加入到temp数组中
        // 直到左右两边的有序序列,有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                // 这里表示左边的有序序列的当前元素小于右边有序序列的当前元素
                // 就将左边的有序序列的当前元素加入到temp数组中
                // 做完之后t和i都要后移
                temp[t++] = arr[i++];
            } else {
                // 反之将右边的当前元素移动到temp数组中
                temp[t++] = arr[j++];
            }
        }


        //=========================第二步==================================
        // 把有剩余的数组的值全部加入到temp数组中
        while (i <= mid) {
            // 左边的有序序列还有剩余的,就全部填充到temp中
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            // 右边的有序序列还有剩余的,就全部填充到temp中
            temp[t++] = arr[j++];
        }


        //=========================第三步==================================
        // 将temp数组中的元素重新拷贝到arr
        // 注意,并不是每一次都拷贝所有数据
        /*
         * 对于 8,4,5,7,1,3,6,2 这个数组,
         * 从栈顶开始合并：8,4 | 5,7       1,3 | 6,2
         * 先左递归的话：
         * 第一次：8,4 合并：tempL=0，right=1
         * 第二次：5,7 合并：tempL=2，right=3
         * 第三次：4,8 | 5，7 进行合并，tempL=0，right=3
         * 直到左递归完成，得到左侧一个有序的序列：4,5,7,8 然后开始右递归
         * 最后回到栈底分解成两个数组的地方，将两个数组合并成一个有序数组
         */
        t = 0;
        int tempLeft = left;
        // 第一次合并 tempLeft=0 , right = 1 // 第二次 tempLeft = 2 right = 3
        //  第三次tempLeft = 0 ,   right = 3
        // 最后一次tempLeft = 0 right = 7
        System.out.println("tempLeft=" + tempLeft + "; right=" + right);
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }


    }


}
