package linklist;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: Linkedlist
 * @CreateTime : 2024/4/8 11:09
 * @Description: ListNode
 * @Author: code_hlb
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
