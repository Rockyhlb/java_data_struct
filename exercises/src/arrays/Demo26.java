package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/15 15:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo26 {
    /**
     * 17.
     * 字符逆序
     * 将一个字符串str的内容颠倒过来，并输出。
     * 如：输入“I am a student”，输出“tneduts a ma I”。
     * 保证字符串长度不超过100。
     * 输入描述：
     * 输入一个仅包含空格和英文字母的字符串str，长度不超过10000。
     */
    public static String reverse(String str) {
        // ^_^
        StringBuilder sbd = new StringBuilder(str);
        return sbd.reverse().toString();
    }

    public static String reverse1(String str) {
        StringBuilder sbd = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sbd.append(str.charAt(i));
        }
        return sbd.toString();
    }

    public static void main(String[] args) {
        String str = "I am a student";
        System.out.println(reverse(str));
        System.out.println(reverse1(str));
    }
}
