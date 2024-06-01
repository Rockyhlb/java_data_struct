package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/15 14:58
 * @Description: other source
 * @Author: code_hlb
 */
public class Demo25 {
    /**
     * 13.
     * 删除有序数组重复元素
     * 给定一个整形数组 nums，固定升序排列，请你删除重复元素，返回删除后数组的新长度。
     * 输入描述：
     * 升序排列的整形数组
     * 输出描述：
     * 删除后数组的新长度，整数
     */
    public static int removeSame(int[] nums) {
        // 双指针，边遍历边替换，最后返回被替换后长度
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (nums[right] == nums[left]) {
                right++;
            } else {
                nums[left + 1] = nums[right];
                left++;
            }
        }
        return left + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums1 = {1, 1, 2};
        System.out.println(removeSame(nums));  // 5
        System.out.println(removeSame(nums1)); // 2
    }
}
