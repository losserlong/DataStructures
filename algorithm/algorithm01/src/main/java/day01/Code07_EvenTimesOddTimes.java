package day01;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/11/15    21:08
 * @Version:1.0
 *
 * 描述：数组中只有两种数，一种数出现了奇数次，另一种数出现了偶数次
 * 要求找出，这两种数
 * 时间复杂度 O(N) 空间复杂度 O(1)
 *
 *
 *
 * 1）0^N == N N^N == 0
 * 2）异或运算满足交换律和结合率
 * 3）不用额外变量交换两个数
 * 4）一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数  (直接全部异或上0，就能得到出现奇数次的数字)
 * 5）一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
 *
 *
 *
 */
public class Code07_EvenTimesOddTimes {


    /**
     * 进行异或找出数组中出现奇数次的数
     * N^0=N
     * N^N=0
     *
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor = eor ^ cur;
        }
        System.out.println(eor);
    }

    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;

        for (int curNum : arr) {
            // eor先从头异或到尾
            eor = eor ^ curNum;
        }

        // eor =a ^ b
        // eor !=0 因为有两种数，所以a,b不相等
        // ~eor 将eor取反
        int rightOne = eor & (~eor + 1); // 提取出二进制位上最右边的1


        int onlyOne = 0; // 这个就是eor'
        for (int cur : arr) {
            // 将数组中的每一个数与rightOne相与
            if ((cur & rightOne) == 0) {
                // 这个cur是a或者是b
                onlyOne ^= cur;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }


    public static void main(String[] args) {

        int arr[] = {1, 1, 1, 2, 2, 2, 43, 43, 9, 9, 54, 54, 65, 65, 32, 32};
        printOddTimesNum2(arr);


    }


}
