package linklist;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/7 9:43
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 方法1：计算链表长度，再遍历到待删除节点
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        int len = getLen(head);
        // 找到待删除节点的前驱
        ListNode cur = newHead;
        for (int i = 0; i < len - n; i++) {
            cur = cur.next;
        }
        // 删除节点
        cur.next = cur.next.next;
        return newHead.next;
    }

    private static int getLen(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        // 方法2：利用栈
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(-1, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = newHead;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode del = new ListNode();
        for (int i = 0; i < n && !stack.isEmpty(); i++) {
            // 获取待删除节点的前驱
            stack.pop();
        }
        ListNode pre = stack.peek();
        if (pre != null) {
            pre.next = pre.next.next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        // head = [1,2,3,4,5], n = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        System.out.println(removeNthFromEnd(head, 1));
        System.out.println(removeNthFromEnd1(head, 1));
    }
}
