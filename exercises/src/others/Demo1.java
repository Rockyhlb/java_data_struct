package others;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/22 8:33
 * @Description: 位运算
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 67. 二进制求和
     * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
     * 示例 1：
     * 输入:a = "11", b = "1"
     * 输出："100"
     * 示例 2：
     * 输入：a = "1010", b = "1011"
     * 输出："10101"
     * 提示：
     * 1 <= a.length, b.length <= 104
     * a 和 b 仅由字符 '0' 或 '1' 组成
     * 字符串如果不是 "0" ，就不含前导零
     */
    public static String addBinary(String a, String b) {
        // 方法1：模拟
        StringBuilder res = new StringBuilder();
        // 记录进位，满2进1
        int sum = 0;
        int n = Math.max(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            // 从末尾开始获取
            sum += i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            sum += i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;
            // 取余值
            res.append((char) (sum % 2 + '0'));
            // 将sum置为0||1
            sum /= 2;
        }
        if (sum > 0) {
            res.append('1');
        }
        return res.reverse().toString();
    }

    public static String addBinary1(String a, String b) {
        // 方法2：位运算，将两个字符串转整型后进行位运算
        // 局限性：
        // 1、如果字符串超过 33 位，不能转化为 Integer
        // 2、如果字符串超过 65 位，不能转化为 Long
        // 3、如果字符串超过 500000001 位，不能转化为 BigInteger
        int x = Integer.parseInt(a, 2); // 二进制转十进制
        int y = Integer.parseInt(b, 2);
        int res = 0, carry = 0;
        while (y != 0) {
            // Loop1: y = 00001011, x = 00001010, x^y = 00000001, (x&Y)<<1 = 00010100
            // Loop2: y = 00010100, x = 00000001, x^y = 00010101, (x&Y)<<1 = 00000000
            res = x ^ y;   // 异或完成二进制的无进位加法运算
            carry = (x & y) << 1;   // 与运算+左移完成进位的计算
            x = res;    // x 保存无进位相加的结果
            y = carry;  // y 保存进位
        }
        return Integer.toBinaryString(x);//十进制转二进制
    }

    public static void main(String[] args) {
        // 输入：a = "1010", b = "1011"
        String a = "1010";
        String b = "1011";
        System.out.println(addBinary(a, b)); // 10101
        System.out.println(addBinary1(a, b)); // 10101
    }
}
