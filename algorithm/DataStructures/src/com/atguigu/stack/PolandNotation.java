package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/16    15:21
 * @Version:1.0
 * 用栈实现逆波兰表达式
 * 1)输入一个逆波兰表达式(后缀表达式)，使用栈(Stack),计算其结果
 * 2)支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。
 * 3)思路分析
 * 4)代码完成
 *
 *
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3+4)x5-6  => 3 4 + 5 x 6 -
        // 为了方便，逆波兰表达式的数字和符号用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 - ";
        // 4*5-8-60+8/2
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / + "; // 76
        // 思路
        // 1、先将suffixExpression放到ArrayList中
        // 2、将ArrayList传递给一个方法，遍历ArrayList配合栈 完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int result = calculate(list);
        System.out.println("result = " + result);

    }
///*

    /**
     *
     * 完成对逆波兰表达式的运算
     * 将逆波兰表达式中的字符串存入字符串数组中
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {

        List<String> list = new ArrayList<>();
        // 根据空格对suffixExpression进行分割得到数字和操作的字符串数组
        String[] split = suffixExpression.split(" ");
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    /**
     * 3 4 + 5 x 6 -
     *将一个逆波兰表达式，依次将数据和运算符放到ArrayList中
     * 1)从左至右扫描,将3和4压入堆栈;
     * 2)遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈;3)将5入栈;
     * 4)接下来是×运算符,因此弹出5和7，计算出7x5=35，将35入栈;5)将6入栈;
     * 6)最后是-运算符,计算出35-6的值，即29，由此得出最终结果
     *
     * @param list
     * @return
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        // 遍历集合，将数字存入stack中
        for (String element : list) {
            // 这里用正则表达式进行判断 \\d+ 表示匹配一个或者多个数字
            if (element.matches("\\d+")) {
                // 将element入栈
                stack.push(element);
            } else {
                // num1和num2是从栈中弹出的两个数字
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0; // 用于存放结果
                if (Objects.equals(element, "+")) {
                    res = num1 + num2;
                } else if (Objects.equals(element, "-")) {
                    res = num1 - num2;
                } else if (Objects.equals(element, "*")) {
                    res = num1 * num2;
                } else if (Objects.equals(element, "/")) {
                    res = num1 / num2;
                } else {
                    // 如果都不是的话，直接抛出异常
                    throw new RuntimeException("输入的运算符有误！！！");
                }
                // 将int转String
                stack.push(String.valueOf(res));
            }
        }
        // 最后留在栈中的元素就是运算结果
        return Integer.parseInt(stack.pop());

    }


}
