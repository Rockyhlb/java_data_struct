package dp;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/9 12:02
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * 示例 1：
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     * 示例 2：
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     * 注意，你可以重复使用字典中的单词。
     * 示例 3：
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        // 数组默认全部为false
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 控制截取字符串比较时的 右区间
        for (int i = 1; i <= s.length(); i++) {
            // 控制截取字符串时的 左区间
            for (int j = 0; j < i; j++) {
                // dp[j]: 后一个字符串的比较依赖于前一个字符串的比较结果
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        // s = "leetcode", wordDict = ["leet", "code"]
        String s = "leetcode";
        List<String> list = Arrays.asList("leet","code");

        String s1 = "leetcode";
        List<String> list1 = Arrays.asList("eet","code");

        System.out.println(wordBreak(s, list));
        System.out.println(wordBreak(s1, list1));
    }
}
