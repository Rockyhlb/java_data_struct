import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @BelongsProject: test-20240321
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/21 11:38
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int  val) {
            this.val = val;
        }
    }
    /**
     * 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                curList.add(node.val);
                size--;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(curList);
        }
        return list;
    }
}
