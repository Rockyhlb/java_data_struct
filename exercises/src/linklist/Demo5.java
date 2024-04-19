package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/4/19 8:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 92. 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left > right) {
            return head;
        }
        ListNode resHead = new ListNode(-1);
        resHead.next = head;
        ListNode leftNode = resHead;
        ListNode rightNode = resHead.next;
        for (int i = 0; i < left - 1; i++) {
            leftNode = leftNode.next;
        }
        // 遍历到right的下一个节点
        for (int i = 0; i < right; i++) {
            rightNode = rightNode.next;
        }
        leftNode.next = reverse(leftNode.next, rightNode);
        return resHead.next;
    }

    private static ListNode reverse(ListNode leftNode, ListNode rightNode) {
        ListNode pre = rightNode;
        ListNode cur = leftNode;
        while (cur != rightNode) {
            ListNode nextNode = cur.next;
            // 采用头插，第一次将right的下一个节点作为尾，后面不断头插实现链表的反转
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
        return pre;
    }

    public static void main(String[] args) {
        // head = [1,2,3,4,5], left = 2, right = 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(reverseBetween(head, 2, 4).toString());
    }
}
