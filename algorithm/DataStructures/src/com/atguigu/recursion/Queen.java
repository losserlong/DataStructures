package com.atguigu.recursion;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/18    12:09
 * @Version:1.0
 * 八皇后问题：用递归实现
 */
public class Queen {
    // 定义一个max表示共有多少个皇后
    int max = 8; // 表示共有8个皇后
    // 定义一个数组array，保存皇后放置的位置，比如array={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int judgeCount=0;
    private static int count = 0;


    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
        System.out.println("count = " + count);
        System.out.println("一共判断了" + judgeCount + "次数");

    }

    /**
     * 编写一个方法，放置第n个皇后 n是从0开始的
     * 特别注意：check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++)
     * @param n
     */
    private void check(int n) {

        if (n == max) {
            // n=8,其实8个皇后就已经放好了
            print();
            return;
        }
        // 如果没有，就依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 n ，放到该行的第一列
            array[n] = i;
            // 判断当前放置的第n个皇后到第i列时，是否冲突
            if (judge(n)) {
                // 表示不冲突,就接着放第n+1个皇后,即开始递归
                check(n + 1);
            }
            // 如果冲突,就继续执行array[n] = i; i++ ; 即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    // 当我们放置第n个皇后时，就去检测该皇后，是否和前面已经拜访的皇后冲突

    /**
     *
     * @param n 表示放第n个皇后
     * @return ture 表示不冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 检测当前放置的皇后和之前的皇后是否冲突
            // array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i])表示判断第n个皇后和第i个皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                // 如果i和n处于同一列或者同一斜线
                return false;
            }
        }
        return true;
    }


    // 写一个方法，可以将皇后拜访的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        // 输出一种解法就换行
        System.out.println();
    }


}
