package com.atguigu.search;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/23    19:21
 * @Version:1.0
 * ===========================================插值查找算法================================================
 * 插值查找和二分查找相比mid变了,变成了下面的
 *int mid = low + (high - low) * (key -arr[low]) / (arr[high] - arr[low])
 *
 *=========================================== 注意事项  ===================================================
 * 对于 数据量较大，关键字分布比较均匀(均匀指跨度比较小)的查找表来说，采用 插值查找，速度较快
 * 关键字分布 不均匀 的情况下，该方法不一定比折半查找要好
 *
 *
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, -1, 8);
        System.out.println("index = " + index);

    }

    /**
     * 插值查找算法
     * 插值查找算法也要求数组是有序的
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找的目标值
     * @return 如果找到就返回对应的下标, 没有找到就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("查找次数 ");


        // 注意：findVal > arr[arr.length - 1] || findVal < arr[0]
        // 是必须需要的否则可能下main相乘会出现数组下标越界
        if (left > right || findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return -1;
        }

        // 求出mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            // 如果查找的值比中间的值大,要向右边进行查找
            // 向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 如果查找的值比中间的值小,要向左边进行查找
            // 向左边递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

//        return -1;
    }

    /**
     * 再写一次简直查找算法
     * 2022年4月24日
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static int insertValueSearch1(int[] arr, int left, int right, int findVal) {

        if (findVal > arr[arr.length - 1] || findVal < arr[0]) {
            return -1;
        }

        int mid = left + (right - left) * (right - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }



    }


}
