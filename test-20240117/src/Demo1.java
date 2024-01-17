import java.util.*;

/**
 * @BelongsProject: test-20240117
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/17 19:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 1、给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。OJ链接【LeetCode题号：46. 全排列】【中等】
     * 示例 1：
     * 输入：nums = [1,2,3] 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 示例 2：
     * 输入：nums = [0,1]   输出：[[0,1],[1,0]]
     * 示例 3：
     * 输入：nums = [1]     输出：[[1]]
     * 提示：1 <= nums.length <= 6; -10 <= nums[i] <= 10; nums 中的所有整数 互不相同
     */
     public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> resList = new ArrayList<>();
         List<Integer> path = new ArrayList<>();
         for (int num: nums) {
             path.add(num);
         }
         dfs(0,nums.length,path,resList);
         return resList;
     }
     private static void dfs(int start,int end,List<Integer> path,List<List<Integer>> resList) {
         //先写以 1 开头的全排列，它们是：[1, 2, 3], [1, 3, 2]，即 1 + [2, 3] 的全排列
         //再写以 2 开头的全排列，它们是：[2, 1, 3], [2, 3, 1]，即 2 + [1, 3] 的全排列
         //最后写 3 开头的全排列，它们是：[3, 1, 2], [3, 2, 1]，即 3 + [1, 2] 的全排列
         // 已经在这条路径上走到尽头时
         if (start == end) {
             resList.add(new ArrayList<>(path));
         }
         for (int i = start; i < end; i++) {
             // 将i和start互换元素
             Collections.swap(path,i,start);
             // 继续递归填入下一个数
             dfs(start+1,end,path,resList);
             // 撤回到递归前的状态
             Collections.swap(path,start,i);
         }
     }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Demo1 demo1 = new Demo1();
        List<List<Integer>> res = demo1.permute(nums);
        Iterator it = res.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
