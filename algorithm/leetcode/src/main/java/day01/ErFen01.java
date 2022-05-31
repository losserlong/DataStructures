package day01;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/12/19    16:59
 * @Version:1.0
 * 二分查找算法
 *leetcode704. 二分查找
 *
 * 注意二分查找必须是有序的，要么是升序，要么是降序
 *
 */
public class ErFen01 {

    public int search(int[] nums, int target) {
        // 定义两个变量，数组长度区间的左部和右部分,right是右边，left是左边
        int right = nums.length - 1, left = 0;


        // 1.先判断不和条件的情况,如果target的值要小于数组中的最小值或者大于数组中的最大值，直接返回-1
        if (target < nums[left] || target > nums[right]) {
            return -1;
        }
        // 因为这里是循环，所以直接一直进行比较二分即可，当最终middle=target的时候，就返回middle的下标
        // ,当left等于right就是target

        while (left <= right) {
            // 这样是为了避免数值越界 ,相当于(right + left) / 2
//            int middle = left + (right -left) / 2;
            // 对于这里除以2还可以用用移位运算进行计算，左移 (<<) n位就是乘以2的n方，右移  (>>) n位就是除以2的n次方
            int middle = left + ((right - left) >> 1);

            if (target < nums[middle]) {
                // [left ,  middle-1]
                right = middle - 1;
            } else if (target > nums[middle]) {
                // [middle+1 , right]
                left = middle + 1;
            } else {
                return middle;
            }

        }
        return -1;

    }


    @Test
    public void test01() {

        int[] arr = {1, 2, 3, 5, 6, 7, 8, 9, 10};
        int search = search(arr, 8);
        System.out.print(search);

    }


}
