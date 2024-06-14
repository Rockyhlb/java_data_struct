package sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sliding_window
 * @CreateTime : 2024/6/14 8:35
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * 示例 1：
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     * 示例 2：
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串。
     * 示例 3:
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     * 提示：
     * m == s.length
     * n == t.length
     * 1 <= m, n <= 105
     * s 和 t 由英文字母组成
     */
    public static String minWindow(String s, String t) {
        // 滑动窗口，定义两个指针，一个用于扩大窗口，一个用于收缩窗口，并且在任意时刻，肯定只有一个指针在移动，另一个静止不动
        int lens = s.length();
        int lent = t.length();
        Map<Character, Integer> tarMap = new HashMap<>(); // 目标map
        Map<Character, Integer> windMap = new HashMap<>(); // 窗口map
        for (Character ch : t.toCharArray()) {
            // 初始化tarMap
            tarMap.put(ch, tarMap.getOrDefault(ch, 0) + 1);
        }
        int winLeft = 0, winRight = 0; // 定义窗口边界
        int start = 0, minLen = Integer.MAX_VALUE;  // 定义目标子串的开始位置及长度
        int match = 0;  // 记录当前窗口的匹配数量
        while (winRight < lens) {
            // 扩大窗口
            Character ch = s.charAt(winRight++);
            if (tarMap.containsKey(ch)) {
                // 当前字符是匹配项时，加入windMap
                windMap.put(ch, windMap.getOrDefault(ch, 0) + 1);
                if (windMap.get(ch).equals(tarMap.get(ch))) {
                    // 匹配数量++
                    match++;
                }
            }
            // 当前窗口的已经包含所有匹配项, 收缩窗口，查找最小开始位置
            while (match == tarMap.size()) {
                // 更新开始位置及长度
                if (winRight - winLeft < minLen) {
                    start = winLeft;
                    minLen = winRight - winLeft;
                }
                // 收缩窗口
                Character tmp = s.charAt(winLeft++);
                if (tarMap.containsKey(tmp)) {
                    windMap.put(tmp, windMap.get(tmp) - 1);
                    // 收缩窗口后，匹配项被移除时
                    if (windMap.get(tmp) < tarMap.get(tmp)) {
                        match--;
                    }
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        // 输入：s = "ADOBECODEBANC", t = "ABC"
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));  // BANC
    }
}
