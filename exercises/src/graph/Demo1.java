package graph;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/5/17 8:49
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * 示例 1：
     * 输入：grid = [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     * 输入：grid = [
     * ["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]
     * ]
     * 输出：3
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     */
    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 当前节点是'陆地'时，进入递归，计数+1
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        // 递归终止条件
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        // 标记被遍历过的位置
        grid[i][j] = '2';
        dfs(grid, i - 1, j); // 往上遍历
        dfs(grid, i + 1, j); // 往下遍历
        dfs(grid, i, j - 1); // 往左遍历
        dfs(grid, i, j + 1); // 往右遍历
    }

    public static void main(String[] args) {
        // 输入：["1","1","0","0","0"],
        //      ["1","1","0","0","0"],
        //      ["0","0","1","0","0"],
        //      ["0","0","0","1","1"]
        // 输出：3
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }
}
