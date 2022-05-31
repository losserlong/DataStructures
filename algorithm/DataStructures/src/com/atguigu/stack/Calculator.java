package com.atguigu.stack;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/15    20:46
 * @Version:1.0
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
 *
 * 验证： 3+2*6-2 = 13
 * 1.
 */
public class Calculator {
    public static void main(String[] args) {
        // 根据老师的思路，完成表达式的运算
        String expression = "77*2*2/2+1-5+3-4";
        // 先创建两个栈，一个是存放数字栈，一个是存放符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关的变量
        int index = 0; //用于扫描
        int num1 = 0; // 用于接收栈中的两个数据
        int num2 = 0;
        // 用来存储当前的操作
        int oper = 0;
        int res = 0; // 用于接收num1和num2计算的结果
        char ch = ' '; // 每次扫描到的char保存到ch中
        String keepNum = "";  // 用于拼接多为数的

        // 开始while循环扫描expression
        while (true) {
            // 依次扫描得到expression的每一个字符
            // expression.substring(index, index + 1)这里的意思是截取一个字符串，后面是将字符串转换成char
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                // 表明这是一个符号
                // 判断当前的符号栈是否为空
                if (operStack.isEmpty()) {
                    // 为空就直接将操作符ch压入栈中
                    operStack.push(ch);
                } else {
                    // 不为空,就先比较当前操作符和符号栈中的操作符的优先级
                    // 3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符，
                    //就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        // 从数栈pop两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        // 将运算的结果再压入到数栈
                        numStack.push(res);
                        // 入玩数栈之后，还要讲当前的压入到符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                }
            } else {

                // 如果是数字的话就直接入数字栈
                // 注意：不能直接加入，因为这时候的数字是字符数字，要减去'0'(字符0)或者48才行
                // 1.0版只考虑了一位数字  numStack.push(ch - '0');

                // 1、这里有一个小bug，在处理多位数时，不能发现是一个数就立即入数栈，因为可能是多位数
                // 2、在处理数，需要向expression的表达式的index后再看一位，如果是数就继续扫描，如果是符号，终止扫描
                // 3、我们需要定义一个字符串变量，用于拼接
                // 处理多位数字
                keepNum += ch;
                // 如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));

                } else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入数栈
                    // 判断后一位
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 后一位是操作符
                        // 将字符串转成int
                        numStack.push(Integer.parseInt(keepNum));
                        // 重要的！！！！！！！！！keepNum清空
                        keepNum = "";

                    }
                }
            }
            // 让index+1，并判断是否扫描到了expression的最后
            index++;
            // 其实吧，只要等于就行了
            if (index >= expression.length()) {
                // 退出循环
                break;
            }

        }
        // 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        while (true) {
            // 如果符号栈为空，则计算到最后的结果了，数栈中就只有一个数字了（结果）
            if (operStack.isEmpty()) {
                break;
            }
            // 从数栈pop两个数
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            // 将算出的结果入栈
            numStack.push(res);
        }
        // 将数栈的结果pop就是结果
        System.out.printf("表达式的%s = %d ", expression, numStack.pop());

    }

}

/**
 *定义 ArrayStack2表示栈
 * 扩展一下栈的功能
 */
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
     * 返回当前栈的栈顶元素
     * @return
     */
    public int peek() {
        return stack[top];
    }


    /**
     *
     * @param oper  传入的操作符
     * @return 返回的是数字的优先级，这里数字越大优先级越高
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            // 这里假定操作符只有+,-,*,/这四种
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     *num1和num2是栈中弹出来的两个数
     * 注意：在做加减乘除法时候要将后面弹出来的数字，写在前面
     * @param num1  栈中的两个数
     * @param num2
     * @param oper 操作符
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int result = 0; // 用于保存结果
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                //  注意顺序
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;

        }

        return result;
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