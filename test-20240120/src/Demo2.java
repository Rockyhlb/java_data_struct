/**
 * @BelongsProject: test-20240120
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/20 17:18
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 1、给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。OJ链接  【LeetCode题号：45. 跳跃游戏 II】【中等】
     * 示例 1:
     * 输入: nums = [2,3,1,1,4]   输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 示例 2:
     * 输入: nums = [2,3,0,1,4]   输出: 2
     * 提示:
     * 1 <= nums.length <= 104
     * 0 <= nums[i] <= 1000
     */
    public int jump(int[] nums) {
        // 记录当前边界
        int end = 0;
        // 记录当前跳跃最大位置
        int maxPos = 0;
        // 记录跳跃步数
        int step = 0;
        // 跳跃游戏中，最后一个位置的数字不需要读取，因此此处的边界设置为 nums.length-1
        for (int i = 0; i < nums.length - 1; i++) {
            maxPos = Math.max(maxPos,i + nums[i]);
            if (i == end) {
                // 当i==end时，说明已经到达边界，因此需要更新新的边界并且步数++
                end = maxPos;
                step++;
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        Demo2 demo2 = new Demo2();
        System.out.println(demo2.jump(nums));
    }
}
