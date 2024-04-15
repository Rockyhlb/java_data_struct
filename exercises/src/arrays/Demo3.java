package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/15 15:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
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
        StringBuilder sbd = new StringBuilder(str);
        return sbd.reverse().toString();
    }

    public static void main(String[] args) {
        String str = "I am a student";
        System.out.println(reverse(str));
    }
}
