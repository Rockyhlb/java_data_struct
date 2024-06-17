package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: matrix
 * @CreateTime : 2024/6/17 14:35
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        // 方法1：模拟,时间复杂度及空间复杂度都是：O(m*n)
        List<Integer> ans = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        // 记录当前已经遍历过的元素
        boolean[][] visited = new boolean[rows][columns];
        // 模拟顺时针四个方向的旋转
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = 0, column = 0;
        int curDirectIndex = 0; // 初始化当前方向
        // 遍历结束条件: 路径长度等于矩阵元素数量，因此当list长度等于矩阵元素个数时，说明遍历结束
        int total = rows * columns;
        for (int i = 0; i < total; i++) {
            ans.add(matrix[row][column]);
            visited[row][column] = true;
            // 判断下一个元素的下标是否已经到达边界
            int newRow = row + directions[curDirectIndex][0];
            int newCol = column + directions[curDirectIndex][1];
            // 下一个元素已经到达边界or已经被遍历过时，调整方向(curDirectIndex)
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= columns || visited[newRow][newCol]) {
                curDirectIndex = (curDirectIndex + 1) % 4;
            }
            row += directions[curDirectIndex][0];
            column += directions[curDirectIndex][1];
        }
        return ans;
    }

    public static List<Integer> spiralOrder1(int[][] matrix) {
        // 方法2：定义四个指针进行旋转遍历，时间复杂度：O(mn), 空间复杂度：O(1)
        List<Integer> ans = new ArrayList<>();
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 往右遍历
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;
            // 往下遍历
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            // 往左遍历
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
            }
            bottom--;
            // 往上遍历
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
            }
            left++;
        }
        return ans;
    }

    public static List<Integer> spiralOrder2(int[][] matrix) {
        // 方法3：对方法2进一步优化，时间复杂度：O(mn), 空间复杂度：O(1)
        List<Integer> ans = new ArrayList<>();
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (true) {
            // 往右遍历
            for (int i = left; i <= right; i++) ans.add(matrix[top][i]);
            if (++top > bottom) break;
            // 往下遍历
            for (int i = top; i <= bottom; i++) ans.add(matrix[i][right]);
            if (--right < left) break;
            // 往左遍历
            for (int i = right; i >= left; i--) ans.add(matrix[bottom][i]);
            if (--bottom < top) break;
            // 往上遍历
            for (int i = bottom; i >= top; i--) ans.add(matrix[i][left]);
            if (++left > right) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(spiralOrder(matrix));  // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(spiralOrder1(matrix));  // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(spiralOrder2(matrix));  // [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }
}
