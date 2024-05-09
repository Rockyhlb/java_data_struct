package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: heap
 * @CreateTime : 2024/5/9 10:38
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 示例 1:
     * 输入: [3,2,1,5,6,4], k = 2
     * 输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6], k = 4
     * 输出: 4
     */
    public static int findKthLargest(int[] nums, int k) {
        // 方法1：调用 sort()
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static int findKthLargest1(int[] nums, int k) {
        // 方法2：通过堆解决 topK 问题
        // 查找最大元素，建立 小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length);
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            int top = priorityQueue.peek();
            // 当前值大于顶部元素时，弹出堆顶，插入新元素
            if (nums[i] > top) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
        // 当前堆顶就是第K大的元素
        return priorityQueue.poll();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(nums, 2));
        System.out.println(findKthLargest1(nums, 2));
    }
}
