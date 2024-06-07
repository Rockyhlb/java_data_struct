package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/16 15:14
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo19 {
    /**
     * 58. 最后一个单词的长度
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串
     */
    public static int lengthOfLastWord(String s) {
        // 方法1：^_^
        String[] strs = s.trim().split(" ");
        return strs[strs.length - 1].length();
    }

    public static int lengthOfLastWord1(String s) {
        // 方法2：遍历尾部
        int count = 0;
        // 对字符串进行处理,去除首尾空格
        s = s.trim();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        String str = "hello world";
        System.out.println(lengthOfLastWord(str));
        System.out.println(lengthOfLastWord1(str));
    }
}
