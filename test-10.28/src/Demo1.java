import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

/**
 * @author: code_hlb
 * @date :  2023/10/28 11:30
 * @desc :  LeetCode --> LinkedList
 */
public class Demo1 {

    public static class ListNode{
        public int val;
        ListNode next;

        public ListNode() {
        }
        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 237. 删除链表中的节点
     *  要求：有一个单链表的 head，我们想删除它其中的一个节点 node。
     *
     * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head。
     *
     * 链表的所有值都是 唯一的，并且保证给定的节点 node 不是链表中的最后一个节点
     *
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 2. 两数相加
     *  要求：给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 思路1、将两个链表翻转，再分别将数据保存在两个数组中，数组一一对应相加，最后将结果数组逆序访问生成新链表
        // 思路2、两个链表直接相加，相加以后只保留个位数，十位数加到下一次相加的结果当中
        // 2 -->
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        int count = 0;
        while (l1 != null && l2 != null) {
            ListNode newNode = new ListNode();
            int sum = l1.val + l2.val;
            newNode.val = sum % 10 + count;
            // 预防newNode.val 加上count正好为10的情况
            if (newNode.val == 10) {
                newNode.val = 0;
                count = 1;
            } else {
                count = sum / 10;
            }

            if (newHead == null) {
                cur = newNode;
            }else {
                cur.next = newNode;
            }
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 当链表l1比l2长时
        // 预防【9,9,9,9,9,9,9】 【9,9,9,9】
        while (l1 != null) {
            ListNode newNode = new ListNode();
            int sum = l1.val + count;
            newNode.val = sum % 10;
            count = sum / 10;

            cur.next = newNode;
            cur = cur.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode newNode = new ListNode();
            int sum = l2.val + count;
            newNode.val = sum % 10;
            count = sum / 10;

            cur.next = newNode;
            cur = cur.next;
            l2 = l2.next;
        }

        // 预防[2,4,9] [5,6,4,9] 无法输最后一位1的情况
        while(count != 0) {
            ListNode newNode = new ListNode();
            newNode.val = count;
            cur.next = newNode;
            count--;
        }
        return newHead.next;
    }

    /**
     * 8、相交链表，判断两个链表的交点，逻辑上的交点，不是值相等的交点
     * 思路：相交肯定是呈'Y'字型相交，并且两个链表的长度不一样主要体现在相交之前，
     *      可以先让长度长的链表先走他们的差值步
     */
    public ListNode gentIntersectionNode(ListNode headA, ListNode headB) {
        // 1、分别求两链表的长度
        ListNode pL = headA;  // 假设A是较长链表
        ListNode pS = headB;  // B是较短链表
        int lenA = 0;
        int lenB = 0;

        while (pL != null) {
            lenA++;
            pL = pL.next;
        }
        while (pS != null) {
            lenB++;
            pS = pS.next;
        }
        int len = lenA - lenB;
        // 当差值为负时，说明较长链表不是headA
        if(len < 0) {
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }
        pL = headA;
        pS = headB;
        // pL一定是长链表，pS一定是短链表，len一定是正数
        // 2、让较长的链表先走差值步
        for (int i = 0; i < len && pL != null; i++) {
            pL = pL.next;
        }

        // 找到交点
        while (pL!= null && pS!= null){
            pL = pL.next;
            pS = pS.next;
        }
        return pL;
    }

    /**
     * 141、环形链表,给你一个链表，判断链表中是否有环
     * 思路：追及问题，给定两个快慢指针，快指针是慢指针速度的2倍，当快慢指针相遇，说明有环
     */
    public boolean hasCycle(ListNode head){
        if (head == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 142、 环形链表,给你一个链表，判断链表中是否有环，并且输出这个环的起点
     * 思路：当 fast 与 slow相遇时，fast一定至少已经跑了一个环的路程，而由于fast的速度是slow的2倍，因此路程也是slow的2倍
     *       设链表起点 到 环的起点距离为 X,环的大小为 C,设fast 与 slow的相遇点距离环的入口距离为Y,
     *       x(fast) = X + C + (C - Y)     x(slow) = X + (C - Y)
     *       2 * x(slow) = x(fast)  -->   X == Y
     *       这时再将fast从头开始遍历，速度和slow保持一致，当fast与slow再相遇时，这个点就是环的起点
     */
    public ListNode findCycle(ListNode head){
        if (head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }
        if (fast != null || fast.next != null) {
            return null;
        }
        // 有环  --> fast走X, slow走Y,当X==Y时，这个点就是入口
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 143、给定一个有序链表，剔除重复元素
     */
    public ListNode deleteDuplication(ListNode pHead){
        ListNode newHead = new ListNode(-1);
        ListNode tmpHead = newHead;
        ListNode cur = pHead;

        while (cur != null){
            if (cur.next != null && cur.val == cur.next.val) {
                // 一直人cur走到不重复的节点，然后把这个节点加到不重复的链表当中
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // 将当前节点移动到不重复元素节点
                cur = cur.next;
            }else {
                tmpHead.next = cur;
                tmpHead = tmpHead.next;
                cur = cur.next;
            }
        }
        tmpHead.next = null;
        return newHead.next;
    }
}
