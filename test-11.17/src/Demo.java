
import java.util.LinkedList;
import java.util.Queue;

/**
 * @BelongsProject: test-11.17
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-17 10:38
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class Demo {
    /**
     * 2283. 判断一个数的数字计数是否等于数位的值
     * 给你一个下标从 0 开始长度为 n 的字符串 num ，它只包含数字。
     * 如果对于 每个 0 <= i < n 的下标 i ，都满足数位 i 在 num 中出现了 num[i]次，那么请你返回 true ，否则返回 false 。
     * 输入：num = "1210"
     * 输出：true
     * 解释：
     * num[0] = '1' 。数字 0 在 num 中出现了一次。
     * num[1] = '2' 。数字 1 在 num 中出现了两次。
     * num[2] = '1' 。数字 2 在 num 中出现了一次。
     * num[3] = '0' 。数字 3 在 num 中出现了零次。
     * "1210" 满足题目要求条件，所以返回 true 。
     */
    public static boolean digitCount(String num) {
        int[] arr = new int[10];
        for (int i = 0; i < num.length(); i++) {
            arr[(Integer.parseInt(num.charAt(i) + "") % 10)]++;
        }
        for (int i = 0; i < num.length(); i++) {
            if(num.charAt(i) - '0' != arr[i]) {
                return false;
            }
        }
        return true;

        /*
        Map<Integer, Integer> h = new HashMap<Integer, Integer>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            h.put(digit, h.getOrDefault(digit, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int v = num.charAt(i) - '0';
            if (h.getOrDefault(i, 0) != v) {
                return false;
            }
        }
        return true;
         */
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        int tmp = 0;
        if(root.val >= low && root.val <= high) {
            tmp = root.val;
        }
        return rangeSumBST(root.left,low,high) + rangeSumBST(root.right,low,high) + tmp;
    }

    /**
     * 958. 二叉树的完全性检验
     * 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
     * 在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。
     * 最后一层（第 h 层）中可以包含 1 到 2h 个节点。
     * 思路：进行层序遍历，若出现空节点以后就不能出现节点了
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)  {
            return true;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null && queue.peek() != null) {
                return false;
            }
            if(cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }
}
