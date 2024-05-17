package graph;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/5/17 9:21
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 130. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * 示例 1：
     * 输入：board = [['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X','X']]
     * 输出：[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，
     * 或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     * 示例 2：
     * 输入：board = [['X']]
     * 输出：[['X']]
     */
    public static void solve(char[][] board) {
        if (board == null) {
            return;
        }
        int row = board.length;
        int column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                boolean isEdge = i == 0 || i == row - 1 || j == 0 || j == column - 1;
                // 查找和边界连通的'o'
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == 'O') {
                    // 当前'O'是被’X‘包围的’O‘
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    // 当前'O'是与边界连通的’O‘
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfs(char[][] grid, int row, int column) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length || grid[row][column] != 'O') {
            return;
        }
        // 当前是“X”时，返回true
        grid[row][column] = '#';  // 将与边界连通的‘o’标记为‘#’
        dfs(grid, row - 1, column);
        dfs(grid, row + 1, column);
        dfs(grid, row, column - 1);
        dfs(grid, row, column + 1);
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
