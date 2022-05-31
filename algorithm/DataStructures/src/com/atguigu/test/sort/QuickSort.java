package com.atguigu.test.sort;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/29    19:27
 * @Version:1.0‘
 * 快速排序是对冒泡排序的一个改进
 * 快速排序：基本思路的分析,其实就是先选择一个基准值(中间下标对应的值),左边的数值永远比pivot中间值大
 * 右边的值永远比pivot小
 */
public class QuickSort {


    public static void main(String[] args) {
        int arr[] = {-9, 78, 0, 23, -567, 70};
        int arr2[] = {-9, 78, 0, -23, 0, 70};
        //
        quickSort(arr, 0, arr.length - 1);
        System.out.println("array=" + Arrays.toString(arr));
    }

    /**
     *
     * @param arr 待排序的数组
     * @param left 排序的左边的指针
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left+ right) / 2];
        int temp = 0;

        while (left < right) {
            // 先从左边向右边找,直到找打比pivot大的值，同理右边也是一样，然后再进行交换
            while (arr[left] < pivot) {
                left++;
            }
            // 同理右边也是一样,然后再进行交换
            while (arr[right] > pivot) {
                right--;
            }

            //交换两个下标的值
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            if (left >= right) {
                break;
            }

            // 避免死循环,下面也是同样的道理
            if (arr[left] == pivot) {
                right--;
            }

            if (arr[right] == pivot) {
                left++;
            }
        }

        if (left == right) {
            left++;
            right--;
        }

        // 开始左递归和右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
        if (r > left) {
            quickSort(arr, left, r);
        }


    }


}
