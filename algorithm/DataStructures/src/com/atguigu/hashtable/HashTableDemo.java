package com.atguigu.hashtable;


import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/04/24    21:24
 * @Version:1.0
 * ==============================================自己用代码实现HashTable========================================
 *
 */
public class HashTableDemo {


    public static void main(String[] args) {
        // 创建一个HashTable
        HashTable hashTable = new HashTable(7);

        // 写一个简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("exit：退出系统");
            System.out.println("find：查找员工");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建雇员
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;

                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员的id");
                     id = scanner.nextInt();
                     hashTable.getEmpById(id);

                    break;
                default:
                    break;

            }


        }


    }

}

/**
 * 创建雇员
 */
class Emp {

    public int id;
    public String name;
    // next默认为空
    public Emp next;

    public Emp() {
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}

/**
 * 创建HashTable用来管理多条链表
 * 这里HashTable是由数组 + 链表构成的
 */
class HashTable {


    private EmployLinkedList[] linkedLists;
    private int size;

    public HashTable() {
    }

    /**
     * 构造器
     * @param size
     */
    public HashTable(int size) {
        this.size = size;
        // 构造器用来初始化EmployLinkedList数组
        linkedLists = new EmployLinkedList[size];
        // ？这里有一个坑,这里要初始化每一个数组
        for (int i = 0; i < size; i++) {
            linkedLists[i] = new EmployLinkedList();
        }
    }

    /**
     * 通过id来查找哈希表中的emp
     * @param id
     * @return
     */
    public void getEmpById(int id) {
        // 先通过哈希函数获取数组的下标
        int index = hashFun(id);
        // 然后到数组中去查找id对应的Emp对象
        Emp emp = linkedLists[index].getEmpById(id);
        if (emp == null) {
            System.out.println("没有找到id为" + id + "的雇员");
        } else {
            System.out.println("找到id为" + id + "的雇员，雇员信息为");
            System.out.println(emp);
        }

    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp) {
        // 根据员工的id,得到该员工应该添加到哪一条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应的链表中
        linkedLists[empLinkedListNo].add(emp);

    }

    /**
     * 遍历哈希表(也就是遍历数组 + 链表)
     */
    public void list() {

        for (int i = 0; i < size; i++) {
            linkedLists[i].list(i);
        }

    }


    /**
     * 编写一个散列函数,使用简单的取模法
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }


}


/**
 * 创建EmployeeLinkedList,表示一条链表
 */
class EmployLinkedList {
    // 头节点，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head; // 默认为空


    /**======================添加员工到链表===================
     * 1. 假定,添加雇员的时候,id是自增长的,即id的分配总是从小到大排序的
     * 因此我们直接将该雇员直接加到本链表的最后即可
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员,就定义一个辅助指针,帮忙定位到最后
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        // 将emp加入到curEmp的最后
        curEmp.next = emp;

    }

    /**
     * 遍历链表
     */
    public void list(int i) {
        if (head == null) {
            // 说明当前链表为空
            System.out.println("说明第" + (i + 1) + "条链表为空");
            return;
        }
        System.out.println("第" + (i + 1) + "条链表的信息为");
        Emp dummy = head;
        // 当前链表的信息位
        while (dummy != null) {
            System.out.println(dummy);
            // dummy后移
            dummy = dummy.next;
        }
        System.out.println();


    }


    /**
     * 通过id去查找id所对应的emp对象
     * @param id
     * @return
     */
    public Emp getEmpById(int id) {
        // 对链表进行遍历
        Emp curEmp = head;

        if (curEmp == null) {
            System.out.println("链表空");
            return null;
        }


        int count = 0;
        while (curEmp.next != null) {
            // 找到了,直接退出
            if (curEmp.id == id) {
                break;
            }

            // 到最后一个元素了没有找到也直接退出
            if (curEmp.next == null) {
                break;
            }

            // 后移
            curEmp = curEmp.next;
        }

        return curEmp;

    }
}


