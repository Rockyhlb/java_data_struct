package heap;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: heap
 * @CreateTime : 2024/5/28 8:49
 * @Description: TODO
 * @Author: code_hlb
 */
public class MedianFinder {
    /**
     * 295. 数据流的中位数
     * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
     * 例如 arr = [2,3,4] 的中位数是 3 。
     * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
     * 实现 MedianFinder 类:
     * MedianFinder() 初始化 MedianFinder 对象。
     * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
     * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
     * 示例 1：
     * 输入
     * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
     * [[], [1], [2], [], [3], []]
     * 输出
     * [null, null, null, 1.5, null, 2.0]
     * 解释
     * MedianFinder medianFinder = new MedianFinder();
     * medianFinder.addNum(1);    // arr = [1]
     * medianFinder.addNum(2);    // arr = [1, 2]
     * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
     * medianFinder.addNum(3);    // arr[1, 2, 3]
     * medianFinder.findMedian(); // return 2.0
     * 提示:
     * -105 <= num <= 105
     * 在调用 findMedian 之前，数据结构中至少有一个元素
     * 最多 5 * 104 次调用 addNum 和 findMedian
     */
    private PriorityQueue<Integer> queueSmaller;
    private PriorityQueue<Integer> queueBigger;

    public MedianFinder() {
        // 存储比中位数小的元素，建立大根堆
        this.queueSmaller = new PriorityQueue<>((a, b) -> b - a);
        // 存储比中位数大的元素，建立小根堆
        this.queueBigger = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNum(int num) {
        if (queueSmaller.isEmpty() || num <= queueSmaller.peek()) {
            // 当前元素比大根堆的最小元素还小时, 插入queueSmaller
            queueSmaller.offer(num);
            // 维护两个堆，使其平衡
            if (queueBigger.size() + 1 < queueSmaller.size()) {
                queueBigger.offer(queueSmaller.poll());
            }
        } else {
            // 当前元素比大根堆的最小元素还大时, 插入queueBigger
            queueBigger.offer(num);
            // 平衡两堆
            if (queueBigger.size() > queueSmaller.size()) {
                queueSmaller.offer(queueBigger.poll());
            }
        }
    }

    public double findMedian() {
        if (queueSmaller.size() > queueBigger.size()) {
            // 当两个数组大小不相等时，说明总共含有奇数个元素，只需返回较小堆的头元素即可
            return queueSmaller.peek();
        }
        return (queueSmaller.peek() + queueBigger.peek()) / 2.0;
    }
}
