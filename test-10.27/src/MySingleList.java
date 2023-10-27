
/**
 * @author: code_hlb
 * @date :  2023/10/27 13:52
 * @desc :  一些对链表操作的面试题
 */

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

        public ListNode() {
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

    // 遍历链表
    public void display(){
        ListNode cur = head;

        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 通过给定节点遍历链表
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

    /**
     * 一、移除所有关键字为key的节点
     *  采用双指针的思想：一个指针指向当前节点，一个指针指向前驱节点
     */
    public void removeAllKey(int key){
        if (head == null){
            return;
        }
        ListNode cur = head.next;
        ListNode prev = head;
        while (cur != null){
            if (cur.value == key){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
            if (head.value == key){
                // 删除头节点
                head = head.next;
            }
        }
    }

    /**
     * 二、链表的反转 --> 采用头插的思想，从头节点往后遍历，逐个将节点头插，head随之一直往前移
     *  1、保存当前反转节点的下一个节点  2、当前节点的next保存头节点的地址  3、head节点往前移  4、将当前节点移动到下一节点
     *  1、curNext = cur.next          2、cur.next = head               3、head = cur     4、cur = curNext
     */
    public ListNode reverse(){
        if (head == null) {
            return null;
        }
        if (head.next == null){
            return head;
        }
        // cur从第二个节点开始
        ListNode cur = head.next;
        // 第一个节点的next置为空
        head.next = null;
        while (cur != null){
            // 记录现在反转节点的下一个节点
            ListNode curNext = cur.next;
            cur.next = head;
            head = cur;
            cur = curNext;
        }
        return head;
    }

    /**
     * 三、找到链表的中间节点，若中间节点有两个，取第二个节点：快慢指针的思想
     *  1、fast: 一次走两步，
     *  2、slow：一次走一步
     *  原理：当fast的速度是slow的两倍的时候，slow一定是在中间位置
     *        x = vt,v相差两倍，t一定，x自然相差两倍
     */
    public ListNode findMiddleNode(){
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        // 都从同一起点开始
        ListNode fast = head;
        ListNode slow = head;
        // 思考：如果是 fast.next != null && fast != null 会发生什么？
        //   答：会发生空指针异常，若链表节点数是偶数个时，fast已经为空，没有指向下一个的地址，当访问next时就会发生空指针异常
        //       解决这个问题只需先判断fast是否为空,再判断next即可
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 四、输入一个链表,输出链表中倒数第 k 个节点
     *  1、先让 fast 走 k 步，
     *  2、将 slow 和 fast 绑定着一起往前走
     *  3、当fast走到最后一个节点时，当前的slow节点就是倒数第 k 个节点
     *  原理：两个人速度v相同，他们起始距离相隔x，当前面一个走到终点时，他们之间还是相距x，而这个x就是倒数x的距离
     */
    public ListNode findKthToTail(int k){
        if (k <= 0 || head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            if (fast == null){
                System.out.println("k过大了");
            }else {
                fast = fast.next;
            }
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 六、给定一个值x,以x为基准将链表风分割成两部分，所有小于x的结点排在大于或大于x的结点之前
     *  思路：定义两个头节点bs,as，两个尾结点be,ae，因为要保证链表原来的顺序不变，所以采用尾插法加入结点
     *  1、首先定义一个结点从链表头开始遍历          ListNode cur = pHead;
     *  2、然后进入循环，当cur不为空时，比较cur.value 与 x 的大小关系，小的往be尾插，大的往ae尾插
     *  3、接着第一次插入时，应该要保证 bs = be,as = ae
     *  4、最后将后半部分的头接到前半部分的尾并返回前半部分的头，be.next = as; return bs;
     */
    public ListNode partiton(ListNode pHead,int x){
        // 第一部分的头（小于 x 的部分）
        ListNode bs = null;
        // 第一部分的尾
        ListNode be = null;
        ListNode as = null;
        ListNode ae = null;

        ListNode cur = pHead;
        // 没有遍历完成链表
        while (cur != null){
            if (cur.value < x ){
                // 第一次插入
                if (bs == null){
                    bs = be = cur;
                }else {
                    be.next = cur;
                    be = be.next;
                }
            }else {
                // 第一次插入
                if (as == null){
                    as = ae = cur;
                }else {
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        // 第一段没有数据
        if (bs == null) {
            return as;
        }
        be.next = as;
        // 如果链表为：23，12，11，2，3，14; x 为 15时，此时的ae最后一个结点为3,但是3.next不为空，就会陷入死循环
        // 因此此处需要防止ae.next不为空时，让它为空
        if (ae != null){
            ae.next = null;
        }
        return bs;
    }

    /**
     * 七、链表的回文结构  --> 要求时间复杂度为 O(n),空间复杂度为 O(1)
     *  思路：翻转链表
     *  1、找到链表的中间结点  --> x = vt
     *  2、翻转中间结点以后的链表  --> 头插
     *  3、从前从后开始比较
     */

    public boolean isHuiWen(){

        // 当结点为奇数时，返回true
        if (head.next == null) {
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        // 1、找到链表的中间结点  --> x = vt
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 2、翻转中间结点以后的链表  --> 头插
        ListNode cur = slow.next;
        // 翻转链表后，第一个结点为尾结点，因此next应该为空
        slow.next = null;
        while (cur != null){
            // 记录下一个结点
            ListNode curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        // 3、从前从后开始比较，判断是否回文
        while (head != slow){
            if (head.value != slow.value){
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }
}
