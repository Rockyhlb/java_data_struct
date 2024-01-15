import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: test-20240115
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/15 13:02
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {

    /**
     * 2、给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
     * 【LeetCode题号：77. 组合】【中等】
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4],]
     * 示例 2：
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     * 提示：
     * 1 <= n <= 20
     * 1 <= k <= n
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (k <= 0 || n < k) {
            return resList;
        }
        // 回溯算法进行求解
        dfs(n,k,1,path,resList);
        return resList;
    }
    private static void dfs(int n,int k,int start,List<Integer> path,List<List<Integer>> resList) {
        if (path.size() == k) {
            resList.add(new ArrayList<>(path));
            // 回到上一层递归剪枝，减少递归次数
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            dfs(n,k,i+1,path,resList);
            path.remove(path.size()-1);
        }
    }
    public static void main(String[] args) {
        combine(4,2);
    }
}
