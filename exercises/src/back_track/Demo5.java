package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/27 10:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 52. N 皇后 II
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
     * 示例 1：
     * 输入：n = 4
     * 输出：2
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     * 输入：n = 1
     * 输出：1
     * 提示：
     * 1 <= n <= 9
     */
    public static int totalNQueens(int n) {
        /**
         * 皇后规则：只能走行列和斜线，此处要求皇后彼此之间不能相互攻击，
         * 因此就需要保证每个皇后所在行列和斜线上都不包含其他皇后，因此一行肯定只能有一个皇后
         */
        Set<Integer> columns = new HashSet<>(); // 记录当前所在列是否已经有皇后
        Set<Integer> diagonals1 = new HashSet<>(); // 记录当前正斜线是否包含皇后
        Set<Integer> diagonals2 = new HashSet<>(); // 记录当前反斜线是否包含皇后
        return backTrack(n, 0, columns, diagonals1, diagonals2);
    }

    private static int backTrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (n == row) {
            // 皇后已经全部放置完毕
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    // 当前列存在其她皇后,跳过本次循环
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    // 当前斜线存在其她皇后
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    // 当前斜线存在其她皇后
                    continue;
                }
                // 放置皇后
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backTrack(n, row + 1, columns, diagonals1, diagonals2);
                // 回溯
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(1));  // 1
        System.out.println(totalNQueens(4));  // 2
    }
}
