package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/18 13:47
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * 进阶：
     * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     */
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }
        // 双指针
        int left = 0;
        int right = 0;
        for (; right < t.length(); right++) {
            if (t.charAt(right) == s.charAt(left)) {
                left++;
            }
            if (left == s.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));

        String s1 = "a";
        String t1 = "c";
        System.out.println(isSubsequence(s1, t1));

        String s2 = "ace";
        String t2 = "asdscdse";
        System.out.println(isSubsequence(s2, t2));

        String s3 = "b";
        String t3 = "abc";
        System.out.println(isSubsequence(s3, t3));
    }
}
