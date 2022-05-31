package com.atguigu.tree.threadedbinarytree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/30    17:06
 * @Version:1.0
 *
 * ===========================堆排序=============================================================
 * ==========================基本介绍=================================================
 * 堆排序是利用 堆 这种 数据结构 而设计的一种排序算法，
 * 它是一种选择排序，最坏 、最好、平均时间复杂度均为 O(nlogn)，它是不稳定排序。
 * 堆是具有以下性质的完全二叉树：
 * 大顶堆：每个节点的值都 大于或等于 其左右孩子节点的值
 * 注：没有要求左右值的大小关系
 * 小顶堆：每个节点的值都 小于或等于 其左右孩子节点的值
 * =========================排序说明==================================================
 * 升序：一般采用大顶堆
 * 降序：一般采用小顶堆
 * ========================基本思想===================================================
 * 1. 将待排序序列构造成一个大顶堆
 * 2. 注意：这里使用的是数组，而不是一颗二叉树
 * 3. 此时：整个序列的 最大值就是堆顶的根节点
 * 4. 将其 与末尾元素进行交换，此时末尾就是最大值
 * 5. 然后将剩余 n-1 个元素重新构造成一个堆，这样 就会得到 n 个元素的次小值。如此反复，便能的得到一个有序序列。
 * ===============================注意一定要画图===================================
 *
 *
 */
public class HeapSort {

    public static void main(String[] args) {

        // 将数组进行升序排列,-1,90,89,56,-999
        int arr[] = {4, 6, 8, 5, 9};

        // 编写一个堆排序的方法
        headSort(arr);



    }


    @Test
    public void test01() {

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        HeapSort.headSort(arr);
//        Arrays.sort(arr);
        long end = System.currentTimeMillis();

        System.out.println("结束时间" + (end - start));


    }

    /**
     * 编写一个堆排序的放
     * @param arr 待排序的数组
     */
    private static void headSort(int[] arr) {
        System.out.println("堆排序！");

/*        // 分布完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第一次调整过后~~~~" + Arrays.toString(arr));

        adjustHeap(arr, 0, arr.length);
        System.out.println("第二次调整过后~~~~" + Arrays.toString(arr));*/

        // 从第一个非叶子节点开始调整，自底向上
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);

        }
        /**
         * 1. 将堆顶元素和末尾元素交换，将最大的元素"沉"到数组末端
         * 2. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前元素，反复操作调整 + 交换步骤，直到整个序列有序
         */

        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 每次都要调整
            adjustHeap(arr, 0, j);
        }
        System.out.println("调整过后~~~~" + Arrays.toString(arr));
    }


    /**
     * 功能：完成将以 i 对应的非叶子节点的树调整成大顶堆
     * 举例 int arr[] = {4, 6, 8, 5, 9}; ==> i = 1 adjustHeap ==> 得到{4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0   ==> 得到 {4, 9, 8, 5, 6}  ==>{9, 4, 8, 5, 6}
     * 将一个数组(也就是对应着一棵二叉树),调整成 大顶堆
     * 大顶堆就是当前节点的值总是大于左右两个孩子的值
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的个数
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int arr[], int i, int length) {

        //  =====================注意自己画示意图演示======================

        // 先去除当前元素的值，保存在临时变量
        int temp = arr[i];

        // 开始调整
        // i 对应的左子节点就是 i * 2 + 1
        // 第一个 k = i * 2 + 1 就是以i为非叶子节点的左子结点
        //  而后面的步长 k = k * 2 + 1 就表示的是以k为非叶子结点的左子结点 (这些要搞清楚)
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 左子结点的小于右子结点的值
                // 就让k的下标到右子节点
                k++;
            }
            if (arr[k] > temp) {
                // 说明左子结点或者右子节点大于父节点
                // 把较大的值赋值给当前结点
                arr[i] = arr[k];
                i = k; // ！！！！将 i 指向 k 继续循环比较

            } else {
                // 小于就不用管,直接退出循环
                break;
            }
        }

        // 当for 循环结束后，我们已经将以i 为父结点的树的最大值放在了最顶上(局部)
        // 其实就是交换位置
        // 因为在上面将数组变成了 4 9 8 5 9   ===> 4 9 8 5 6就是将6换到9的位置
        arr[i] = temp;  // 将temp的值放到调整后的位置


    }


}
