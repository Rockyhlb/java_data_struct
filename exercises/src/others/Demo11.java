package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/28 10:24
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo11 {
    /**
     * 50. Pow(x, n)
     * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
     * 示例 1：
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     * 示例 2：
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     * 示例 3：
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     * 提示：
     * -100.0 < x < 100.0
     * -231 <= n <= 231-1
     * n 是一个整数
     * 要么 x 不为零，要么 n > 0 。
     * -104 <= xn <= 104
     */
    public static double myPow(double x, int n) {
        // 方法1：快速幂(分治) + 递归, 时间复杂度：O(log(n))  空间复杂度：O(log(n))
        long N = n;  // 转换为长整
        return N > 0 ? quick(x, N) : 1.0 / quick(x, -N);
//        return N > 0 ? quick1(x, N) : 1.0 / quick(x, -N);
    }

    private static double quick(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        // 分治思想,例如 x^24 <=> x^12 * x^12 <=> x^6 * x^6 * x^6 * x^6, 就不需要对每个x都进行相乘了
        double tmp = quick(x, N / 2);
        // 当奇数次幂时，只需再多乘上个自身就可以了
        return N % 2 == 0 ? tmp * tmp : tmp * tmp * x;
    }

    private static double quick1(double x, long N) {
        // 方法2：快速幂 + 迭代, 时间复杂度：O(logn)  空间复杂度：O(1)
        double res = 1.0;
        // 初始化贡献为x
        double contribute = x;
        while (N > 0) {
            // 当前N是奇数时，计入结果
            if (N % 2 == 1) {
                res *= contribute;
            }
            contribute *= contribute;
            // 分治，无论 N 本身原本是奇数or偶数，除以2后都会变成偶数，并且永远会有==1的时候
            N /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 10)); // 1024.00000
        System.out.println(myPow(2.10000, 3));  // 9.26100
    }
}
