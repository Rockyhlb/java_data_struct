package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 14:41
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 137. 只出现一次的数字 II
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
     * 示例 1：
     * 输入：nums = [2,2,3,2]
     * 输出：3
     * 示例 2：
     * 输入：nums = [0,1,0,1,0,1,99]
     * 输出：99
     * 提示：
     * 1 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     */
    public static int singleNumber(int[] nums) {
        int res = 0;
        // 依次计算res的每一个二进制位
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                // (num >> i) & 1 得到 num 的第 i 个二进制位,将它们累加
                total += ((num >> i) & 1);
            }
            // 对 3 取余，得到的结果一定为 0 或 1，即为最终结果的第 i 个二进制位
            if (total % 3 != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //  输入：nums = [0,1,0,1,0,1,99]
        int[] nums = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(singleNumber(nums));  // 99
    }
}
