
/**
 * @BelongsProject: test-20240404
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/4/4 23:02
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class demo {
    /**
     * 2182. 构造限制重复的字符串
     * 给你一个字符串 s 和一个整数 repeatLimit ，用 s 中的字符构造一个新字符串 repeatLimitedString ，
     * 使任何字母 连续 出现的次数都不超过 repeatLimit 次。你不必使用 s 中的全部字符。
     * 返回 字典序最大的 repeatLimitedString。
     * 如果在字符串 a 和 b 不同的第一个位置，字符串 a 中的字母在字母表中出现时间比字符串 b 对应的字母晚，
     * 则认为字符串 a 比字符串 b 字典序更大。如果字符串中前 min(a.length, b.length) 个字符都相同，那么较长的字符串字典序更大。
     * <p>
     * 示例 1：
     * 输入：s = "cczazcc", repeatLimit = 3
     * 输出："zzcccac"
     * 解释：使用 s 中的所有字符来构造 repeatLimitedString "zzcccac"。
     * 字母 'a' 连续出现至多 1 次。
     * 字母 'c' 连续出现至多 3 次。
     * 字母 'z' 连续出现至多 2 次。
     * 因此，没有字母连续出现超过 repeatLimit 次，字符串是一个有效的 repeatLimitedString 。
     * 该字符串是字典序最大的 repeatLimitedString ，所以返回 "zzcccac" 。
     * 注意，尽管 "zzcccca" 字典序更大，但字母 'c' 连续出现超过 3 次，所以它不是一个有效的 repeatLimitedString 。
     */
    public static String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[26];
        // 将字符根据ASCII码转换后存储到计数数组中
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder sbd = new StringBuilder();
        // 记录当前插入相同字母的数量，如果大于repeatLimit,则将j--并清空m
        int curCount = 0;
        // 循环遍历
        for (int i = 25 ,j = i-1; i >= 0 && j >= 0;) {
            if (count[i] == 0) {
                // 当计数数组为0时，说明当前字符已经填完，需要继续向前遍历, 令curCount=0,i--
                i--;
                curCount = 0;
            }else if (curCount < repeatLimit) {
                // 当前字符不为空，且数量没有超出限制时
                count[i]--;
                sbd.append((char) (i + 'a'));
                curCount++;
            }else if (i <= j || count[j] == 0) {
                // 当 i 走在 j 的前面 或者 count[j] 已经没有字符,此时需要将j往前遍历，以便找出下一个可添加的字符
                j--;
            }else {
                // 此时只剩下curCount 超出限制的情况, 应该将j的字符append，然后将curCount置0
                count[j]--;
                sbd.append((char) (j + 'a'));
                curCount = 0;
            }
        }
        return sbd.toString();
    }

    public static void main(String[] args) {
        String s = "cczazcc";
        int repeatLimit = 3;
        System.out.println(repeatLimitedString(s, repeatLimit));
    }
}
