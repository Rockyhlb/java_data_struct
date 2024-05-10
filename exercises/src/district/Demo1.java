package district;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: district
 * @CreateTime : 2024/5/10 9:30
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 228. 汇总区间
     * 给定一个  无重复元素 的 有序 整数数组 nums 。
     * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
     * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
     * "a->b" ，如果 a != b
     * "a" ，如果 a == b
     * 示例 1：
     * 输入：nums = [0,1,2,4,5,7]
     * 输出：["0->2","4->5","7"]
     * 解释：区间范围是：
     * [0,2] --> "0->2"
     * [4,5] --> "4->5"
     * [7,7] --> "7"
     * 示例 2：
     * 输入：nums = [0,2,3,4,6,8,9]
     * 输出：["0","2->4","6","8->9"]
     * 解释：区间范围是：
     * [0,0] --> "0"
     * [2,4] --> "2->4"
     * [6,6] --> "6"
     * [8,9] --> "8->9"
     */
    public static List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> resList = new ArrayList<>();
        // 双指针
        int left = 0;
        int right = 1;
        while (right < nums.length) {
            if (1 == nums[right] - nums[right - 1]) {
                right++;
                continue;
            } else {
                StringBuilder sbd = new StringBuilder();
                sbd.append(nums[left]);
                if (left != right - 1) {
                    sbd.append("->");
                    sbd.append(nums[right - 1]);
                }
                left = right++;
                resList.add(sbd.toString());
            }
        }
        if (left != right - 1) {
            resList.add(nums[left] + "->" + nums[right - 1]);
        } else {
            resList.add(nums[left] + "");
        }
        return resList;
    }

    public static void main(String[] args) {
        // 输入：nums = [0,1,2,4,5,7]
        // 输出：["0->2","4->5","7"]
        int[] nums = {0, 1, 2, 4, 5, 7};
        int[] nums1 = {0, 2, 3, 4, 6, 8, 9};
        int[] nums2 = {-1};
        System.out.println(summaryRanges(nums));
        System.out.println(summaryRanges(nums1));
        System.out.println(summaryRanges(nums2));
    }
}
