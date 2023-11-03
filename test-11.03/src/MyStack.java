
/**
 * @author: code_hlb
 * @date :  2023/11/3 14:29
 * @desc :  自定义栈
 */
public class MyStack {

    public static final int DEFAULT_CAPASITY = 10;
    public int usedSize = 0;
    public int[] elems;

    public MyStack() {
        this.elems = new int[DEFAULT_CAPASITY];
    }

    public MyStack(int size) {
        this.elems = new int[size];
    }

    // 入栈
    public void push(int val) {
        this.elems[usedSize++] = val;
    }

    // 出栈
    public int pop() {
        // usedSize位置处元素为空，因此并不是栈顶
        return this.elems[--usedSize];
    }

    // 查看栈顶元素(栈顶元素不出栈)
    public int peek() {
        return this.elems[usedSize - 1];
    }
}
