package com.atguigu.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/21    19:16
 * @Version:1.0
 * =====================================二分查找=====================================
 * 注意：二分查找的前提是该数组是有序的,从小到大或者从大到小
 *
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 89, 100, 1000, 1000, 1234};
//        int result = binarySearchNoRecursion(arr, 0, arr.length - 1, 89);
//        System.out.println("result = " + result);
        int i = binarySearch(arr, 0, arr.length - 1, 89);
        System.out.println(i);


    }

    @Test
    public void testBinarySearchPlus() {
        int[] arr = {1, 8, 89, 1000, 1000, 1000, 1234};

        List<Integer> list = binarySearchPlus(arr, 0, arr.length - 1, 1000);
        System.out.println(list);

    }


    //完成一个课后思考题:
    /*
     *课后思考题:{1,8，10，89，1000，1000，1234}当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     * 也就是返回所有的target值的索引下标
     *
     * 思路分析
     * 1. 在找到mid索引值,不要马上返回
     * 2. 向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
     * 3. 向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
     * 4. 最后将结果返回
     *
     */
    public static List<Integer> binarySearchPlus(int[] arr, int left, int right, int target) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];


        List<Integer> list = new ArrayList<>();

        if (left > right) {
            return list;
        }
        if (target > midValue) {
            return binarySearchPlus(arr, mid + 1, right, target);
        } else if (target < midValue) {
            return binarySearchPlus(arr, left, mid - 1, target);
        } else {
            /**
             *  思路分析
             *  1. 在找到mid索引值,不要马上返回
             *  2. 向mid索引值的左边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
             *  3. 向mid索引值的右边扫描，将所有满足1000，的元素的下标，加入到集合ArrayList
             *  4. 最后将结果返回
             */

            // 向左边扫描,从mid-1开始扫描(也就是以mid为中心开始向两边再寻找target的下标值)
            int temp = mid - 1;

            // 将mid放入到list中
            list.add(mid);

            while (true) {
                // 向左遍历,这里不要用while因为二分是有序如果下一个不是target,它的后面(前面)就不可能是target了
                if (temp < 0 || arr[temp] != target) {
                    break;
                }

                // 否则就将temp放进入
                if (arr[temp] == target) {
                    list.add(temp);
                }
                // temp左移
                temp--;

            }

            // 向右边扫描
            temp = mid + 1;
            while (true) {
                // 当temp大于数组的长度-1或者arr[temp] !=target就退出循环
                // 这里不要用while因为二分是有序如果下一个不是target,它的后面(前面)就不可能是target了
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }
                // 否则就将temp放进入
                if (arr[temp] == target) {
                    list.add(temp);
                }
                // temp左移
                temp++;

            }

        }


        return list;
    }


    /**
     * 递归版
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int target) {
        System.out.println("查找次数");

        int mid = (left + right) / 2;


        // 当 left > right 时候,说明递归整个数组,但是没有找到

        if (left > right) {
            return -1;
        }

        // 中间位置的值
        int midValue = arr[mid];
        if (target > midValue) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, target);

        } else if (target < midValue) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, target);

        } else {
            return mid;
        }


//        return -1;

    }


    /**
     * 二分查找非递归版本
     * @param arr
     * @param left 左边索引
     * @param right 右边索引
     * @param target 目标值
     * @return
     */
    public static int binarySearchNoRecursion(int[] arr, int left, int right, int target) {

        while (left <= right) {
            // 中间的索引
                int mid = (left + right) / 2;

            // 中间位置的值
            int midValue = arr[mid];
            if (target > midValue) {
                mid = (mid + 1 + right) / 2;
            } else if (target < midValue) {
                mid = (left + mid - 1) / 2;
            } else {
                return mid;
            }
        }

        return -1;
    }


    @Test
    public void test01(){
        int[] nums={1,2,3,4,5,6,7,8,9};
        int search = search(nums, 4);
        System.out.println(search);


    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public int search (int[] nums, int target) {
        // write code here

        int left=0;
        int right=nums.length-1;
        if(nums.length==0){
            return -1;
        }
        if(target>nums[right]||target<nums[0]){
            return -1;
        }

        while(left<=right){
            int mid=(right+left)/2;
            if(target>nums[mid]){
                left=mid+1    ;
            }else if(target<nums[mid]){
                right=mid-1;
            }else{
                return mid;
            }

        }


        return -1;
    }
}
