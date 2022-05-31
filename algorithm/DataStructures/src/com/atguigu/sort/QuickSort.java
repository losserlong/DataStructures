package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/19    12:58
 * @Version:1.0
 * ==========================================快速排序============================================================
 * 快速排序的基本介绍：
 * 通过一趟排序将要排序的数据 分割成独立的两个部分，一部分的所有数据都比另外一部分的所有数据都要小。
 * 然后再按如上的方法对这两部分数据分别进行快速排序，排序过程可以递归进行，以此达到整个数据变成有序序列。
 * 比如如下的示意图：
 *
 */
public class QuickSort {

    public static void main(String[] args) {
        int arr[] = {-9, 78, 0, 23, -567, 70};
        int arr2[] = {-9, 78, 0, -23, 0, 70};
        //
        quickSort(arr, 0, arr.length - 1);
        System.out.println("array=" + Arrays.toString(arr));


    }

    @Test
    public void test01() {
        int[] arr = {101, 99, 34, 1, -1, 90, 123};
        int[] arr1 = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000000);
        }
        long begin = System.currentTimeMillis();
        quickSort(arr1, 0, arr1.length - 1);

        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("quickSort花费" + (end - begin));



    }

    @Test
    public void test02() {
        int arr2[] = {-9, 78, 0, -23, 0, 70};
        //
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("array2=" + Arrays.toString(arr2));
    }


    /**
     * 快速排序
     * @param arr 要排序的数组
     * @param left 左边的索引
     * @param right 右边的索引
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标

        // 定义用于交换的临时变量
        int temp = 0;

        // pivot 中轴
        // 中间那个值
        int pivot = arr[(left + right) / 2];
        // -9, 78, 0, 23, -567, 70
        // while循环将小于pivot的放在它的左边，大于它的放在右边
        while (l < r) {
            // 在pivot的左边一直找，直到找到大于等于pivot的值,才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找，直到找到小于等于pivot的值,才退出
            while (arr[r] > pivot) {
                r--;
            }

            // 如果l >= r 说明pivot的左右两的值,已经按照左边全部是
            // 小于等于pivot的值,右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }

            // -9, 78, 0, 23, -567, 70   ====>  -9 -567 0 23 78 70

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;


            /*
                如果交换完了之后,发现这个arr[l] == pivot值 相等 就让 r -- , 前移一步
                int arr2[] = {-9, 78, 0, -23, 0, 70};,交换完成之后为 {-9, 0, 0, -23, 78, 70}
                l=1 , arr[l]=0
                r=4 , arr[r]=78


             */
            if (arr[l] == pivot) {
                r--;
            }

            // 如果交换完了之后,发现这个arr[r] == pivot值 相等 就让 l ++ , 后移一步
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r , 必须 l++ , r-- , 否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }


    }
}
