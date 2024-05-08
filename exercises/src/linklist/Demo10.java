package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/8 19:06
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo10 {
    /**
     * 86. 分隔链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * 示例 1：
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     * 示例 2：
     * 输入：head = [2,1], x = 2
     * 输出：[1,2]
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smaller = new ListNode(-1);
        ListNode tmpSma = smaller;
        ListNode larger = new ListNode(-1);
        ListNode tmpLar = larger;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                tmpSma.next = new ListNode(cur.val);
                tmpSma = tmpSma.next;
            } else {
                tmpLar.next = new ListNode(cur.val);
                tmpLar = tmpLar.next;
            }
            cur = cur.next;
        }
        // 将当前较小链表的尾 接到 较大链表的头，形成完整链表
        tmpSma.next = larger.next;
        return smaller.next;
    }

    public static void main(String[] args) {
        // head = [1,4,3,2,5,2], x = 3
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        System.out.println(partition(head, 3));
    }
}
