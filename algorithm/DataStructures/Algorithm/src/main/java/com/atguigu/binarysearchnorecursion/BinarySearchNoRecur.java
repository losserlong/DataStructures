package com.atguigu.binarysearchnorecursion;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/12    14:42
 * @Version:1.0
 * 非递归二分查找算法
 *
 *
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 8, 9, 10, 21, 52, 834, 3444};
        int index = binarySearch(arr, 1);
        System.out.println("index = " + index);


    }


    /**
     * 二分查找的非递归实现
     * @param target 表示待要查找的数字
     * @param arr 传入数组
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        // 找不到返回-1
        return -1;

    }


}
