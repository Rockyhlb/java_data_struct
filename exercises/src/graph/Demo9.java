package graph;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/6/24 21:37
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 127. 单词接龙
     * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
     * 每一对相邻的单词只差一个字母。
     * 对于 1 <= i <= k 时，每个 Si 都在 wordList 中。注意，beginWord 不需要在 wordList 中。
     * sk == endWord
     * 给你两个单词 beginWord 和 endWord 和一个字典 wordList，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目。
     * 如果不存在这样的转换序列，返回 0 。
     * 示例 1：
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出：5
     * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
     * 示例 2：
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * 输出：0
     * 解释：endWord "cog" 不在字典中，所以无法进行转换。
     * 提示：
     * 1 <= beginWord.length <= 10
     * endWord.length == beginWord.length
     * 1 <= wordList.length <= 5000
     * wordList[i].length == beginWord.length
     * beginWord、endWord 和 wordList[i] 由小写英文字母组成
     * beginWord != endWord
     * wordList 中的所有字符串 互不相同
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 类似于基因变换，采用广搜解题，给每个字符串设置一个id，这个id就是图节点
        int n = wordList.size();
        // 将起始节点加入到wordList中
        wordList.add(beginWord);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int steps = 1;
        // 将下标作为字符串的id，先从末尾字符串遍历
        queue.offer(n);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 本层遍历
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (endWord.equals(wordList.get(cur))) {
                    return steps;
                }
                for (int j = 0; j < n; j++) {
                    if (visited.contains(j)) {
                        continue;
                    }
                    // 判断当前字符串和下一个字符串是否可以一次变换得到
                    if (isTransformOne(wordList.get(cur), wordList.get(j))) {
                        visited.add(j);
                        queue.offer(j);
                    }
                }
            }
            steps++;
        }
        return 0;
    }

    private static boolean isTransformOne(String str1, String str2) {
        int len = str1.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 对上述方法的优化
        Set<String> records = new HashSet<>(wordList);  // 记录字典
        Set<String> visited = new HashSet<>();  // 记录已遍历过的节点
        Queue<String> queue = new LinkedList<>();
        int len = beginWord.length();  // 记录字符串长度，因为后续字符串无论怎么变换，长度都不会变
        queue.offer(beginWord);
        int steps = 0;
        // 广搜
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            // 本层遍历
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                if (endWord.equals(curWord)) {
                    return steps;
                }
                // 构造备份，操作备份数据
                StringBuilder sbd = new StringBuilder(curWord);
                for (int j = 0; j < len; j++) {
                    char ch = sbd.charAt(j);
                    for (int k = 0; k < 26; k++) {
                        // 将sbd[j]和每个英文字母进行对比
                        if (ch != (char) ('a' + k)) {
                            // 不相同则构建字符串变换
                            sbd.setCharAt(j, (char) ('a' + k));
                            String newWord = sbd.toString();
                            // 如果构建出来的新字符串在字典中，则将其放入队列中
                            if (records.contains(newWord) && !visited.contains(newWord)) {
                                visited.add(newWord);
                                queue.offer(newWord);
                            }
                        }
                    }
                    // 复位
                    sbd.setCharAt(j, ch);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));  // 5
        System.out.println(ladderLength1(beginWord, endWord, wordList));  // 5
    }
}
