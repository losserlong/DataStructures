package com.atguigu.test.sort;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/29    20:53
 * @Version:1.0
 * 归并排序，
 * 归并排序是利用归并的思想，是经典的分治策略，
 * 先分开，然后再递归求解
 * 治，将分开得到的各个答案修补在一起
 *
 */
public class MergeSort {


    /**
     * 分解
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {
            int mid = (left + right) / 2;

            // 向左边递归
            mergeSort(arr, left, mid, temp);

            // 像右递归
            mergeSort(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);
        }


    }


    /**
     * 这是按照最后一次来的
     * 合并算法
     * @param arr 待排序的数组
     * @param left 左指针
     * @param mid 中间指针
     * @param right 最右边指针
     * @param temp 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化左边序列的初始索引
        int j = mid + 1; // 初始化j右边有序序列的初始化索引
        int t = 0; // 指向temp数组的当前索引

        // ==================================第一步===================================
        // 先将两个数组中的值填充到temp中
        // 直到两边的有序序列,有一边处理完为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i];
            } else {
                temp[t++] = arr[j];
            }
        }

        // =============================第二部,如果一个数组中还有多余的就全部都加入到temp数组
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // ===============================第三步,将temp中的数据加入到arr中
        // 注意不是每次都要拷贝全部数据,只有最后一次才是
        // ① 8,4合并：tempLeft=0,right=1
        // ② 5,7合并：tempLeft=2,right=3
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + "; right=" + right);

        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];

        }


    }


}
