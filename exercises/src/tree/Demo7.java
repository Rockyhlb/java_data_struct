package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: tree
 * @CreateTime : 2024/5/13 13:17
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     * 给定一个二叉树：
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
     * 初始状态下，所有 next 指针都被设置为 NULL 。
     * 示例 1：
     * 输入：root = [1,2,3,4,5,null,7]
     * 输出：[1,#,2,3,#,4,5,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
     * 序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     */
    public static Node connect(Node root) {
        // 处理边界
        if (root == null) {
            return null;
        }
        // 层次遍历
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;  // 记录当前层的最后一个节点
            for (int i = 1; i <= size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (i != 1) {
                    // 当不是
                    last.next = cur;
                }
                last = cur;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // root = [1,2,3,4,5,null,7]
        Node root = new Node(1, new Node(2), new Node(3), null);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        System.out.println(connect(root));
    }
}
