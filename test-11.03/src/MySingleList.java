
import java.util.Stack;

/**
 * @author: code_hlb
 * @date :  2023/11/3 14:41
 * @desc :  自定义链表，通过栈实现对链表的逆序遍历(替代原来通过递归打印链表的思想)
 */
public class MySingleList {
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

    public void addHead(int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        }else {
            node.next = head;
            head = node;
        }
    }

    public void display() {
        ListNode cur = this.head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void displayReverse() {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = this.head;
        while (cur != null) {
            ListNode curNext = cur.next;
            stack.push(cur);
            cur = curNext;
        }
        while (!stack.empty()) {
            System.out.print(stack.pop().val + " ");
        }
        System.out.println();
    }
}
