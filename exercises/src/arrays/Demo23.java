package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/8 20:31
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo23 {
    /**
     * 28. 找出字符串中第一个匹配项的下标
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
     * 如果 needle 不是 haystack 的一部分，则返回  -1 。
     * 示例 1：
     * 输入：haystack = "sadbutsad", needle = "sad"
     * 输出：0
     * 解释："sad" 在下标 0 和 6 处匹配。
     * 第一个匹配项的下标是 0 ，所以返回 0 。
     * 示例 2：
     * 输入：haystack = "leetcode", needle = "leeto"
     * 输出：-1
     * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
     * 提示：
     * 1 <= haystack.length, needle.length <= 104
     * haystack 和 needle 仅由小写英文字符组成
     */
    public static int strStr(String haystack, String needle) {
        // 暴力
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            int j = 0;
            while (i + j < haystack.length() && needle.charAt(j) == haystack.charAt(i + j)) {
                j++;
                if (j == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int strStr1(String haystack, String needle) {
        // 对暴力进行优化
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int hayLen = haystack.length();
        int neeLen = needle.length();
        // 控制匹配长度为needLen,减少不必要的匹配
        for (int i = 0; i + neeLen <= hayLen; i++) {
            boolean flag = true;
            for (int j = 0; j < neeLen; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 输入：haystack = "sadbutsad", needle = "sad"
        String haystack = "sadbutsad";
        String needle = "sad";
        System.out.println(strStr(haystack, needle));  // 0
        System.out.println(strStr1(haystack, needle));  // 0

        String haystack1 = "leetcode";
        String needle1 = "leeto";
        System.out.println(strStr(haystack1, needle1));  // -1
        System.out.println(strStr1(haystack1, needle1));  // -1

        String s1 = "mississippi";
        String s2 = "issip";
        System.out.println(strStr(s1, s2));  // 4
        System.out.println(strStr1(s1, s2));  // 4
    }
}
