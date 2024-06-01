package double_pointer;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: double_pointer
 * @CreateTime : 2024/4/21 16:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
     * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new LinkedList<>();
        // 输入校验
        if (nums.length < 3) {
            return resList;
        }
        // 将数组进行排序
        Arrays.sort(nums);
        // 定一找二
        for (int i = 0; i < nums.length; i++) {
            // 固定老大
            int first = nums[i];
            // 如果老大都大于0,那么后面的只会更大，也就更不可能使得三数之和为0
            if (first > 0) {
                break;
            }
            // 去重，防止老大占位，避免出现重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 利用 Set 特性对老二进行去重
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int third = nums[j];
                int second = -(first + third);  // first + second + third == 0
                if (set.contains(second)) {
                    resList.add(new ArrayList<>(Arrays.asList(first, third, -(first + third))));
                    // 去重，防止老三占位
                    while (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                        j++;
                    }
                }
                // 将老二加入set当中
                set.add(third);
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}