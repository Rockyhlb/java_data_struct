package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 12:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * 示例 1:
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     * 示例 2:
     * 输入：inorder = [-1], postorder = [-1]
     * 输出：[-1]
     */
    public static int postIndex;
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        return buildTreeChild(inorder, postorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTreeChild(int[] inorder, int[] postorder, int inStart, int inEnd) {
        // 1、递归终止条件
        if (inStart > inEnd) {
            return null;
        }
        // 2、创建根节点
        TreeNode root = new TreeNode(postorder[postIndex]);
        // 3、创建左右子树,由于后序遍历是“左右根”的顺序，因此递归创建子树时应该是 “根右左”的顺序
        int rootIndex = findRootIndex(inorder, inStart, inEnd, postorder[postIndex--]);
        if (-1 == rootIndex) {
            return null;
        }
        root.right = buildTreeChild(inorder, postorder, rootIndex + 1, inEnd);
        root.left = buildTreeChild(inorder, postorder, inStart, rootIndex - 1);
        return root;
    }

    private static int findRootIndex(int[] inorder, int inStart, int inEnd, int key) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        int[] inorder = {9,3,15,20,7};
        int[] postOrder = {9,15,7,20,3};
        System.out.println(buildTree(inorder, postOrder));
    }
}