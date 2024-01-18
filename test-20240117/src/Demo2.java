import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @BelongsProject: test-20240118
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/18 19:55
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 2、给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。OJ链接 【LeetCode题号：47. 全排列 II】【中等】
     * 示例 1：
     * 输入：nums = [1,1,2]  输出：[ [1,1,2],[1,2,1],[2,1,1] ]
     * 示例 2：
     * 输入：nums = [1,2,3]  输出：[ [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1] ]
     * 提示：
     * 1 <= nums.length <= 8; -10 <= nums[i] <= 10
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num: nums) {
            path.add(num);
        }
        dfs(0,nums.length,path,resList);
        return resList;
    }
    private static void dfs(int start,int end,List<Integer> path,List<List<Integer>> resList) {
        if (start == end && !resList.contains(path)) {
            resList.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < end; i++) {
            Collections.swap(path,i,start);
            dfs(start+1,end,path,resList);
            Collections.swap(path,i,start);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        Demo1 demo1 = new Demo1();
        List<List<Integer>> resList = demo1.permuteUnique(nums);
        Iterator iterator = resList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
