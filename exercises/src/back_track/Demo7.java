package back_track;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/27 21:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * 示例 1：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     * 示例 2：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
     * 输出：true
     * 示例 3：
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
     * 输出：false
     * 提示：
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board 和 word 仅由大小写英文字母组成
     */
    public static boolean exist(char[][] board, String word) {
        // 方法1：新建标记数组进行回溯
        int row = board.length;
        int column = board[0].length;
        // 记录数组遍历过的位置
        boolean[][] visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (check(board, visited, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(char[][] board, boolean[][] visited, String word, int i, int j, int index) {
        if (board[i][j] != word.charAt(index)) {
            // 当前元素和word[index]不同时，返回false
            return false;
        } else if (index == word.length() - 1) {
            // 遍历完字符串时
            return true;
        }
        // 标记当前遍历元素
        visited[i][j] = true;
        // 设置遍历方向，默认为上下左右四个方向
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] direct : directions) {
            int newi = i + direct[0];
            int newj = j + direct[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                // 递归进入未被遍历过的元素
                if (!visited[newi][newj] && check(board, visited, word, newi, newj, index + 1)) {
                    // 回溯当前遍历元素
                    visited[i][j] = false;
                    return true;
                }
            }
        }
        // 回溯当前遍历元素
        visited[i][j] = false;
        return false;
    }

    public static boolean exist1(char[][] board, String word) {
        // 方法2：利用原数组进行回溯
        int row = board.length;
        int column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // 初步判断
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                if (backTrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean backTrack(char[][] board, String word, int i, int j, int index) {
        // 处理边界
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            // 遍历完word
            return true;
        }
        // 标记已遍历元素
        char tmp = board[i][j];
        board[i][j] = '#';
        boolean res = backTrack(board, word, i, j + 1, index + 1) || backTrack(board, word, i, j - 1, index + 1) || backTrack(board, word, i + 1, j, index + 1) || backTrack(board, word, i - 1, j, index + 1);
        // 回溯之前的已遍历元素
        board[i][j] = tmp;
        return res;
    }

    public static void main(String[] args) {
        // 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));
        System.out.println(exist1(board, word));
    }
}
