package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/22    21:41
 * @Version:1.0
 * 复习二分查找
 *
 */
public class BinarySearchReview {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 54, 54};

    /*    int result = binarySearch(arr, 0, arr.length - 1, -3);
        System.out.println(result);*/
        int[] arr1 = new int[100];
        for (int i = 0; i < 100; i++) {
            arr1[i] = i + 1;
        }

        List<Integer> list = binarySearchPlus(arr1, 0, arr1.length - 1, 33);
        list.stream().forEach(System.out::print);
    }


    /**
     *
     * 二分查找
     * @param arr 带查找的数组
     * @param left  数组左边指针
     * @param target 要查找的值
     */
    private static int binarySearch(int[] arr, int left, int right, int target) {


        int mid = (left + right) / 2;

        // 说明走过头了
        if (left > right) {
            return -1;
        }

        if (target > arr[mid]) {
            binarySearch(arr, mid + 1, right, target);
        } else if (target < arr[mid]) {
            binarySearch(arr, left, mid - 1, target);
        } else {

            return mid;
        }

        return -1;


    }


    /**
     * 课后思考题:{1,8，10，89，1000，1000，1234}当一个有序数组中，
     *  有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     *  也就是返回所有的target值的索引下标
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    private static List<Integer> binarySearchPlus(int[] arr, int left, int right, int target) {

        System.out.println("查询次数");

        int mid = (left + right) / 2;

        List<Integer> list = new ArrayList<Integer>();

        // 说明走过头了
        if (left > right) {
            return list;
        }

        if (target > arr[mid]) {
          return   binarySearchPlus(arr, mid + 1, right, target);
        } else if (target < arr[mid]) {
          return   binarySearchPlus(arr, left, mid - 1, target);
        } else {
            // 这里是arr[mid]=target的情况
            list.add(mid);

            // 看mid的前面和后面是不是target
            int temp = mid - 1;

            // 向左边扫描
            while (true) {
                if (temp < 0 || arr[temp] != target) {
                    break;
                }

                if (arr[temp] == target) {
                    list.add(temp);
                }
                // temp向前移动
                temp--;
            }

            temp = mid + 1;
            // 向右边进行扫描
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }

                if (arr[temp] == target) {
                    list.add(temp);
                }
                // temp向前移动
                temp++;
            }
        }
        return list;
    }


}
