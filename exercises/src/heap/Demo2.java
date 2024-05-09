package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: heap
 * @CreateTime : 2024/5/9 11:24
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 502. IPO
     * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
     * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
     * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
     * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
     * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
     * 答案保证在 32 位有符号整数范围内。
     * 示例 1：
     * 输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
     * 输出：4
     * 解释：
     * 由于你的初始资本为 0，你仅可以从 0 号项目开始。
     * 在完成后，你将获得 1 的利润，你的总资本将变为 1。
     * 此时你可以选择开始 1 号或 2 号项目。
     * 由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
     * 因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
     * 示例 2：
     * 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
     * 输出：6
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        /**
         * 贪心算法+堆
         * 题目中限定了可以选择的次数最多为 k 次，因此我们应该贪心地保证每次选择投资的项目获取的利润最大，
         * 这样就能保持选择 k 次后获取的利润最大.
         * 利用大根堆的特性，将所有能够投资的项目的利润全部压入到堆中，每次从堆中取出最大值，然后更新手中持有的资本，
         * 同时将待选的项目利润进入堆，不断重复上述操作得出最优解.
         */
        int len = profits.length;
        // 采用二维数组存储 profits 和 capital
        int[][] arr = new int[len][2];
        for (int i = 0; i < len; i++) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        // 1、将项目按照所需资本(capital)从小到大进行排序
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        // 借用 大根堆的特性 将所有能够投资的项目的利润全部压入到堆中，每次从堆中取出最大值，然后更新手中持有的资本
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (y - x));
        int cur = 0;  // 标记当前已插入项目的下标位置
        for (int i = 0; i < k; i++) {
            // 2、每次选择时，从所有投入资本小于等于 w 的项目中选择利润最大的项目 j，大根堆的队顶就是利润最大的项目
            while (cur < len && arr[cur][0] <= w) {
                pq.offer(arr[cur][1]);
                cur++;
            }
            // 3、更新手中持有的资本为 w+=profits[j],同时再从所有花费资本小于等于 w+profits[j]的项目中选择，不断选择 k 次得出最优解.
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }
        return w;
    }

    public static void main(String[] args) {
        // 输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
        int k = 3, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 2};
        System.out.println(findMaximizedCapital(k, w, profits, capital));
    }
}