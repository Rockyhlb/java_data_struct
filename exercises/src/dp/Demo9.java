package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/6/11 15:11
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo9 {
    /**
     * 97. 交错字符串
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空子字符串：
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 注意：a + b 意味着字符串 a 和 b 连接。
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * 示例 3：
     * 输入：s1 = "", s2 = "", s3 = ""
     * 输出：true
     * 提示：
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1、s2、和 s3 都由小写英文字母组成
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        // 方法1：双指针,只能通过 92/106
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        int index1 = 0; // s1
        int index2 = 0; // s2
        for (int i = 0; i < s3.length(); i++) {
            char ch3 = s3.charAt(i);
            char ch1 = ' ';
            char ch2 = ' ';
            if (index1 < s1.length()) {
                ch1 = s1.charAt(index1);
            }
            if (index2 < s2.length()) {
                ch2 = s2.charAt(index2);
            }
            if (ch3 != ch1 && ch3 != ch2) {
                // 都不相等时
                return false;
            } else if (ch3 != ch2) {
                // 等于s1头元素
                index1++;
            } else {
                // 等于s2头元素
                index2++;
            }
        }
        return true;
    }

    public static boolean isInterleave1(String s1, String s2, String s3) {
        // 方法2：动态规划, 时间复杂度：O(mn) 空间复杂度：O(mn)
        // 定义子问题：f[i,j] 表示s1的前i个元素 和 s2的前j个元素 交错组成s3的前i+j个元素
        // 递推关系：当前i 和 i+j 的元素相等时，f[i,j]依赖于f[i-1][j]
        //         当前j 和 i+j 的元素相等时，f[i,j]依赖于f[i][j-1]
        // 得出状态转移方程：f[i][j] = ( s1(i-1)==s3(i+j-1) && f[i-1][j] ) || ( s2(j-1)==s3(i+j-1) && f[i][j-1] )
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (len != m + n) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) {
                    // 当前s1(i-1)==s3(i+j-1)
                    // |= 原因：dp[i][j]默认为false,如果不加 |=,
                    // 若经过本轮判断修改为true之后，下一次s2的语句可能又会将其修改为false,不利于后续的运算
                    if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                    // dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
                if (j > 0) {
                    // 当前s2(j-1)==s3(i+j-1)
                    if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                        dp[i][j] |= dp[i][j - 1];
                    }
                    // dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[m][n];
    }

    public static boolean isInterleave2(String s1, String s2, String s3) {
        // 方法3：优化动态规划, 时间复杂度：O(mn) 空间复杂度：O(n)
        // 通过滚动数组优化上述动规(优化空间复杂度)
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (len != m + n) {
            return false;
        }
        // 由于数组 dp 的第 i 行只和第 i−1 行相关，所以我们可以用滚动数组优化这个动态规划，遍历到i时就抛弃掉i-1之前的数据
        // 因此空间复杂度可以变成 O(n)
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) {
                    dp[j] = dp[j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1));
                }
                if (j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        System.out.println(isInterleave(s1, s2, s3));  // true
        System.out.println(isInterleave1(s1, s2, s3));  // true
        System.out.println(isInterleave2(s1, s2, s3));  // true

        // 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        String s11 = "aabcc";
        String s22 = "dbbca";
        String s33 = "aadbbbaccc";
        System.out.println(isInterleave(s11, s22, s33));  // false
        System.out.println(isInterleave1(s11, s22, s33));  // false
        System.out.println(isInterleave2(s11, s22, s33));  // false
    }
}