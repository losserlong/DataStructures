package day01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/15    19:26
 * @Version:1.0
 * 冒泡排序算法
 *
 */
public class Code01_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 规定在哪个范围进行排序
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }

            }

        }


    }

    /**
     *     交换arri和j位置上的值
     *     注意这里用异或的话，数组i位置不能等于j位置，不然就会将i位置的值设置为0了(地址值要不一样才行)
     *
      */
    private static void swap(int[] arr, int i, int j) {
        // 这里用的异或运算(也相当于无进位相加)
        // 0^N=N  N^N=0
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void test01() {
        int[] arr = {1, 23, 45, 7687, 9323, 43, 6, 7, 2, 4, 67, 8, 92, 24, 5, 7, 8};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i + "   ");
        }


    }


}
