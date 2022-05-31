package com.atguigu.test.spare_array.stack;

import java.nio.file.attribute.GroupPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/20    22:22
 * @Version:1.0
 * 将中缀表达式转换成后缀表达式
 * 将中缀表达式转换成后缀表达式
 * 思路分析
 * 1、  1+((2+3)×4)-5 =>转成1 2 3 + 4 × + 5 -
 * 2、  因为直接对str进行操作不方便，因此先将 "1+((2+3)x4)-5" =>转换中缀表达式对应的List
 * 即 "1+((2+3)x4)-5" => ArrayList [1,+,(,(,2,+,3,),x,4,),-,5]
 *
 */
public class PolandNotation1 {
    public static void main(String[] args) {

        String express = "11+((2+3)*4)-15";
        List<String> list = toInfixExpressionList(express);
        System.out.println(list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("list1 = " + list1);

    }


    /**
     * 将中缀表达式的list集合转换成逆波兰表达式
     *
     * 1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2) 从左至右扫描中缀表达式；
     * 3) 遇到操作数时，将其压s2；
     * 4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     * 1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     * 3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     * 5) 遇到括号时：  (1) 如果是左括号“(”，则直接压入s1 (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤2至5，直到表达式的最右边
     * 7) 将s1中剩余的运算符依次弹出并压入s2
     * 8)  依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 用于存放数字和表达式
        ArrayList<String> data = new ArrayList<>();
        // 用于存放字符串
        Stack<String> stack = new Stack<>();

        // 遍历list
        for (String str : list) {
            if (str.matches("\\d+")) {
                // 如果是数字，直接加入到data中
                data.add(str);
            } else {
                // 表示是除数字
                if (str.equals("(")) {
                    // 如果是  "("，直接加入到stack栈中
                    stack.push(str);
                } else {
                    // 表示不是(，就先判断当前的元素的优先级和栈顶元素的优先
                    if (stack.isEmpty()) {
                        // 当栈是空的时，直接加入栈
                        stack.push(str);
                    } else if (str.equals(")")) {
                        // 当匹配到的是右括号时,将stack中的元素弹出加到data中，只要匹配到(，同时将()取出
                        while (!stack.isEmpty()) {
                            if (stack.peek().equals("(")) {
                                // 将(弹出
                                stack.pop();
                                // 退出循环
                                break;
                            } else {
                                data.add(stack.pop());

                            }
                        }
                    } else if (Operation.getValue(str) > Operation.getValue(stack.peek())) {
                        // 如果当前元素大于栈顶的元素优先级，就将当前元素直接加入到栈中
                        stack.push(str);
                    } else if (Operation.getValue(str) <= Operation.getValue(stack.peek())) {
                        // 如果当前元素小于栈顶的元素优先级,将栈顶的元素弹出，放入到data中
                        data.add(stack.pop());
                        stack.add(str);
                    }

                }

            }


        }

        // 最后剩下的运算符，直接加到data中即可
        while (!stack.isEmpty()) {
            data.add(stack.pop());
        }

        return data;
    }


    /**
     * 将express表达式转换成list集合
     * @param express
     * @return
     */
    public static List<String> toInfixExpressionList(String express) {
        // 用于获取每一个字符串的字符
        char ch;
        // 用于存储每一个字符串
        ArrayList<String> list = new ArrayList<>();
        String str; // 用于多位数拼接工作

        int index = 0; // 表示遍历的索引
        do {
            // 当不是数字的时候,ascll码48~57是数字
            if ((ch = express.charAt(index)) < 48 || (ch = express.charAt(index)) > 57) {
                // 不是数字的话直接加入到list中
                list.add(ch + "");
                index++;
            } else {
                // 是数字的情况，要判断是否是多位数
                // 置空
                str = "";
                // 这要考虑是否为多位数，直接用while循环，因为不知道后面有几位数字
                while (index < express.length() && (ch = express.charAt(index)) >= 48 && (ch = express.charAt(index)) <= 57) {
                    str += ch;
                    // 遍历后面的字符串
                    index++;
                }
                list.add(str);

            }


        } while (index < express.length());


        return list;

    }


}

class Operation {

    public static final int ADD = 1;
    public static final int SUB = 1;
    public static final int DIV = 2;
    public static final int MUL = 2;

    /**
     * 判断操作符的优先级
     * @param operation
     * @return
     */
    public static int getValue(String operation) {
        int oper = 0;
        switch (operation) {
            case "+":
                oper = ADD;
                break;
            case "-":
                oper = SUB;
                break;
            case "*":
                oper = DIV;
                break;
            case "/":
                oper = MUL;
                break;
            default:
                break;

        }

        return oper;
    }


}