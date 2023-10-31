import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/31 19:14
 * @desc :  测试类
 */
public class Test {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.addHead(5);
        demo1.addHead(4);
        demo1.addHead(3);
        demo1.addHead(2);
        demo1.addHead(1);
        demo1.display();
        /*demo1.swapPairs(demo1.head);
        demo1.display();*/
        demo1.reverseKGroup(demo1.head,2);
        demo1.display();
    }
}
