import java.util.PriorityQueue;

/**
 * @author: code_hlb
 * @date :  2023/11/13 11:16
 * @desc :
 */
public class Demo {

    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */
    public int findKthLargest(int[] nums, int k) {
        // 要求找最大元素，建立小根堆,因为小根堆保证每次调整都会将最小的元素留在最上面，因此最后保留下来的就是最大元素
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length);
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            int top = priorityQueue.peek();
            if (nums[i] > top) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }else {
                break;
            }
        }
        return priorityQueue.peek();
    }


}
