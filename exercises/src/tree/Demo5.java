package tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 12:01
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * 示例 1:
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * 示例 2:
     * 输入: preorder = [-1], inorder = [-1]
     * 输出: [-1]
     * 提示:
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder 和 inorder 均 无重复 元素
     * inorder 均出现在 preorder
     * preorder 保证 为二叉树的前序遍历序列
     * inorder 保证 为二叉树的中序遍历序列
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 递归创建
        return buildTreeChiild(preorder, inorder, 0, inorder.length - 1);
    }

    public static int preIndex = 0;  // 限制前序遍历索引

    private static TreeNode buildTreeChiild(int[] preorder, int[] inorder, int inStart, int inEnd) {
        // 1、处理边界
        if (inStart > inEnd) {
            return null;
        }
        // 2、创建节点
        TreeNode root = new TreeNode(preorder[preIndex]);
        // 3、确定左右子树
        int rootIndex = findRootIndex(inorder, preorder[preIndex++], inStart, inEnd);
        root.left = buildTreeChiild(preorder, inorder, inStart, rootIndex - 1);
        root.right = buildTreeChiild(preorder, inorder, rootIndex + 1, inEnd);
        return root;
    }

    private static int findRootIndex(int[] inOrder, int key, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};

        System.out.println(buildTree(pre, in));
    }
}
