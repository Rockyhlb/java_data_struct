package matrix;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: matrix
 * @CreateTime : 2024/6/19 16:18
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * 示例 1：
     * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：[[1,0,1],[0,0,0],[1,0,1]]
     * 示例 2：
     * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
     * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
     * 提示：
     * m == matrix.length
     * n == matrix[0].length
     * 1 <= m, n <= 200
     * -231 <= matrix[i][j] <= 231 - 1
     */
    public static void setZeroes(int[][] matrix) {
        // 方法1：使用标记数组，进行两次遍历，时间复杂度：O(mn) 空间复杂度：O(m+n)
        // 第一次遍历寻找每行每列哪个位置含有0
        // 第二次遍历源数组，根据标记数组，将对应行列换为0
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[] row = new boolean[n];
        boolean[] column = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = column[j] = true;
                }
            }
        }
        // 第二次遍历
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes1(int[][] matrix) {
        // 方法2：将标记数组优化为标记变量，降低空间复杂度, 最终时间复杂度为：O(mn), 空间复杂度为：O(1)
        // 用 matrix 第一行和第一列记录该行该列是否有0,作为标志位
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // matrix = [[1,1,1],[1,0,1],[1,1,1]]
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix);
        // [1, 0, 1],
        // [0, 0, 0],
        // [1, 0, 1]
        System.out.println(Arrays.deepToString(matrix));
    }
}
