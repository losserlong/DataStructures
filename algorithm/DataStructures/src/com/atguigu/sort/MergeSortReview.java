package com.atguigu.sort;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/21    13:18
 * @Version:1.0
 * 归并排序算法
 * 归并排序是分治算法的一个很好的体现,先将数字分开排序,
 * 保证每个分组中的数字是有序的,然后在合并
 */
public class MergeSortReview {


    public static void main(String[] args) {


    }


    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 一直在动态变化的
            int mid = (left + right) / 2;
            // 左递归
            mergeSort(arr, left, mid, temp);
            // 左递归
            mergeSort(arr, mid + 1, right, temp);

            // 合并
            merge(arr, left, mid, right, temp);

        }


    }


    /**
     * 归并排序
     * @param arr 要排序的数组
     * @param left 左边的索引
     * @param mid 中间的索引
     * @param right  数组的最右边的索引
     * @param temp 一个临时数组,用于临时存储原数组中的值
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // ========================分====================================

        // 定义临时变量,用来遍历数组比较
        int l = left;
        int r = mid + 1;
        // 定义temp数组的索引
        int t = 0;


        // 其实这下面的两个数组并不是两个数组而是将它们看作了两个数组
        // 1，按照规则将两个数组合并到temp中,就是按照小到大按规则加入到temp中
        // 然后遍历完成之后就判断看哪个数组有剩余,有的话就全部都加入到temp数组中
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }

        // 2. 看哪个数组有剩余,有的话就全部加入到temp数组中
        while (l <= mid) {
            temp[t++] = arr[l];
        }
        while (r <= right) {
            temp[t++] = arr[r];
        }

        // 3. 将temp数组中的数据重新拷贝到arr中
        // 注意不是每一次都拷贝的是全部的数据,因为不一定是最后一次拷贝
        // =====================合并==================
        //
        /*
         * 对于 8,4,5,7,1,3,6,2 这个数组,
         * 从栈顶开始合并：8,4 | 5,7       1,3 | 6,2
         * 先左递归的话：
         * 第一次：8,4 合并：tempL=0，right=1
         * 第二次：5,7 合并：tempL=2，right=3
         * 第三次：4,8 | 5，7 进行合并，tempL=0，right=3
         * 直到左递归完成，得到左侧一个有序的序列：4,5,7,8 然后开始右递归
         * 最后回到栈底分解成两个数组的地方，将两个数组合并成一个有序数组
         */
        int tempLeft = left;
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }


    }


}
