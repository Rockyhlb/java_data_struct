package sliding_window;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sliding_window
 * @CreateTime : 2024/5/21 14:17
 * @Description: 滑动窗口
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组
     * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 示例 2：
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     * 提示：
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     */
    public static int minSubArrayLen(int target, int[] nums) {
        // 方法1：暴力循环，时间复杂度：O(n^2)
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int count = nums[i];
            int len = 1;
            // 任意一个值大于target，直接返回1
            if (count >= target) {
                return 1;
            }
            // 开始遍历
            for (int j = i + 1; j < nums.length; j++) {
                count += nums[j];
                if (count >= target) {
                    minLen = Math.min(minLen, ++len);
                    break;
                }
                len++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static int minSubArrayLen1(int target, int[] nums) {
        // 方法2：前缀和 + 二分查找, 时间复杂度：O( nlog(n) )
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] sum = new int[len + 1];
        int res = Integer.MAX_VALUE;
        sum[0] = 0;  // 初始化第一个元素，sum[i] 表示前i个元素的前缀和
        // 前缀和(由于nums[]都是正数，因此前缀严格递增)
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 二分查找
        for (int i = 1; i <= len; i++) {
            int tag = target + sum[i - 1];
            // 二分查找得到大于或等于 iii 的最小下标 bound
            int bound = Arrays.binarySearch(sum, tag);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= len) {
                res = Math.min(res, bound - (i - 1));
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        // 方法3：滑动窗口,  时间复杂度：O(n)
        int len = nums.length;
        if (0 == len) {
            return 0;
        }
        int start = 0, end = 0;
        int res = Integer.MAX_VALUE;
        int sum = 0;
        while (end < len) {
            sum += nums[end];
            while (sum >= target) {
                // 更新最小长度
                res = Math.min(res, end - start + 1);
                sum -= nums[start++];
            }
            end++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        // 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        int[] nums1 = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        System.out.println(minSubArrayLen(11, nums));
        System.out.println(minSubArrayLen1(11, nums));
        System.out.println(minSubArrayLen2(11, nums));

        System.out.println(minSubArrayLen(213, nums1));
        System.out.println(minSubArrayLen1(213, nums1));
        System.out.println(minSubArrayLen2(213, nums1));
    }
}
