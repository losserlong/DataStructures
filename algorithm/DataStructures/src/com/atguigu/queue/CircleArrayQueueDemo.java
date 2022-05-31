package com.atguigu.queue;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/18    20:54
 * @Version:1.0
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {

        // 创建队列对象
        CircleArray arrayQueue = new CircleArray(4); // 说明这里设置4，其队列的有效数据最大是3，因为做了一个约定
        char key = ' '; // 接收用户的输入
        Scanner scann = new java.util.Scanner(System.in);

        boolean loop = true;

        // 输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("h (head):查看队列的头部列取数据");
            key = scann.next().charAt(0);

            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    // 进行关闭
                    scann.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数字");
                    int value = scann.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': // 取数据
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("去除的数据是" + queue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.showHead();
                        System.out.println("队列头的数据是" + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;

            }
            System.out.println("程序退出");


        }


    }


}

class CircleArray {

    private int maxSize; // 表示数组的最大容量
    // front变量的含义做出一个调整：front就指向队列的第一个元素，也就是说arr[front]
    // front的初始值为0
    private int front; // 队列头
    // rear变量的含义做了一个调整：rear指向队列的最后一个元素的最后一个位置，因为希望留出一个位置作为约定
    // rear的初始值为0
    private int rear; // 指向队尾的最后一个元素的后一个元素
    private int[] arr;// 该数据用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isNull() {
        return rear == front;
    }


    // 添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满了！不能加入数据！");
            return;
        }
        if (rear < maxSize - 1) {
            // 直接将数据加入
            arr[rear] = n;
            // 将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }
    }

    // 获取队列中的数据
    public int getQueue() {
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }

        // 这里需要分析出front是指向队列的第一个元素
        // 1. 先要把front对应的值保存到一个临时变量
        // 2. 将front后移,考虑取余
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isNull()) {
            System.out.println("队列空的，没有数据");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < size(); i++) {
            // 因为在i++的时候可能超过数组的大小，所以要取模
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列中有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


    // 显示对头
    public int showHead() {
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front];

    }


}