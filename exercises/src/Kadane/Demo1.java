package Kadane;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: Kadane
 * @CreateTime : 2024/6/28 8:37
 * @Description: Kadane算法：用于解决最大子数组和的动规算法
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组是数组中的一个连续部分。
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     * 提示：
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     */
    public static int maxSubArray(int[] nums) {
        /**
         * 动规：时间复杂度:O(n), 空间复杂度:O(1)
         * 令f(i)表示 从0到i的连续子数组最大和
         * 因此状态转移方程为：f(i) = Max( f(i-1)+num[i] ,num[i] );
         */
        int max = nums[0];
        int prefix = 0;
        for (int num : nums) {
            // 如果前边累加后还不如自己本身大，那就把前边的都扔掉，重新从自己本身开始累加
            prefix = Math.max(prefix + num, num);
            max = Math.max(prefix, max);
        }
        return max;
    }

    public static void main(String[] args) {
        // 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));  // 6
        System.out.println(maxSubArray(new int[]{5, 4, -1, 7, 8}));  // 23
    }
}
