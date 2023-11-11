
/**
 * @author: code_hlb
 * @date :  2023/11/11 12:09
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

        public TreeNode() {
        }
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder,其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     */
    public int preIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeChild(preorder,inorder,0,inorder.length - 1);
    }

    private TreeNode buildTreeChild(int[] preOrder,int[] inOrder,int inBegin,int inEnd) {
        // 1、没有左子树 或 右子树了
        if (inBegin > inEnd) {
            return null;
        }
        // 2、创建根节点
        TreeNode root = new TreeNode(preOrder[preIndex]);
        // 3、在中序遍历中找到当前根节点的索引
        int rootIndex = findRootIndex(inOrder,inBegin,inEnd,preOrder[preIndex]);
        if (rootIndex == -1) {
            return null;
        }
        // 4、前序列表向前遍历
        preIndex++;
        // 5、创建当前根节点的左子树 和 右子树
        root.left = buildTreeChild(preOrder,inOrder,inBegin,rootIndex - 1);
        root.right = buildTreeChild(preOrder,inOrder,rootIndex + 1,inEnd);
        return root;
    }
    private int findRootIndex(int[] inOrder,int inBegin,int inEnd,int keyVal) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == keyVal) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 给定两个整数数组 inorder 和 postorder,其中 inorder是二叉树的中序遍历,postorder是同一棵树的后序遍历,请你构造并返回这颗二叉树 。
     *
     */
    public int postIndex;
    public TreeNode buildTree1(int[] inOrder, int[] postOrder) {
        postIndex = postOrder.length - 1;
        return buildTreeChild(inOrder,0,inOrder.length-1,postOrder);
    }

    private TreeNode buildTreeChild(int[] inOrder,int inBegin,int inEnd,int[] postOrder) {

        // 1、没有左子树 或 右子树了
        if (inBegin > inEnd) {
            return null;
        }
        // 2、创建根节点
        TreeNode root = new TreeNode(postOrder[postIndex]);
        // 3、在中序遍历中找到当前根节点的索引
        int rootIndex = findRootIndex1(inOrder,inBegin,inEnd,postOrder[postIndex]);
        if (rootIndex == -1) {
            return null;
        }
        // 4、前序列表向前遍历
        postIndex--;
        // 5、创建当前根节点的左子树 和 右子树
        root.right = buildTreeChild(inOrder,rootIndex+1,inEnd,postOrder);
        root.left = buildTreeChild(inOrder,inBegin,rootIndex-1,postOrder);
        return root;
    }

    private int findRootIndex1(int[] inOrder,int inBegin,int inEnd,int keyVal) {
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == keyVal) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 606. 根据二叉树创建字符串
     * 给你二叉树的根节点 root ，请你采用前序遍历的方式，将二叉树转化为一个由括号和整数组成的字符串，返回构造出的字符串。
     * 空节点使用一对空括号对 "()" 表示，转化后需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     * 输入：root = [1,2,3,4]
     * 输出："1(2(4))(3)"
     * 解释：初步转化后得到 "1(2(4)())(3()())" ，但省略所有不必要的空括号对后，字符串应该是"1(2(4))(3)"
     */
    public String tree2str(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        treeToStrChild(root,stringBuilder);
        return stringBuilder.toString();
    }
    private void treeToStrChild(TreeNode root,StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        stringBuilder.append(root.val);
        if (root.left != null) {
            stringBuilder.append("(");
            treeToStrChild(root.left,stringBuilder);
            stringBuilder.append(")");
        }else {
            if (root.right == null) {
                return;
            }else {
                stringBuilder.append("()");
            }
        }
        if (root.right != null) {
            stringBuilder.append("(");
            treeToStrChild(root.right,stringBuilder);
            stringBuilder.append(")");
        }else {
            return;
        }
    }
}
