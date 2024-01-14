import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: test-20240114
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/14 23:26
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 1、给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。解集 不能 包含重
     * 复的子集。你可以按 任意顺序 返回解集。 OJ链接【LeetCode题号：78. 子集】【中等】
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * 提示：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 通过 “回溯 + dfs” 实现子集合的查找
        dfs(nums,0,nums.length,path,resList);
        // 返回：{[],[1],{1,2},{1,2,3},{1,3},{2},{2,3},{3}}
        return resList;
    }
    private static void dfs(int[] nums,int begin,int end,List<Integer> path,List<List<Integer>> resList) {
        // 拷贝path并加入到结果集中(如果是直接添加path，由于引用机制，党后面对path进行修改时，结果集中对应的path也会发生改变)
        resList.add(new ArrayList<>(path));
        for (int i = begin; i < end; i++) {
            // 将数组第begin个元素添加到路径之中
            path.add(nums[i]);
            dfs(nums,i+1,end,path,resList);
            path.remove(path.size()-1);
        }
    }
}
