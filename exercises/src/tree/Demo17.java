package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/16 11:27
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo17 {
    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * 示例 1：
     * 输入：root = [4,2,6,1,3]
     * 输出：1
     * 示例 2：
     * 输入：root = [1,0,48,null,null,12,49]
     * 输出：1
     */
    private static int pre;  // 记录前一个值
    private static int min;

    public static int getMinimumDifference(TreeNode root) {
        pre = -1;
        min = Integer.MAX_VALUE;
        dfs(root);
        return min;
    }

    private static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // 由于二叉搜索树中序遍历得到的序列是递增有序的，因此借用这个特性进行最小值的维护
        dfs(root.left);
        if (-1 == pre) {
            pre = root.val;
        } else {
            min = Math.min(min, Math.abs(root.val - pre));
            pre = root.val;
        }
        dfs(root.right);
    }

    public static void main(String[] args) {
        // 输入：root = [1,0,48,null,null,12,49]
        TreeNode root = new TreeNode(1, new TreeNode(0), new TreeNode(48));
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(49);
        System.out.println(getMinimumDifference(root));

        TreeNode root1 = new TreeNode(1, null, new TreeNode(5));
        root1.right.left = new TreeNode(3);
        System.out.println(getMinimumDifference(root1));
    }
}
