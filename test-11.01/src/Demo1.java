/**
 * @author: code_hlb
 * @date :  2023/11/1 13:36
 * @desc :  LeetCode-LinkList
 */
public class Demo1 {

    public static class ListNode{
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode head;

    public void init() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        head = node1;
    }

    /**
     * 143. 重排链表
     *      给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     *      L0 → L1 → … → Ln - 1 → Ln
     *      请将其重新排列后变为：
     *      L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     *      不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        // 1、找到中点
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 2、反转
        ListNode acur = slow.next;
        slow.next = null;
        while(acur != null) {
            ListNode acurNext = acur.next;
            acur.next = slow;
            slow = acur;
            acur = acurNext;
        }
        // 3、链表合并
        ListNode bcur = head;
        while(bcur != null && slow.next != null) {
            ListNode bcurNext = bcur.next;
            bcur.next = slow;
            slow = slow.next;
            bcur = bcur.next;
            bcur.next = bcurNext;
            bcur = bcur.next;
        }
    }
}
