package divide;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: divide
 * @CreateTime : 2024/6/10 20:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 427. 建立四叉树
     * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
     * 你需要返回能表示矩阵 grid 的 四叉树 的根结点。
     * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
     * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False。
     * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
     * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
     * class Node {
     * public boolean val;
     * public boolean isLeaf;
     * public Node topLeft;
     * public Node topRight;
     * public Node bottomLeft;
     * public Node bottomRight;
     * }
     * 我们可以按以下步骤为二维区域构建四叉树：
     * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
     * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
     * 使用适当的子网格递归每个子节点。
     * 如果你想了解更多关于四叉树的内容，可以参考 wiki 。
     * 四叉树格式：
     * 你不需要阅读本节来解决这个问题。只有当你想了解输出格式时才会这样做。输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
     * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
     * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
     * 示例 1：
     * 输入：grid = [[0,1],[1,0]]
     * 输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
     * 解释：此示例的解释如下：
     * 请注意，在下面四叉树的图示中，0 表示 false，1 表示 True 。
     * 示例 2：
     * 输入：grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
     * 输出：[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
     * 解释：网格中的所有值都不相同。我们将网格划分为四个子网格。
     * topLeft，bottomLeft 和 bottomRight 均具有相同的值。
     * topRight 具有不同的值，因此我们将其再分为 4 个子网格，这样每个子网格都具有相同的值。
     * 解释如下图所示：
     * 提示：
     * n == grid.length == grid[i].length
     * n == 2x 其中 0 <= x <= 6
     */
    public static QuadTreeNode construct(int[][] grid) {
        // 方法1：递归分组
        return dfs(grid, 0, 0, grid.length, grid[0].length);
    }

    private static QuadTreeNode dfs(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
        // 遍历给定区间上的所有元素，若相同则说明是叶子节点，反之则说明不是叶子节点
        boolean isSame = true;
        int flag = grid[startRow][startCol];  // 作为基准
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                if (grid[i][j] != flag) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) {
                break;
            }
        }
        if (isSame) {
            // 返回构造叶子节点, val: 1 => true , 0 => false
            return new QuadTreeNode(flag == 1, true);
        }
        // 非叶子节点，往下递归
        // 当 isLeaf 为 False 时，把 True 或者 False 赋值给节点都可以
        QuadTreeNode node = new QuadTreeNode(true, false);
        node.topLeft = dfs(grid, startRow, startCol, (startRow + endRow) / 2, (startCol + endCol) / 2);
        node.topRight = dfs(grid, startRow, (startCol + endCol) / 2, (startRow + endRow) / 2, endCol);
        node.bottomLeft = dfs(grid, (startRow + endRow) / 2, startCol, endRow, (startCol + endCol) / 2);
        node.bottomRight = dfs(grid, (startRow + endRow) / 2, (startCol + endCol) / 2, endRow, endCol);
        return node;
    }

    public static QuadTreeNode construct1(int[][] grid) {
        // 方法2：引入 前缀和 对每次递归都需要对该区域进行 暴力判定 进行优化
        int row = grid.length;
        int col = grid[0].length;
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        return dfs1(grid, prefix, 0, 0, row, col);
    }

    private static QuadTreeNode dfs1(int[][] grid, int[][] prefix, int startRow, int startCol, int endRow, int endCol) {
        // 获取前缀和
        int totalSum = getSum(prefix, startRow, startCol, endRow, endCol);
        // 遍历给定区间上的前缀和，若全为1则totalSum为面积，若全为0，totalSum为0
        if (totalSum == 0) {
            // 返回构造叶子节点, val: 1 => true , 0 => false
            return new QuadTreeNode(false, true);
        } else if (totalSum == (endCol - startCol) * (endRow - startRow)) {
            return new QuadTreeNode(true, true);
        }
        // 非叶子节点，往下递归
        // 当 isLeaf 为 False 时，把 True 或者 False 赋值给节点都可以
        QuadTreeNode node = new QuadTreeNode(true, false);
        node.topLeft = dfs1(grid, prefix, startRow, startCol, (startRow + endRow) / 2, (startCol + endCol) / 2);
        node.topRight = dfs1(grid, prefix, startRow, (startCol + endCol) / 2, (startRow + endRow) / 2, endCol);
        node.bottomLeft = dfs1(grid, prefix, (startRow + endRow) / 2, startCol, endRow, (startCol + endCol) / 2);
        node.bottomRight = dfs1(grid, prefix, (startRow + endRow) / 2, (startCol + endCol) / 2, endRow, endCol);
        return node;
    }

    private static int getSum(int[][] prefix, int startRow, int startCol, int endRow, int endCol) {
        return prefix[endRow][endCol] - prefix[endRow][startCol] - prefix[startRow][endCol] + prefix[startRow][startCol];
    }

    public static void main(String[] args) {
        // 输入：grid = [[0,1],[1,0]]
        int[][] grid = {{0, 1}, {1, 0}};
        // 输出：[[0,1],[1,0],[1,1],[1,1],[1,0]]
        System.out.println(construct(grid));
        System.out.println(construct1(grid));
    }
}