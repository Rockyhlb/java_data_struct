package linklist;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/8 8:46
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 82. 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     * 示例 1：
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     * 示例 2：
     * 输入：head = [1,1,1,2,3]
     * 输出：[2,3]
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 方法1：双指针
        ListNode newHead = new ListNode(-1);
        ListNode tmpNode = newHead;
        // 双指针进行遍历，不同则新建节点并添加到newHead
        ListNode prev = new ListNode(-100000);
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                prev = cur.next;
                cur = cur.next.next;
            } else if (cur.val == prev.val) {
                prev = cur;
                cur = cur.next;
            } else {
                tmpNode.next = new ListNode(cur.val);
                tmpNode = tmpNode.next;
                prev = cur;
                cur = cur.next;
            }
        }
        // 处理最后一个节点：经过上述循环以后，当前节点一定是尾节点
        if (cur != null && cur.val != prev.val) {
            tmpNode.next = cur;
        }
        return newHead.next;
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        // 方法2：通过Map进行键值对映射
        Map<Integer, Boolean> map = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, true);
            } else {
                map.put(cur.val, false);
            }
            cur = cur.next;
        }
        // 构造返回的新链表
        ListNode newHead = new ListNode(-100000, head);
        ListNode pre = newHead;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val)) {
                // 如果value为true说明这是重复节点
                pre.next = cur.next;
            } else {
                // 不是重复节点
                pre = cur;
            }
            cur = cur.next;
        }
        return newHead.next;
    }


    public static void main(String[] args) {
        // head = [1,2,3,4,5,5];
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(5);

//        System.out.println(deleteDuplicates(head));
        System.out.println(deleteDuplicates1(head));
    }
}
