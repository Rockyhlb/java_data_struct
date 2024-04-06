import java.util.List;

/**
 * @BelongsProject: test-20240406
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/4/6 20:28
 * @Description: TODO
 * @Author: code_hlb
 */
public class demo2 {
    /**
     * 120. 三角形最小路径和
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1
     * 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     * 示例 1：
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 动态规划问题，我们用 dp[i][j] 表示从三角形顶部走到位置 (i,j) 的最小路径和
        // 状态转移方程为：dp[i][j] = min(dp[i−1][j−1],dp[i−1][j]) + c[i][j]
        int size = triangle.size();
        int[][] dp = new int[size][size];
        // dp[0][0] 就等于 第一行的数值
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            // 当j=0时,dp[i-1][j-1] 没有意义
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                // 由于每一步只能移动到下一行相邻的结点上，因此要想走到位置 (i,j)，上一步就只能在位置 (i−1,j−1) 或者位置 (i−1,j)
                dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]) + triangle.get(i).get(j);
            }
            // 当j=i时，dp[i-1][j] 没有意义
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }
        // 遍历找出dp[][]最后一行的各个虽小路径和
        int minTotal = dp[size-1][0];
        for (int i = 1; i < size; i++) {
            minTotal = Math.min(minTotal,dp[size-1][i]);
        }
        return minTotal;
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        // 解法2：通过从三角形的底开始向上转移
        // 动态规划问题，dp[i][j] = min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j)
        int size = triangle.size();
        int[][] dp = new int[size+1][size+1];
        // 控制行
        for(int i = size-1; i >= 0;i--) {
            // 控制列
            for(int j = 0; j <= i;j++) {
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
