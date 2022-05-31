package com.atguigu.linkedlist.exer;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/14    11:13
 * @Version:1.0
 * 演示栈的使用
 * 栈是先进后出，类似于弹夹
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("zsf");
        stack.add("lisi");
        stack.add("wangwu");
        stack.add("zhaoliu");
        System.out.println("出栈");

        // 出栈
        while (stack.size() > 0) {
            // 将栈的数据进行弹出
            System.out.println(stack.pop());
        }

    }

}
