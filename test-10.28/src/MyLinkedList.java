import java.util.LinkedList;

/**
 * @author: code_hlb
 * @date :  2023/10/28 16:49
 * @desc :  双向链表
 */
public class MyLinkedList {

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

    //　双向链表的头部
    public ListNode head;
    // 双向链表的尾巴
    public ListNode last;

    // 初始化链表
    public void init() {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode.prev = null;
        listNode.next = listNode1;
        listNode1.prev = listNode;
        listNode1.next = listNode2;
        listNode2.prev = listNode1;
        listNode2.next = listNode3;
        listNode3.prev = listNode2;
        listNode3.next = listNode4;
        listNode4.prev = listNode3;
        listNode4.next = null;
        this.head = listNode;
        this.last = listNode4;
    }

    // 遍历链表
    public void display() {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 链表的大小
    public int size() {
        int count = 0;
        ListNode cur = head;
        if (cur == null) {
            return 0;
        }
        if (cur.next == null) {
            return 1;
        }
        while (cur != null){
            count++;
            cur = cur.next;
        }
        return count;
    }

    // 是否包含元素key
    public boolean contains(int key) {
        ListNode cur = last;
        while (cur.prev != null) {
            if (cur.val == key){
                return true;
            }
            cur = cur.prev;
        }
        return false;
    }

    // 头插
    public void addHead(int elem) {
        ListNode newNode = new ListNode(elem);
        if (head == null){
            head = last = newNode;
        }else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // 尾插
    public void addTail(int elem) {
        ListNode newNode = new ListNode(elem);
        if (last == null){
            head = last = newNode;
        }else {
            newNode.prev = last;
            last.next = newNode;
            last = last.next;
        }
    }

    // 根据下标插入
    public void addIndex(int index, int elem) {
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (index == size()) {
            addTail(elem);
        }
        if (index == 0) {
            addHead(elem);
        }

        ListNode newNode = new ListNode(elem);
        ListNode cur = head;

        while (index > 0){
            cur = cur.next;
            index--;
        }
        newNode.next = cur;
        cur.prev.next = newNode;
        newNode.prev = cur.prev;
        cur.prev = newNode;
    }

    // 头删
    public void deleteHead() {
        if ( head.next == null) {
            head = null;
        }else {
            head.next.prev = null;
            head = head.next;
        }
    }

    // 尾删
    public void deleteTail() {
        if ( head.next == null) {
            head = null;
        }else {
            last.prev.next = null;
            last = last.prev;
        }
    }

    // 索引删  [9,1,2,3,4,5]  3
    public void deleteByIndex(int index) {
        if (head.next == null){
            head = null;
        }
        ListNode cur = head;
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (index == 0) {
            deleteHead();
        }else if (index == size()-1){
            deleteTail();
        }else {
            while (index > 0) {
                cur = cur.next;
                index--;
            }
            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
        }
    }

    // 删除第一次出现的关键字  [9,1,2,3,4,5]  3
    public void remove(int key) {
        // 链表为空时
        if (head == null) {
            return;
        }
        // 链表只有一个元素时
        if (head.next == null) {
            if (head.val == key) {
                head = last = null;
            }
        }
        // 链表有多个元素时
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == key) {
                // 预防待删除元素节点正好是尾节点时
                if (cur.next == null) {
                    deleteTail();
                    return;
                }
                // 预防待删除元素节点正好是头节点时
                if (cur.prev == null) {
                    deleteHead();
                    return;
                }
                cur.next.prev = cur.prev;
                cur.prev.next = cur.next;
                return;
            }
            cur = cur.next;
        }
    }

    // 删除所有出现的关键字
    public void removeAll(int key) {
        // 链表为空时
        if (head == null) {
            return;
        }
        // 链表只有一个元素时
        if (head.next == null) {
            if (head.val == key) {
                head = last = null;
            }
        }
        // 链表节点有多个时
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == key) {
                // 预防待删除元素节点正好是尾节点时
                if (cur.next == null) {
                    deleteTail();
                }
                // 预防待删除元素节点正好是头节点时
                if (cur.prev == null) {
                    deleteHead();
                }else {
                    cur.next.prev = cur.prev;
                    cur.prev.next = cur.next;
                    cur = cur.next;
                }
            }
            cur = cur.next;
        }
    }

    // 清空链表
    public void clean(){
        ListNode curNext = head;
        ListNode cur = head;
        while (cur != null){
            curNext = cur.next;
            cur.prev = null;
            cur.next = null;
            cur = curNext;
        }
        head = last = null;
    }
}
