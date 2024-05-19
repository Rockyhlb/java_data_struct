package binary_search;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: binary_search
 * @CreateTime : 2024/5/19 12:36
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 162. 寻找峰值
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * 示例 1：
     * 输入：nums = [1,2,3,1]
     * 输出：2
     * 解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2：
     * 输入：nums = [1,2,1,3,5,6,4]
     * 输出：1 或 5
     * 解释：你的函数可以返回索引 1，其峰值元素为 2；
     * 或者返回索引 5， 其峰值元素为 6。
     */
    public static int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        // 二分查找
        while (low < high) {
            int mid = (low + high) >> 1;
            // 贪心思想：当处于“爬坡”阶段时，说明右边必定有峰值
            if (nums[mid] < nums[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        // 输入：nums = [1,2,1,3,5,6,4]
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));

        System.out.println("----------带符号右移-----------");
        // 如果操作数是正数，则在左侧填充0；如果操作数是负数，则在左侧填充1，即使用符号位进行填充。
        System.out.println(-4 >> 1);   // -2
        System.out.println(-4 >> 2);   // -1
        System.out.println(4 >> 1);    // 2
        System.out.println(4 >> 2);    // 1
        System.out.println("----------无符号右移-----------");
        // 无论操作数是正数还是负数，都在左侧填充0，即使用0进行填充。
        System.out.println(-4 >>> 1);  // 2147483646
        System.out.println(-4 >>> 2);  // 1073741823
        System.out.println(4 >>> 1);   // 2
        System.out.println(4 >>> 2);   // 1
    }
}
