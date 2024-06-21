package matrix;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: matrix
 * @CreateTime : 2024/6/21 21:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 289. 生命游戏
     * 根据 百度百科，生命游戏，简称为 生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
     * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
     * 每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。
     * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
     * 示例 1：
     * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
     * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
     * 示例 2：
     * 输入：board = [[1,1],[1,0]]
     * 输出：[[1,1],[1,1]]
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 25
     * board[i][j] 为 0 或 1
     */
    public static void gameOfLife(int[][] board) {
        // 方法1：模拟,时间复杂度：O(nm) 空间复杂度：O(nm)
        // 可以将规则简化为：1、活细胞周围只能有两个或三个活细胞，反之死亡  2、死细胞周围有三个活细胞，死细胞复活
        int row = board.length;
        int column = board[0].length;
        // 建立缓存数组
        int[][] tmp = new int[row][column];
        // 暴力遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int alive = countAlive(board, i, j);
                if (board[i][j] == 1) {
                    // 活细胞
                    if (alive == 2 || alive == 3) {
                        tmp[i][j] = 1;
                    }
                } else {
                    // 死细胞
                    if (alive == 3) {
                        tmp[i][j] = 1;
                    }
                }
            }
        }
        // 将临时数组写回源数组
        for (int i = 0; i < row; i++) {
            // System.arraycopy(tmp[i], 0, board[i], 0, column);
            for (int j = 0; j < column; j++) {
                board[i][j] = tmp[i][j];
            }
        }
    }

    private static int countAlive(int[][] board, int row, int column) {
        // 计算当前相邻元素的活细胞个数
        int alive = 0;
        int[][] direct = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // 直接定义几个方向
        for (int[] dir : direct) {
            int x = row + dir[0];
            int y = column + dir[1];
            if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1) {
                continue;
            }
            alive += (board[x][y] & 1); // 由于board[x][y]只有两种状态(0/1)，因此此处可以直接将其和1进行按位与运算
        }
        return alive;
    }

    public static void gameOfLife1(int[][] board) {
        /*
         * 方法2：使用额外的状态对方法1进行优化，时间复杂度：O(nm) 空间复杂度：O(1)
         * 状态-1：之前是活细胞，现在由于规则而变成死细胞
         * 状态1：之前是活细胞，现在还是活细胞
         * 状态2：之前是死细胞，现在由于规则变成活细胞
         * */
        int row = board.length;
        int column = board[0].length;
        // 第一次遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int alive = countAlive1(board, i, j);
                if (1 == board[i][j]) {
                    // 当前细胞是活细胞, 由于周围活细胞小于2 || 大于3 变成死细胞
                    if (alive < 2 || alive > 3) {
                        board[i][j] = -1;
                    }
                } else {
                    // 当前细胞是死细胞,由于周围活细胞恰好为3个，因此变为活细胞
                    if (alive == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        // 第二次遍历 将2修改为1，将-1修改为0
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (2 == board[i][j]) {
                    board[i][j] = 1;
                }
                if (-1 == board[i][j]) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static int countAlive1(int[][] board, int row, int column) {
        // 计算当前相邻元素的活细胞个数
        int alive = 0;
        // 右边元素
        if (column < board[0].length - 1) {
            // 中右
            if (board[row][column + 1] == 1 || board[row][column + 1] == -1) {
                alive++;
            }
            // 右上
            if (row > 0 && (board[row - 1][column + 1] == 1 || board[row - 1][column + 1] == -1)) {
                alive++;
            }
            // 右下
            if (row < board.length - 1 && (board[row + 1][column + 1] == 1 || board[row + 1][column + 1] == -1)) {
                alive++;
            }
        }
        // 左边元素
        if (column > 0) {
            // 中左
            if (board[row][column - 1] == 1 || board[row][column - 1] == -1) {
                alive++;
            }
            // 左上
            if (row > 0 && (board[row - 1][column - 1] == 1 || board[row - 1][column - 1] == -1)) {
                alive++;
            }
            // 左下
            if (row < board.length - 1 && (board[row + 1][column - 1] == 1 || board[row + 1][column - 1] == -1)) {
                alive++;
            }
        }
        // 中上
        if (row > 0 && (board[row - 1][column] == 1 || board[row - 1][column] == -1)) {
            alive++;
        }
        // 中下
        if (row < board.length - 1 && (board[row + 1][column] == 1 || board[row + 1][column] == -1)) {
            alive++;
        }
        return alive;
    }

    public static void main(String[] args) {
        // board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife(board);
        System.out.println(Arrays.deepToString(board)); // [[0, 0, 0], [1, 0, 1], [0, 1, 1], [0, 1, 0]]

        // board1 = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        int[][] board1 = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife1(board1);
        System.out.println(Arrays.deepToString(board1)); // [[0, 0, 0], [1, 0, 1], [0, 1, 1], [0, 1, 0]]
    }
}
