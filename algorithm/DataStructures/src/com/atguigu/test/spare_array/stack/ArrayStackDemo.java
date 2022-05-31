package com.atguigu.test.spare_array.stack;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/16    11:34
 * @Version:1.0
 * 重新写一遍，用数组来模拟实现栈
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.list();

    }

}

class ArrayStack {
    // 定义一个数组stack，用于存储栈
    public int stack[];
    // 定义一个指针，指向栈顶，初始化为-1
    public int top = -1;
    // 栈的长度
    public int size;

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 判断栈是否已经满了
    public boolean isFull() {
        return top == size - 1;
    }

    // 对栈弹出数据
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，不能弹出数据！！！");
        }
        return stack[top--];
    }

    // 对栈进行push数据
    public void push(int data) {
        // 当栈没有满的时候才能进行添加数据
        if (!isFull()) {
            stack[++top] = data;
        } else {
            throw new RuntimeException("栈已经满了，不能进行添加数据!!!!");
        }

    }

    // 对栈进行遍历操作
    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }


    public ArrayStack(int size) {
        // 对数组进行初始化
        stack = new int[size];
        this.size = size;
    }

    public ArrayStack() {
    }


    @Override
    public String toString() {
        return "ArrayStack{" +
                "stack=" + Arrays.toString(stack) +
                '}';
    }
}