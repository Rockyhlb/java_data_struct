package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/26 18:58
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 示例 2：
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     * 示例 3：
     * 输入：nums = [1]
     * 输出：[[1]]
     * 提示：
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums 中的所有整数 互不相同
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length == 0) {
            return resList;
        }
        // 全排列：递归遍历将每两元素进行交换后得到的结果就是全排列结果
        // 申请一个列表记录所有元素，后续针对该路径进行维护
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        backTrack(0, nums.length, resList, path);
        return resList;
    }

    private static void backTrack(int start, int end, List<List<Integer>> resList, List<Integer> path) {
        if (start == end) {
            resList.add(new ArrayList<>(path));
        }
        for (int i = start; i < end; i++) {
            // 对路径进行交换元素
            Collections.swap(path, i, start);
            // 进入递归
            backTrack(start + 1, end, resList, path);
            // 回溯，对路径进行复位，达到丢弃已经遍历过元素的效果
            Collections.swap(path, i, start);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
