package matrix;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: matrix
 * @CreateTime : 2024/6/18 8:34
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 48. 旋转图像
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     * 示例 2：
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     * 提示：
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 20
     * -1000 <= matrix[i][j] <= 1000
     */
    public static void rotate(int[][] matrix) {
        /**
         * 方法1：时间复杂度：O(n^2) 空间复杂度：O(1)
         * 观察旋转后的图像可知，第一行经过旋转后到达最后一列，第二行经过旋转到达倒数第二列，以此内推，
         * 对于第i行的第j个元素，经过旋转后，它出现在倒数第i列的第j个位置，matrix[i][j] = matrix[j][n-i-1]
         * {
         *     tmp = matrix[j][n-i-1];
         *     matrix[j][n-i-1] = matrix[i][j];
         * }
         * 再进行下一次变换，令i=j,j=n-i-1
         * {
         *      tmp = matrix[n-i-1][n-j-1];
         *      matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
         *      matrix[j][n-i-1] = matrix[i][j];
         * }
         * 再进行下一次变换，令i=n-i-1,j=n-j-1
         * {
         *      tmp = matrix[n-j-1][i];
         *      matrix[n−j−1][i] = matrix[n−i−1][n−j−1]
         *      matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
         *      matrix[j][n-i-1] = matrix[i][j];
         * }
         * 再进行下一次变换，令i=n-j-1,j=i, 得出：matrix[row][col]=matrix[n−col−1][row]，又回到了最开始的起点，
         * 因此：
         * {
         *      matrix[i][j]
         *      matrix[j][n-i-1]
         *      matrix[n-i-1][n-j-1]
         *      matrix[n-j-1][i]
         * }
         * 这四项处于循环之中，并且每一项旋转后的位置就是下一项所在位置，因此可以有：
         * {
         *     tmp = matrix[i][j];
         *     matrix[i][j] = matrix[n-j-1][i];
         *     matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
         *     matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
         *     matrix[j][n-i-1] = tmp;
         * }
         */
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }

    public static void rotate1(int[][] matrix) {
        // 方法2：先对矩阵进行装置，然后再将左右对称的两列进行互换
        int n = matrix.length;
        int tmp = 0;
        // 矩阵装置
        for (int i = 0; i < n; i++) {
            // 上一次装置已经将下一次转置的头元素交换过去，因此j从i+1开始装置
            for (int j = i + 1; j < n; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 将左右对称列互换
        for (int j = 0; j < n / 2; j++) {
            for (int i = 0; i < n; i++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        // 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));  // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]

        // 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate1(matrix1);
        System.out.println(Arrays.deepToString(matrix1));  // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    }
}
