package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/24 9:05
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo10 {
    /**
     * 69. x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     * 示例 1：
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     */
    public static int mySqrt(int x) {
        // 任意值的平方根res^2一定 >=0 && <= x,因此可以采用二分查找[0,x]
        int low = 0;
        int high = x;
        int res = 0;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (((long) mid * mid) <= x) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(4));  // 2
        System.out.println(mySqrt(8));  // 2
    }
}
