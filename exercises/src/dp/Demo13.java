package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/6/15 17:05
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo13 {
    /**
     * 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * 示例 1：
     * 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
     * 输出：4
     * 示例 2：
     * 输入：matrix = [['0','1'],['1','0']]
     * 输出：1
     * 示例 3：
     * 输入：matrix = [['0']]
     * 输出：0
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 300
     * matrix[i][j] 为 '0' 或 '1'
     */
    public static int maximalSquare(char[][] matrix) {
        /**
         * 动规：定义dp[i,j]表示以[i,j]，且只包含1的正方形最长边
         * 递推关系：
         * 1、如果当前[i,j]的值是0,则dp[i,j]只能为0,因为0不能出现在正方形中
         * 2、如果当前[i,j]的值是1,则dp[i,j]由上方，左上，左三个相邻位置的dp值确定
         * 状态转移方程：
         * 1、边界：当[i,j]值为1时，dp[i,j]也只能为1
         * 2、其它情况：dp[i,j] = 1 + min(dp[i-1,j], dp[i-1,j-1], dp[i,j-1]);
         * matrix[][]:
         * 1 0 1 0 0
         * 1 0 1 1 1
         * 1 1 1 1 1
         * 1 0 0 1 0
         * dp[][]:
         * 1 0 1 0 0
         * 1 0 1 1 1
         * 1 1 1 2 2
         * 1 0 0 1 0
         */
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxSide = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // 边界
                        dp[i][j] = 1;
                    } else {
                        // 其它情况
                        dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j]));
                    }
                }
                // 更新maxSide,不能直接返回dp[i][j]，因为如果matrix[i][j] == '0'，则dp[i][j]也会为0，因此只能依靠记录最大边返回
                maxSide = Math.max(maxSide, dp[i][j]);
                // 当前值为0时，dp[i][j]默认为0
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        // 输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        System.out.println(maximalSquare(matrix));  // 4
    }
}
