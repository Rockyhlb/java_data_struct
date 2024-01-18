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
public class Demo1 {
    /**
     * 1、数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 有效括号组合需满足：左括号必须以正确的顺序闭合。 OJ链接 【LeetCode题号：22. 括号生成】【中等】
     * 示例 1：
     * 输入：n = 3   输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     * 输入：n = 1   输出：["()"]
     * 提示：1 <= n <= 8
     */
    public List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        dfs("",n,n,resList);
        return resList;
    }
    private static void dfs(String curStr,int leftCanUse,int rightCanUse,List<String> resList) {
        // 因为每一次进入dfs都是新的字符串变量，因此不需要回溯
        if (leftCanUse == 0 && rightCanUse == 0) {
            resList.add(curStr);
        }
        // 剪枝:因为左边能用括号数不能小于右边能用括号数,例如不能使用右括号开头(此时左3右2)
        if (leftCanUse > rightCanUse) {
            return;
        }
        // 加入左括号
        if (leftCanUse > 0) {
            dfs(curStr+"(",leftCanUse-1,rightCanUse,resList);
        }
        // 加入右括号
        if (rightCanUse > 0) {
            dfs(curStr+")",leftCanUse,rightCanUse-1,resList);
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        List<String> resList = demo1.generateParenthesis(3);
        Iterator iterator = resList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
