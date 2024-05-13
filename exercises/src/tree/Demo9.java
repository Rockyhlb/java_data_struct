package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 15:22
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点。
     * <p>
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     * 解释：树中存在两条根节点到叶子节点的路径：
     * (1 --> 2): 和为 3
     * (1 --> 3): 和为 4
     * 不存在 sum = 5 的根节点到叶子节点的路径。
     * 示例 3：
     * 输入：root = [], targetSum = 0
     * 输出：false
     * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     * <p>
     * 提示：
     * 树中节点的数目在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // 方法1：DFS递归
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 一真则真
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    public static boolean hasPathSum1(TreeNode root, int targetSum) {
        // 方法2：BFS
        if (root == null) {
            return false;
        }
        // 记录节点
        Deque<TreeNode> queueNode = new LinkedList<>();
        // 记录到该节点的路径和
        Queue<Integer> queueVal = new LinkedList<>();
        queueNode.offer(root);
        queueVal.offer(root.val);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int curSum = queueVal.poll();
            if (node.left == null && node.right == null) {
                if (curSum == targetSum) {
                    return true;
                }
                continue;
            }
            if (node.left != null) {
                queueNode.offer(node.left);
                queueVal.offer(node.left.val + curSum);
            }
            if (node.right != null) {
                queueNode.offer(node.right);
                queueVal.offer(node.right.val + curSum);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // root = [1,2,3], targetSum = 5
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(hasPathSum(root, 5));
        System.out.println(hasPathSum1(root, 3));
    }
}
