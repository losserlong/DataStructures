package com.atguigu.tree.threadedbinarytree;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/01    17:07
 * @Version:1.0
 * ==================================堆排序复习===========================================
 * ============================堆排序的基本思路============================================
 * 首先堆分为大顶堆和小顶堆
 * 大顶堆就是：当前结点总是大于它的左右孩子结点(左右孩子的大小无关)
 * 大顶堆就是：当前结点总是小于它的左右孩子结点(左右孩子的大小无关)
 * 1. 将待排序序列构造成一个大顶堆
 * 注意这里用的是数组，而不是二叉树
 * 2. 此时最大值就在堆顶的根节点(这样就构建好了)
 * 3. 然后将其与尾部元素进行交换，末尾就是最大值了
 * 4. 然后将剩余的 n - 1 个元素重新构造成一个堆，这样就会得到 n - 1 个元素的小值。
 * 如此反复，便能得到一个有序序列。
 *
 */
public class HeapSort1 {


    public static void main(String[] args) {

        // 将数组进行升序排列,-1,90,89,56,-999
        int arr[] = {4, 6, 8, 5, 9};

        // 编写一个堆排序的方法
        heapSort(arr);
//        System.out.println(Arrays.toString(arr));


    }


    public static void heapSort(int[] arr) {
        // 堆排序！！！
        System.out.println("堆排序");

        // 从第一个非叶子节点开始进行调整成大顶堆，自底向上
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 1. 将堆顶元素和
         * 2. 重新调整结构，使其满足堆的定义，然后继续交换堆顶元素和当前元素，反复操作 + 交换步骤
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
     *
     * @param arr 待调整的数组
     * @param i 非叶子结点在数组中的位置
     * @param length 对多少个元素进行调整
     * int arr= {4,6,8,5,9}  => {4,9,8,5,6}
     *
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 除去当前元素的值,保存在临时变量
        int temp = arr[i];

        // 开始调整
        // i的左子节点是i * 2 + 1 ，右子节点是 i * 2 + 2
        // 第一个k = i * 2 + 1 ，就是以i为非叶子节点的左子节点
        // k = k * 2 + 1表示下一次以k为非叶子节点的左子节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 如果左子节点小于右子节点,就将下标指向右子结点
                k++;
            }
            // 到这里说明,左子节点或者右子结点的值比当前结点大,进行交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                // 将k的下标赋值给i，以便后面进行交换数组中的值
                i = k;
            } else {
                break;
            }
        }

        // 然后将第 i 和 k个位置的值进行交换
        // 将之前存储大的值用小的temp覆盖
        arr[i] = temp;


    }


}
