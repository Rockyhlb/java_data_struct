package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 11:59
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 191. 位1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中设置位的个数（也被称为汉明重量）。
     * 示例 1：
     * 输入：n = 11
     * 输出：3
     * 解释：输入的二进制串 1011 中，共有 3 个设置位。
     * 示例 2：
     * 输入：n = 128
     * 输出：1
     * 解释：输入的二进制串 10000000 中，共有 1 个设置位。
     * 示例 3：
     * 输入：n = 2147483645
     * 输出：30
     * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 30 个设置位。
     * 提示：
     * 1 <= n <= 231 - 1
     */
    public static int hammingWeight(int n) {
        String biStr = Integer.toBinaryString(n);
        int count = 0;
        for (char ch : biStr.toCharArray()) {
            if (ch == '1') {
                count++;
            }
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        int count = 0;
        while (n > 0) {
            n &= n - 1;  // 把 n 的二进制位中的最低位的 1 变为 0 之后的结果
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(128));  // 10000000
        System.out.println(hammingWeight(128));  // 1
        System.out.println(hammingWeight1(128));  // 1

        System.out.println(Integer.toBinaryString(2147483645)); // 1111111111111111111111111111101
        System.out.println(hammingWeight(2147483645));  //30
        System.out.println(hammingWeight1(2147483645));  //30
    }
}
