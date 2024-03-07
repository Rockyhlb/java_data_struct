package data_struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: test-20240307
 * @BelongsPackage: data_struct
 * @CreateTime : 2024/3/7 10:33
 * @Description: List
 * @Author: code_hlb
 */
public class Demo {
    /**
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     */
    public class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val,ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public TreeNode sortedListToBST(ListNode head) {
        // 想象成提绳子，每次都提中间，然后将中间节点作为树的根节点，左部分作为树的左子树，右部分作为树的右子树
        ListNode tmpHead = head;
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        List<Integer> list = new ArrayList<>();
        while (tmpHead.next != null) {
            list.add(tmpHead.val);
            tmpHead = tmpHead.next;
        }
        // 通过递归的方式建立搜索树
        return buildTree(0,list.size()-1,list);
    }
    private TreeNode buildTree(int left,int right,List<Integer> list) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = buildTree(left,mid-1,list);
        root.right = buildTree(mid+1,right,list);
        return root;
    }

    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     */
    public void flatten(Demo.TreeNode root) {
        // 题目要求展开后的单链表和二叉树的 先序遍历 顺序相同，
        // 因此可以通过：将当前节点的左子树接到当前节点的右子树处
        // 然后再将原来的右子树 接到原来左子树的最右子树上
        while (root != null) {
            if (root.left == null) {
                // 左子树为空，直接移动到右子树处
                root = root.right;
            }else {
                // 找到左子树最右边节点
                Demo.TreeNode preRight = root.left;
                while (preRight.right != null) {
                    preRight = preRight.right;
                }
                // 将原来的右子树接到当前左子树最右节点上
                preRight.right = root.right;
                // 将左子树连接到当前的右树上
                root.right = root.left;
                root.left = null;
                // 进入下一个节点
                root = root.right;
            }
        }
    }
}
