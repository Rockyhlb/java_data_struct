import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: test-20240321
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/21 10:43
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 219. 存在重复元素 II
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。
     * 如果存在，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：nums = [1,2,3,1], k = 3
     * 输出：true
     *
     * 示例 2：
     * 输入：nums = [1,0,1,1], k = 1
     * 输出：true
     *
     * 示例 3：
     * 输入：nums = [1,2,3,1,2,3], k = 2
     * 输出：false
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        /*// 暴力循环，时间复杂度过高
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j] && Math.abs(i-j) <= k) {
                    return true;
                }
            }
        }
        return false;*/

        // 引入HashSet提高效率
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 维护set长度为k,因此当当前元素已经存在于set当中时，就说明该元素同时满足“num[i]==nums[j] && abs(i-j) <= k”
            if (hashSet.contains(nums[i])) {
                return true;
            }
            hashSet.add(nums[i]);
            // 当set长度大于k时，移除最前面个元素
            if (hashSet.size() > k) {
                hashSet.remove(nums[i-k]);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums, 2));
    }
}
