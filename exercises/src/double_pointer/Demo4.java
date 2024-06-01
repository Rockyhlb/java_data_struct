package double_pointer;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: double_pointer
 * @CreateTime : 2024/6/1 21:43
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     * 提示：
     * n == height.length
     * 2 <= n <= 105
     * 0 <= height[i] <= 104
     */
    public static int maxArea(int[] height) {
        // 暴力
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int size = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, size);
            }
        }
        return max;
    }

    public static int maxArea1(int[] height) {
        // 双指针进行优化
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int size = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, size);
            // "短板效应"：最大容积由最短的板决定，因此我们应该尽可能使短板最长
            if (height[right] < height[left]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }

    public static int maxArea2(int[] height) {
        // 对双指针继续进行优化，进一步降低时间复杂度
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            // 记录当前最小短板
            int minHeight = Math.min(height[left], height[right]);
            max = Math.max(max, (right - left) * minHeight);
            // "短板效应"：最大容积由最短的板决定，因此我们应该尽可能使短板最长
            // 将left和right尽可能移动到最能使容积最大的位置
            while (left < right && height[left] <= minHeight) {
                left++;
            }
            while (left < right && height[right] <= minHeight) {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // 输入：[1,8,6,2,5,4,8,3,7]
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        System.out.println(maxArea1(height));
        System.out.println(maxArea2(height));
    }
}
