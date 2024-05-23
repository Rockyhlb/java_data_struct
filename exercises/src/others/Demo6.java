package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 14:06
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 201. 数字范围按位与
     * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
     * 示例 1：
     * 输入：left = 5, right = 7
     * 输出：4
     * 示例 2：
     * 输入：left = 0, right = 0
     * 输出：0
     * 示例 3：
     * 输入：left = 1, right = 2147483647
     * 输出：0
     * 提示：
     * 0 <= left <= right <= 231 - 1
     */
    public static int rangeBitwiseAnd(int left, int right) {
        // 思路：对所有数字执行按位与运算的结果其实就是所有对应二进制字符串的公共前缀再用零补上后面的剩余位。
        // 例如：9 & 10 = 8
        //    9: 0000 1001   (9)
        //   10: 0000 1010   (10)
        // 9&10: 0000 1000   (8)
        // 步骤：9和10的二进制的公共前缀为第五个位置上的1, 需要右移3位后才能得到该前缀(0000 0001)，
        // 然后再将该前缀左移3位得到最终结果(0000 10000), 转换为十进制也就是8
        int count = 0;  // 在迭代过程中，记录右移操作次数
        // 将两个数字不断向右移动缩减为它们的公共前缀，直到数字相等
        while (left < right) {
            // 通过右移，将两个数字压缩为它们的公共前缀
            left >>= 1;
            right >>= 1;
            count++;
        }
        // 将公共前缀左移相同的操作次数得到最终结果
        return left << count;
    }

    public static void main(String[] args) {
        int left = 5, right = 7;
        int left1 = 1, right1 = 2147483647;
        System.out.println(rangeBitwiseAnd(left, right));  // 4
        System.out.println(rangeBitwiseAnd(left1, right1));  // 0

        System.out.println(9&10);
    }
}