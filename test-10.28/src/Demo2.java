
/**
 * @author: code_hlb
 * @date :  2023/10/28 14:42
 * @desc :  测试双向链表
 */
public class Demo2 {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.init();

        System.out.println("===========init()============");
        myLinkedList.display();

        System.out.println("===========size()============");
        System.out.println(myLinkedList.size());

        System.out.println("===========contains(5)============");
        System.out.println(myLinkedList.contains(5));

        System.out.println("===========addHead(10)============");
        myLinkedList.addHead(10);
        myLinkedList.display();

        System.out.println("===========addTail(10)============");
        myLinkedList.addTail(100);
        myLinkedList.display();

        System.out.println("===========addIndex(1,9)============");
        myLinkedList.addIndex(1,9);
        myLinkedList.display();

        System.out.println("===========deleteHead()============");
        myLinkedList.deleteHead();
        myLinkedList.display();

        System.out.println("===========deleteTail()============");
        myLinkedList.deleteTail();
        myLinkedList.display();

        System.out.println("===========deleteByIndex(2)============");
        myLinkedList.deleteByIndex(2);
        myLinkedList.display();

        System.out.println("===========deleteByKey(1)============");
        myLinkedList.remove(9);
        myLinkedList.display();

        System.out.println("===========removeAll(9)============");
        myLinkedList.addHead(9);
        myLinkedList.addHead(9);
        System.out.print("before removeAll: ");
        myLinkedList.display();
        System.out.print("before removeAll: ");
        myLinkedList.removeAll(9);
        myLinkedList.display();

        System.out.println("===========clean()============");
        myLinkedList.clean();
        myLinkedList.display();
    }
}
