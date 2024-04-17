package linklist;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/4/17 9:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode(-1);
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode tmpHead = newHead;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                tmpHead.next = new ListNode(list2.val);
                list2 = list2.next;
            } else {
                tmpHead.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            tmpHead = tmpHead.next;
        }
        while (list1 != null) {
            tmpHead.next = new ListNode(list1.val);
            tmpHead = tmpHead.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            tmpHead.next = new ListNode(list2.val);
            tmpHead = tmpHead.next;
            list2 = list2.next;
        }

        return newHead.next;
    }

    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        // 采用递归进行优化
        if (list1 == null) {
            // 递归终止条件
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists1(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists1(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        System.out.println(mergeTwoLists(list1, list2));
        System.out.println(mergeTwoLists1(list1, list2));
    }
}
