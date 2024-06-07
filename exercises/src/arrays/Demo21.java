package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/7 20:08
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo21 {
    /**
     * 151. 反转字符串中的单词
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     * 示例 1：
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：反转后的字符串中不能存在前导空格和尾随空格。
     * 示例 3：
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
     * 提示：
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
     */
    public static String reverseWords(String s) {
        // 模拟，借用两个 StringBuilder 实现字符串的反转
        StringBuilder sbd = new StringBuilder();
        StringBuilder sbd1 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ' ') {
                // 将sbd作为缓冲区
                sbd.append(ch);
            } else {
                // 当前字符是空格的时候
                while (i < s.length() - 1 && s.charAt(i + 1) == ' ') {
                    // 去除多个空格
                    i++;
                }
                if (sbd.length() != 0) {
                    sbd.append(" " + sbd1);
                    // 将拼接后的字符串重新赋值给sbd1
                    sbd1.setLength(0);
                    sbd1.append(sbd);
                    // 清空缓冲区
                    sbd.setLength(0);
                }
            }
        }
        // 拼接成最终结果
        if (sbd.length() != 0) {
            sbd.append(" " + sbd1);
            // 将拼接后的字符串重新赋值给sbd
            sbd1.setLength(0);
            sbd1.append(sbd);
        }
        return sbd1.toString().trim();
    }

    public static String reverseWords1(String s) {
        // 调用spLit(" ")方法分割成数组，然后从后往前遍历，最终实现单词的反转
        String[] strs = s.split(" ");
        if (s.length() < 2) {
            return s;
        }
        StringBuilder sbd = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            // "a good   example" --> ["a","good","","","example"]
            if (!"".equals(strs[i])) {
                sbd.append(strs[i]).append(" ");
            }
        }
        return sbd.substring(0, sbd.length() - 1);
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        String s1 = "  hello world  ";
        String s2 = "a good   example";
        System.out.println(reverseWords(s));  // blue is sky the
        System.out.println(reverseWords(s1)); // world hello
        System.out.println(reverseWords(s1)); // example good a

        System.out.println(reverseWords1(s));
        System.out.println(reverseWords1(s1));
        System.out.println(reverseWords1(s2));
    }
}