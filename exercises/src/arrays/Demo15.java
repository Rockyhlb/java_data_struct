package arrays;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/5 14:15
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo15 {
    /**
     * 135. 分发糖果
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     * 示例 1：
     * 输入：ratings = [1,0,2]
     * 输出：5
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     * 示例 2：
     * 输入：ratings = [1,2,2]
     * 输出：4
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
     * 提示：
     * n == ratings.length
     * 1 <= n <= 2 * 104
     * 0 <= ratings[i] <= 2 * 104
     */
    public static int candy(int[] ratings) {
        // 方法1：两次遍历，时间复杂度：O(n), 空间复杂度：O(n)
        // 将规则拆分成两部分：
        // 左规则：当ratings[i-1] < ratings[i] 时，学生i应该获得比学生i-1更多的糖果
        // 右规则：当ratings[i+1] < ratings[i] 时，学生i应该获得比学生i-1更多的糖果
        int len = ratings.length;
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        // right记录按照右规则需要分配的糖果数
        int right = 0;
        int ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (i < len - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            // 计算右规则的同时统计总分配糖果数
            ans += Math.max(left[i], right);
        }
        return ans;
    }

    public static int candy1(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        // 贪心思想，先让所有学生满足左规则
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        // 让所有学生满足右规则
        int count = left[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            // 计数，取left 和 right 的最大值为糖数量，使学生同时满足左规则 和 右规则
            count += Math.max(left[i], right[i]);
        }
        return count;
    }

    public static void main(String[] args) {
        // 输入：ratings = [1,0,2]
        int[] ratings = {1, 0, 2};
        System.out.println(candy(ratings));  // 5 -> 2 + 1 + 2
        System.out.println(candy1(ratings));  // 5 -> 2 + 1 + 2
    }
}