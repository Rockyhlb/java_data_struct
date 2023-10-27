
/**
 * @author: code_hlb
 * @date :  2023/10/26 15:52
 * @desc :  测试自定义链表
 */

/*
* 总结：ArraList 物理地址和逻辑地址都是有序的，通过数组的下标可以随机访问数据，支持频繁的对数据进行读取，
*           但是对数据的增加和删除效率比较低，会导时间复杂度过高  -->  顺序存取，随机读取
*       LinkedList 物理地址可能不连续，逻辑地址连续，通过next对链表进行遍历， -->　顺序读取，随机存取
*       当我们对链表进行遍历时，我们需要读取到每一个节点，因此我们的循环判断条件应该是 head != null
*       当我们对链表进行增删操作时，我们只需要读取到尾节点，因此我们的循环判断条件应该是 head.next != null
* */
public class MySingleList {

    static class ListNode{
        // 存储链表的值
        private int value;
        // 引用 --> 指向下一个节点的地址
        public ListNode next;

        public int getValue() {
            return value;
        }

        public ListNode(int value) {
            this.value = value;
        }
    }

    // 头节点  属于单链表的属性，因此不能定义在ListNode当中
    public ListNode head;

    public void createList(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // 让节点node1成为头节点
        this.head = node1;

    }

    // 头插  node.next = head; head = node;
    public void addHead(int value){
        ListNode newNode = new ListNode(value);

        if (head == null){
            head = newNode;
        }else {
            newNode.next = head;
            head = newNode;
        }
    }

    // 尾插 --> 遍历链表到最后一个节点，将最后一个节点的next指向node
    public void addtail(int value){
        ListNode newNode = new ListNode(value);
        ListNode cur = head;

        if (cur == null){
            head = newNode;
            return;
        }
        // 找到最后一个节点
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = newNode;
    }

    // 任意插 -->  走到index-1的位置, newNode.next = cur.next;  cur.next = newNode;
    public void addByIndex(int index,int value){
        if (index < 0 || index > size()){
            System.out.println("索引不合法！");
            return;
        }
        if (index == 0){
            addHead(value);
            return;
        }
        if (index == size()){
            addtail(value);
            return;
        }
        ListNode cur = findPrevNodeByIndex(index);
        ListNode newNode = new ListNode(value);
        newNode.next = cur.next;
        cur.next = newNode;
    }


    /**
     * 写一个帮助通过索引找到索引前一个位置节点的私有方法，
     * 节省任意加和任意删的代码重复
     */
    private ListNode findPrevNodeByIndex(int index){
        ListNode cur = head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 头删
    public void delHead(){
        if (size() == 0){
            System.out.println("链表为空！");
            return;
        }
        head = head.next;
    }

    // 尾删
    public void delTail(){
        ListNode cur = findPrevNodeByIndex(size() - 1);
        cur.next = null;
    }

    // 任意位置删  1、找到删除节点的前一个节点  2、删除的节点 del = cur.next  3、cur.next = del.ext
    public void delByIndex(int index){
        if (index < 0 || index > size()){
            System.out.println("索引不合法！");
            return;
        }
        if (index == 0){
            delHead();
            return;
        }
        if (index == size()) {
            delTail();
            return;
        }
        ListNode cur = findPrevNodeByIndex(index);
        ListNode del = cur.next;
        cur.next = del.next;
    }

    // 删除第一次出现关键字为key的节点
    public void delByKey(int key){

        if (head == null){
            return;
        }

        // 单独删除头节点
        if (head.value == key){
            head = head.next;
            return;
        }

        ListNode cur = findPrevNodeByKey(key);
        if (cur == null){
            System.out.println("没有要删除的数字！");
            return;
        }
        ListNode del = cur.next;
        cur.next = del.next;
    }

    // 找到与关键字匹配的前驱节点
    private ListNode findPrevNodeByKey(int key){
        ListNode cur = head;
        while (cur.next != null ){
            if (cur.next.value == key){
                return cur;
            }
            cur = cur.next;
        }
        return cur;
    }

    // 遍历链表
    public void display(){
        ListNode cur = head;

        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 方法重载 --> 从传入的节点处开始遍历链表
    public void display(ListNode node){
        while (node != null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    // 得到链表的长度
    public int size(){
        ListNode cur = head;
        int count = 0;

        while (cur != null){
            count++;
            cur = cur.next;
        }
        return count;
    }

    // 清空链表
    public void clean(){
        this.head = null;
    }

    // 查找元素key是否包含在链表中
    public boolean contains(int key){
        ListNode cur = head;
        while (cur != null){
            if (cur.value == key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
}
