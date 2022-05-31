package com.atguigu.queue_bak;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/18    19:20
 * @Version:1.0
 */

public class ArrayQueueDemo {

    public static void main(String[] args) {
        // 创建队列对象
        ArrayQueue arrayQueue = new ArrayQueue(4);
        char key = ' '; // 接收用户的输入
        Scanner scann = new Scanner(System.in);

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
                    System.out.println("程序退出");
                    break;

            }


        }


    }
}


/**
 * 用数组实现队列  ArrayQueueDemo
 */
class ArrayQueue {
    // 队列的最大长度
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    private int[] arr; //该数组用于存储数据，模拟队列


    // 将队列进行初始化
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        arr = new int[maxSize];
    }




    // 判断队列是否已经满了
    public boolean isFull() {
        if (rear == maxSize - 1) {
            return true;
        }
        return false;
    }

    // 判断队列是否为空
    public boolean isNull() {
        return rear == -1 && front == -1;
    }

    // 往队列中加入数据
    public void addQueue(int n) {
        // 先判断队列是否已经满了
        if (isFull()) {
            throw new RuntimeException("队列已满，不能插入数据");
        }
        // 队尾后移
        rear++;
        arr[rear] = n;


    }

    // 获取队列中的数据
    public int getQueue() {
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }

        // front后移
        front++;

        // 然后出队列
        int oldQueue = arr[front];

        // 将arr[front]设置为默认值0
        arr[front] = 0;
        return oldQueue;
    }

    // 显示所有数据
    public void showQueue() {
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }
        for (int i = 0; i < rear + 1; i++) {
            System.out.println(arr[i]);
        }
    }

    // 显示对头
    public int showHead() {
        if (isNull()) {
            throw new RuntimeException("队列为空！");
        }
        return arr[front + 1];

    }


}