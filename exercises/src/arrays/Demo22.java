package arrays;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/7 21:18
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo22 {
    /**
     * 6. Z 字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * 请你实现这个将字符串进行指定行数变换的函数：
     * string convert(string s, int numRows);
     * 示例 1：
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 示例 3：
     * 输入：s = "A", numRows = 1
     * 输出："A"
     * 提示：
     * 1 <= s.length <= 1000
     * s 由英文字母（小写和大写）、',' 和 '.' 组成
     * 1 <= numRows <= 1000
     */
    public static String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        // 模拟行索引的变化
        List<StringBuilder> rows = new ArrayList<>(numRows);
        // 初始化行
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // 遍历字符串，插入字符
        int i = 0; // 控制行
        int flag = -1; // 通过 i += flag, 实现i的向上/向下移动
        for (char ch : s.toCharArray()) {
            rows.get(i).append(ch);
            if (i == 0 || i == numRows - 1) {
                // 当前i处于边界时，通过更新 flag 来设置控制移动方向
                flag = -flag;
            }
            i += flag;
        }
        // 遍历行索引，返回最终结果
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sbd : rows) {
            ans.append(sbd);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        // 输入：s = "PAYPALISHIRING", numRows = 3
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3)); // PAHNAPLSIIGYIR
    }
}