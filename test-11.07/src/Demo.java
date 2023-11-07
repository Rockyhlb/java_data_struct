
/**
 * @author: code_hlb
 * @date :  2023/11/7 16:34
 * @desc :  LeetCode-->BinaryTree
 */
public class Demo {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(char val) {
            this.val = val;
        }
    }

    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q== null) {
            return true;    // 当两个节点都为空时返回true
        }else if (p == null || q == null) {
            return false;   // 当一方节点为空时返回false
        }else if (p.val != q.val) {
            return false;   // 节点值不一致时返回false
        }else {
            // 返回 递归对比两颗二叉树对应左右节点的 与值
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }

    /**
     * 判断subRoot是不是root的子树
     */
    public boolean isSubTree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSameTree(root,subRoot)) {
            return true;
        }
        if (isSameTree(root.left,subRoot)) {
            return true;
        }
        if (isSameTree(root.right,subRoot)) {
            return true;
        }
        return false;
    }

    /**
     * 226. 翻转二叉树
     * 给你一棵二叉树的根节点 root，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        if (root.left != null || root.right != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 判断平衡二叉树
     */
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        // 调用该isBalanced(root.left)时会造成重复计算
        // return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
        if(leftHeight >= 0 && rightHeight >= 0 &&
                Math.abs(leftHeight-rightHeight) <= 1) {
            // 返回左树右树的最大高度加一
            return Math.max(leftHeight,rightHeight) + 1;
        }else {
            return -1;
        }
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        /*int leftHeght = getHeight(root.left);
        int rightHeght = getHeight(root.right);
        return Math.abs(leftHeght-rightHeght) <= 1 && isBalanced(root.left) && isBalanced(root.right);*/
        return getHeight(root) >= 0;
    }

    /**
     * 404. 左叶子之和
     * 给定二叉树的根节点 root ，返回所有左叶子数值之和。
     */
    int res = 0;
    public int sumOfLeftLeaves(BinaryTree.TreeNode root) {
        dfs(root,false);
        return res;
    }

    public void dfs(BinaryTree.TreeNode root, boolean isLeft) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null && isLeft) {
            res += root.val;
        }
        dfs(root.left,true);
        dfs(root.right,false);
    }

    /**
     * 判断是否是轴对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isSymmetricChild(root.left,root.right);
    }
    public boolean isSymmetricChild(TreeNode leftTree,TreeNode rightTree) {
        if (leftTree == null && rightTree != null || leftTree != null && rightTree == null) {
            return false;
        }
        if (leftTree == null && rightTree == null) {
            return true;
        }
        if (leftTree.val != rightTree.val) {
            return false;
        }
        return isSymmetricChild(leftTree.left,rightTree.right) &&
                isSymmetricChild(leftTree.right,rightTree.left);
    }

    /**
     * 给定一个先序遍历的字符串，‘＃’号为空，完成二叉树的创建
     */
    public static int i = 0;
    public TreeNode preCreateTree(String str) {
        TreeNode root = null;
        if (str.charAt(i) != '#') {
            root = new TreeNode(str.charAt(i++));
            root.left = preCreateTree(str);
            root.right = preCreateTree(str);
        }else {
            // 当遇见‘#’时，直接i++,然后返回root(null),跳回上一次递归
            i++;
        }
        return root;
    }
}
