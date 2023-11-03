import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/11/3 14:29
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {
        // 测试通过栈对链表的逆序打印
        MySingleList mySingleList = new MySingleList();
        mySingleList.addHead(5);
        mySingleList.addHead(4);
        mySingleList.addHead(3);
        mySingleList.addHead(2);
        mySingleList.addHead(1);
        System.out.println("========linkedList()===========");
        mySingleList.display();
        System.out.println("========displayReverse()===========");
        mySingleList.displayReverse();
    }

    public static void main1(String[] args) {
        // 测试自定义栈
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println("========push()===========");
        System.out.println(Arrays.toString(myStack.elems));
        System.out.println("========pop()===========");
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println("========peek()===========");
        System.out.println(myStack.peek());
        System.out.println(myStack.peek());
    }
}
