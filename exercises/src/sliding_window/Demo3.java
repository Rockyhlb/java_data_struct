package sliding_window;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sliding_window
 * @CreateTime : 2024/6/12 10:35
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 30. 串联所有单词的子串
     * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
     * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
     * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
     * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
     * 示例 1：
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
     * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
     * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
     * 输出顺序无关紧要。返回 [9,0] 也是可以的。
     * 示例 2：
     * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     * 输出：[]
     * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
     * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
     * 所以我们返回一个空数组。
     * 示例 3：
     * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * 输出：[6,9,12]
     * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
     * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
     * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
     * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
     * 提示：
     * 1 <= s.length <= 104
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * words[i] 和 s 由小写英文字母组成
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        int wordLen = words[0].length();
        int wordsNum = words.length;
        int slen = s.length();
        // 统计words数组中单词出现个数
        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }
        // 使用滑动窗口统计每个窗口内单词个数，滑动窗口大小设置为wordLen，因为一个单词长度的窗口大小移动后都是重复的
        for (int window = 0; window < wordLen; window++) {
            // 设置窗口左右边界
            int windowLeft = window;
            int windowRight = window;
            // 存储当前窗口中的单词
            Map<String, Integer> windowMap = new HashMap<>();
            // 控制右边界遍历到字符串 s 的尾部
            while (windowRight + wordLen <= slen) {
                // 移动右边界，统计遍历到的单词, substring(start,end): 区间为左开右闭
                String word = s.substring(windowRight, windowRight + wordLen);
                windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                windowRight += wordLen;
                // 如果窗口内出现了不满足条件的单词，则需要将其剔除(移动左边界)
                // 因此可以通过比较windowMap和countMap，若单词不在words数组，或者当前单词出现数量大于words数组提供个数
                // 循环判断当前窗口元素情况，不满足则继续剔除左边界元素，直至满足条件为止
                while (windowMap.getOrDefault(word, 0) > countMap.getOrDefault(word, 0)) {
                    // 获取左边界单词
                    String leftWord = s.substring(windowLeft, windowLeft + wordLen);
                    // 将元素踢出窗口map
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    // 移动左边界
                    windowLeft += wordLen;
                }
                // 判断当前窗口内单词个数是否等于wordsNum,
                // 若相等，则说明windowLeft和windowRight之间的字符串就是满足条件的子串
                int windowWordNum = (windowRight - windowLeft) / wordLen;
                if (windowWordNum == wordsNum) {
                    ans.add(windowLeft);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 输入：s = "barfoothefoobarman", words = ["foo","bar"]
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(findSubstring(s, words));  // [0,9]
    }
}
