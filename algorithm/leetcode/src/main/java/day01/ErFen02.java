package day01;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * @Author: pzx
 * @Date: 2021/12/19    18:19
 * @Version:1.0
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ErFen02 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 先是两个不在数组中的情况
        if (target > nums[right]) {
            return nums.length;
        }
        if (target < nums[left]) {
            return 0;
        }
        // 这个当target在这个数组中的情况下
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }

        }

        // 当
        for (int i = 0; i < nums.length; i++) {
            if (target > nums[i] && target < nums[i + 1]) {
                return i + 1;
            }
        }

        return 0;
    }
    @Test
    public void test01(){
        int[] arr={1,3,5,6};
        int i = searchInsert(arr, 7);
        System.out.print( i);
    }


}
