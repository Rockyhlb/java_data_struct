/**
 * @author: code_hlb
 * @date :  2023/11/5 15:07
 * @desc :  通过数组设计循环链表
 */

/**
 * 622. 设计循环队列
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。
 * 它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，
 * 即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 */
public class MyCircularQueue {
    public int[] elems;
    public int front;
    public int rear;

    // 构造器，设置队列长度为 k
    // 牺牲一个空间来判断当前空间是否已满
    // 也可以通过定义一个usedSize和flag来优化
    public MyCircularQueue(int k) {
        elems = new int[k+1];
    }

    // 向循环队列插入一个元素,如果成功插入则返回真
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        elems[rear] = value;
        rear = (rear + 1) % elems.length;
        return true;
    }

    // 从循环队列中删除一个元素,如果成功删除则返回真
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % elems.length;
        return true;
    }

    // 从队首获取元素,如果队列为空，返回 -1
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return elems[front];
    }

    // 获取队尾元素,如果队列为空，返回 -1
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int index = (rear == 0) ? elems.length - 1 : rear-1;
        return elems[index];
    }

    // 检查循环队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 检查循环队列是否已满
    public boolean isFull() {
        return (rear + 1) % elems.length == front;
    }
}