package com.atguigu.test.spare_array.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/17    10:36
 * @Version:1.0
 * 用栈来实现中缀表达式
 *
 * 用栈来实现计算器
 * 使用栈完成表达式的计算 思路
 * 1. 通过一个 index  值（索引），来遍历我们的表达式
 * 2. 如果我们发现是一个数字, 就直接入数栈
 * 3. 如果发现扫描到是一个符号,  就分如下情况
 * 3.1 如果发现当前的符号栈为 空，就直接入栈
 * 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
 * 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，
 * 入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
 * 5. 最后在数栈只有一个数字，就是表达式的结果
 * 验证： 3+2*6-2=13
 *
 */
public class Calculator {


    public static void main(String[] args) {

        String expression = "88+2*6-2+4-3";
        // 数据栈
        ArrayStack2 dataStack = new ArrayStack2(10);
        // 操作符栈
        ArrayStack2 operStack = new ArrayStack2(10);
        int result = 0; // 用来存储结果
        // 定义一个index记录循环了多少次
        int index = -1;
        String keepNum = ""; // 用来拼接多位数
        List<String> list = tranFrom(expression);
        // 开始对expression进行扫描，分别加入到数栈和操作符栈
        for (String str : list) {
            index++;
            // 将当前expression转换成char
            char ch = str.charAt(0);
            if (operStack.isOper(ch)) {
                // 这表明str是一个操作符
                if (operStack.isEmpty()) {
                    // 当操作符栈是空的就直接将操作符加入到操作符栈
                    operStack.push(ch);
                } else {
                    //如果不为空，则比较当前操作符和栈顶的优先级的大小
                    if (operStack.priority(ch) > operStack.priority(operStack.peek())) {
                        // 大于栈顶的操作符的优先级,直接入栈
                        operStack.push(ch);
                    } else {
                        // 从数栈中弹出两个数字，再从符号栈中弹出一个符号来
                        result = dataStack.cal(dataStack.pop(), dataStack.pop(), operStack.pop());
                        dataStack.push(result);
                        // 再将当前的操作符加入到操作栈
                        operStack.push(ch);
                    }
                }

            } else {
                keepNum += ch;
                // 如果ch已经是expression的最后一位，就直接入栈
                if (index == list.size() - 1) {
                    // 这里表明str是数字
                    dataStack.push(Integer.parseInt(keepNum));
                } else {
                    //  不是最后一位
                    // 判断后一位是不是数字
                    if (operStack.isOper(list.get(index + 1).charAt(0))) {
                        dataStack.push(Integer.parseInt(keepNum));
                        // 重要的！！！！！！！！！keepNum清空
                        keepNum = "";

                    }

                }

            }
        }

        // 扫描完毕了，现在将
        while (true) {
            if (operStack.isEmpty()) {
                // 当operStack为空就退出循环
                break;
            }

            int cal = dataStack.cal(dataStack.pop(), dataStack.pop(), operStack.pop());
            dataStack.push(cal);
        }

        System.out.println(dataStack.peek());


    }

    /**
     * 将接收的运算expression转换成list
     * @param expression
     * @return
     */
    public static List<String> tranFrom(String expression) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            list.add(expression.substring(i, i + 1));
        }

        return list;
    }


}

class ArrayStack2 {
    // 表示栈的最大空间
    public int maxSize;

    // 数组模拟栈，数据放到stack中
    public int[] stack;

    // top表示栈顶初始化为-1
    public int top = -1;

    public ArrayStack2() {

    }

    /**
     * 对操作进行计算
     * @param a 数栈中弹出的两个元素
     * @param b
     * @param oper 操作符号
     * @return
     */
    public int cal(int a, int b, int oper) {
        if (oper == '+') {
            return b + a;
        } else if (oper == '-') {
            return b - a;
        } else if (oper == '*') {
            return b * a;
        } else if (oper == '/') {
            return b / a;
        } else {
            throw new RuntimeException("输入的运算符有误！！！");
        }
    }

    /**
     * 查看当前栈顶元素
     * @return
     */
    public int peek() {
        return stack[top];
    }


    /**
     * 判断传入的是否为操作符
     * @param c
     * @return
     */
    public boolean isOper(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * 自定义操作符的优先级
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }


    // 构造器栈的大小
    public ArrayStack2(int maxSize) {
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
