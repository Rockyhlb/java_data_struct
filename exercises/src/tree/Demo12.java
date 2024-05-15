package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/15 11:23
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo12 {
    /**
     * 222. 完全二叉树的节点个数
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
     * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * 示例 1：
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     * 示例 2：
     * 输入：root = []
     * 输出：0
     * 示例 3：
     * 输入：root = [1]
     * 输出：1
     * 提示：
     * 树中节点的数目范围是[0, 5 * 104]
     * 0 <= Node.val <= 5 * 104
     * 题目数据保证输入的树是 完全二叉树
     * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     */
    public static int countNodes(TreeNode root) {
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        // 时间复杂度 和 空间复杂度都是 O(n)
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        return left + right + 1;
    }

    public static void main(String[] args) {
        // 输入：root = [1,2,3,4,5,6]    输出：6
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        System.out.println(countNodes(root));
    }
}
