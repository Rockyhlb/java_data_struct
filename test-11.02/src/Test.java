import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/11/2 14:58
 * @desc :
 */
public class Test {
    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();
        demo1.init();
        // demo1.rotateRight(demo1.head,1);

        Demo1 demo2 = new Demo1();
        demo2.addHead(5);
        demo2.addHead(3);
        demo2.addHead(4);
        demo2.addHead(7);
        demo2.addHead(2);
        System.out.println(Arrays.toString(demo1.nextLargerNodes(demo2.head)));
    }
}
