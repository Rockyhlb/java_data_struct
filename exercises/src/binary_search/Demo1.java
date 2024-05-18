package binary_search;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: binary_search
 * @CreateTime : 2024/5/18 15:15
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     */
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (high + low) >>> 1;
            if (nums[mid] > target) {
                if (mid > 1 && nums[mid - 1] < target) {
                    return mid;
                }
                high = mid - 1;
            } else if (nums[mid] < target) {
                if (mid < nums.length - 2 && nums[mid + 1] > target) {
                    return mid + 1;
                }
                low = mid + 1;
            } else {
                return mid;
            }
        }
        // 检索元素比数组中所有元素都大时
        return high + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 7));
    }
}
