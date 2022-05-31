package com.atguigu.test.sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/30    9:51
 * @Version:1.0
 * 快速排序
 */
public class quickSort1 {


    public static void main(String[] args) {


        int[] arr = {1, -21, 5, 75, 187, 2, 1, -12, 99, 1, 1, 1, 1, 1, 1};
        quickSort(0, arr.length - 1, arr);
        System.out.println(Arrays.toString(arr));

    }


    /**
     *
     * @param left 最左边的位置
     * @param right 最右边的位置
     * @param arr 待排序的数组
     */
    public static void quickSort(int left, int right, int[] arr) {
        int l = left;
        int r = right;

        int midVal = arr[(left + right) / 2];

        while (left < right) {
            // 左边找到比midVal大的
            while (arr[left] < midVal) {
                left++;
            }

            // 找到比midVal小的
            while (arr[right] > midVal) {
                right--;
            }

            // 交换
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            if (left >= right) {
                break;
            }

            if (arr[left] == midVal) {
                right--;
            }
            if (arr[right] == midVal) {
                left++;
            }
        }

        if (left == right) {
            left++;
            right--;
        }

        if (l < right) {
            quickSort(l, right, arr);
        }
        if (left < r) {
            quickSort(left, r, arr);
        }


    }


}
