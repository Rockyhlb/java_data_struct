package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/7 8:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 25. K 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     * 示例 2：
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 采用 递归+头插 的方式进行分组翻转
        if (head == null) {
            // 递归退出条件
            return null;
        }
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            if (cur == null) {
                // 如果节点总数不是 k 的整数倍，将最后剩余的节点保持原有顺序
                return head;
            }
            // 将cur移动到尾部
            cur = cur.next;
        }
        // head作为leftNode, cur作为 rightNode
        ListNode newHead = reverse(head, cur);
        // 进入递归，将递归返回的头节点 接到 当前头的尾部
        head.next = reverseKGroup(cur, k);
        return newHead;
    }

    private static ListNode reverse(ListNode leftNode, ListNode rightNode) {
        ListNode pre = null;
        while (leftNode != rightNode) {
            ListNode nextNode = leftNode.next;
            leftNode.next = pre;
            pre = leftNode;
            leftNode = nextNode;
        }
        return pre;
    }

    public static void main(String[] args) {
        // head = [1,2,3,4,5], k = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(reverseKGroup(head, 2).toString());
    }
}
