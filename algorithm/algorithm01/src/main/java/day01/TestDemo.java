package day01;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/15    18:58
 * @Version:1.0
 */
public class TestDemo {

    @Test
    public void test01() {
        int N = 1000;
        int a = 0;
        for (int i = 0; i < N; i++) {
            a = 2 + 5;
            a = 4 * 7;
            a = 6 * 8;

        }
        System.out.println(a);


    }

    @Test
    public void test02() {
        int N = 1000;
        int a = 0;
        for (int i = 0; i < N; i++) {
            a = 3 | 6;
            a = 4 &7;
            a = 6^ 8;

        }

        System.out.println(a);

    }


}
