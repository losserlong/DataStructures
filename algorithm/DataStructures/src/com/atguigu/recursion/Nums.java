package com.atguigu.recursion;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/26    11:05
 * @Version:1.0
 */
public class Nums {


    public static void main(String[] args) {
        int add = add(100);
        System.out.println(add);
    }

    /**
     * 递归求和
     * @param n
     * @return
     */
    public static int add(int n) {
        int sum = 0;
        if (n > 0) {
            return n + add(n - 1);
        } else {
            return 0;
        }



    }


}