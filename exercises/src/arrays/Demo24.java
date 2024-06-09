package arrays;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/9 19:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo24 {
    /**
     * 68. 文本左右对齐
     * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
     * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     * 注意:
     * 单词是指由非空格字符组成的字符序列。
     * 每个单词的长度大于 0，小于等于 maxWidth。
     * 输入单词数组 words 至少包含一个单词。
     * 示例 1:
     * 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
     * 输出:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     * 示例 2:
     * 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
     * 输出:
     * [
     * "What   must   be",
     * "acknowledgment  ",
     * "shall be        "
     * ]
     * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     * 因为最后一行应为左对齐，而不是左右两端对齐。
     * 第二行同样为左对齐，这是因为这行只包含一个单词。
     * 示例 3:
     * 输入:words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
     * 输出:
     * [
     * "Science  is  what we",
     * "understand      well",
     * "enough to explain to",
     * "a  computer.  Art is",
     * "everything  else  we",
     * "do                  "
     * ]
     * 提示:
     * 1 <= words.length <= 300
     * 1 <= words[i].length <= 20
     * words[i] 由小写英文字母和符号组成
     * 1 <= maxWidth <= 100
     * words[i].length <= maxWidth
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> resList = new ArrayList<>();
        // 双指针 -_-
        int left = 0, right = 0;
        while (left < words.length) {
            int len = 0;
            while (right < words.length && len < maxWidth) {
                // 寻找能使len满足maxWidth的最大下标
                len += words[right].length();
                // 再加上分隔符(空格)的长度
                if (len + (right - left) <= maxWidth) {
                    right++;
                } else {
                    // 加上当前字符串后长度超标，len/right回退
                    len -= words[right].length();
                    right--;
                    break;
                }
            }
            // 根据len和right-left的计算平均插入多少个空格
            int space = 1;
            // 单词数大于3才需要平均空格
            if (right >= words.length) {
                // 当right越界时进行复位
                right = words.length - 1;
            }
            if (right != words.length - 1 && right - left != 0) {
                space = (int) Math.ceil((double) (maxWidth - len) / (right - left));
            }
            StringBuilder spaceStr = new StringBuilder();
            // 拼接空格串
            while (space > 0) {
                spaceStr.append(" ");
                space--;
            }
            // 拼接字符串
            StringBuilder sbd = new StringBuilder();
            while (left < right) {
                sbd.append(words[left++]);
                // 添加空格，补全长度
                sbd.append(spaceStr);
            }
            // 添加末尾单词
            if (left < words.length) {
                if (sbd.length() != 0 && sbd.length() + words[left].length() > maxWidth) {
                    // 去除最后一个分割串过多的情况
                    sbd.deleteCharAt(sbd.length() - 1);
                }
                sbd.append(words[left++]);
            }
            right++;
            // 补齐长度
            int tab = maxWidth - sbd.length();
            while (tab > 0) {
                sbd.append(" ");
                tab--;
            }
            resList.add(sbd.toString());
        }
        return resList;
    }

    public static List<String> fullJustify1(String[] words, int maxWidth) {
        // 将规则提取成两份： ^_^
        // 1、当前行只有一个单词||当前行是最后一行，都进行左对其处理
        // 2、其余情况，分别计算当前行单词个数，空格长度，往下取整后的单位空格长度，然后依次进行拼接，当空格无法均分时，每次对靠左的空格追加一个长度
        int n = words.length;
        List<String> resList = new ArrayList<>();
        List<String> row = new ArrayList<>();
        for (int i = 0; i < n; ) {
            // 读取行
            row.clear();
            row.add(words[i]);
            // 记录当前行长度
            int curLen = words[i++].length();
            while (i < n && curLen + 1 + words[i].length() <= maxWidth) {
                curLen += words[i].length() + 1;  // 加上默认空格长度
                row.add(words[i++]);
            }
            int wordsNum = row.size();
            // 1、当前行只有一个单词，左对齐
            if (wordsNum == 1) {
                StringBuilder sbd = new StringBuilder(row.get(0));
                while (sbd.length() < maxWidth) {
                    sbd.append(" ");
                }
                resList.add(sbd.toString());
                continue;
            }
            // 2、当前行是最后一行，左对齐
            if (i == n) {
                StringBuilder sbd = new StringBuilder(row.get(0));
                for (int j = 1; j < wordsNum; j++) {
                    sbd.append(" ").append(row.get(j));
                }
                while (sbd.length() < maxWidth) {
                    sbd.append(" ");
                }
                resList.add(sbd.toString());
                break;
            }
            // 3、一般情况
            int wordsLen = curLen - (wordsNum - 1);  // 计算当前行单词总长度
            int spaceLen = maxWidth - wordsLen;  // 计算空格总长度
            int spaceItemLen = spaceLen / (wordsNum - 1); // 计算空格单位长度,向下取整
            // 初始化单位空格
            String spaceItem = "";
            for (int j = 0; j < spaceItemLen; j++) {
                spaceItem += " ";
            }
            StringBuilder sbd = new StringBuilder();
            int curSpaceLen = 0;  // 记录当前已插入空格总长度
            for (int k = 0; k < wordsNum; k++) {
                sbd.append(row.get(k));
                if (k == wordsNum - 1) {
                    // 最后一个单词不需要添加空格后缀
                    break;
                }
                sbd.append(spaceItem);
                curSpaceLen += spaceItemLen;
                // 计算当前还能插入空格数量
                int remainSpace = wordsNum - k - 1 - 1;
                // 如果当前 剩余空格 * 单位空格长度 + 当前已插入空格长度 小于空格总长度，则需再追加一个空格
                if (remainSpace * spaceItemLen + curSpaceLen < spaceLen) {
                    sbd.append(" ");
                    curSpaceLen++;
                }
            }
            resList.add(sbd.toString());
        }
        return resList;
    }

    public static void main(String[] args) {
        // 输入: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        // 输出：[This    is    an,example  of text,justification.  ]
        System.out.println(fullJustify(words, 16));
        System.out.println(fullJustify1(words, 16));

        // 输入:words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
        String[] words1 = {"What", "must", "be", "acknowledgment", "shall", "be"};
        // 输出：[What   must   be,acknowledgment  ,shall be        ]
        System.out.println(fullJustify(words1, 16));
        System.out.println(fullJustify1(words1, 16));

        // words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
        String[] words2 = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        // 输出：[Science  is  what we, understand      well, enough to explain to, a  computer.  Art is, everything  else  we, do                  ]
        System.out.println(fullJustify(words2, 20));
        System.out.println(fullJustify1(words2, 20));
    }
}
