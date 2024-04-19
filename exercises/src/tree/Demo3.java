package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/4/19 20:10
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     */
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        if (root.left != null || root.right != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);

        System.out.println(root);
        TreeNode invert = invertTree(root);
        System.out.println(invert);
    }
}
