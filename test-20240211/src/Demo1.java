/**
 * @BelongsProject: test-20240211
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/11 21:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 、一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？OJ链接 【LeetCode题号：62. 不同路径】【中等】
     * 示例 1：
     * 输入：m = 3, n = 7    输出：28
     * 示例 2：
     * 输入：m = 3, n = 2    输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * 示例 3：
     * 输入：m = 7, n = 3    输出：28
     * 示例 4：
     * 输入：m = 3, n = 3    输出：6
     * 提示：
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 109
     */
   /* 时间复杂度过高！
   public int uniquePaths(int m, int n) {
        return dfs(1,1,m,n);
    }
    private static int dfs(int startCol,int startRow,int endCol,int endRow) {
        if(startCol == endCol && startRow == endRow) {
            return 1;
        }
        int count = 0;
        if(startCol < endCol) {
            count += dfs(startCol+1,startRow,endCol,endRow);
        }
        if(startRow < endRow) {
            count += dfs(startCol,startRow+1,endCol,endRow);
        }
        return count;
    }*/
    public int uniquePaths(int m, int n) {
        // 动态规划问题，由于我们只能向右或者向下移动，因此当前的路径一定等于上一次向下或者向右的路径之和
        // 用f[i,j]表示从起点走到(i,j)的路径数量，可以表示出：f(m, n) = f(m - 1, n) + f(m, n - 1)
        // 起始位置为(0, 0) 终点位置为(m - 1, n - 1)
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 只有一行或者一列都是只有一条路径时
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        System.out.println(demo1.uniquePaths(3, 7));
    }
}
