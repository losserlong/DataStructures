package com.atguigu.stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/16    15:21
 * @Version:1.0
 *
 * 将中缀表达式转换成后缀表达式
 * 思路分析
 * 1、  1+((2+3)×4)-5 =>转成1 2 3 + 4 × + 5 -
 * 2、  因为直接对str进行操作不方便，因此先将 "1+((2+3)x4)-5" =>转换中缀表达式对应的List
 * 即 "1+((2+3)x4)-5" => ArrayList [1,+,(,(,2,+,3,),x,4,),-,5]
 *
 *
 *
 */
public class PolandNotation1 {

    @Test
    public void test01() {
        System.out.println("111".matches("\\d+"));
    }

    public static void main(String[] args) {


        String express = "11+((2+3)*4)-15";
        List<String> list = toInfixExpressionList(express);
        System.out.println("中缀表达式对应的list = " + list);
        List<String> parseList = parseSuffixExpressionList(list);
        System.out.println("后缀表达式对应的 parseList = " + parseList);
        int calculate = calculate(parseList);
        System.out.println("calculate = " + calculate);
    }


    /**
     * 将ArrayList对应的中缀表达式[1,+,(,(,2,+,3,),x,4,),-,5]转换成后缀表达式1 2 3 + 4 × + 5 -
     * @param list
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> stack1 = new Stack<>(); // 符号栈
        // 因为比较麻烦用栈的话要逆序输出，所以这里就直接用List<String>输出即可
        // Stack<String> stack2 = new Stack<>(); // 存放中间结果的栈
        List<String> ls = new ArrayList<>(); // 存储中间结果的list2 其实也就是s2
        // 对传过来的中缀表达式进行遍历
        for (String item : list) {
            // 如果是数字就将item放入到数栈
            if (item.matches("\\d+")) {
                ls.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号的话直接加入到符号栈
                stack1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                // 其实这里用if都一样的
                if (!stack1.peek().equals("(")) {
                    ls.add(stack1.pop());
                }
                // 将stack1中左边的小括号进行丢弃
                stack1.pop();
            } else {
                // 考虑运算符的优先级
                // 当前符号的优先级小于等于符号栈顶的优先级时
                // 将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                if (stack1.size() != 0 && Operation.getValue(stack1.peek()) >= Operation.getValue(item)) {
                    // 如果符号栈不为空，并且符号栈顶的元素的优先大于当前遍历的元素的优先级,
                    ls.add(stack1.pop());
                }
                // 还需要将item压入栈中
                stack1.push(item);
            }


        }
        // 7.将s1中剩余的运算符依次压入到s2中
        if (!stack1.isEmpty()) {
            ls.add(stack1.pop());
        }
        // 注意，因为是存放在List中的，因此按顺序输出就是对应的后缀表达式对应的List
        return ls;
    }


    /**
     *  String express="1+((2+3)×4)-5";
     *  将中缀表达式转换成对于的List
     * @param express
     * @return
     */
    public static List<String> toInfixExpressionList(String express) {
        // 定义一个List，存放中缀表达式对应的内容
        ArrayList<String> list = new ArrayList<>();
        int i = 0; // 这个是一个指针，用于遍历字符串表达式express
        String str; // 这个是用来对多位数的拼接工作
        char c; // 每遍历到一个字符，就放到c中
        do {
            // 如果c是一个非数字，就需要加到list中
            // ascll码数字的范围是48~57之间
            if ((c = express.charAt(i)) < 48 || (c = express.charAt(i)) > 57) {
                // 不是数字的话就加入到list中
                list.add("" + c);
                // 将i进行后移
                i++;
            } else {
                // 如果是数字的话，要考虑多位数
                str = ""; // 先将str置空 ""  '0'[48] -> '9' [57]
                while (i < express.length() && (c = express.charAt(i)) >= 48 && (c = express.charAt(i)) <= 57) {
                    str += c; // 对字符串进行拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < express.length());
        return list;
    }


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

/**
 * 定义一个类，可以返回运算符的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     *     写一个方法，返回对应的优先级数字
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;

        }

        return result;
    }


}



