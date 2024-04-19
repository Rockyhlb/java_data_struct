package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/4/19 20:00
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * 示例 1：
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // 相同的树，则左子树和右子树也一定相同
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        boolean leftRes = isSameTree(p.left, q.left);
        boolean rightRes = isSameTree(p.right, q.right);
        return leftRes && rightRes;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);

        System.out.println(isSameTree(root, root1));
    }
}
