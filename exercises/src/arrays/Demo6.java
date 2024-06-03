package arrays;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/3 11:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 189. 轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右轮转 1 步: [99,-1,-100,3]
     * 向右轮转 2 步: [3,99,-1,-100]
     * 提示：
     * 1 <= nums.length <= 105
     * -231 <= nums[i] <= 231 - 1
     * 0 <= k <= 105
     */
    public static void rotate(int[] nums, int k) {
        // 暴力轮转，时间复杂度过高，
        int n = nums.length;
        int count = k % n;
        if (count == 0) {
            return;
        }
        while (count > 0) {
            // 轮转数组
            int tmp = nums[0];
            int i = n - 1;
            // 从后往前替换
            for (; i > 0; i--) {
                nums[(i + 1) % n] = nums[i];
            }
            nums[1] = tmp;
            count--;
        }
    }

    public static void rotate1(int[] nums, int k) {
        // 空间换时间
        int n = nums.length;
        if (n == 0 || k % n == 0) {
            return;
        }
        int[] tmp = new int[n];
        // 装填临时数组
        for (int i = 0; i < n; i++) {
            tmp[(i + k) % n] = nums[i];
        }
        // 替换原始数组
        for (int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        // 输入: nums = [1,2,3,4,5,6,7], k = 3
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        // rotate(nums, 3);
        rotate1(nums, 3);
        System.out.println(Arrays.toString(nums));  // 输出: [5,6,7,1,2,3,4]
    }
}