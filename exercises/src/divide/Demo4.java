package divide;

import linklist.ListNode;

import java.util.PriorityQueue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: divide
 * @CreateTime : 2024/6/11 10:51
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 23. 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * 示例 1：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     * 输入：lists = [[]]
     * 输出：[]
     * 提示：
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // 方法1：分治，将数组一直细分，最后演变为两个有序链表的合并
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left >= right) {
            return lists[left];
        }
        // 1、分解链表
        int mid = left + (right - left) / 2;
        ListNode list1 = merge(lists, left, mid);
        ListNode list2 = merge(lists, mid + 1, right);
        // 2、合并链表
        return mergeSort(list1, list2);
    }

    private static ListNode mergeSort(ListNode list1, ListNode list2) {
        // 两个有序链表的合并
        ListNode resList = new ListNode(-1);
        ListNode tmp = resList;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                tmp.next = list2;
                list2 = list2.next;
            } else {
                tmp.next = list1;
                list1 = list1.next;
            }
            tmp = tmp.next;
        }
        tmp.next = list1 == null ? list2 : list1;
        return resList.next;
    }

    public static ListNode mergeKLists1(ListNode[] lists) {
        // 方法2：借用小根堆的特性，每次将当前堆顶 元素插入哨兵节点，并且判定如果当前节点还有后驱，则将后驱插入堆中
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        // 初始化堆
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        // 建立哨兵节点
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        while (!priorityQueue.isEmpty()) {
            ListNode cur = priorityQueue.poll();
            // 一个一个元素插入
            tmp.next = cur;
            // 如果当前节点还有后驱，则将后驱节点加入堆中
            if (cur.next != null) {
                priorityQueue.add(cur.next);
            }
            tmp = tmp.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        // 输入：lists = [[1,4,5],[1,3,4],[2,6]]
        ListNode[] lists = {new ListNode(1, new ListNode(4, new ListNode(5))), new ListNode(1, new ListNode(3, new ListNode(4))), new ListNode(2, new ListNode(6))};
        ListNode[] lists1 = {new ListNode(1)};
        System.out.println(mergeKLists(lists));
        System.out.println(mergeKLists1(lists));
        System.out.println(mergeKLists(lists1));
        System.out.println(mergeKLists1(lists1));
    }
}
