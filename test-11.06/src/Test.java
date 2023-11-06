import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: code_hlb
 * @date :  2023/11/6 10:59
 * @desc :
 */
public class Test {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.TreeNode root = binaryTree.init();
        System.out.println("=========preOrder()==========");
        binaryTree.preOrder(root);
        System.out.println("\n=========inOrder()==========");
        binaryTree.inOrder(root);
        System.out.println("\n=========postOrder()==========");
        binaryTree.postOrder(root);

        System.out.println("\n=========preOrder2()==========");
        List<BinaryTree.TreeNode> list = binaryTree.preOrder2(root);
        for (BinaryTree.TreeNode treeNode : list) {
            System.out.print(treeNode.val + " ");
        }

        System.out.println("\n=========inOrder2()==========");
        List<BinaryTree.TreeNode> list1 = binaryTree.inOrder2(root);
        for (BinaryTree.TreeNode treeNode : list1) {
            System.out.print(treeNode.val + " ");
        }

        System.out.println("\n=========postOrder2()==========");
        List<BinaryTree.TreeNode> list2 = binaryTree.postOrder2(root);
        for (BinaryTree.TreeNode treeNode : list2) {
            System.out.print(treeNode.val + " ");
        }

        System.out.println("\n=========nodeSize()==========");
        System.out.println("nodeSize: " + binaryTree.nodeSize(root));

        System.out.println("\n=========leafSize()==========");
        System.out.println("nodeSize: " + binaryTree.leafSize(root));

        System.out.println("\n=========getKLevelNodeCount(root,3)==========");
        System.out.println("getKLevelNodeCount: " + binaryTree.getKLevelNodeCount(root,3));

        System.out.println("\n=========getHeight()==========");
        System.out.println("height: " + binaryTree.getHeight(root));

        System.out.println("\n=========find(root,'A')==========");
        System.out.println(binaryTree.find(root, 'C'));
    }
}
