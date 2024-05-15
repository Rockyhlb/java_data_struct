package tree;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/15 22:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo15 {
    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
     * 因此返回 [3, 14.5, 11] 。
     * 示例 2:
     * 输入：root = [3,9,20,15,7]
     * 输出：[3.00000,14.50000,11.00000]
     */
    public static List<Double> averageOfLevels(TreeNode root) {
         return bfs(root);
    }

    private static List<Double> bfs (TreeNode root) {
        // 方法2：BFS
        List<Double> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;   // 记录累计和
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            resList.add(sum / size);
        }
        return resList;
    }

    public static void main(String[] args) {
        // 输入：root = [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3,new TreeNode(9),new TreeNode(20));
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(averageOfLevels(root));
    }
}
