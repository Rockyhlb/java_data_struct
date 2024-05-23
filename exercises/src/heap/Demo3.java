package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: heap
 * @CreateTime : 2024/5/23 10:26
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 373. 查找和最小的 K 对数字
     * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
     * 示例 1:
     * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 输出: [1,2],[1,4],[1,6]
     * 解释: 返回序列中的前 3 对数：
     * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     * 输出: [1,1],[1,1]
     * 解释: 返回序列中的前 2 对数：
     * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     * 提示:
     * 1 <= nums1.length, nums2.length <= 105
     * -109 <= nums1[i], nums2[i] <= 109
     * nums1 和 nums2 均为 升序排列
     * 1 <= k <= 104
     * k <= nums1.length * nums2.length
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> resList = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 由于两个数组都是严格递增的，因此[0,0] 一定是和最小的数对
        priorityQueue.add(new int[]{nums1[0] + nums2[0], 0, 0});  // 存储三元组，第一个元素是和，方便进行比较
        while (!priorityQueue.isEmpty() && resList.size() < k) {
            // 取出堆顶元素
            int[] cur = priorityQueue.poll();
            int i = cur[1], j = cur[2];  // 取出下标
            resList.add(Arrays.asList(nums1[i], nums2[j]));
            // 当[i,j]被弹出时，下一个元素一定是[i+1,j] || [i,j+1],一定没有其它元素比这两个更小
            // 将[i+1,0]入堆
            if (j == 0 && i + 1 < len1) {
                priorityQueue.add(new int[]{nums1[i + 1] + nums2[0], i + 1, 0});
            }
            // 当 (1,0) 出堆时，会把 (1,1) 入堆；当 (0,1) 出堆时，也把 (1,1) 入堆，这样堆中会有重复元素
            // 因此规定 (i,j−1) 出堆时，将 (i,j) 入堆；而 (i−1,j) 出堆时只计入答案，其它什么也不做。
            // 换句话说，在 (i,j) 出堆时，只需将 (i,j+1) 入堆，无需将(i+1,j) 入堆。
            if (j + 1 < len2) {
                priorityQueue.add(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        // 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        System.out.println(kSmallestPairs(nums1, nums2, 3));
    }
}
