package dp;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dp
 * @CreateTime : 2024/6/12 8:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo10 {
    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * 示例 1：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     * 提示：
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     */
    public static int minDistance(String word1, String word2) {
        /**
         * 题目给出我们可以对每个单词进行三种操作，分别为插入一个字符，删除一个字符，替换一个字符，
         * 因此总共有2*3种操作，又由于对A进行插入操作和对B进行删除操作等价，对A进行删除操作和对B进行插入操作等价，对A替换一个字符和对B替换一个字符等价，
         * 因此实际上可以抽象成三种操作，子问题也就出来了：
         * 1、对A进行插入操作
         * 2、对B进行插入操作
         * 3、对A进行替换操作
         * 边界：当A为空时，编辑距离即为len(B)，当B为空时，编辑距离即为len(A);
         * 假设dp[i][j] 表示 A 的前i个字母和 B 的前j个字母之间的编辑距离
         * 则dp[i-1][j]: A的前i-1个字符和B的前j个字符的编辑距离，那么对于A的第i个字符，我们在B的末尾添加了相同字符，dp[i][j]最小可以为dp[i-1][j] + 1
         * 则dp[i][j-1]: A的前i个字符和B的前j-1个字符的编辑距离，那么对于B的第i个字符，我们在A的末尾添加了相同字符，dp[i][j]最小可以为dp[i][j-1] + 1
         * 则dp[i-1][j-1]: A的前i-1个字符和B的前j-1个字符的编辑距离，那么对于B的第j个字符，我们修改A的第i个字符使其相同，
         *   则dp[i][j]最小可以为dp[i-1][j-1] + 1，但是当A(i)==B(j)时，实际上不需要进行替换操作，dp[i][j]最小就可以为dp[i-1][j-1];
         * 最终状态转移方程为：
         * 1、当A(i-1) != B(j-1) 时：
         * dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1)
         *          = 1 + min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])
         * 2、当A(i-1) == B(j-1) 时：
         * dp[i][j] = 1 + min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1] - 1)
         * 3、对于边界情况：
         * dp[i][0] = i;
         * dp[0][j] = j;
         */
        int n = word1.length();
        int m = word2.length();
        if (n * m == 0) {
            // 当有一个输入为空串时
            return n + m;
        }
        int[][] dp = new int[n + 1][m + 1];
        // 初始化边界
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        // 开始规划
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int first = dp[i][j - 1] + 1;
                int second = dp[i - 1][j] + 1;
                int third = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    third += 1;
                }
                dp[i][j] = Math.min(first, Math.min(second, third));
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        // 输入：word1 = "horse", word2 = "ros"
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }
}