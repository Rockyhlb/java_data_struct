import javax.swing.plaf.IconUIResource;

/**
 * @author: code_hlb
 * @date :  2023/10/29 13:29
 * @desc :
 */
public class Demo1 {
    public static class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }
        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 83. 删除排序链表中的重复元素
     *  要求：给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     *  思路：比较当前节点的值和下一个节点的值，若相同，删除当前元素的下一个元素，反之则继续移动当前节点
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 141. 环形链表: 给你一个链表的头节点 head ，判断链表中是否有环。
     *  思路：给定一个快慢指针，速度悬殊2倍，两个指针往前走，当二者相遇说明有环
     */
    public boolean hasCycle(ListNode head) {
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

    /**
     * 142. 环形链表 II: 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null){
            return  null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 当fast重新以slow的速度从head出发并与slow相遇时，此时的fast就是环的起点
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 160. 相交链表:给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
     * 如果两个链表不存在相交节点，返回 null 。
     *  思路：这里要求的是指针相等，不是数值相等
     *      也就是对比 "lenA - lenB部分"
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        ListNode lHead = headA; // 假设headA是长链表
        ListNode sHead = headB;

        if(headA.next == null && headB.next == null) {
            if(headA.val == headB.val) {
                return headA;
            }
            return null;
        }

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        // 如果长度差是负数，则说明B才是长链表
        int len = lenA - lenB;
        if (len < 0) {
            lHead = headB;
            sHead = headA;
            len = lenB - lenA;
        }

        // 长链表先走差值步
        while (len > 0 && lHead != null){
            lHead = lHead.next;
            len--;
        }

        while (lHead != null ){
            if (lHead == sHead) {
                return lHead;
            }else {
                lHead = lHead.next;
                sHead = sHead.next;
            }
        }
        return null;
    }

    /**
     * 82. 删除排序链表中的重复元素 II：给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
     * 只留下不同的数字 。返回 已排序的链表 。
     *  思路：
     */

    public ListNode deleteDuplicatesAll(ListNode head) {
        ListNode newHead = new ListNode(-1);
        ListNode tmpNode = newHead;
        ListNode cur = head;
        ListNode prev = new ListNode(-100000);
        if(head == null || head.next == null) {
            return head;
        }
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                prev = cur.next;
                cur = cur.next.next;
            }else if (cur.val == prev.val){
                prev = cur;
                cur = cur.next;
            }else{
                tmpNode.next = new ListNode(cur.val);
                tmpNode = tmpNode.next;
                prev = cur;
                cur = cur.next;
            }

            if(cur != null && cur.next == null && cur.val != prev.val) {
                tmpNode.next = cur;
            }
        }
        return newHead.next;
    }

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，
     * 返回 反转后的链表 。
     *  思路：可以通过将left到right位置的元素依次往前头插进行反转
     *  输入：head = [1,2,3,4,5], left = 2, right = 4
     *  输出：[1,4,3,2,5]
     */
    public ListNode reverseBetween (ListNode head, int left, int right) {
        ListNode listNode = new ListNode(-1);
        listNode.next = head;
        ListNode rightNode = head;
        ListNode leftNode = listNode;
        for (int i = 0; i < left - 1; i++) {
            leftNode = leftNode.next;
        }
        for (int i = 0; i < right; i++) {
            rightNode = rightNode.next;
        }
        leftNode.next = reverseLinkedList(leftNode.next,rightNode);
        return listNode.next;
    }
    // 功能函数，翻转链表
    public ListNode reverseLinkedList(ListNode left,ListNode right){
        // right相当于以前的null,以前翻转整个链表，自然是到尾巴，这次就到right
        ListNode prev = right;
        // 这里的left相当于以前的head，只不过这次的头并不一定是第一个节点
        ListNode curr = left;
        // 循环条件也要响应改变，以前是head!=null,这次同理，curr!=right,直到2,3,4这几个节点翻转完毕
        while(curr!=right){
            // 常规翻转链表操作，先记录后面一个结点，然后让指向前一个，然后地址赋值
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
