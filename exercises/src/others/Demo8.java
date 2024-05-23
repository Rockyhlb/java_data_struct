package others;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 15:56
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 66. 加一
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * 示例 1：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     * 示例 2：
     * 输入：digits = [4,3,2,1]
     * 输出：[4,3,2,2]
     * 解释：输入数组表示数字 4321。
     * 示例 3：
     * 输入：digits = [0]
     * 输出：[1]
     * 提示：
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     */
    public static int[] plusOne(int[] digits) {
        // 思路：从后往前遍历数组，如果为9则置0，反之则+=1并返回数组
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        // 所有都进位时扩容数组，并置第一位为1
//        digits = Arrays.copyOf(digits,digits.length+1);
        // 全部都是进位时，则后面肯定全部都是0，新建数组默认元素则为0，正好符合需求，因此省去了调用copyOf 方法
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        // 输入：digits = [4,3,2,1]
        int[] digits = {4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOne(digits)));
    }
}
