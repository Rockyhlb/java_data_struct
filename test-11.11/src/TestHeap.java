import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/11/11 15:40
 * @desc :  堆：堆是一颗完全二叉树，因此可以采用顺序的方式来高效存储
 *          优先级队列PriorityQueue的底层就是使用了堆这种数据结构
 *          java集合框架提供了PriorityQueue 和 PriorityBlockingQueue两种类型的优先级队列
 *          PriorityQueue 是线程不安全的，PriorityBlockingQueue是线程安全的
 */
public class TestHeap {
    private int[] elems;
    private int usedSize;

    public TestHeap() {
        this.elems = new int[10];
    }

    public void initHeap(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            elems[i] = arrays[i];
            usedSize++;
        }
    }

    // 向下调整创建大根堆
    public void createHeap() {
        for (int parent = (usedSize-1-1)/2; parent >= 0; parent--) {
            shiftDown(parent,usedSize);
        }
    }

    private void shiftDown(int parent,int usedSize) {
        // 左孩子下标
        int child = (2 * parent) + 1;
        while ( child < usedSize) {
            if (child + 1 < usedSize && elems[child] < elems[child + 1]) {
                child++;
            }
            // 此时child一定是左右孩子最大值的下标
            if (elems[child] > elems[parent]) {
                swap(child,parent);
                parent = child;
                child = 2 * parent + 1;
            }else {
                // 已经是大根堆了
                break;
            }
        }
    }

    private void swap(int i,int j) {
        int tmp = elems[i];
        elems[i] = elems[j];
        elems[j] = tmp;
    }

    public void offer(int val) {
        if (isFull()) {
            this.elems = Arrays.copyOf(elems,2 * elems.length);
        }
        this.elems[usedSize] = val;

        // 向上调整
        shifUp(usedSize);

        usedSize++;
    }

    private void shifUp(int child) {
        int parent = (child - 1) / 2;
        while (child > 0){
            if (elems[child] > elems[parent]) {
                swap(child,parent);
                child = parent;
                parent = (child-1) / 2;
            }else {
                break;
            }
        }
    }

    public int poll() {
        int tmp = elems[0];
        swap(0,usedSize - 1);
        usedSize--;
        shiftDown(0,usedSize - 1);
        return tmp;
    }

    public boolean isFull() {
        return usedSize == elems.length;
    }

    // 堆排序 --> 升序：建大堆   降序：建小堆
    public void heapSort() {
        // 将堆按从小到大排序
        int end = usedSize - 1;
        // 1、调整为大根堆
        // 2、让第一个元素和最后一个未排序的元素进行交换
        while (end > 0) {
            swap(0,end);
            shiftDown(0,end);
            end--;
        }
    }
}
