package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/17    9:21
 * @Version:1.0
 * 冒泡排序（Bubble Sorting）的基本思想：通过对待排序序列 从前向后（从下标较小的元素开始），
 * 依次比较相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就像水底下的气泡一样逐渐向上冒。
 *
 * 优化点：因为排序过程中，个元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，
 * 因此要在排序过程中设置一个标志判断元素是否进行过交换。从而减少不必要的比较。（该优化点可以在完成基本的冒泡排序之后再做）
 *
 *
 * 冒泡排序的时间复杂度是O(n ^ 2)
 * 个人简而言之：冒泡排序就是相邻的两个元素之间比较大小相互交换值
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
        int arr[] = {1, 3, 2, 4, 5, 6, 7};


        // 临时变量
        int temp = 0;
        // 定义一个标识符，判断是否已经进行优化了，简单的优化
        boolean flag = false;
//        bubbleSort(arr);


        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }

        long begin = System.currentTimeMillis();
        bubbleSort(arr1);

        long last = System.currentTimeMillis();
        // 总耗时8377
        System.out.println("总耗时" + (last - begin));
        System.out.println(Arrays.toString(arr1));


    }

    /**
     * 冒泡排序
     * 第 1 趟排序后的数组
     * [3, -1, 9, -2, 10]
     * 第 2 趟排序后的数组
     * [-1, 3, -2, 9, 10]
     * 第 3 趟排序后的数组
     * [-1, -2, 3, 9, 10]
     * 第 4 趟排序后的数组
     * [-2, -1, 3, 9, 10]
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        boolean flag = false;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }

//            System.out.println("第" + (i + 1) + "次排序" + Arrays.toString(arr));
            if (!flag) {
                // 说明在一次排序中一次都没有进行交换，直接break;
                break;
            } else {
                // 置为false，进行下一次判断，这样能有点优化
                flag = false;
            }
        }
    }


    /**
     * 2022年4月18日   第二次写冒泡排序
     * @param arr
     */
    private static void bubbleSort1(int[] arr) {

        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temp = 0;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次");
            if (!flag) {
                // 说明在排序中一次都没有交换
                break;
            } else {
                // 重置为false
                flag = false;
            }

        }
    }

    @Test
    public void test01() {
        int[] arr = {1, -3, 24, 5, 89, -2};
        int[] arr1 = {1, 2, 3, 5, 6, 90};
        BubbleSort.bubbleSort1(arr1);
        System.out.println(Arrays.toString(arr1));
    }

}
