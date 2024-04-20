package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/4/19 21:50
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 示例 2：
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    public static int rob(int[] nums) {
        /**
         * 1、定义子问题：
         *    原问题：从 全部房子 中能偷到的最多金额
         *    子问题：从 k 个房子中能偷到的最多金额 f(k)  (原问题要能由子问题表示，子问题的解要能通过其它子问题的解求出)
         * 2、确定子问题的递推关系
         *    偷k个房子(最后一个房子是nums[k-1])有两种偷法：
         *    1) 不偷当前房子，则演变成前k-1个房子偷到的最大金额，即f(k-1)
         *    2) 偷当前房子,则不能偷前一个房子，演变成前k-2个房子偷到的最大金额加上当前房子的金额,即nums[k-1] + f(k-2)
         *    3) 最后归纳得出：f(k) = max{dp[k-1],nums[k-1] + dp[k-2]}
         * 3、确定dp数组的计算顺序：采用自底向上的dp数组，由上得出f(k) 依赖于f(k-1) 和 f(k-2),因此dp数组的计算顺序都是自左向右
         */
        int[] dp = new int[nums.length + 1];
        // 处理边界
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            // 当前房子的下标是 i-1
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i-1]);
        }
        return dp[nums.length];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int[] nums1 = {2, 7, 9, 3, 1};
        int[] nums2 = {2, 1, 1, 2};
        System.out.println(rob(nums));
        System.out.println(rob(nums1));
        System.out.println(rob(nums2));
    }
}
