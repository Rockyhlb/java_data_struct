package others;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 12:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 136. 只出现一次的数字
     * 提示
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * 示例 1 ：
     * 输入：nums = [2,2,1]
     * 输出：1
     * 示例 2 ：
     * 输入：nums = [4,1,2,1,2]
     * 输出：4
     * 示例 3 ：
     * 输入：nums = [1]
     * 输出：1
     */
    public static int singleNumber(int[] nums) {
        /**
         * 1、与运算（AND）：
         * 任何数和0做与运算，结果是0，即 x & 0 = 0。例如，5（101） & 0 = 0。
         * 任何数和其自身做与运算，结果是自身，即 x & x = x。例如，5（101） & 5（101） = 5（101）。
         * 2、或运算（OR）：
         * 任何数和0做或运算，结果是自身，即 x | 0 = x。例如，5（101） | 0 = 5（101）。
         * 任何数和其自身做或运算，结果是自身，即 x | x = x。例如，5（101） | 5（101） = 5（101）。
         * 3、异或运算（XOR）：
         * 任何数和0做异或运算，结果是自身，即 x ^ 0 = x。例如，5（101） ^ 0 = 5（101）。
         * 任何数和其自身做异或运算，结果是0，即 x ^ x = 0。例如，5（101） ^ 5（101） = 0。
         * 异或运算满足交换律和结合律，即 a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c。
         * 例如，5（101） ^ 3（011） ^ 4（100） = 5 ^ (3 ^ 4) = (5 ^ 3) ^ 4。
         * 4、非运算（NOT）：
         * 非运算会反转操作数的所有位。例如，~5（101） = 2（010）。
         * 5、左移运算（SHL）：
         * 左移n位等于乘以2的n次方，即 x << n = x * 2^n。例如，5（101） << 2 = 20（10100）。
         * 左移运算不改变操作数的符号位。
         * 6、逻辑右移运算（SHR）：
         * 右移n位等于除以2的n次方，即 x >> n = x / 2^n。例如，20（10100） >> 2 = 5（101）。
         * 逻辑右移运算会用0填充移位后产生的空位。
         * 7、算术右移运算（SAR）：
         * 算术右移运算会用符号位填充移位后产生的空位，因此它可以保持负数的符号。例如，对于负数-5（1011） >>> 2 = -2（1110）。
         */
        int res = 0;
        for (int num : nums) {
            // x^x = 0; 0^x = x
            res ^= num;
        }
        return res;
    }

    public static void main(String[] args) {
        // 输入：nums = [4,1,2,1,2]
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));  // 4
    }
}
