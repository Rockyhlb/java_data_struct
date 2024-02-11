import java.util.List;

/**
 * @BelongsProject: test-20240211
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/11 22:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一
     * 层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i+ 1 .
     *【LeetCode题号：120. 三角形最小路径和】【中等】
     *
     * 示例 1：
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]    输出：11
     * 解释：如下面简图所示：
     * 2
     * 3 4
     * 6 5 7
     * 4 1 8 3
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * 示例 2：
     * 输入：triangle = [[-10]]     输出：-10
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 动态规划问题，dp[i][j] = min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j)
        int size = triangle.size();
        // dp[i][j] 表示从点[i,j]到底边的最小路径和
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
