package binary_search;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: binary_search
 * @CreateTime : 2024/5/18 15:44
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 74. 搜索二维矩阵
     * 给你一个满足下述两条属性的 m x n 整数矩阵：
     * 每行中的整数从左到右按非严格递增顺序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * 输出：true
     * 示例 2：
     * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * 输出：false
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        // 先普通查找确定行
        int row = 0;
        for (int i = matrix.length - 1; i >= 0; i--) {
            if (matrix[i][0] <= target) {
                row = i;
                break;
            }
        }
        // 二分查找 列
        int low = 0;
        int high = matrix[row].length - 1;
        while (low <= high) {
            int mid = (high + low) >>> 1;
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] > target) {
                high = mid - 1;
            }
            if (matrix[row][mid] < target) {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchMatrix(matrix, 33));
    }
}
