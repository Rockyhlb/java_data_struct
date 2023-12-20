import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: test-12.20
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023/12/20 19:45
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class Test {
    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     */
    public int majorityElement(int[] nums) {
        // 将数组元素放入哈希表中，key作为元素，value作为元素出现次数
        Map<Integer,Integer> countMap = countNum(nums);
        // 声明一个变量存储众数
        Map.Entry<Integer,Integer> much = null;

        for (Map.Entry<Integer,Integer> entry: countMap.entrySet()) {
            if (much == null || much.getValue() < entry.getValue()) {
                much = entry;
            }
        }
        return much.getKey();
    }
    private Map<Integer,Integer> countNum(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num: nums) {
            if (!map.containsKey(num)) {
                map.put(num,1);
            }else {
                map.put(num,map.get(num)+1);
            }
        }
        return map;
    }

    /**
     * 189. 轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     */
    public static void rotate(int[] nums, int k) {
        // 时间复杂度过高，不可取
        /*for (int i = 0; i < k; i++) {
            int tmp = nums[nums.length-1];
            for (int j = nums.length-1-1; j >=0; j--) {
                nums[j+1] = nums[j];
            }
            nums[0] = tmp;
        }*/
        // 由于数组反转后的元素位置被调换到 (i + k) % nums.len 处，因此可以采用临时数组存储来解决问题
        int len = nums.length;
        int[] tmpArr = new int[len];
        for (int i = 0; i < len; i++) {
            tmpArr[(i+k)%len] = nums[i];
        }
        // 将临时数组元素拷贝到原数组
        for (int i = 0; i < tmpArr.length; i++) {
            nums[i] = tmpArr[i];
        }
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 示例 1：
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     */
    public int maxProfit(int[] prices) {
        // 暴力解法，时间复杂度过高
        /*int max = 0;
        for (int i = prices.length-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                int profit = prices[i] - prices[j];
                if (max < profit) {
                    max = profit;
                }
            }
        }
        return max;*/

        // 股票最佳售卖时机本质上就是找出数组中的最大差值，因此可以定义快慢指针，慢指针找最小元素，快指针找最大元素
        int max = 0, slow = 0, fast = 1;
        while (fast < prices.length) {
            if (prices[slow] > prices[fast]) {
                slow = fast;
            }else {
                max = Math.max(max,prices[fast]-prices[slow]);
            }
            fast++;
        }
        return max;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     */
    public static int maxProfit1(int[] prices) {
        // 最大利润本质上是上题的演化，当第二天的价格比今天高时，立即抛出股票，不会再继续往后等待更到价格
        int sumMax = 0, slow = 0, fast = 1;
        while (fast < prices.length) {
            if (prices[slow] > prices[fast]) {
                slow = fast;
            }else {
                sumMax += (prices[fast]-prices[slow]);
                slow = fast;
            }
            fast++;
        }
        return sumMax;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int[] nums1 = {7,1,5,3,6,4};

        rotate(nums,3);
        System.out.println(maxProfit1(nums1));
    }
}
