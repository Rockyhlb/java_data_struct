package hashtable;

import java.util.HashMap;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: HashTable
 * @CreateTime : 2024/4/11 11:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 205. 同构字符串
     * 给定两个字符串 s 和 t ，判断它们是否是同构的。
     * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
     * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，
     * 相同字符只能映射到同一个字符上，字符可以映射到自己本身。
     * <p>
     * 示例 1:
     * 输入：s = "egg", t = "add"
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：s = "foo", t = "bar"
     * 输出：false
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 创建HashMap进行键值对的映射，将s作为key,t作为value
        HashMap<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                // 当map中不包含s.charAt(i) 且 value中已经包含t.charAt(i)时,说明s和t不构成映射关系，返回false
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                // 记录映射
                map.put(s.charAt(i),t.charAt(i));
            }else {
                // 根据key取出value，与t对应位置的值进行比较，不相等则说明不构成映射关系
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "agg";
        String t = "add";

        String s1 = "foo";
        String t1 = "ban";
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphic(s1, t1));
    }
}
