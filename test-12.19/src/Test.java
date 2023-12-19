/**
 * @BelongsProject: test-12.19
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023/12/19 16:11
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class Test {
    /**
     * 1903. 字符串中的最大奇数
     * 给你一个字符串 num ，表示一个大整数。请你在字符串 num 的所有 非空子字符串 中找出 值最大的奇数 ，
     * 并以字符串形式返回。如果不存在奇数，则返回一个空字符串 "" 。
     * 子字符串 是字符串中的一个连续的字符序列。
     * 示例 1：
     * 输入：num = "52"
     */
    public static String largestOddNumber(String num) {
        char[] chars = num.toCharArray();
        if (Character.getNumericValue(chars[chars.length - 1]) % 2 != 0) {
            return num;
        }
        int maxIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            int value = Character.getNumericValue(chars[i]);
            if (value%2 != 0) {
                maxIndex = i;
            }
        }
        if (-1 != maxIndex) {
            // java 默认的区间都是左闭右开，此处我们需要截取到maxIndex处
            return num.substring(0,maxIndex+1);
        }
        return "";
    }

    /**
     * 1910. 删除一个字符串中所有出现的给定子字符串
     * 给你两个字符串 s 和 part ，请你对 s 反复执行以下操作直到 所有 子字符串 part 都被删除：
     * 找到 s 中 最左边 的子字符串 part ，并将它从 s 中删除。
     * 请你返回从 s 中删除所有 part 子字符串以后得到的剩余字符串。
     * 一个 子字符串 是一个字符串中连续的字符序列。
     * 示例 1：
     * 输入：s = "daabcbaabcbc", part = "abc"
     * 输出："dab"
     * 解释：以下操作按顺序执行：
     * - s = "daabcbaabcbc" ，删除下标从 2 开始的 "abc" ，得到 s = "dabaabcbc" 。
     * - s = "dabaabcbc" ，删除下标从 4 开始的 "abc" ，得到 s = "dababc" 。
     * - s = "dababc" ，删除下标从 3 开始的 "abc" ，得到 s = "dab" 。
     * 此时 s 中不再含有子字符串 "abc" 。
     */
    public String removeOccurrences(String s, String part) {
        while (s.contains(part)) {
            for (int i = 0; i < s.length(); i++) {
                if (part.equals(s.substring(i,i+part.length()))) {
                    s = s.substring(0,i) + s.substring(i+part.length());
                    break;
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(largestOddNumber("10133890"));
    }
}
