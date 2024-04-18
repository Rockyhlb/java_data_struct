package hashtable;

import java.util.HashMap;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: hashtable
 * @CreateTime : 2024/4/18 14:18
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 290. 单词规律
     * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
     * 示例1:
     * 输入: pattern = "abba", s = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     * 输入:pattern = "abba", s = "dog cat cat fish"
     * 输出: false
     */
    public static boolean wordPattern(String pattern, String s) {
        String[] strArr = s.split(" ");
        if (strArr.length != pattern.length()) {
            return false;
        }
        // 采用 HashMap 完成字符串和字符之间的[双射]
        HashMap<Character, String> c2s = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            Character ch = pattern.charAt(i);
            if (!c2s.containsKey(ch)) {
                if (c2s.containsValue(strArr[i])) {
                    // 当前映射中不包含 pattern 但是已经包含str[i]时返回false
                    return false;
                }
                c2s.put(ch, strArr[i]);
            } else if (!c2s.get(ch).equals(strArr[i])) {
                // 根据当前字符取出的value与strArr[i]不匹配时返回false
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
        System.out.println(wordPattern(pattern, str));
    }
}
