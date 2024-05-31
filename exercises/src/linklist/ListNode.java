package linklist;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: Linkedlist
 * @CreateTime : 2024/4/8 11:09
 * @Description: ListNode
 * @Author: code_hlb
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode random;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val, ListNode next, ListNode random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                ", random=" + random +
                '}';
    }
}
