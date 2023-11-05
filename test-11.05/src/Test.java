import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: code_hlb
 * @date :  2023/11/5 13:20
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {
        StackToQueue stackToQueue = new StackToQueue();
        System.out.println(stackToQueue.empty());
        stackToQueue.push(1);
        stackToQueue.push(2);
        stackToQueue.push(3);
        stackToQueue.push(4);
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.top());
    }

    public static void main3(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(5);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.Front());
        System.out.println(myCircularQueue.Rear());
    }

    public static void main2(String[] args) {
        MyQueue myQueue = new MyQueue();
        System.out.println("=========offer()============");
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(4);
        myQueue.disPlay();
        System.out.println("=========poll()============");
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println("=========peek()============");
        System.out.println(myQueue.peek());
        System.out.println("=========getUsedSize()============");
        System.out.println(myQueue.getUsedSize());
        System.out.println("=========isEmpty()============");
        System.out.println(myQueue.isEmpty());
    }

    public static void main1(String[] args) {
        // 双端队列
        Deque<Integer> deque = new LinkedList<>();

        // 普通队列
        Queue<Integer> queue1 = new LinkedList<>();

        queue1.offer(1);
        queue1.offer(2);
        queue1.offer(3);
        // 取出元素
        System.out.println(queue1.poll());
        // 获取队头元素
        System.out.println(queue1.peek());
    }
}
