import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: code_hlb
 * @date :  2023/11/5 15:51
 * @desc :  用队列实现栈
 */
public class QueueToStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public QueueToStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    // 入栈：进入不为空的队列中，如果都为空则进入queue1
    public void push(int x) {
        if(!queue1.isEmpty()) {
            queue1.offer(x);
        }else if(!queue2.isEmpty()) {
            queue2.offer(x);
        }else {
            queue1.offer(x);
        }
    }

    // 出栈，将不为空队列的前size-1个元素加入到另一个队列之中，该队列的最后一个元素就是栈顶元素
    public int pop() {
        if(empty()) {
            return -1;
        }
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            // 循环当中不能使用queue1.size()作为循环终止条件，size是个变量，会动态变化
            for (int i = 0; i < size-1; i++) {
                int tmp = queue1.poll();
                queue2.offer(tmp);
            }
            return queue1.poll();
        }else {
            int size = queue2.size();
            for (int i = 0; i < size-1; i++) {
                int tmp = queue2.poll();
                queue1.offer(tmp);
            }
            return queue2.poll();
        }
    }

    // 查看栈顶元素，将不为空的队列queue1每个元素都往空队列queue2插入，每次都利用临时变量保存插入的元素，
    // 当queue1为空时，此时的临时变量就是栈顶元素
    public int top() {
        if(empty()) {
            return -1;
        }
        int tmp = -1;
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            // 循环当中不能使用queue1.size()作为循环终止条件，size是个变量，会动态变化
            for (int i = 0; i < size; i++) {
                tmp = queue1.poll();
                queue2.offer(tmp);
            }
            return queue1.poll();
        }else {
            int size = queue2.size();
            for (int i = 0; i < size; i++) {
                tmp = queue2.poll();
                queue1.offer(tmp);
            }
            return tmp;
        }
    }

    // 两个队列都为空说明栈为空
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
