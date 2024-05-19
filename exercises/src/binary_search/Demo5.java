package binary_search;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: binary_search
 * @CreateTime : 2024/5/19 15:10
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * 提示：
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     */
    public static int[] searchRange(int[] nums, int target) {
        // 查找target的起始位置，其实就是查找数组中第一个大于等于target的下标(leftIndex) 和 第一个大于target的下标(rightIndex) - 1
        int leftIndex = binary_search(nums, target, true);
        int rightIndex = binary_search(nums, target, false) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};
    }

    private static int binary_search(int[] nums, int target, boolean lower) {
        int low = 0;
        int high = nums.length - 1;
        int index = nums.length;
        while (low <= high) {
            int mid = (low + high) >> 1;
            // nums[mid] > target 查找第一个大于target的下标
            // (lower && nums[mid] >= target) 查找第一个大于大于target的下标
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                high = mid - 1;
                index = mid;
            } else {
                low = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        // 输入：nums = [5,7,7,8,8,10], target = 8
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));  // [3,4]
    }
}
