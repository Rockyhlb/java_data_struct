package arrays;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/6 19:38
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo17 {
    /**
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
     * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
     * 这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     * 示例 1:
     * 输入: s = "III"
     * 输出: 3
     * 示例 2:
     * 输入: s = "IV"
     * 输出: 4
     * 示例 3:
     * 输入: s = "IX"
     * 输出: 9
     * 示例 4:
     * 输入: s = "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     * 输入: s = "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * 提示：
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     */
    public static int romanToInt(String s) {
        // HashMap存储键值对
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        // 罗马数字的转换只涉及加法/减法，初始化为0
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            Character nextch = ' ';
            if (i < s.length() - 1) {
                nextch = s.charAt(i + 1);
            }
            if (ch == 'I') {
                if (nextch == 'V' || nextch == 'X') {
                    ans += map.get(nextch) - map.get(ch);
                    i++;
                } else {
                    ans += map.get(ch);
                }
            } else if (ch == 'X') {
                if (nextch == 'L' || nextch == 'C') {
                    ans += map.get(nextch) - map.get(ch);
                    i++;
                } else {
                    ans += map.get(ch);
                }
            } else if (ch == 'C') {
                if (nextch == 'D' || nextch == 'M') {
                    ans += map.get(nextch) - map.get(ch);
                    i++;
                } else {
                    ans += map.get(ch);
                }
            } else {
                ans += map.get(ch);
            }
        }
        return ans;
    }

    public static int romanToInt1(String s) {
        // 小数据的匹配尽量减少使用HashMap
        int ans = 0;
        // 罗马数字的转换本质上其实只有两种规则
        // 1、当前数字<=上一个数字时，例如：VI，则只需加上 上一个数字
        // 2、当前数字 >上一个数字时，例如：IV，则需要减去 上一个数字
        // 采用双指针进行遍历
        int left = 0;
        int right = 1;
        while (right < s.length()) {
            int pre = getValue(s.charAt(left++));
            int now = getValue(s.charAt(right++));
            /*if (now > pre) {
                ans -= pre;
            } else {
                ans += pre;
            }*/
            ans = now > pre ? ans - pre : ans + pre;
        }
        // 转换最后一个字符
        ans += getValue(s.charAt(right - 1));
        return ans;
    }

    private static int getValue(Character ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        String s = "III";
        String s1 = "IV";
        String s2 = "MCMXCIV";
        System.out.println(romanToInt(s)); // 3
        System.out.println(romanToInt(s1)); // 4
        System.out.println(romanToInt(s2)); // 1994

        System.out.println(romanToInt1(s)); // 3
        System.out.println(romanToInt1(s1)); // 4
        System.out.println(romanToInt1(s2)); // 1994
    }
}
