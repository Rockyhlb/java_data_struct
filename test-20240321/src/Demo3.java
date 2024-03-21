/**
 * @BelongsProject: test-20240321
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/21 11:52
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    /**
     * 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if(slow == null || fast == null) {
            return false;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }
}
