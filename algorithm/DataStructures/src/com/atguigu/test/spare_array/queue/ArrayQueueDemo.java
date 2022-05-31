package com.atguigu.test.spare_array.queue;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/01/11    16:17
 * @Version:1.0
 * 将之前写的队列重新再写一遍
 * 队列存入数据，是从对头开始存入数据，取出数据是从队尾开始取出数据
 *队列是一种特殊的线性表，特殊之处在于它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作
 * front（头指针）会随着数据的输出而改变，输出front会后移
 * rear（尾指针）会随着数据的输入而改变，输入数据rear后移
 * 初始化的时候，rear=front=-1
 * 然后插入数据的时候，尾指针加一，将要插入的数据放到，已经加以后的arr[rear]数组的位置
 * 数组满了的条件就是rear=arr.length-1;
 * 移除数据是从队列队首，进行移除数据，先将front进行加一，
 * 然后移除数组中front所在位置的元素
 *
 */
class ArrayQueue {

    // 初始化队列的时候都是指向-1
    // 头指针
    private int front;
    // 尾指针
    private int rear;

    // 表示数组的最大容量
    private int maxSize;

    // 新建一个数组
    private int[] arr;

    public ArrayQueue() {
    }

    // 构造方法，同时初始化数组的长度
    public ArrayQueue(int maxSize) {
        this.front = -1;
        this.rear = -1;
        this.maxSize = maxSize;
        // 初始化数组的长度为输入的数字
        arr = new int[maxSize];
    }


    /**
     * true 表示队列已经满了
     * @return
     */
    // 队列存入数据是从队首开始存入，取出数据是从队尾开始取
    // 判断数组是否已经满了
    public boolean isFull() {
        // 当尾指针指向数组的最后一个元素的时候，就满了
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空队列
     * @return true表示队列为空
     */
    public boolean isEmpty() {
        // 当对头等于队尾的时候就是空队列
        return rear == front;
    }

    /**
     * 从后端进行输入
     * 队列添加一个元素
     */
    public void addElement(int element) {
        // 判断队列是否已经满了
        if (isFull()) {
            throw new RuntimeException("队列已满，不能添加数据！！！");
        }
        // 先将队列的尾指针后移，然后将输入的元素赋值给队尾的指针
        arr[++rear] = element;
    }

    /**
     * 获取队列中的数据
     */
    public int removeElement() {

        if (isEmpty()) {
            throw new RuntimeException("队列为空！不能获取数据");
        }
        // 尾指针后移
        front++;
        int old = arr[front];
        // 将头指针的值制空，（这里只能设置为0）
        arr[front] = 0;
        return old;
    }

    /**
     * 显示队列的全部元素
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据显示");
        } else {
            // 进行遍历得到队列中的数据
            for (int i = front + 1; i < rear + 1; i++) {
                System.out.println("队列的所有元素为" + arr[i]);
            }
        }
    }


    /**
     * 显示队列的头元素
     */
    public int showHead() {
        if (isEmpty()) {
            System.out.println("队列为空！没有数据");
        }
        return arr[front + 1];
    }


}

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
                    arrayQueue.addElement(value);
                    break;
                case 'g': // 取数据
                    try {
                        int queue = arrayQueue.removeElement();
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
