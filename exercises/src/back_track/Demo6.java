package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/27 11:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     * 输入：n = 1
     * 输出：["()"]
     * 提示：
     * 1 <= n <= 8
     */
    public static List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        backTrack(n, n, resList, new StringBuilder());
        return resList;
    }

    private static void backTrack(int leftCanUse, int rightCanUse, List<String> resList, StringBuilder path) {
        if (leftCanUse == 0 && rightCanUse == 0) {
            // 当左右无可用括号时,说明匹配结束
            resList.add(path.toString());
        }
        // 剪枝, 括号匹配过程中，一定不能使右括号比左括号多(右括号排在前面)，此时leftCanUse>rightCanUse
        if (leftCanUse > rightCanUse) {
            return;
        }
        if (leftCanUse > 0) {
            // 添加左括号
            path.append("(");
            backTrack(leftCanUse - 1, rightCanUse, resList, path);
            // 回溯
            path.deleteCharAt(path.length() - 1);
        }
        if (rightCanUse > 0) {
            path.append(")");
            backTrack(leftCanUse, rightCanUse - 1, resList, path);
            // 回溯
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
