package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: hashtable
 * @CreateTime : 2024/4/22 13:15
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 49. 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * <p>
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * <p>
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     * <p>
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 完成key与list之间的映射
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 计数数组
            int[] count = new int[26];
            for (int j = 0; j < str.length(); j++) {
                count[str.charAt(j) - 'a']++;
            }
            // 将计数数组转换成字母并且和计数顺序进行拼接，作为map的key(同素异构体的key值一定唯一)
            StringBuilder sbd = new StringBuilder();
            for (int j = 0; j < count.length; j++) {
                if (count[j] != 0) {
                    sbd.append((char) ('a' + j));
                    sbd.append(count[j]);
                }
            }
            String key = sbd.toString();
            // 没有就新建一个ArrayList
            List<String> curList = map.getOrDefault(key, new ArrayList<>());
            curList.add(str);
            map.put(key, curList);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}