package arrays;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/6/7 9:14
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo20 {
    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 示例 1：
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     * 提示：
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     */
    public static String longestCommonPrefix(String[] strs) {
        // 暴力，纵向扫描
        // 最长公共前缀由短字符串确定
        StringBuilder sbd = new StringBuilder();
        String tmp = strs[0];  // 取第一个字符串为基准
        int index = 0;
        while (index < tmp.length()) {
            boolean isSame = true;
            for (int i = 1; i < strs.length; i++) {
                if (index >= strs[i].length()) {
                    // 遍历到比当前基准更短的字符串
                    isSame = false;
                    break;
                }
                if (strs[i].charAt(index) != tmp.charAt(index)) {
                    isSame = false;
                }
            }
            if (isSame) {
                sbd.append(tmp.charAt(index++));
            } else {
                break;
            }
        }
        return sbd.toString();
    }

    public static String longestCommonPrefix1(String[] strs) {
        // 排序，对比首尾元素
        StringBuilder sbd = new StringBuilder();
        // Arrays.sort(strs);
        shellSort(strs);
        String head = strs[0];
        String tail = strs[strs.length - 1];
        int minLen = Math.min(head.length(), tail.length());
        int index = 0;
        while (index < minLen) {
            if (head.charAt(index) != tail.charAt(index)) {
                break;
            }
            sbd.append(head.charAt(index++));
        }
        return sbd.toString();
    }

    private static void shellSort(String[] strs) {
        int gap = strs.length;
        // 希尔排序
        while (gap > 1) {
            gap >>= 1;
            shell(strs, gap);
        }
    }

    private static void shell(String[] strs, int gap) {
        for (int i = gap; i < strs.length; i++) {
            String tmp = strs[i];
            int j = i - gap;
            for (; j >= 0; j -= gap) {
                if (strs[j].length() > tmp.length()) {
                    strs[j + gap] = strs[j];
                } else {
                    break;
                }
            }
            strs[j + gap] = tmp;
        }
    }

    public static void main(String[] args) {
        // 输入：strs = ["flower","flow","flight"];
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix1(strs));
    }
}