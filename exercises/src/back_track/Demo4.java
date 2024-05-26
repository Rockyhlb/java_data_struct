package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/26 21:04
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
     * 并以列表形式返回。你可以按 任意顺序 返回这些组合。candidates 中的 同一个 数字可以 无限制重复被选取 。
     * 如果至少一个数字的被选数量不同，则两种组合是不同的。对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。仅有这两种组合。
     * 示例 2：
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     * 示例 3：
     * 输入: candidates = [2], target = 1
     * 输出: []
     * 提示：
     * 1 <= candidates.length <= 30
     * 2 <= candidates[i] <= 40
     * candidates 的所有元素 互不相同
     * 1 <= target <= 40
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 搜索回溯,将整个搜索过程设想为遍历一棵树，每次搜索都会产生两个分支，即加入当前数字||直接遍历下一个数字
        // 因此可以通过递归来对树进行遍历，以下给的是完整遍历，不含剪枝算法
        List<List<Integer>> resList = new ArrayList<>();
        dfs(candidates, target, resList, new ArrayList<>(), 0);
        return resList;
    }

    private static void dfs(int[] candidates, int target, List<List<Integer>> resList, List<Integer> combine, int index) {
        // 控制边界
        if (index == candidates.length) {
            return;
        }
        // 当target==0说明当前组合已经满足插入条件
        if (target == 0) {
            resList.add(new ArrayList<>(combine));
            return;
        }
        // 往下遍历
        dfs(candidates, target, resList, combine, index + 1);
        // 当剩余target>=当前索引的值时,将自身加入组合
        if (target - candidates[index] >= 0) {
            combine.add(candidates[index]);
            dfs(candidates, target - candidates[index], resList, combine, index);
            // 回溯，去除已经遍历过的元素
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {
        // 输入: candidates = [2,3,5], target = 8
        int[] candidates = {2, 3, 5};
        System.out.println(combinationSum(candidates, 8));
    }
}
