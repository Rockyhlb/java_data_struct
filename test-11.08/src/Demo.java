import sun.security.rsa.RSAKeyFactory;

import java.util.*;

/**
 * @author: code_hlb
 * @date :  2023/11/8 15:34
 * @desc :  LeetCode-BinaryTree
 */
public class Demo {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode init() {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(5);
        TreeNode F = new TreeNode(6);
        TreeNode G = new TreeNode(7);
        TreeNode H = new TreeNode(8);
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;
        D.right = H;
        return A;
    }

    /**
     * 102. 二叉树的层序遍历
     * 给你二叉树的根节点 root，返回其节点值的 层序遍历。（即逐层地，从左到右访问所有节点）。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                size--;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ret.add(curList);
        }
        return ret;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curList = new ArrayList<>();
            while (size > 0) {
                TreeNode cur = queue.poll();
                curList.add(cur.val);
                size--;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            ret.add(0,curList);
        }
        return ret;
    }

    /**
     * 判断是否是完全二叉树
     * 思路： 在层序遍历的代码上优化
     *       当左子树为null时，若右子树不为空，则不是完全二叉树
     */
    public boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
            }else {
                break;
            }
        }

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) return false;
        }
        return true;
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出：3
     * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p == root || q == root) {
            return root;
        }
        TreeNode leftTree = lowestCommonAncestor(root.left,p,q);
        TreeNode rightTree = lowestCommonAncestor(root.right,p,q);
        if(leftTree != null && rightTree != null) {
            return root;
        }else if(leftTree != null) {
            return leftTree;
        }else {
            return rightTree;
        }
    }
    // 方法2：转变为求链表的交点，但是时间复杂度更高
    // 当p 和 q 都在根节点的左树或者右数时，找它们的最近公共祖先节点其实就是找他们到根节点这两个路线的交点
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> PStack = new Stack<>();
        Stack<TreeNode> QStack = new Stack<>();
        getPath(root,p,PStack);
        getPath(root,q,QStack);

        // 对栈的操作
        int sizeP = PStack.size();
        int sizeQ = QStack.size();
        if (sizeP > sizeQ) {
            int size = sizeP - sizeQ;
            while (size != 0) {
                PStack.pop();
                size--;
            }
            // 此时两个栈当中的元素个数相同
        }else {
            int size = sizeQ - sizeP;
            while (size != 0) {
                QStack.pop();
                size--;
            }
        }
        // 两个栈当中元素的个数是相同的
        while(!PStack.isEmpty() && !QStack.isEmpty()) {
            if (PStack.peek().equals(QStack.peek())) {
                return PStack.peek();
            }
            PStack.pop();
            QStack.pop();
        }
        return null;
    }

    /**
     * 将 root 到 node 的节点压入栈
     */
    private boolean getPath(TreeNode root, TreeNode node, Stack<TreeNode> stack) {
        if (root == null || node == null) return false;
        stack.push(root);
        if (root == node) {
            return true;
        }
        boolean flag = getPath(root.left,node,stack);
        if (flag) {
            return true;
        }

        boolean flag2 = getPath(root.right,node,stack);
        if (flag2) {
            return true;
        }

        stack.pop();
        return false;
    }
}
