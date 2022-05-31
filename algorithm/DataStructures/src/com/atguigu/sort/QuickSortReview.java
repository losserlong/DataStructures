package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/20    9:25
 * @Version:1.0
 * 复习昨天的快排
 * 快速排序的主要思路,就是选取数组中间的值,分为左右两边,
 * 将小于中间值的放在左边,大于中间值的放在右边,然后遍历完一轮之后
 * 使用左递归和右递归
 */
public class QuickSortReview {

    public static void main(String[] args) {
        int arr[] = {-9, 78, 0, 23, -567, 70};
        int arr2[] = {-9, 78, 9999999, 0, 0, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));


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
        quickSort(arr1, 0, arr1.length - 1);
        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("quickSort花费" + (end - begin));

    }


    /**
     * 快速排序方法
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr, int left, int right) {
        // 左边的指针
        int l = left;
        // 右边的指针
        int r = right;
        // 位于中间的值
        int centerValue = arr[(l + r) / 2];
        int temp = 0;
        // 当左指针小于右指针时候,进入循环
        while (l < r) {
            // 直到找到比中间的值大的
            while (arr[l] < centerValue) {
                // 一直循环,直到找到比中间的值大就退出while循环
                l++;
            }
            // 直到找到比中间的值小的
            while (arr[r] > centerValue) {
                r--;
            }
            // 退出循环条件就是和进入循环条件相反
            // 如果l >= r 就表明 左边全是按照小于 centerValue进行排序
            // 右边全是按照大于 centerValue进行排序,就直接退出即可
            if (l >= r) {
                break;
            }

            // 交换两个位置的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;


            // 如果没有这个判定，将会导致，l 永远 小于 r。循环不能退出来的情况
            if (arr[l] == centerValue) {
                r--;
            }
            // 同理
            if (arr[r] == centerValue) {
                l++;
            }
        }

        // 如果l== r 下面就会出现死循环
        // 这种情况就是  l = r 为  (l + r) / 2 的值,也就是中间的值
        if (l == r) {
            l++;
            r--;
        }

        // 开始左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 开始右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }


    /**
     * 2022年4月21日
     * 第二次复习快速排序
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort1(int[] arr, int left, int right) {
        // l是左指针
        int l = left;
        // r是右指针
        int r = right;

        // 定义一个临时变量用于交换两个位置上的值
        int temp = 0;

        // 数组中间的那个值
        int pivot = arr[(left + right) / 2];

        while (l < r) {
            // 直到找到>pivot的数组中的值
            while (arr[l] < pivot) {
                // 左指针后移
                l++;
            }

            // 直到找到 < pivot的
            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }


        }

        // 循环完成之后
        // 判断l==r如果等于就需要将两个指针都要移动
        if (l == r) {
            l++;
            r--;
        }
        // 然后开始左递归和右递归
        if (r > left) {
            quickSort1(arr, left, r);
        }

        if (right > l) {
            quickSort1(arr, right, l);
        }


    }

    @Test
    public void test02() {
        int[] arr = {-9, 78, 0, 23, -567, 70};
 /*       int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }*/
        long begin = System.currentTimeMillis();
        quickSort1(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("quickSort花费" + (end - begin));
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快速排序复习
     * 2022年4月23日
     * 基本思路从中间分为两个组,将小于中间的值的放在左边,大于中间的值放在右边,然后再左递归和右递归
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort2(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];
        int temp = 0;

        while (l < r) {

            // 在pivot之前遍历,直到找到比pivot大的
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot之后进行遍历,直到找到比pivot小的
            while (arr[r] > pivot) {
                r--;
            }

            if (l >= r) {
                // l >= r 就说明
                break;
            }

            // 找到了两个值,然后交换位置
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
                r--;
            }

            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            l--;
            r++;
        }

        // 开始向左递归
        if (r == l) {
            l++;
            r--;
        }

        // 左递归
        if (r > left) {
            quickSort2(arr, left, r);
        }

        // 左递归
        if (l <right) {
            quickSort2(arr, l, right);
        }

    }

}
