package arrays;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/3 10:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     * 示例 2：
     * 输入：nums = [2,2,1,1,1,2,2]
     * 输出：2
     * 提示：
     * n == nums.length
     * 1 <= n <= 5 * 104
     * -109 <= nums[i] <= 109
     */
    public static int majorityElement(int[] nums) {
        // 方法1：通过Map记录键值对
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            }
            map.put(num, map.get(num) + 1);
        }
        // 遍历map
        for (int num : nums) {
            if (map.get(num) > (n / 2 + 1)) {
                return num;
            }
        }
        return -1;
    }

    public static int majorityElement1(int[] nums) {
        // 方法2：排序后返回中间元素
        // Arrays.sort(nums);
        // 插入排序
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > tmp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = tmp;
        }
        return nums[nums.length / 2];
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static int majorityElement2(int[] nums) {
        // 方法3：Boyer-Moore 投票算法
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (candidate == num) ? 1 : -1;
        }
        // 最终的candidate就是众数
        return candidate;
    }

    public static void main(String[] args) {
        // 输入：nums = [2,2,1,1,1,2,2]
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums)); // 2
        System.out.println(majorityElement1(nums)); // 2
        System.out.println(majorityElement2(nums)); // 2
    }
}