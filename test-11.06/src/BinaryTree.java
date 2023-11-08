
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: code_hlb
 * @date :  2023/11/6 16:08
 * @desc :  二叉树的实现
 */
public class BinaryTree {
    static class TreeNode {
        public char val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    /**
     * 初始化二叉树：
     *              A
     *            /   \
     *           B     C
     *         /  \   /  \
     *        D    E F    G
     *         \
     *          H
     * 前序遍历： A B D H E C F G
     * 中序遍历： D H B E A F C G
     * 后序遍历： H D E B F G C A
     * 层次遍历：
     */
    public TreeNode init() {
        TreeNode A = new TreeNode('A');
        TreeNode B = new TreeNode('B');
        TreeNode C = new TreeNode('C');
        TreeNode D = new TreeNode('D');
        TreeNode E = new TreeNode('E');
        TreeNode F = new TreeNode('F');
        TreeNode G = new TreeNode('G');
        TreeNode H = new TreeNode('H');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;
        D.right = H;
        return A;
    }

    // 递归实现前序遍历
    public void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /*List<TreeNode> list = new LinkedList<>();
    // 将前序遍历的结果存储到list当中
    public List<TreeNode> preOrder2(TreeNode root) {
        if (root == null) return list;
        list.add(root);
        preOrder2(root.left);
        preOrder2(root.right);
        return list;
    }*/
    public List<TreeNode> preOrder2(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        if (root == null) return ret;
        // 先将当前节点加入list当中
        ret.add(root);

        // 递归遍历当前节点的左节点，并接收返回值列表
        List<TreeNode> leftTree = preOrder2(root.left);
        // 将接收到的左子树节点全部加入到当前列表中
        ret.addAll(leftTree);

        // 递归遍历右子树
        List<TreeNode> rightTree = preOrder2(root.right);
        ret.addAll(rightTree);
        // 返回当前列表
        return ret;
    }

    // 递归实现中序遍历
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    // 将中序遍历的结果存储到list当中
    public List<TreeNode> inOrder2(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        if (root == null) return ret;

        List<TreeNode> leftTree = inOrder2(root.left);
        ret.addAll(leftTree);

        ret.add(root);

        List<TreeNode> rightTree = inOrder2(root.right);
        ret.addAll(rightTree);
        return ret;
    }

    // 递归实现后序遍历
    public void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    // 将后序遍历的结果存储到list当中
    public List<TreeNode> postOrder2(TreeNode root) {
        List<TreeNode> ret = new ArrayList<>();
        if (root == null) return ret;

        List<TreeNode> leftTree = postOrder2(root.left);
        ret.addAll(leftTree);

        List<TreeNode> rightTree = postOrder2(root.right);
        ret.addAll(rightTree);

        ret.add(root);
        return ret;
    }

    // 层序遍历
    public void levelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    // 获得当前二叉树的节点数,通过前序遍历完成
    public int nodeSize(TreeNode root) {
        if (root == null) return 0;
        return nodeSize(root.left) + nodeSize(root.right) + 1;
    }

    // 获取叶子个数
    public int leafSize(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) {
            return 1;
        }
        return leafSize(root.left) + leafSize(root.right);
    }

    // 获取第 k 层节点个数
    public int getKLevelNodeCount(TreeNode root,int k) {
        if (root == null) return 0;
        if (1 == k) return 1;
        return getKLevelNodeCount(root.left,k - 1) + getKLevelNodeCount(root.right,k - 1);
    }

    // 获取二叉树的高度
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }
}
