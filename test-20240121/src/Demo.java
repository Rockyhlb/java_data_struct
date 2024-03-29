/**
 * @BelongsProject: test-20240121
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/21 19:14
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo {
    /**
     * 2、给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的
     * 整数。假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。你设计的解决方案必须不修改数组 nums 且只用
     * 常量级 O(1) 的额外空间。OJ链接 【LeetCode题号：287. 寻找重复数】【中等】
     * 示例 1：
     * 输入：nums = [1,3,4,2,2]   输出：2
     * 示例 2：
     * 输入：nums = [3,1,3,4,2]   输出：3
     * 示例 3：
     * 输入：nums = [1,1]         输出：1
     * 示例 4：
     * 输入：nums = [1,1,2]       输出：1
     * 提示：
     * 1 <= n <= 105
     * nums.length == n + 1
     * 1 <= nums[i] <= n
     * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
     */
    public int findDuplicate(int[] nums) {
        // 将数组看成是一个链表，当出现重复数字时，则可以认为该"链表"出现 环
        // 因此可以引用快慢指针的方法解决本题
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] nums = {1,3,4,2,2};
        System.out.println(demo.findDuplicate(nums));
    }
}
