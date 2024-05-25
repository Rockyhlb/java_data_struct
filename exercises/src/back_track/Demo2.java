package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/25 18:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     * 提示：
     * 1 <= n <= 20
     * 1 <= k <= n
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resList = new ArrayList<>();
        back_track(resList, 1, n, k, new ArrayList<Integer>());
        return resList;
    }

    private static void back_track(List<List<Integer>> resList, int start, int end, int size, List<Integer> tmpList) {
        if (tmpList.size() == size) {
            resList.add(new ArrayList<>(tmpList));
        } else {
            for (int i = start; i <= end; i++) {
                tmpList.add(i);
                back_track(resList, i + 1, end, size, tmpList);
                // 回溯，丢弃重复元素
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
}
