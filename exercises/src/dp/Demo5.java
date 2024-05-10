package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/10 8:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     */
    public static int lengthOfLIS(int[] nums) {
        // 动规
        // 子问题：从k个元素当中获取最长递增子序列的长度
        // 递推关系：当前值大于之前的值时, dp[i] = Math.max(dp[i], dp[j] + 1); 反之默认为1
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // 输入：nums = [10,9,2,5,3,7,101,18]  输出：4
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums1 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS(nums1));
    }
}
