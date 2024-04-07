import java.util.Arrays;

/**
 * @BelongsProject: test-20240407
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/4/7 19:48
 * @Description: TODO
 * @Author: code_hlb
 */
public class demo2 {
    /**
     * 238. 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 示例 1:
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     * <P>
     * 示例 2:
     * 输入: nums = [-1,1,0,-3,3]
     * 输出: [0,0,9,0,0]
     */
    // 1、时间复杂度过高
    public static int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = multiply(nums,i);
        }
        return res;
    }
    private static int multiply(int[] nums,int except) {
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i == except) {
                continue;
            }
            res *= nums[i];
        }
        return res;
    }

    public static int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int len = nums.length;
        int[] answer = new int[len];
        // 将待计算的数值想象成一个链表，从i处就可以分割成两个部分L[] 和 R[]，最终answer[] 就等于L[] * R[]
        int[] left = new int[len];
        int[] right = new int[len];
        // 当i=0时，左边已经没有值可以乘了，因此默认为1
        left[0] = 1;
        // 通过迭代，计算出 L[]，本质上 left[i] 其实是 nums[i] 之前的累计(不算nums[i])
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        // 当 i = len-1时，右边也没有值可以乘，因此R[i] = 1;
        right[len-1] = 1;
        for (int i = len-1-1; i >= 0; i--) {
            right[i] = nums[i+1] * right[i+1];
        }
        // 最后遍历写入 answer[]
        for (int i = 0; i < len; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }

    // 3、通过利用answer[] 作为left[],再维护一个变量来记录R,即可降低空间复杂度
    public int[] productExceptSelf3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] answer = new int[length];
        // 1、先计算 left[]
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = answer[i-1] * nums[i-1];
        }
        // 2、计算 right[]
        // tmp 为右边所有数值的乘积
        int right = 1;
        for (int i = length-1; i >= 0; i--) {
            // 对于索引i，左边的乘积是answer[i],右边的乘积是 right
            answer[i] = answer[i] * right;
            // 更新右边乘积，因为乘积这个结果是一次性的
            right *= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }
}
