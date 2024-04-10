package HashTable;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: HashTable
 * @CreateTime : 2024/4/10 19:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 383. 赎金信
     * 给你两个字符串：ransomNote 和 magazine，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     * <p>
     * 示例 1：
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * <p>
     * 示例 2：
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        // 采用hash思想，声明一个装字母的数组(ransomNote 和 magazine 由小写英文字母组成)
        int[] array = new int[26];
        // 记录已知条件 magazine
        for (int i = 0; i < magazine.length(); i++) {
            array[magazine.charAt(i) - 'a']++;
        }
        // 遍历ransomNote
        for (int i = 0; i < ransomNote.length(); i++) {
            array[ransomNote.charAt(i) - 'a']--;
            if (array[ransomNote.charAt(i) - 'a'] < 0) {
                // 日志当中没有该字符
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b";
        String ransomNote1 = "aa", magazine1 = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
        System.out.println(canConstruct(ransomNote1, magazine1));
    }
}
