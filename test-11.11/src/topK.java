import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: code_hlb
 * @date :  2023/11/12 11:32
 * @desc :  topK 问题
 */

public class topK {
    /**
     * topK问题
     * 面试题 17.14. 最小K个数
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 这类问题，当需要求前 k 个最大元素时，建小堆
     *          当需要求前 k 个最小元素时，建大堆
     * 当需要返回第 k 大（小） 的元素时，建立对应的小（大）根堆并返回堆顶元素即可
     */
    // 方法一：通过将数组元素依次放入优先级队列，最后再遍历优先级队列取出前k个元素，
    // 但是时间复杂度过高，并且当数据量很大时无法将所有元素放入优先级队列
  public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(arr.length);
        int[] res = new int[k];
        if (arr.length == 0 || k < 1) {
            return res;
        }
        // 将数组遍历插入小根堆中
        for (int value : arr) {
            priorityQueue.offer(value);
        }
        // 循环遍历小根堆取出前k个元素
        for (int i = 0; i < k  && !priorityQueue.isEmpty(); i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }
    // 方法二(时间复杂度更低)：建立一个大小为K的大根堆，将arr[k] 后的元素依次与大根堆的队顶元素比较，
    // 队顶元素大则插入，小则不变，最后这个大根堆的元素就是前k个最小元素
    public static int[] smallestK1(int[] arr, int k) {
        int[] res = new int[k];
        if(arr == null || k < 1) {
            return res;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new IntCompare());
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }
        for (int i = k; i < arr.length && !priorityQueue.isEmpty(); i++) {
            int top = priorityQueue.peek();
            if (arr[i] < top) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k && !priorityQueue.isEmpty(); i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {3,2,33,11,6,8,45,23};
        System.out.println(Arrays.toString(smallestK1(array, 3)));
    }
}

/**
 * 将小根堆转化为大根堆的本质：
 * 1、public boolean offer(E e) {                    2、private void siftUp(int k, E x) {
 *         if (e == null)                                   if (comparator != null)
 *             throw new NullPointerException();                siftUpUsingComparator(k, x);
 *         modCount++;                                       else
 *         int i = size;                                        siftUpComparable(k, x);
 *         if (i >= queue.length)                       }
 *             grow(i + 1);
 *         size = i + 1;
 *         if (i == 0)
 *             queue[0] = e;
 *         else
 *             siftUp(i, e); //当已经有一个元素时调用siftUp
 *         return true;
 *     }
 *
 *  3、private void siftUpUsingComparator(int k, E x) {
 *         while (k > 0) {
 *             int parent = (k - 1) >>> 1;
 *             Object e = queue[parent];  //取出父节点元素
 *             if (comparator.compare(x, (E) e) >= 0)  //调用比较器方法，默认为o1 - o2,此处自定义为o2 - o1，因此成为大根堆
 *                 break;
 *             queue[k] = e;
 *             k = parent;
 *         }
 *         queue[k] = x;
 *     }
 */
class IntCompare implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        // 大根堆
        return o2 - o1;
        // 小根堆，默认
        // return o1 - o2;
    }
}
