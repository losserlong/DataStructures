package com.atguigu.test.sort;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/30    9:18
 * @Version:1.0
 * 归并排序
 */
public class MergeSort1 {


    public void mergeSort(int arr[], int left, int right, int temp[]) {

        if (left < right) {
            int mid = (left + right) / 2;

            // 上面两步是分
            // 左递归
            mergeSort(arr, left, mid, temp);

            // 右递归
            mergeSort(arr, mid, right, temp);

            // 然后调用合方法
            merge(arr, left, mid, right, temp);

        }


    }


    /**
     *
     * @param arr 待排序的数组
     * @param left 左边的指针
     * @param mid 中间的指针
     * @param right 最右边的指针
     * @param temp 临时指针
     */
    public void merge(int arr[], int left, int mid, int right, int temp[]) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 将基准值两边的值放入到数组中
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[t++] = arr[j++];
            } else {
                temp[t++] = arr[i++];
            }
        }

        // 哪一个有剩余，就全部加入到temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 将temp中的值全部加入到
        t = 0;
        int tempLeft = left;
        while (tempLeft < right) {
            arr[tempLeft++] = temp[t++];
        }


    }


}
