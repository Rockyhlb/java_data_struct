package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/4 8:40
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 55. 跳跃游戏
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * 示例 2：
     * 输入：nums = [3,2,1,0,4]
     * 输出：false
     * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
     * 提示：
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 105
     */
    public static boolean canJump(int[] nums) {
        // 贪心思想，维护一个最远变量，每一次跳跃都对其更新
        int farthest = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]);
                // 当前最远位置已经越过数组末尾时，返回true
                if (farthest >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // nums = [2,3,1,1,4]
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
    }
}