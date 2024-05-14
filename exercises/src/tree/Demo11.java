package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/14 8:38
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo11 {
    /**
     * 124. 二叉树中的最大路径和
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
     * 该路径 至少包含一个 节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * 示例 1：
     * 输入：root = [1,2,3]
     * 输出：6
     * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
     * 示例 2：
     * 输入：root = [-10,9,20,null,null,15,7]
     * 输出：42
     * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     */
    public static int maxPath = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPath;
    }

    private static int dfs(TreeNode root) {
        // 递归终止条件
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int count = root.val;
        if (left > 0) {
            count += left;
        }
        if (right > 0) {
            count += right;
        }
        maxPath = Math.max(maxPath, count);
        // 贪心思想，只引入最大贡献
        int max = Math.max(left, right);
        return max > 0 ? root.val + max : root.val;
    }

    private static int dfs1(TreeNode root) {
        // 递归终止条件
        if (root == null) {
            return 0;
        }
        // 递归计算左右字节点的最大贡献值
        // 只选取最大贡献值大于0的节点
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        int count = root.val + left + right;
        maxPath = Math.max(maxPath, count);
        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // root = [1,2,3]
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(maxPathSum(root));
    }
}