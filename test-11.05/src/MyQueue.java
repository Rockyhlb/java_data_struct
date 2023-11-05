
/**
 * @author: code_hlb
 * @date :  2023/11/5 14:12
 * @desc :  通过双向链表实现自定义队列
 */
public class MyQueue {

    static class ListNode {
        private int val;
        private ListNode prev;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode() {
        }
    }
    // 队头
    private ListNode front;
    // 队尾
    private ListNode rear;
    private int usedSize;

    // 入队 --> 尾插法实现
    public void offer(int value) {
        ListNode node = new ListNode(value);
        if (front == null) {
            front = rear = node;
        }else {
            rear.next = node;
            node.prev = rear;
            rear = rear.next;
        }
        usedSize++;
    }

    // 出队 --> 删除头节点
    public int poll() {
        if (front == null) {
            return -1;
        }
        int val = front.val;
        if (front == rear) {
            front = null;
            rear = null;
            usedSize--;
            return val;
        }
        front = front.next;
        front.prev = null;
        usedSize--;
        return val;
    }

    // 查看队头元素
    public int peek() {
        if (front == null) {
            return -1;
        }
        return front.val;
    }

    // 查看队列
    public void disPlay() {
        ListNode cur = front;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public int getUsedSize() {
        return usedSize;
    }

    public boolean isEmpty() {
        return usedSize == 0;
    }
}
