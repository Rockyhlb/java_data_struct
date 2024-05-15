package tree;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/15 21:06
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo14 {
    /**
     * 199. 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * 示例 1:
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1,3,4]
     * 示例 2:
     * 输入: [1,null,3]
     * 输出: [1,3]
     * 示例 3:
     * 输入: []
     * 输出: []
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> resList = new ArrayList<>();
        dfs(root, resList, 0);
        return resList;
        // return bfs(root);
    }

    private static void dfs(TreeNode root, List<Integer> resList, int depth) {
        // 方法1：dfs
        if (root == null) {
            return;
        }
        // 深度遍历，则当resList 和 depth的长度相等时，说明该节点就是当前层最右侧节点
        if (resList.size() == depth) {
            resList.add(root.val);
        }
        // 先遍历右子树，再遍历左子树，因为右视图的节点一定优先是在右子树上，其次才是左子树
        dfs(root.right, resList, depth + 1);
        dfs(root.left, resList, depth + 1);
    }

    private static List<Integer> bfs(TreeNode root) {
        // 方法2：BFS
        // 存储右视图结果
        List<Integer> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        // 借助队列进行层序遍历
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 获取当前层的节点数
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (size - 1 == 0) {
                    // 当前层的最后一个节点就是右视图的第一个节点
                    resList.add(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                size--;
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        // [1,2,3,null,5,null,4]
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        System.out.println(rightSideView(root));
    }
}
