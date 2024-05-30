package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/5/30 21:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     * 提示：
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母组成
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        // 中心扩散法，定义两个指针left和right,分别向左右寻找与当前位置相同的字符，最后再对left和right双向扩散
        int left = 0, right = 0;
        int maxStart = 0;  // 记录最长回文串开始下标
        int maxLen = 0;  // 记录最长回文串长度
        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            int curLen = 1;
            // 往左遍历
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                curLen++;
                left--;
            }
            // 往右遍历
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                curLen++;
                right++;
            }
            // 双向遍历
            while (left >= 0 && right < strLen && s.charAt(left) == s.charAt(right)) {
                curLen += 2;
                left--;
                right++;
            }
            // 更新maxLen及maxStart
            if (curLen > maxLen) {
                maxStart = left;
                maxLen = curLen;
            }
        }
        // 根据最长回文串的开始下标及长度截取字符串
        return s.substring(maxStart + 1, maxStart + maxLen + 1);
    }

    public static String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        // 方法2：动态规划，以空间换时间，标记dp[i][j] 为[i,j]是否为回文串，因此只需判断[i-1,j+1]字符是否相同即可
        int strLen = s.length();
        int maxStart = 0;
        int maxLen = 1;
        boolean[][] dp = new boolean[strLen][strLen];
        for (int right = 1; right < strLen; right++) {
            for (int left = 0; left < right; left++) {
                // 两个字符相等的同时，还要限制两个字符是否满足构成回文串的条件，(right - left <= 2) 限制两个字符是否构成对称(回文串构成对称)
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left + 1;
                        maxStart = left;
                    }
                }
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    public static void main(String[] args) {
        //  输入：s = "babad"
        String s = "babad";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome1(s));
    }
}
