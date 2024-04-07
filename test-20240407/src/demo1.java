/**
 * @BelongsProject: test-20240407
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/4/7 19:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class demo1 {
    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 采用双指针进行解决
        // 左右指针都指向开头，右指针往前走，当出现 不等于val 的值时，将right的值同步到left
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != val) {
                // 不相等的往左移
                nums[left++] = nums[right];
            }
            right++;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,3,3};
        System.out.println(removeElement(nums, 2));
    }
}
