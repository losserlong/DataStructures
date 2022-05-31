package com.atguigu.search;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/21    17:24
 * @Version:1.0
 * =================================线性查找=================================
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, -1);
        System.out.println("index = " + index);
    }

    /**
     * 线性查找,其实就是for循环遍历
     * 这里我们查找的是有一个满足条件就返回
     * @param arr
     * @return
     */
    private static int seqSearch(int[] arr, int value) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;

    }

}
