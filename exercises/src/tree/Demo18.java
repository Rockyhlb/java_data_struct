package tree;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/16 13:34
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo18 {
    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * 示例 1：
     * 输入：root = [3,1,4,null,2], k = 1
     * 输出：1
     * 示例 2：
     * 输入：root = [5,3,6,2,4,null,null,1], k = 3
     * 输出：3
     */
    public static int kthSmallest(TreeNode root, int k) {
        // 二叉搜索树：左子树一定比根节点小，右子树一定比根节点大
        // 因此只需要对左子树进行k次遍历，最后的元素就是第k小的元素
        List<Integer> resList = new ArrayList<>();
        dfs(root, resList);
        return resList.get(k - 1);
    }

    private static void dfs(TreeNode root, List<Integer> resList) {
        if (root == null) {
            return;
        }
        // 中序遍历得到递增有序序列
        dfs(root.left, resList);
        resList.add(root.val);
        dfs(root.right, resList);
    }

    private static int res, count;

    public static int kthSmallest1(TreeNode root, int k) {
        count = k;
        dfs1(root);
        return res;
    }

    private static void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs1(root.left);
        // 已经找到目标元素，打断后续遍历
        if (count == 0) return;
        if (--count == 0) {
            res = root.val;
        }
        dfs1(root.right);
    }

    public static void main(String[] args) {
        // 输入：root = [3,1,4,null,2], k = 1
        TreeNode root = new TreeNode(3, new TreeNode(1), new TreeNode(4));
        root.left.right = new TreeNode(2);
        System.out.println(kthSmallest(root, 1));
        System.out.println(kthSmallest1(root, 2));
    }
}
