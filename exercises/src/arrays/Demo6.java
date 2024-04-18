package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/18 11:26
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 125. 验证回文串
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入: s = "A man, a plan, a canal: Panama"
     * 输出：true
     * 解释："amanaplanacanalpanama" 是回文串。
     */
    public static boolean isPalindrome(String s) {
        // 处理字符串
        String tmp = s.trim().toLowerCase();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < tmp.length(); i++) {
            char ch = tmp.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sbd.append(ch);
            }
        }
        // 方法1：双指针
        int left = 0;
        int right = sbd.length() - 1;
        while (left < sbd.length() / 2) {
            if (sbd.charAt(left) != sbd.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

        // 方法2：reverse(), 反转后 与 反转前进行比较
//        StringBuilder sbd1 = new StringBuilder(sbd).reverse();
//        return sbd1.toString().equals(sbd.toString());
    }

    public static void main(String[] args) {
        String str = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(str));
    }
}
