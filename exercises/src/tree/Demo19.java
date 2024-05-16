package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/16 14:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo19 {
    /**
     * 98. 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1：
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     */
    public static boolean isValidBST(TreeNode root) {
//        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return dfs1(root);
    }

    private static boolean dfs(TreeNode root, long lower, long upper) {
        // 方法1：递归
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        return dfs(root.left, lower, root.val) && dfs(root.right, root.val, upper);
    }

    private static int pre = Integer.MIN_VALUE;

    private static boolean dfs1(TreeNode root) {
        // 方法2：中序遍历递归，判断当前值是否小于前一个元素
        if (root == null) {
            return true;
        }
        boolean resLeft = dfs1(root.left);
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        boolean resRight = dfs1(root.right);
        return resLeft && resRight;
    }

    public static void main(String[] args) {
        // 输入：root = [5,1,4,null,null,3,6]  输出：false
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(4));
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(isValidBST(root));

        // 输入：root = [5,4,6,null,null,3,7]  输出：false
        TreeNode root1 = new TreeNode(5, new TreeNode(4), new TreeNode(6));
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        System.out.println(isValidBST(root1));
    }
}
