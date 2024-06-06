package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/6 10:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo16 {
    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 示例 1：
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例 2：
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     * 提示：
     * n == height.length
     * 1 <= n <= 2 * 104
     * 0 <= height[i] <= 105
     */
    public static int trap(int[] height) {
        // 方法1：暴力,时间复杂度O(n^2)，空间复杂度O(n)
        // 对于下标i处,能接的雨水数为两边最大高度的较小值减去height[i]
        int len = height.length;
        int[] water = new int[len];
        for (int i = 0; i < len; i++) {
            int left = 0;  // 记录左边最高高度
            int right = 0; // 记录右边最高高度
            int j = i - 1, k = i + 1; // 控制左右遍历方向
            // 寻找左边最大高度
            while (j >= 0) {
                left = Math.max(left, height[j--]);
            }
            // 寻找右边最大高度
            while (k < len) {
                right = Math.max(right, height[k++]);
            }
            //如果 Math.min(left, right) - height[i] < 0 说明当前i已经是较高处，无法收集雨水
            water[i] = Math.max(Math.min(left, right) - height[i], 0);
        }
        int count = 0;
        for (int num : water) {
            count += num;
        }
        return count;
    }

    public static int trap1(int[] height) {
        // 方法2：动态规划,以空间换时间,时间复杂度O(n)，空间复杂度O(n)
        int len = height.length;
        int[] leftMax = new int[len];  // 记录i左边的最大高度
        int[] rightMax = new int[len]; // 记录i右边最大高度
        // 初始化边界
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];
        // 填充leftMax
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 填充leftMax
        for (int i = len - 1 - 1; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 计算收集到的雨水总量
        int count = 0;
        for (int i = 0; i < len; i++) {
            count += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return count;
    }

    public static int trap2(int[] height) {
        // 方法3：双指针,时间复杂度O(n)，空间复杂度O(1)
        int len = height.length;
        int ans = 0;
        int left = 0, right = len - 1;
        int leftMax = 0;  // 记录i左边的最大高度
        int rightMax = 0; // 记录i右边最大高度
        while (left < right) {
            // 边记录边更新 最左/最右 的最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                // 当前left小于right时，说明当前位置能获取到的雨水数由leftMax决定
                ans += leftMax - height[left++];
            } else {
                // 当前left大于right时，说明当前位置能获取到的雨水数由rightMax决定
                ans += rightMax - height[right--];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        // 输入：height = [4,2,0,3,2,5]
        int[] height1 = {4, 2, 0, 3, 2, 5};
        System.out.println(trap(height));  // 6
        System.out.println(trap(height1));  // 9

        System.out.println(trap1(height));  // 6
        System.out.println(trap1(height1));  // 9

        System.out.println(trap2(height));  // 6
        System.out.println(trap2(height1));  // 9
    }
}