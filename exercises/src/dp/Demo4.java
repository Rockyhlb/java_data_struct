package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/9 13:02
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * 示例 1：
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * 示例 2：
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * 示例 3：
     * 输入：coins = [1], amount = 0
     * 输出：0
     */
    int res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        // 方法1：采用自上而下的递归，顶部为amount,一直 递归+循环 减去coins[i]
        if (coins.length < 1) {
            return -1;
        }
        recursive(coins, amount, 0);
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }

    private void recursive(int[] coins, int amount, int count) {
        if (amount < 0) {
            // 递归终止条件
            return;
        }
        if (amount == 0) {
            res = Math.min(res, count);
        }
        // 递归，时间复杂度过高,超出时间限制
        for (int coin : coins) {
            recursive(coins, amount - coin, count + 1);
        }
    }

    public int coinChange1(int[] coins, int amount) {
        // 方法2：采用自下而上的动规
        if (coins.length < 1) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        // 从1开始往上规划
        for (int i = 1; i <= amount; i++) {
            int res = Integer.MAX_VALUE;
            // 遍历 coin[]
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] < res) {
                    res = dp[i - coin] + 1;
                }
            }
            // 更新dp[i]
            dp[i] = res;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        // coins = [1, 2, 5], amount = 11
        int[] coins = {1, 2, 5};
        int amount = 11;

        Demo4 demo4 = new Demo4();
        System.out.println(demo4.coinChange(coins, amount));
        System.out.println(demo4.coinChange1(coins, amount));
    }
}
