package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 17:36
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo10 {
    /**
     * 129. 求根节点到叶节点数字之和
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。每条从根节点到叶节点的路径都代表一个数字：
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。计算从根节点到叶节点生成的 所有数字之和 。叶节点 是指没有子节点的节点。
     * 示例 1：
     * 输入：root = [1,2,3]
     * 输出：25
     * 解释：
     * 从根到叶子节点路径 1->2 代表数字 12
     * 从根到叶子节点路径 1->3 代表数字 13
     * 因此，数字总和 = 12 + 13 = 25
     * 示例 2：
     * 输入：root = [4,9,0,5,1]
     * 输出：1026
     * 解释：
     * 从根到叶子节点路径 4->9->5 代表数字 495
     * 从根到叶子节点路径 4->9->1 代表数字 491
     * 从根到叶子节点路径 4->0 代表数字 40
     * 因此，数字总和 = 495 + 491 + 40 = 1026
     * 提示：
     * 树中节点的数目在范围 [1, 1000] 内
     * 0 <= Node.val <= 9
     * 树的深度不超过 10
     */
    public static int sumNumbers(TreeNode root) {
        // 方法1：DFS
        return dfs(root, 0);
    }

    private static int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int curSum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return curSum;
        }
        return dfs(root.left, curSum) + dfs(root.right, curSum);
    }

    public static int sumNumbers1(TreeNode root) {
        // 方法2：BFS
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valQueue = new LinkedList<>();
        nodeQueue.offer(root);
        valQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int curSum = valQueue.poll();
            if (node.left == null && node.right == null) {
                sum += curSum;
            }
            if (node.left != null) {
                nodeQueue.offer(node.left);
                valQueue.offer(curSum * 10 + node.left.val);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                valQueue.offer(curSum * 10 + node.right.val);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(sumNumbers(root));
        System.out.println(sumNumbers1(root));
    }
}
