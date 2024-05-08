package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/8 15:00
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     * 示例 2：
     * 输入：head = [0,1,2], k = 4
     * 输出：[2,0,1]
     */
    public static ListNode rotateRight(ListNode head, int k) {
        ListNode cur = head;
        ListNode pre = head;
        int len = 0;
        while (cur != null) {
            len++;
            pre = cur;
            cur = cur.next;
        }
        // 当链表长度为0/1，或者旋转长度与当前链表的长度成倍数关系时，原链表不变
        if (len <= 1 || k % len == 0) {
            return head;
        }
        // 构建环
        pre.next = head;
        // 找到新的尾节点：即在原链表的 (len - 1) - (k % len) 位置
        int step = (len - 1) - (k % len);
        cur = head;
        while (step > 0) {
            cur = cur.next;
            step--;
        }
        // cur.next 即为新链表的头
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        // head = [1,2,3,4,5], k = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(rotateRight(head, 2));
    }
}
