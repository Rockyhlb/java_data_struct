package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/21 15:17
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 167. 两数之和 II - 输入有序数组
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数 target 的两个数。
     * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * 示例 1：
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     */
    public static int[] twoSum(int[] numbers, int target) {
        // 双指针
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (target == sum) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                // 当前 两数和 大于目标值
                right--;
            } else {
                // 当前两数和 小于目标值
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSum1(int[] numbers, int target) {
        // 二分查找
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1;
            int high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (target - numbers[i] == numbers[mid]) {
                    return new int[]{i + 1, mid + 1};
                } else if (target - numbers[i] > numbers[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int[] numbers1 = {5, 25, 75};
        System.out.println(Arrays.toString(twoSum(numbers, 9)));
        System.out.println(Arrays.toString(twoSum(numbers1, 100)));

        int[] numbers2 = {2, 7, 11, 15};
        int[] numbers3 = {5, 25, 75};
        System.out.println(Arrays.toString(twoSum1(numbers2, 9)));
        System.out.println(Arrays.toString(twoSum1(numbers3, 100)));
    }
}