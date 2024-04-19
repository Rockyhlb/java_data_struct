package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/19 21:35
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * DP题目：f(1) = 1;
     * DP题目：f(2) = 2;
     *        f(3) = 3;
     *        f(4) = 5;
     *        f(n) = f(n-1) + f(n-2);
     * 最终演变成了对 斐波那契数列 的求解
     */
    public static int climbStairs(int n) {
        int res = 0;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }
}
