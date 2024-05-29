package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/28 20:06
 * @Description: 多维动规
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 示例 1：
     * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
     * 输出：7
     * 解释：因为路径 1→3→1→1→1 的总和最小。
     * 示例 2：
     * 输入：grid = [[1,2,3],[4,5,6]]
     * 输出：12
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 200
     */
    public static int minPathSum(int[][] grid) {
        /**
         * 动规思想：假设dp[i][j] 表示走到路径[i,j]的最小路径和，由题意知路径只能往下或者往右走，
         * 也就是说,[i,j]只能由[i-1,j] or [i,j-1]得到，又由于要求最小路径和，
         * 因此[i,j]一定是由Math.min([i-1,j],[i,j-1])得出;
         * 边界：当i==0&&j==0时，dp[i][j] = grid[i][j]
         *      当i==0时，dp[i][j] = dp[i,j-1] + grid[i][j]
         *      当j==0时，dp[i][j] = dp[i-1][j] + grid[i][j]
         * 此处还可以将dp数组优化，只需操作原数组即可，最后返回终点元素，避免无效的空间开销
         */
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    // 当i==0时，说明正处于第一行元素，因此当前元素只能由同行的上一列元素得到
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    // 当j==0时，说明正处于第一列元素，因此当前元素只能由同列的上一行元素得到
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));   // 7
    }
}
