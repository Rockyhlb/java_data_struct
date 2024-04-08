package linklist;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: linklist
 * @CreateTime : 2024/4/8 11:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 2、两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 新建节点头
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        // 记录进位数
        int count = 0;
        // 当l1 和 l2 都 不为空时
        while (l1 != null && l2 != null) {
            ListNode node = new ListNode();
            int sum = l1.val + l2.val;
            node.val = sum % 10 + count;
            if (node.val == 10) {
                // 当进位以后正好是10时,归零
                node.val = 0;
                // 记录进位
                count = 1;
            } else {
                count = sum / 10;
            }
            cur.next = node;
            l1 = l1.next;
            l2 = l2.next;
            cur = cur.next;
        }
        // 当l1 比 l2长时
        if(l1 != null) {
            traverse(l1,cur,count);
        }
        // 当l2 比 l1长时
        if(l2 != null) {
            traverse(l2,cur,count);
        }
        // 当进位不为0时
        while (count != 0) {
            cur.next = new ListNode(count);
            count--;
        }
        return newHead.next;
    }
    private static void traverse(ListNode head,ListNode cur,int count) {
        while (head != null) {
            int sum = head.val + count;
            ListNode node = new ListNode(sum % 10);
            count = sum / 10;
            cur.next = node;
            cur = cur.next;
            head = head.next;
        }
    }

    // 解法2: 优化上述过程
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        // 记录进位值
        int count = 0;
        while (l1 != null || l2 != null) {
            // 三目运算符判断链表是否为空，true 则返回0(用0占位，使两链表长度统一)，false则返回val
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + count;
            count = sum / 10;
            // 将 sum%10 的节点插入结果链表
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 由于题目给出：每个节点只能存储一位数字，因此count的最大值只可能为1
        if (count == 1) {
            cur.next = new ListNode(count);
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        // 输入：l1 = [2,4,3], l2 = [5,6,4]
        // 输出：[7,0,8]
        // 解释：342 + 465 = 807.
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println(addTwoNumbers(l1, l2).toString());
        System.out.println(addTwoNumbers2(l1, l2).toString());
    }
}
