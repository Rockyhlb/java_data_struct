import java.util.PriorityQueue;

/**
 * @BelongsProject: test-20240119
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/19 23:21
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 1、给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个
     * 最大的元素，而不是第 k 个不同的元素。OJ链接 【LeetCode题号：215. 数组中的第K个最大元素】【中等】
     * 示例 1:
     * 输入: [3,2,1,5,6,4] 和 k = 2          输出: 5
     * 示例 2:
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4    输出: 4
     * 提示：
     * 1 <= k <= nums.length <= 104
     * -104 <= nums[i] <= 104
     */
    public int findKthLargest(int[] nums, int k) {
        // 这是典型的topK问题，可以通过建立大/小根堆的方式维护数据
        // 本题是查找数组中第k个 最大 元素，因此需要建立小根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length);
        // 先向小根堆中插入k个元素
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        // 将后续元素依次根据条件插入到小根堆中
        for (int i = k; i < nums.length; i++) {
            int top = priorityQueue.peek();
            if (nums[i] > top) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
        // 最后该小根堆中的根节点元素就是第K大的元素
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(demo2.findKthLargest(nums, 4));
    }
}
