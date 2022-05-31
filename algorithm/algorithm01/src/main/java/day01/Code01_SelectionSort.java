package day01;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/15    19:06
 * @Version:1.0
 * 选择排序算法
 *  选择排序时间复杂度O(N^2)
 *  空间复杂O(1)，因为只创建了 int minIndex = i;都进行释放了
 *
 *
 *
 */
public class Code01_SelectionSort {


    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) { // i~ 0到n-1
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {  // i~ N-1上寻找最小值的下标
                // 将最小值的下标放在minIndex
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 将i位置的值和j位置的值进行对换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void test01(){
        int[] arr={1,23,45,7687,9323,43,6,7,2,4,67,8,92,24,5,7,8};
        Code01_SelectionSort.selectionSort(arr);
//        Arrays.sort(arr);
        for (int i:arr

             ) {
            System.out.print(i+"  ");

        }
    }

}
