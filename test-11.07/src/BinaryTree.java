
/**
 * @author: code_hlb
 * @date :  2023/11/7 15:50
 * @desc :
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

    // 先序遍历
    public void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // 返回总节点数
    public int getAllNode(TreeNode root) {
        if (root == null) return 0;
        return getAllNode(root.left) + getAllNode(root.right) + 1;
    }

    // 返回叶子数
    public int leavesNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leavesNode(root.left) + leavesNode(root.right);
    }

    // 返回高度
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    // 判断元素k是否在二叉树中
    public boolean contains(TreeNode root,char key) {
        if (root == null) {
            return false;
        }
        if (root.val == key) {
            return true;
        }
        boolean leftFind = contains(root.left,key);
        if (leftFind) return true;
        boolean rightFind = contains(root.right,key);
        if (rightFind) {
            return true;
        }
        return false;
    }
}
