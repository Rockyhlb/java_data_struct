import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @BelongsProject: test-20240120
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/20 14:05
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 2、找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数
     * 字。OJ链接 【LeetCode题号：216. 组合总和 III】【中等】
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(k,0,1,n,path,resList);
        return resList;
    }
    private static void dfs(int count,int curCount,int start,int pos,List<Integer> path,List<List<Integer>> resList) {
        if (path.size() > count) {
            return;
        }
        if (path.size() == count && curCount == pos) {
            resList.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < 10; i++) {
            path.add(i);
            curCount += i;
            dfs(count,curCount,i+1,pos,path,resList);
            // 撤销之前的curCount
            curCount -= i;
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Iterator<List<Integer>> iterator = demo1.combinationSum3(3,9).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
