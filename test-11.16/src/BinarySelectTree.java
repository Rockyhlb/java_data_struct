/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 15:49
 * @Description: 二叉搜索树的实现
 * @Author: code_hlb
 */
public class BinarySelectTree {

    /**
     * 二叉搜索树：左子树的值一定给都小于根节点的值，右子树的值一定都大于根节点的值，并且左右子树也分别为搜索树
     *      最优情况(完全二叉树)：平均比较次数为 log2^N
     *      最差情况(单分支树) ： 平均比较次数为 N/2
     * AVL树(高度平衡的二叉搜索树):通过旋转降低树的高度，解决了二叉搜索树为单分支的问题，有左旋，右旋，左右双旋，右左双旋
     * 红黑树：通过节点颜色旋转来调整树的高度，旋转不会太多
     */
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode root = null;

    //  二叉搜索树的左子树的值一定比根的值小，右子树的值一定比根的值大
    public boolean search(int val) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < val) {
                cur = cur.right;
            }else if (cur.val > val){
                cur = cur.left;
            }else {
                return true;
            }
        }
        return false;
    }

    // 往 搜索树中插入元素
    public void add(int val) {
        TreeNode node = new TreeNode(val);
        if (root == null) {
            root = node;
            return;
        }
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null) {
            if (cur.val < val) {
                parent = cur;
                cur = cur.right;
            }else if (cur.val > val){
                parent = cur;
                cur = cur.left;
            }else {
                return;
            }
        }
        if (parent.val < val) {
            parent.right = node;
        }else {
            parent.left = node;
        }
    }

    public void remove(int val) {
        TreeNode cur = root;
        TreeNode parent = null;
        while(cur != null) {
            if (cur.val > val) {
                parent = cur;
                cur = cur.left;
            }else if (cur.val < val){
                parent = cur;
                cur = cur.right;
            }else {
                // 删除节点
                removeNode(parent,cur);
            }
        }
    }

    private void removeNode(TreeNode parent, TreeNode cur) {
        if (cur.left == null) {
            // 1、待删除节点的左子树为null时
            if (cur == root) {
                // 当待删除节点是根节点时
                root = cur.right;
            }else if (parent.left == cur) {
                // 当cur不是root,cur是parent的left时
                parent.left = cur.right;
            }else if (parent.right == cur) {
                // 当cur不是root,cur是parent的right时
                parent.right = cur.right;
            }
        }else if (cur.right == null) {
            // 2、待删除节点的右子树为空时
            if (cur == root) {
                // 当待删除节点是根节点时
                root = cur.left;
            }else if (parent.left == cur) {
                // 当cur不是root,cur是parent的left时
                parent.left = cur.left;
            }else if (parent.right == cur) {
                // 当cur不是root,cur是parent的right时
                parent.right = cur.left;
            }
        }else {
            // 3、当待删除节点的左右子树都不为null时
            // 替罪羊删除法：将待删除节点的值替换成左子树的最右个节点 或 右子树的最左个节点,保证删除该节点以后仍是一棵二叉搜索树
            TreeNode targetParent = cur;
            TreeNode target = cur.right;
            while (target.left != null) {
                targetParent = target;
                target = target.left;
            }
            // 将右子树的最小节点 覆盖 待删除节点
            cur.val = target.val;
            if (target == targetParent.left) {
                targetParent.left = target.right;
            }else {
                targetParent.right = target.right;
            }
        }
    }
}
