package com.atguigu.divideandconquer;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/12    15:09
 * @Version:1.0
 * ============================分治算法之汉诺塔问题===========================
 * 这里解决汉诺塔问题的基本思路
 * 假设有n个盘子, 都放在A这个区域,还有B、C两个区域
 * 要将所有的盘子都从A移动到C,同时大的盘子不能放在小的盘子上面
 * 当n=1的时候,直接将A移动到C即可
 * 当n>=2的时候,可以看成两个部分：
 * 将上面的n-1个盘子放在B,然后再将最大的盘子从A移动到C
 *
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoitower1(5, 'a', 'b', 'c');


    }

    static int count = 0;

    /**
     * 汉诺塔的移动方法
     * 使用分治算法
     * @param num 盘子的数量
     * @param a 代表A盘子
     * @param b 代表B盘子
     * @param c 代表C盘子
     */
    public static void hanoitower(int num, char a, char b, char c) {

        count++;
        System.out.println("count = " + count);
        // 如果只有一个盘子
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            // 当n>=2的时候,分成最上面的n-1个盘和最下面的一个盘
            // 先把A->B
            // 1. 先把上面的n - 1 个盘从A移动到B, C在中间是因为移动过程中会利用到C
            hanoitower(num - 1, a, c, b);

            // 2. 把最下面的那个盘移动到c
            System.out.println("第" + num + "个盘从" + a + "->" + c);

            // 3. 把B塔的所有的盘移动到C,移动过程使用到了A
            hanoitower(num - 1, b, a, c);
        }
    }

    /**
     * 汉诺塔复习
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoitower1(int num, char a, char b, char c) {
        // 如果盘子数量只有一个的话,就直接将a移动到c输出即可
        if (num == 1) {
            System.out.println("第1个盘子从 " + a + "-->" + c);
        } else {
         // 如果当前的num数量大于1
         // 就先将a上面的n-1个盘子移动到b,然后将最大的盘子从a移动到c
         hanoitower1(num-1, a, c, b);
         // 输出a中最大的盘子
            System.out.println("第" + num + "个盘子从" + a + "-->" + c);
            // 然后将上面的num-1个元素从b移动到c,可以借助中间节点a
            hanoitower1(num-1, b, a, c);


        }
    }


}
