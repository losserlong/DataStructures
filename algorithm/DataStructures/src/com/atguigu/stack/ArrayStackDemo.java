package com.atguigu.stack;


import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/15    16:54
 * @Version:1.0
 * 用数组实现栈
 * 栈的特点，先进后出
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);


        String key = "";
        boolean loop = true; // 控制是否退出循环
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show：表示显示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加到栈(入栈)");
            System.out.println("pop：表示从栈中取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈的顺序是%d\n", res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    // 将scanner进行关闭避免操作资源的泄露
                    scanner.close();
                    loop = true;
                    break;
                default:
                    break;


            }

        }

        System.out.println("程序退出了");
    }


}

/**
 *定义 ArrayStack表示栈
 */
class ArrayStack {
    // 表示栈的最大空间
    public int maxSize;

    // 数组模拟栈，数据放到stack中
    public int[] stack;

    // top表示栈顶初始化为-1
    public int top = -1;

    public ArrayStack() {

    }

    // 构造器栈的大小
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        // 对栈进行初始化
        stack = new int[maxSize];

    }

    // 判断栈是否满了
    public boolean isFull() {
        return top == stack.length - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈操作
    public void push(int data) {
        // 当栈没有满的时候才能进行添加操作
        if (!isFull()) {
            stack[++top] = data;
        } else {
            System.out.println("栈已经满了，不能进行添加");

        }


    }

    // 出栈操作
    public int pop() {
        if (!isEmpty()) {
            // 先用一个临时变量去接收，要移除的值
            int temp;
            temp = stack[top--];
            return temp;
        }
        throw new RuntimeException("栈空，没有数据！");
    }

    /**
     * 遍历栈
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据！！！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    @Override
    public String toString() {
        return "ArrayStack{" +
                "maxSize=" + maxSize +
                ", stack=" + Arrays.toString(stack) +
                ", top=" + top +
                '}';
    }
}