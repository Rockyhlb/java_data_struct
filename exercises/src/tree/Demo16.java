package tree;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/16 10:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo16 {
    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[20,9],[15,7]]
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 方法1：通过调用 reverse() 控制遍历顺序
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;   // 标识是否需要翻转
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            // 偶数层顺序遍历，奇数层逆序遍历
            List<Integer> tmpList = new ArrayList<>();
            while (queueSize > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                tmpList.add(cur.val);
                queueSize--;
            }
            if (flag) {
                Collections.reverse(tmpList);
            }
            resList.add(tmpList);
            flag = !flag;
        }
        return resList;
    }

    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        // 方法2：通过双端队列控制遍历顺序
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            // 借用双向队列尾插头插的特性实现顺序逆序遍历
            Deque<Integer> tmpQueue = new LinkedList<>();
            while (queueSize > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                // 偶数层顺序遍历(尾插)，奇数层逆序遍历(头插)`
                if (resList.size() % 2 == 0) {
                    tmpQueue.addLast(cur.val);
                } else {
                    tmpQueue.addFirst(cur.val);
                }
                queueSize--;
            }
            resList.add((List<Integer>) tmpQueue);
        }
        return resList;
    }

    public static void main(String[] args) {
        // root = [3,9,20,null,null,15,7]
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20));
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(zigzagLevelOrder(root));
        System.out.println(zigzagLevelOrder1(root));
    }
}
