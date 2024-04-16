package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/4/16 15:21
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 141. 环形链表 --> https://leetcode.cn/problems/linked-list-cycle/description/?envType=study-plan-v2&envId=top-interview-150
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
     * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。
     * 仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     */
    public static boolean hasCycle(ListNode head) {
        // 快慢指针法
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        // 构建循环链表  节点2 是循环节点
//        head.next.next.next.next = head.next;
        System.out.println(hasCycle(head));
    }
}
