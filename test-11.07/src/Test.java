/**
 * @author: code_hlb
 * @date :  2023/11/7 15:50
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.TreeNode root = binaryTree.init();
        System.out.println("=========init()==========");
        binaryTree.preOrder(root);

        System.out.println("\n=========getAllNode()==========");
        System.out.println("allNode:" + binaryTree.getAllNode(root));

        System.out.println("=========leavesNode()==========");
        System.out.println("leavesNode: " + binaryTree.leavesNode(root));

        System.out.println("=========getHeight()==========");
        System.out.println("getHeight: " + binaryTree.getHeight(root));

        System.out.println("=========contains(root,'F')==========");
        System.out.println(binaryTree.contains(root, 'F'));
    }
}
