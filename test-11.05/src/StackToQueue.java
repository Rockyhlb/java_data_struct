
import java.util.Stack;

/**
 * @author: code_hlb
 * @date :  2023/11/5 16:14
 * @desc :  通过栈实现队列
 */
public class StackToQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public StackToQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // 入队的时候把所有元素进入stack1
    public void push(int x) {
        stack1.push(x);
    }

    // 出队
    public int pop() {
        if(empty()) {
            return -1;
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int top() {
        if(empty()) {
            return -1;
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
}
