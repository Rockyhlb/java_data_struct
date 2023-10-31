import org.w3c.dom.ls.LSException;

import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/31 19:14
 * @desc :  LeetCode --> LinkList
 */
public class Demo1 {

    static class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {
        }
        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode head;
    public void addHead(int value) {
        ListNode node = new ListNode(value);
        node.next = this.head;
        head = node;
    }

    public void display() {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * LCR 123. 图书整理:
     *  书店店员有一张链表形式的书单，每个节点代表一本书，节点中的值表示书的编号。
     *  为更方便整理书架，店员需要将书单倒过来排列，就可以从最后一本书开始整理，逐一将书放回到书架上。请倒序返回这个书单链表。
     *  输入：head = [3,6,4,1]
     *  输出：[1,4,6,3]
     */
    public int[] reverseBookList(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while(cur != null) {
            cur = cur.next;
            size++;
        }
        int[] books = new int[size];
        while(head != null) {
            books[size - 1] = head.val;
            head = head.next;
            size--;
        }
        return books;
    }

    /**
     * 24. 两两交换链表中的节点
     *  给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     *  你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     *  思路：采用递归的思想，
     *    1: 每次循环都先保存下一个节点，然后将当前节点与前一个节点（head节点）调换，ListNode curNext = cur.next; cur.next = head;
     *    2: 返回本次调换的前一个节点，也就是cur节点()  return cur;
     *    3: 将本次调换的后面个节点的next指向下次递归的返回值，最终完成每次调换节点时，当前尾节点与下次的头节点拼接
     *    head.next = swapPairs(curNext); !!!
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head.next;
        ListNode curNext = cur.next;
        cur.next = head;
        head.next = swapPairs(curNext);
        return cur;
    }

    /**
     * 25. K 个一组翻转链表
     *     给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     *     k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *     你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 链表的长度
        int size = 0;
        ListNode cur = head;
        while(cur != null) {
            size++;
            cur = cur.next;
        }
        if(k > size) {
            return head;
        }
        // 从头节点开始进入循环
        cur = head;
        ListNode kHead = null, curNext = head;
        for(int i = 0; i < k; i++){
            // 保存下一个节点
            curNext = cur.next;
            // 将本次翻转链表的头接到当前节点的next
            cur.next = kHead;
            // 链表头往前移
            kHead = cur;
            // 当前节点往后移
            cur = curNext;
        }
        // 翻转完以后，原本的头已经被成了尾，也就是head变成了尾
        // 将本次翻转的尾接到下一次递归返回的头上
        head.next = reverseKGroup(cur, k);
        // 返回本次翻转的头节点
        return kHead;
    }
}
