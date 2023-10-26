
/**
 * @author: code_hlb
 * @date :  2023/10/26 13:59
 * @desc :  链表
 */
public class Demo2 {

    public static void main(String[] args) {

        MySingleList mySingleList = new MySingleList();
        mySingleList.createList();

        System.out.println("=========display()==========");
        mySingleList.display();

        System.out.println("=========size()==========");
        System.out.println(mySingleList.size());

        System.out.println("=========contains(3)==========");
        System.out.println(mySingleList.contains(3));

        System.out.println("=========addFirst()==========");
        mySingleList.addHead(10);
        mySingleList.addHead(9);
        mySingleList.display();

        System.out.println("=========addEnd(100)==========");
        mySingleList.addtail(100);
        mySingleList.display();

        System.out.println("=========addByIndex()==========");
        mySingleList.addByIndex(2,20);
        mySingleList.addByIndex(0,8);
        mySingleList.display();

        System.out.println("=========delHead()==========");
        mySingleList.delHead();
        mySingleList.display();

        System.out.println("=========delTail()==========");
        mySingleList.delTail();
        mySingleList.display();

        System.out.println("=========delByKey(10)==========");
        mySingleList.delByKey(10);
        mySingleList.display();

        System.out.println("=========delByIndex(1)==========");
        mySingleList.delByIndex(1);
        mySingleList.display();
    }
}
