package sliding_window;

import java.util.HashMap;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sliding_window
 * @CreateTime : 2024/5/22 21:44
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 提示：
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     */
    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        if (0 == s.length()) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, end = 1;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 如果当前字符之前已经出现过，则更新start下标
            // 又由于每个字符出现的下标有大有小，因此需要取大值
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            // 默认将当前字符的下标放入映射
            map.put(ch, i);
            // 更新最大长度
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        // 输入: s = "abcabcbb"
        String s = "abcabcbb";
        String s1 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));  // 3
        System.out.println(lengthOfLongestSubstring(s1)); // 3
    }
}
