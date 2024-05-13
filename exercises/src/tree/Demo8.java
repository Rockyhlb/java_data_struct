package tree;

import java.util.HashMap;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 14:49
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 示例 1：
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * 提示：
     * 树中结点数在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
     */
    public static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                // 左子树不为空
                // 1、找出当前左子树的最右节点
                TreeNode preRight = cur.left;
                while (preRight.right != null) {
                    preRight = preRight.right;
                }
                // 2、将当前右子树拼接到preRight
                preRight.right = cur.right;
                // 3、将当前右子树替换为左子树，然后左子树置空
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        // root = [1,2,5,3,4,null,6]
        TreeNode root = new TreeNode(1,new TreeNode(2),new TreeNode(5));
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        flatten(root);
        System.out.println(root);
    }
}
