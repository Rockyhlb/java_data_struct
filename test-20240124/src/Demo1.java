import java.util.List;

/**
 * @BelongsProject: test-20240124
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/24 22:31
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。OJ链接 【LeetCode题号：70. 爬楼梯】【简单】
     * 示例 1：
     * 输入： 2    输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * 示例 2：
     * 输入： 3    输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     */
    public int climbStairs(int n) {
        // 设 函数f(n)为爬楼梯数
        // 已知f(1)=1  f(2)=2  f(3)=3=f(1)+f(2)
        // 因此可以演变成斐波拉契数列求f(n)的问题  f(n)=f(n-1)+f(n-2)
        int res = 0;
        // f(0)
        int first = 0;
        // f(1)
        int second = 1;
        for (int i = 0; i < n; i++) {
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        System.out.println(demo1.climbStairs(2));
    }
}
