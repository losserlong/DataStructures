package com.atguigu.recursion;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/17    20:27
 * @Version:1.0
 *
 * 递归机制的回顾：
 * 递归简单的来说就是自己调用自己，可以让代码变得简洁
 *
 */
public class RecursionTest {

    public static void main(String[] args) {
        // 通过打印问题，回顾递归的调用机制
        test(10);

        int factorial = factorial(1000);
        System.out.println("factorial = " + factorial);

    }


    /**
     * 阶乘问题
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) + n;
        }

    }


    /**
     * 加了else是打印2
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
//        else {
        System.out.println("n = " + n);
//        }
    }


}
