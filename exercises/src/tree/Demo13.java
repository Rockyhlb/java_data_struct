package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/15 15:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo13 {
    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 示例 1：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     * 示例 2：
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出：5
     * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
     * 示例 3：
     * 输入：root = [1,2], p = 1, q = 2
     * 输出：1
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件
        if (root == null) {
            return null;
        }
        // 当有个节点等于根节点时，返回根节点
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        // 递归遍历左子树/右子树
        TreeNode resLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode resRight = lowestCommonAncestor(root.right, p, q);
        // 当左右子树都不为空时，返回当前root
        if (resLeft != null && resRight != null) {
            return root;
        } else if (resLeft != null) {
            return resLeft;
        } else {
            return resRight;
        }
    }

    public static void main(String[] args) {
        // 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        TreeNode root = new TreeNode(3, new TreeNode(5), new TreeNode(1));
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        System.out.println(lowestCommonAncestor(root, p, q));

        // 输入：root = [1,2], p = 1, q = 2    输出：1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        TreeNode p1 = new TreeNode(1);
        TreeNode q1 = new TreeNode(2);
        System.out.println(lowestCommonAncestor(root1, p1, q1));
    }
}