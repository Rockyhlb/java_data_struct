package matrix;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: matrix
 * @CreateTime : 2024/6/17 10:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 36. 有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 注意：
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     * 空白格用 '.' 表示。
     * 示例 1：
     * 输入：board =
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * 输出：true
     * 示例 2：
     * 输入：board =
     * [["8","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * 输出：false
     * 解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
     * 提示：
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字（1-9）或者 '.'
     */
    public static boolean isValidSudoku(char[][] board) {
        // 方法1：暴力搜索
        int row = board.length;
        int column = board[0].length;
        Set<Character> set = new HashSet<>();
        // 遍历行
        for (char[] chars : board) {
            for (int j = 0; j < column; j++) {
                if (chars[j] != '.' && set.contains(chars[j])) {
                    return false;
                }
                set.add(chars[j]);
            }
            set.clear();
        }
        // 遍历列
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (board[j][i] != '.' && set.contains(board[j][i])) {
                    return false;
                }
                set.add(board[j][i]);
            }
            set.clear();
        }
        // 遍历九宫格
        for (int i = 0; i < row; i += 3) {
            int tmpi = i;
            for (int j = 0; j < column; j += 3) {
                int tmpj = j;
                while (tmpi != i + 3) {
                    char ch = board[tmpi][tmpj++];
                    if (ch != '.') {
                        if (set.contains(ch)) {
                            return false;
                        }
                        set.add(ch);
                    }
                    // 遍历九宫格的下一行
                    if (tmpj == j + 3) {
                        tmpj = j;
                        tmpi++;
                    }
                }
                // 当前九宫格遍历结束
                tmpi = i;
                set.clear();
            }
        }
        return true;
    }

    public static boolean isValidSudoku1(char[][] board) {
        // 一次遍历，采用Hash表存储元素在任意维度上的存储情况
        // 维度有三个，分别是：所在行，所在列，以及所在九宫格
        int[][] row = new int[9][10];  // 题目给出数字范围为0~9,因此Hash范围也为0~9,因此大小应该为10
        int[][] column = new int[9][10];
        int[][] box = new int[9][10];
        // 题目已经确定输入数组大小为 9*9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                // 将字符转换为数值下标
                int curIndex = board[i][j] - '0';
                // 0 - 当前维度不包含当前字符，1 - 当前维度已经包含当前字符
                if (1 == row[i][curIndex]) {
                    return false;
                }
                if (1 == column[j][curIndex]) {
                    return false;
                }
                // 当前处于第几个九宫格，由i/j确定，
                // 如果当前数组大小为 3 * 9，则由 j / 3即可得出是第几个九宫格
                // 递推到 9 * 9 的数组，则 式子应该变化为 j / 3 + (i / 3) * 3;
                if (1 == box[j / 3 + (i / 3) * 3][curIndex]) {
                    return false;
                }
                row[i][curIndex] = 1;
                column[j][curIndex] = 1;
                box[j / 3 + (i / 3) * 3][curIndex] = 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudoku1(board));
    }
}
