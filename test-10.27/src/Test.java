import java.util.List;

/**
 * @author: code_hlb
 * @date :  2023/10/27 11:22
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {

        MySingleList mySingleList = new MySingleList();
        mySingleList.createList();
        mySingleList.addHead(3);

        System.out.println("=========oldLinkedList()==========");
        mySingleList.display();

        System.out.println("=========removeAllKey(3)==========");
        mySingleList.addHead(3);
        mySingleList.removeAllKey(3);
        mySingleList.display();

        // 链表的反转
        System.out.println("=========reverse()==========");
        mySingleList.reverse();
        mySingleList.display();

        // 找到链表的中间节点
        System.out.println("=========findMiddleNode()==========");
        MySingleList.ListNode middleNode =  mySingleList.findMiddleNode();
        System.out.println(middleNode.getValue());

        // 找到链表的倒数第k个节点
        System.out.println("=========findKthToTail(3)==========");
        MySingleList.ListNode findKthToTail =  mySingleList.findKthToTail(3);
        System.out.println(findKthToTail.getValue());

        System.out.println("=========partiton(4)==========");
        mySingleList.addHead(10);
        MySingleList.ListNode node = mySingleList.partiton(mySingleList.head,4);
        mySingleList.display(node);

        System.out.println("=========ishuiwen()==========");
        System.out.println(mySingleList.isHuiWen());

        MySingleList mySingleList1 = new MySingleList();
        mySingleList1.addHead(2);
        System.out.println(mySingleList1.isHuiWen());
    }

    public static void main1(String[] args) {
        MySingleList mySingleList1 = new MySingleList();
        mySingleList1.addHead(5);
        mySingleList1.addHead(4);
        mySingleList1.addHead(3);
        mySingleList1.addHead(2);
        mySingleList1.addHead(1);

        MySingleList mySingleList2 = new MySingleList();
        mySingleList2.addHead(15);
        mySingleList2.addHead(12);
        mySingleList2.addHead(9);
        mySingleList2.addHead(6);
        mySingleList2.addHead(3);

        System.out.println("=========splicingTwoList(mySingleList1.head,mySingleList2.head)==========");
        MySingleList.ListNode newHead = splicingTwoList(mySingleList1.head,mySingleList2.head);
        mySingleList1.display(newHead);
    }

    /**
     * 五、将两个有序链表合并为一个新的有序链表并返回，新链表是通过拼接给定的两个链表的所有节点组成的
     *  1、声明两个节点，newHead 和 tailHead,
     *  2、newHead = tailHead;
     *  3、进入循环，链表headA 和 headB的value进行比较，若A > B，则令tailHead = headB; tailHead = tailHead.next; headB = heeadB.next;
     */
    public static MySingleList.ListNode splicingTwoList(MySingleList.ListNode A, MySingleList.ListNode B){
        MySingleList.ListNode newHead = new MySingleList.ListNode(-1);  // 不具备有效数据
        MySingleList.ListNode tmpHead = newHead;

        while(A != null && B != null){
            if(A.getValue() > B.getValue()){
                tmpHead.next = B;
                B = B.next;
            }else {
                tmpHead.next = A;
                A = A.next;
            }
            tmpHead = tmpHead.next;
        }
        // 当链表B已经被合并时，拼接剩下的A链表
        if (A != null){
            tmpHead.next = A;
        }
        // 当链表A已经被合并时，拼接剩下的B链表
        if (B != null){
            tmpHead.next = B;
        }
        return newHead.next;
    }
}
