package com.atguigu.search;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/23    21:07
 * @Version:1.0
 * ==================================斐波那契查找==========================================
 *
 * 斐波那契数列性质 F[k] = F[k-1] + F[k-2]
 *  由以上性质可以得到上图的组合: F[k]-1 = (F[k -1] -1) + (F[k-2] -1) + 1
 *  上图的公式 F[k - 1] - 1 + 中间 mid 占用 1 个位置 + F[k - 2] -1 + 1 个位置 就是这个数组的所有元素个数。
 * 那么说明：只要顺序表的长度为 F[k]-1，则可以将该表分成 长度为 F[k-1]-1 和 F[k-2]-1 两段，如上图所示
 * 那么中间值则为：mid = low + F[k-1]-1
 *
 *
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int result = fibSearch(arr, 1000);
        System.out.println("result = " + result);
    }


    /**
     * 使用非递归的方式编写斐波那契查找算法
     * 编写斐波那契查找算法
     * @param arr 在那个数组中进行查找
     * @param key 待查找的值
     * @return
     */
    private static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        // 其实就是mid = low + F[k-1]-1
        int k = 0; // 表示要去的斐波那契分割数值的下标
        int mid = 0; // 存放我们的中间值

        // 获取到斐波那契数列
        int[] f = fib();

        //
        while (high > f[k] - 1) {
            k++;
        }

        // 因为f[k]值可能大于a的长度,因此我们需要使用Arrays类,构造一个新的数组,并指向a[]
        // 不足的部分会用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);

        // 实际上需要使用a数组的最后的数填充temp
        // 举例  一开始a[] ={1, 8, 10, 89, 1000, 1234}  而temp大于a的长度就会变成
        //                           ||
        //                           ||
        //                           \/
        //  temp[]={1, 8, 10, 89, 1000, 1234,0,0,0}  用a最后的数变成 ===> {1, 8, 10, 89, 1000, 1234,1234,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用while来循环处理,找到我们这个key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                // 我们应该继续向数组的前面查找(左边进行查找)
                high = mid - 1;
                // 为什么是k--；
                // 1.  全部元素 = 前面的元素 + 后面的元素
                // 2.  f[k] = f[k - 1] + f[k - 2]
                // 3.  因为 前面有 f[k - 1]个元素,所以可以继续拆分f[k - 1] =f[k - 2] + f[k - 3]
                // 即在f[k - 1]的前面继续查找就是k--了
                k--;
            } else if (key > temp[mid]) { // 说明应该向后买你查找
                low = mid + 1;
                // 1.  全部元素 = 前面的元素 + 后面的元素
                // 2.  f[k] = f[k - 1] + f[k - 2]
                // 3.  因为 后面有 f[k - 2]个元素,所以可以继续拆分f[k - 2] =f[k - 3] + f[k - 4]
                // 即在f[k - 1]的后面继续查找就是k-=2了
                k -= 2;
            } else {

                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }

            }
        }


        // 没有找到返回-1
        return -1;
    }


    /**
     * 因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列,因此我们需要先获取到一个斐波那契数列
     * 这里使用非递归方法获取斐波那契数列
     * @param
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

}
