package back_track;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: back_track
 * @CreateTime : 2024/5/25 9:24
 * @Description: 回溯
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     * 提示：
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     */
    public static List<String> letterCombinations(String digits) {
        List<String> resList = new ArrayList<>();
        if (digits.length() == 0) {
            return resList;
        }
        Map<Character, String> phoneMap = new HashMap<>();  // 存储对应字符串
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
        backtrack(digits, 0, phoneMap, resList, new StringBuilder());
        return resList;
    }

    private static void backtrack(String digits, int index, Map<Character, String> phoneMap, List<String> resList, StringBuilder combine) {
        // 回溯
        if (index == digits.length()) {
            // 当前已经遍历完输入时，将组合加入到结果列表中
            resList.add(combine.toString());
        } else {
            char key = digits.charAt(index);
            // 从映射中获取对应字符串
            String letters = phoneMap.get(key);
            for (int i = 0; i < letters.length(); i++) {
                // 遍历字符集
                combine.append(letters.charAt(i));
                // 进入递归
                backtrack(digits, index + 1, phoneMap, resList, combine);
                // 递归结束后,通过回溯舍弃已经遍历过的字符
                // 一次递归只能加入一个字符or添加一个结果集，因此其实combine的长度是和index保持一致的，
                // 想要移除当前已经遍历过的元素，只需删除当前index下标的元素即可
                combine.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations("234"));
    }
}