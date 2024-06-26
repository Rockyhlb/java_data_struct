package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/23 15:14
 * @Description: 数学
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 9. 回文数
     * 提示
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     * 示例 1：
     * 输入：x = 121
     * 输出：true
     * 示例 2：
     * 输入：x = -121
     * 输出：false
     * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3：
     * 输入：x = 10
     * 输出：false
     * 解释：从右向左读, 为 01 。因此它不是一个回文数。
     * 提示：
     * -231 <= x <= 231 - 1
     */
    public static boolean isPalindrome(int x) {
        // 1、偷懒^_^
        StringBuilder sbd = new StringBuilder(Integer.toString(x));
        return sbd.toString().equals(sbd.reverse().toString());
    }

    public static boolean isPalindrome1(int x) {
        // 2、双指针
        String str = Integer.toString(x);
        int left = 0, right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        // 3、数学
        if (x < 0) {
            return false;
        }
        int cur = 0;
        int num = x;
        while (num > 0) {
            // 从末尾开始计算, 翻倍->加余
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(101));
        System.out.println(isPalindrome(-101));

        System.out.println(isPalindrome1(101));
        System.out.println(isPalindrome1(-101));

        System.out.println(isPalindrome2(101));
        System.out.println(isPalindrome2(-101));
    }
}
