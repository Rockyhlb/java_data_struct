/**
 * @author: code_hlb
 * @date :  2023/11/8 16:38
 * @desc :
 */
public class Test {

    public static void main(String[] args) {

        Demo demo = new Demo();
        Demo.TreeNode root = demo.init();
        System.out.println(demo.levelOrder(root));
        System.out.println(demo.levelOrderBottom(root));

        System.out.println("========isCompleteBinaryTree(root)==========");
        System.out.println(demo.isCompleteBinaryTree(root));
    }
}
