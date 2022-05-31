package com.atguigu.singleton;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2022/05/09    9:26
 * @Version:1.0 单例模式
 */
public class Singleton {
    // volatile的作用
    // 1. 保证可见性
    // 2. 不保证复合原子操作
    // 3. 禁止指令重排


    private volatile static Singleton singleton;

    private Singleton() {

    }

    /**
     * 懒汉模式
     * DCL (double check lock 双检锁)
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;


    }


}
