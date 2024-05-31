package divide;

import linklist.ListNode;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: divide
 * @CreateTime : 2024/5/31 8:59
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * 示例 1：
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * 示例 2：
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     * 提示：
     * 链表中节点的数目在范围 [0, 5 * 104] 内
     * -105 <= Node.val <= 105
     */
    public static ListNode sortList(ListNode head) {
        // 借用归并排序+递归的思想
        if (head == null || head.next == null) {
            return head;
        }
        // 1、分解链表
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;  // 断开链表
        // 分解出左右部分
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        // 2、合并并返回链表头
        return mergeSort(left, right);
    }

    private static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针查找中点
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode mergeSort(ListNode l1, ListNode l2) {
        // 两个有序链表的合并(操作原始链表)
        ListNode tmpRoot = new ListNode(-1);
        ListNode cur = tmpRoot;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return tmpRoot.next;
    }

    public static void main(String[] args) {
        // 输入：head = [4,2,1,3]
        ListNode root = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        System.out.println(sortList(root));
    }
}
