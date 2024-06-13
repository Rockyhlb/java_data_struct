package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/6/13 10:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo11 {
    /**
     * 123. 买卖股票的最佳时机 III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2：
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
     * 示例 4：
     * 输入：prices = [1]
     * 输出：0，
     * 提示：
     * 1 <= prices.length <= 105
     * 0 <= prices[i] <= 105
     */
    public static int maxProfit(int[] prices) {
        /**
         * 动态规划
         * 由于最多可以完成两笔交易，因此每一天结束后的的状态只能是以下几种状态之一
         * 1、什么也没做，收益为0，可以不用记录
         * 2、第一次买入股票, 记为buy1
         * 3、第一次卖出股票, 记为sell1
         * 4、第二次买入股票, 记为buy2
         * 5、第二次卖出股票, 记为sell2
         * 状态转移：
         * 当已知i-1天时的状态时，那第i天的状态转移方程为：
         * buy1 = Max(buy1', -prices[i]);  // buy1‘表示第i-1天的第一次买入价格
         * sell1 = Max(sell1', buy1' + prices[i]);  // sell1‘表示第i-1天的第一次买入价格
         * buy2 = Max(buy2', sell1' - prices[i]);  // buy2‘表示第i-1天的第一次买入价格
         * sell2 = Max(sell2', buy2' + prices[i]);  // sell2‘表示第i-1天的第二次买入价格
         * <p>
         * 由于在同一天买入和卖出对收益都没有影响，因此上述状态转移方程可以优化为:
         * buy1 = Max(buy1, -prices[i]);  // buy1‘表示第i-1天的第一次买入价格
         * sell1 = Max(sell1, buy1 + prices[i]);  // sell1‘表示第i-1天的第一次买入价格
         * buy2 = Max(buy2, sell1 - prices[i]);  // buy2‘表示第i-1天的第一次买入价格
         * sell2 = Max(sell2, buy2 + prices[i]);  // sell2‘表示第i-1天的第二次买入价格
         * <P>
         * 边界条件：当i=0时，
         *   buy1 = -prices[0], sell1 = 0;
         *   buy2 = -prices[0],sell2 = 0;
         */
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        // 输入：prices = [3,3,5,0,0,3,1,4]
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices));  // 6
    }
}
