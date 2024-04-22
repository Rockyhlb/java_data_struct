package hashtable;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: hashtable
 * @CreateTime : 2024/4/22 13:01
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * <p>
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * <p>
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     * <p>
     * 提示:
     * 1 <= s.length, t.length <= 5 * 104
     * s 和 t 仅包含小写字母
     */
    public static boolean isAnagram(String s, String t) {
        // 长度不相等直接返回false
        if (s.length() != t.length()) {
            return false;
        }
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 一增一减，如果最后是字母异位词，则维护的hash应该全部为0
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }

        for (int j : hash) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "rat";
        String t = "car";
        System.out.println(isAnagram(s, t));
    }
}
