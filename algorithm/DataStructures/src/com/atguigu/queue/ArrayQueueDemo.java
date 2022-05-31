package com.atguigu.queue;


import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/16    17:29
 * @Version:1.0
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

        // 创建队列对象
        ArrayQueue arrayQueue = new ArrayQueue(4);
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
                        int res = arrayQueue.headQueue();
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

class ArrayQueue {

    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr;// 该数据用于存放数据，模拟队列


    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;    // 指向队列头部，分析出front是指向队列头部的前一个位置
        this.rear = -1;     // 指向队列尾部，指向队列尾部的具体的位置
        arr= new int[maxSize];

    }

    // 判断队列是否满
    private boolean isFull() {
        return rear == maxSize - 1;
    }



    // 判断队列是否为空
    private boolean isEmpty() {
        return rear == front;
    }

    public synchronized void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        // 让rear后移
        rear++;
        arr[rear] = n;
    }

    // 获取队列的数据，出队列
    public int getQueue() throws Exception {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++; // front后移

        // 然后出队列
        int oldValue = arr[front];

        // 将队列中的arr[front]的值置空(设置为默认值0)
        arr[front] = 0;
        return oldValue;

    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据");
            return;
        }

        for (int i = front + 1; i < rear + 1; i++) {
            System.out.println(i + "   " + arr[i]);
        }
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        // 判断是否为空
        if (isEmpty()) {
//            System.out.println("队列为空，没有数据！");
            throw new RuntimeException("队列为空，没有数据！");
        }
        return front + 1;

    }


}
