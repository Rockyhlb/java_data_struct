package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/29 9:26
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     * 提示：
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] 为 0 或 1
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int[] dp = new int[m];
        // 避免起始点就是障碍物
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int[] elem : obstacleGrid) {
            for (int j = 0; j < elem.length; j++) {
                // 遇见障碍物时，标记没有路径可以到达该坐标
                if (elem[j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && elem[j - 1] == 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }

    public static void main(String[] args) {
        //  输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
