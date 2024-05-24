package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/24 8:36
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 172. 阶乘后的零
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     * 示例 1：
     * 输入：n = 3
     * 输出：0
     * 解释：3! = 6 ，不含尾随 0
     * 示例 2：
     * 输入：n = 5
     * 输出：1
     * 解释：5! = 120 ，有一个尾随 0
     * 示例 3：
     * 输入：n = 0
     * 输出：0
     * 提示：
     * 0 <= n <= 104
     */
    public static int trailingZeroes(int n) {
        // 递归，时间复杂度空间复杂都过高
        int res = reverse(n);
        int count = 0;
        while (res % 10 == 0) {
            count++;
            res /= 10;
        }
        return count;
    }

    private static int reverse(int n) {
        if (n == 1) {
            return 1;
        }
        return n * reverse(n - 1);
    }

    public static int trailingZeroes1(int n) {
        // 暴力，时间复杂度过高
        int count = 0;
        int res = 1;
        while (n != 1) {
            res *= n--;
        }
        while (res % 10 == 0) {
            count++;
            res /= 10;
        }
        return count;
    }

    public static int trailingZeroes2(int n) {
        // 6! = 6 * 5 * 4 * 3 * 2 * 1 <=>  可以看出只有2*5及其倍数才会出现0,因此此处只有一对2*5，也就只有1个0 (一个数字只能配对一次)
        // 10! = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1 <=> 4可以拆分成2*2,10可以被拆分成2*5，因此可以组合两个 2*5
        // 综上所述：因此可以简化为求5的数量即可得出0的个数
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(3));   // 0
        System.out.println(trailingZeroes(5));   // 1
        System.out.println(trailingZeroes1(3));  // 0
        System.out.println(trailingZeroes1(5));  // 1
        System.out.println(trailingZeroes2(3));  // 0
        System.out.println(trailingZeroes2(5));  // 1
    }
}
