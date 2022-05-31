package com.atguigu.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/20    19:16
 * @Version:1.0
 *
 * 基数排序(桶排序)
 * 基数排序（radix sort）属于 分配式排序（distribution sort），又称 桶子法（bucket sort 或 bin sort），
 * 顾名思义，它是通过键值的各个位的值，将要排序的 元素分配 至某些「桶」中，达到排序的作用。
 * 基数排序属于 稳定性 的排序，基数排序法是效率高的稳定性排序法。
 *
 * 1.将所有待比较数值 统一为同样的数位长度，数位较短的数 前面补零
 * 2.然后从最低位开始，依次进行一次排序
 * 3.这样从最低位排序一直到最高位排序完成以后，序列就变成了一个有序序列
 *
 *
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
//        processRadixSort(arr);
        System.out.println("===================================================");
        radixSort(arr);
        System.out.println(Arrays.toString(arr));


    }


    /**
     * 对基数排序的测试
     */
    @Test
    public void test01() {
//        int[] arr = {101, 99, 34, 1, -1, 90, 123};
        // 如果是8千万的话80000000 * 11 * 4 /1024 /1024 /1024 = 3.278G
        // 也就是如果有把千万条数据那么要耗费三个多G的内存

        int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            // 生成0~80000的随机数
            arr1[i] = (int) (Math.random() * 80000);
        }
        long begin = System.currentTimeMillis();
        radixSort(arr1);
        long end = System.currentTimeMillis();
        // 花费1628
        System.out.println("花费" + (end - begin));

    }

    /**
     * 写出完整的基数排序
     * @param arr
     */
    private static void radixSort(int[] arr) {

        // 根据下面的代码可以得到基数排序的最终代码
        // 1. 得到数组中最大的位数
        int max = arr[0]; // 假设第一个数就是最大的数
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 得到最大的数是几位数
        int maxLength = (max + "").length();


        // 定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        // 定义一个二维数组,就相当于桶
        int[][] bucket = new int[10][arr.length];

        // 记录每个桶中放入的数据的个数
        int[] bucketElementCounts = new int[10];

        // 这里的for循环的次数就是maxLength的长度,也就是最大的数值的位数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //(针对每个元素的对应的位排序处理),第一次是个位,第二次是十位
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的对应位的值
                int temp = arr[j] / n % 10;
                // 放入到对应的桶中,做完之后bucketElementCounts[temp]++ 进行数量+1
                bucket[temp][bucketElementCounts[temp]++] = arr[j];
            }

            // 按照这个桶的顺序(一维数组的下标依次取出数据,放入到原来数组)
            int index = 0;
            // 遍历每一个桶,有数据就放回原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据,我们才放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    // 循环该桶即第K个桶,放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                // 第一轮之后,需要对每个bucketElementCounts[k]置为0！！！！
                bucketElementCounts[k] = 0;
            }
//            System.out.println("第"+(i+1)+"轮：对个位的排序处理" + Arrays.toString(arr));

        }


    }

    /**
     * 基数排序方法的推导过程
     * @param arr
     */
    private static void processRadixSort(int[] arr) {
        // 第一轮(针对每个元素的个位进行排序处理)

        // 定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        // 说明
        // 1. 二维数组包含10个一维数组
        // 2. 为了在放入数据的时候,数据溢出,则每一个一维数组(桶),大小定义为arr.length
        // 3. 很明显,基数排序是用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中,我们放入了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据的个数
        // 比如bucketElementCounts[0],记录的就是bucket[0]桶中放入数据的个数(这一点很重要)
        int[] bucketElementCounts = new int[10];

        // 第1轮(针对每个元素的个位排序处理)
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位值
            int temp = arr[i] % 10;
            // 放入到对应的桶中,做完之后bucketElementCounts[temp]++ 进行数量+1
            bucket[temp][bucketElementCounts[temp]++] = arr[i];
        }

        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入到原来数组)
        int index = 0;
        // 遍历每一个桶,有数据就放回原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第K个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放到arr
                    arr[index++] = bucket[k][l];
                }
            }
            // 第一轮之后,需要对每个bucketElementCounts[k]置为0！！！！
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮：对个位的排序处理" + Arrays.toString(arr));

        // =========================第二轮=================================
        // 第1轮(针对每个元素的个位排序处理)
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位值
            int temp = arr[i] / 10 % 10; // 748 => 74 % 10 =>4
            // 放入到对应的桶中,做完之后bucketElementCounts[temp]++ 进行数量+1
            bucket[temp][bucketElementCounts[temp]++] = arr[i];
        }

        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入到原来数组)
        index = 0;
        // 遍历每一个桶,有数据就放回原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第K个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮：对个位的排序处理" + Arrays.toString(arr));


        // =========================第三轮=================================
        // 第1轮(针对每个元素的个位排序处理)
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素的个位值
            int temp = arr[i] / 100 % 10; // 748 => 74 % 10 =>4
            // 放入到对应的桶中,做完之后bucketElementCounts[temp]++ 进行数量+1
            bucket[temp][bucketElementCounts[temp]++] = arr[i];
        }

        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入到原来数组)
        index = 0;
        // 遍历每一个桶,有数据就放回原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据,我们才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第K个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮：对个位的排序处理" + Arrays.toString(arr));
    }


}
