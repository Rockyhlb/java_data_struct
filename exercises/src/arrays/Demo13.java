package arrays;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/5 9:11
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo13 {
    /**
     * 238. 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 示例 1:
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     * 示例 2:
     * 输入: nums = [-1,1,0,-3,3]
     * 输出: [0,0,9,0,0]
     * 提示：
     * 2 <= nums.length <= 105
     * -30 <= nums[i] <= 30
     * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
     * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
     */
    public static int[] productExceptSelf(int[] nums) {
        // 方法1：暴力，时间复杂度过高
        int prefix = 1;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int mul = prefix;
            while (j < nums.length) {
                mul *= nums[j++];
            }
            ans[i] = mul;
            prefix *= nums[i];
        }
        return ans;
    }

    public static int[] productExceptSelf1(int[] nums) {
        // 方法2：左右乘积列表, 时间复杂度O(n), 空间复杂度O(n)
        // 新建两个数组L[] 和 R[] 记录索引的左边乘积和右边乘积，最后的ans[i] = L[i] * R[i]
        int len = nums.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] ans = new int[len];
        // 计算所有前缀乘积
        left[0] = 1; // 限定边界
        for (int i = 1; i < len; i++) {
            // 第i个元素的前缀积 = 前i-1元素的前缀积 * 第i-1个元素
            left[i] = left[i - 1] * nums[i - 1];
        }
        // 计算后缀积
        right[len - 1] = 1;
        for (int i = len - 1 - 1; i >= 0; i--) {
            // 第i个元素的后缀积 = 前i+1元素的后缀积 * 第i+1个元素
            right[i] = right[i + 1] * nums[i + 1];
        }
        // 计算结果
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    public static int[] productExceptSelf2(int[] nums) {
        // 方法3：优化左右乘积列表, 时间复杂度O(n), 空间复杂度O(1)(结果数组不计入空间复杂度内)
        int len = nums.length;
        int[] ans = new int[len];
        // 初始化ans, 计算所有前缀乘积
        ans[0] = 1; // 限定边界
        for (int i = 1; i < len; i++) {
            // 第i个元素的前缀积 = 前i-1元素的前缀积 * 第i-1个元素
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        // 计算结果,从后往前计算(因为最后一个元素的后缀积为1), 同时更新后缀积
        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入: nums = [1,2,3,4]
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));  // [24, 12, 8, 6]
        System.out.println(Arrays.toString(productExceptSelf1(nums)));  // [24, 12, 8, 6]
        System.out.println(Arrays.toString(productExceptSelf2(nums)));  // [24, 12, 8, 6]
    }
}
