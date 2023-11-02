
/**
 * @author: code_hlb
 * @date :  2023/11/2 14:58
 * @desc :  LeetCode-LinkList
 */
public class Demo1 {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode() {
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

    public void addHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        }else {
            node.next = head;
            head = node;
        }
    }

    /**
     *  61. 旋转链表: 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     *    思路：算出给定链表的长度为 n，当向右移动的次数 k >= n 时，我们只需向右移动 k mod n 次即可。
     *    因为每 n 次移动都会让链表变为原状。
     *    因此可知新链表的最后一个节点为原链表的第 (n−1) − (k mod n) 个节点（从 0 开始计数)
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode cur = head;
        ListNode prev = head;
        int size = 0;
        // 1、计算长度
        // 循环结束时prev作为原链表的尾节点
        while(cur != null) {
            size++;
            prev = cur;
            cur = cur.next;
        }
        if(size <= 1 || k % size == 0) {
            return head;
        }
        // 2、形成环
        prev.next = head;
        // 3、找到新的尾节点，从0开始计数
        int num = (size - 1) - (k % size);
        cur = head;
        while(num > 0) {
            cur = cur.next;
            num--;
        }
        // 新尾节点的下一个节点就是新头节点
        ListNode newHead = cur.next;
        //　将新的尾节点next赋空，将环断开
        cur.next = null;
        return newHead;
    }

    /**
     * 725. 分隔链表
     *  给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
     *  每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
     *  这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
     *  返回一个由上述 k 部分组成的数组。
     *  输入：head = [1,2,3], k = 5
     *  输出：[[1],[2],[3],[],[]]
     *
     *  输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
     *  输出：[[1,2,3,4],[5,6,7],[8,9,10]]
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode cur = head;
        // 1、求出链表的长度
        int size = 0;
        while(cur != null) {
            size++;
            cur = cur.next;
        }
        // 2、控制子链表元素数量，例如size = 5,k = 2,则第一个部分的结点数量为 5/2 + 1，第二个部分的数量为 5/2;
        int num1 = size / k;
        // 控制当前部分的索引，进入循环时若 i<num2，说明这是第一个子链表，则结点数量应该num1+1;
        int num2 = size % k;
        ListNode[] parts = new ListNode[k];
        cur = head;
        for(int i = 0; i < k && cur != null; i++) {
            // 将cur作为当前部分的头结点
            parts[i] = cur;
            // 计算当前子链表的长度
            int partSize = num1 + (i < num2 ? 1 : 0);
            // 将cur移动到子链表的尾结点处
            // 前面已经将cur作为头节点，因此从1开始进入循环
            for(int j = 1; j < partSize; j++) {
                cur = cur.next;
            }
            ListNode curNext = cur.next;
            cur.next = null;
            // curNext为下一部分的头节点
            cur = curNext;
        }
        return parts;
    }

    /**
     * 1019. 链表中的下一个更大节点
     * 给定一个长度为 n 的链表 head
     * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
     * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
     * 如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
     *
     *  输入：head = [2,1,5]
     *  输出：[5,5,0]
     *  输入：head = [2,7,4,3,5]
     * 输出：[7,0,5,5,0]
     */
    public int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while(cur != null) {
            size++;
            cur = cur.next;
        }
        // 创建返回的结果数组
        int[] answer = new int[size];
        if(size < 1) {
            return answer;
        }
        cur = head;
        int i = 0;
        while(cur != null && cur.next != null) {
            ListNode curNext = cur.next;
            int max = cur.val;
            while(curNext != null) {
                if(cur.val < curNext.val) {
                    max = curNext.val;
                    break;
                }else {
                    curNext = curNext.next;
                }
            }
            if (max != cur.val) {
                answer[i++] = max;
            }else {
                answer[i++] = 0;
            }
            cur = cur.next;
        }
        answer[i] = 0;
        return answer;
    }
}
