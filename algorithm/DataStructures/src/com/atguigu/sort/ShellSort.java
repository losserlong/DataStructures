package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/18    9:50
 * @Version:1.0
 *
 * 简单介绍
 * 希尔排序是希尔（Donald Shell）于 1959 年提出的一种排序算法。
 *  希尔排序也是一种 插入排序，它是简单插入排序经过改进后的一个 更高效的版本，也称为 缩小增量排序。
 *
 *  基本思想
 *  希尔排序把记录按 下标的一定增量分组，对每组使用 直接插入排序算法 排序，随着 增量逐渐减少，
 *  每组包含的关键词越来越多（要排序的数），当增量减至 1 时，整个文件被分成一组，算法遍终止。
 *
 * 光看上面的描述，对于首次接触的人来说，不知道是啥意思，认真思考下面的说明：
 *
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        processShellSort(arr);
//        shellSort(arr);
    }


    @Test
    public void test01() {
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        ShellSort.shellSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("花费时间" + (end - begin));
    }

    @Test
    public void test02() {
        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        ShellSort.shellSort1(arr1);
        long end = System.currentTimeMillis();
        System.out.println("花费时间" + (end - begin));
        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 完整版的shellSort
     * 交换法shell排序
     * 下面那个是逐步分析的方法
     * @param arr
     */
    private static void shellSort(int[] arr) {
        // 根据前面的逐步分析，使用循环处理，每次除以二
        // 第一次是5组
        // 第二次是2组
        // 第三次是1组
        int temp = 0;
        // gap也是步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中的所有元素(共gap组，每组个元素),步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前的元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }
                }

            }
//            System.out.println("希尔排序第" + (++count) + "轮是=" + Arrays.toString(arr));
        }
    }


    @Test
    public void test03() {
        int[] arr1 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 8000000);
        }
        long begin = System.currentTimeMillis();
        ShellSort.shellSortPlus(arr1);
        long end = System.currentTimeMillis();
        System.out.println("shellSortPlus花费时间" + (end - begin));
//        System.out.println(Arrays.toString(arr1));
    }

    /**
     * 插入排序改进版：将交换式改成移位式
     * @param arr
     */
    public static void shellSortPlus(int arr[]) {
        // 根据前面的逐步分析，使用循环处理，每次除以二
        // 第一次是5组
        // 第二次是2组
        // 第三次是1组
        int temp = 0;
        // gap也是步长,这里的步长从之前的1(insertSort)换成了gap
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;

                    }
                    // 当退出while之后,temp找到插入位置
                    arr[j] = temp;

                }


            }
//            System.out.println("希尔排序第" + (++count) + "轮是=" + Arrays.toString(arr));
        }
    }


    /**
     * 第二次希尔排序
     * 2022年4月19日
     * @param arr
     */
    private static void shellSort1(int[] arr) {

        int temp = 0;

        // gap是分组，分组是每次都除以2
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {

                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前的元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }
                }

            }


        }


    }


    /**
     * 使用逐步编写的方式编写希尔排序
     * @param arr
     */
    private static void processShellSort(int[] arr) {

        // 顶一个临时变量
        int temp = 0;
        // shellSort的第一轮排序
        // 因为第1轮排序，是将10个数据分成了5组(length/2),增量也是5,需要对5组进行排序
        // 8, 9, 1, 7, 2, 3, 5, 4, 6, 0
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中的所有元素(共5组，每组2个元素),步长为5
            // 第 1 组：[8,3] , 分别对应原始数组的下标 0,5
            // 第 2 组：[9,5] , 分别对应原始数组的下标 1,6
            // ...
            // 内层循环对 每一组 进行直接排序操作
            // i = 5 ：j = 0, j-=5 = 0 - 5 = -5,跳出循环，这是对第 1 组进行插入排序
            // i = 6 ：j = 1, j-=5 = 0 - 1 = -1,跳出循环，这是对第 2 组进行插入排序
            // i = 9 ：j = 4, j-=5 = 0 - 4 = -4,跳出循环，这是对第 3 组进行插入排序
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前的元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;

                }
            }
        }
        System.out.println("希尔排序第" + (1) + "轮后=" + Arrays.toString(arr));


        // 希尔排序的第2轮排序
        // 因为第2轮排序，是将10个数据分成了 5/2 = 2 组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中的所有元素(共5组，每组2个元素),步长为5
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前的元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第" + (2) + "轮后=" + Arrays.toString(arr));
        // 希尔排序的第3轮排序
        // 因为第3轮排序，是将10个数据分成了 2/2 =  1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中的所有元素(共5组，每组2个元素),步长为5
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前的元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第" + (3) + "轮后=" + Arrays.toString(arr));


    }


}
