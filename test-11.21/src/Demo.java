
/**
 * @BelongsProject: test-11.21
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-21 16:10
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class Demo {
    public static void duplicateZeros(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            if(arr[i] == 0) {
                for(int j = arr.length-1-1; j > i; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[++i] = 0;
            }
        }
    }

    /**
     *1016. 子串能表示从 1 到 N 数字的二进制串
     * 给定一个二进制字符串 s 和一个正整数 n，如果对于 [1, n] 范围内的每个整数，
     * 其二进制表示都是 s 的 子字符串 ，就返回 true，否则返回 false 。
     * 子字符串 是字符串中连续的字符序列。
     */
    public static boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String str = Integer.toBinaryString(i);
            System.out.println(str);
            if (!s.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题 02.08. 环路检测   https://leetcode.cn/problems/linked-list-cycle-lcci/
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     *
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     */
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            // 快慢指针，当两者相遇，说明有环，
            fast = fast.next.next;
            slow = slow.next;
            // 有环以后，再让fast从起点继续以 slow 的速率往前遍历，当二者再次相遇时就是环的起点
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 面试题 02.01. 移除重复节点
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * 示例1:
     *  输入：[1, 2, 3, 3, 2, 1]
     *  输出：[1, 2, 3]
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            ListNode newCur = cur;
            while(newCur.next != null) {
                if(newCur.next.val == cur.val) {
                    newCur.next = newCur.next.next;
                }else {
                    newCur = newCur.next;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 面试题 01.06. 字符串压缩  -->  https://leetcode.cn/problems/compress-string-lcci/
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
     * 你可以假设字符串中只包含大小写英文字母（a至z）。
     * 示例1:
     *  输入："aabcccccaaa"
     *  输出："a2b1c5a3"
     */
    public static String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            sb.append(S.charAt(i));
            int count = 1; // 本身含有一个字母
            // 备份 i 的下标
            int index = i;
            for (int j = i + 1; j < S.length(); j++) {
                if (S.charAt(j) == S.charAt(i)) {
                    count++;
                    index++;
                }else {
                    break;
                }
            }
            i = index;
            sb.append(count);
        }
        if (sb.toString().length() >= S.length()) {
            return S;
        }
        return sb.toString();
    }

    /**
     * 面试题 08.03. 魔术索引
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
     * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
     * 若有多个魔术索引，返回索引值最小的一个。
     */
    public int findMagicIndex(int[] nums) {
        int minIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return minIndex;
    }


    public static void main(String[] args) {
        int[] num = {1,2,0,2,1};
        duplicateZeros(num);

        System.out.println(queryString("0110", 3));

        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode newHead = removeDuplicateNodes(head);
        System.out.println(compressString("aabcccccaaa"));
    }
}
