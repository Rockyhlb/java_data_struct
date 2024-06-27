package dictionary_tree;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dictionary_tree
 * @CreateTime : 2024/6/27 11:03
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 212. 单词搜索 II
     * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
     * 示例 1：
     * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
     * 输出：["eat","oath"]
     * 示例 2：
     * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
     * 输出：[]
     * 提示：
     * m == board.length
     * n == board[i].length
     * 1 <= m, n <= 12
     * board[i][j] 是一个小写英文字母
     * 1 <= words.length <= 3 * 104
     * 1 <= words[i].length <= 10
     * words[i] 由小写英文字母组成
     * words 中的所有字符串互不相同
     */
    static class Trie {
        private String word;  // 叶子节点存储字符串，默认为""
        private Map<Character, Trie> children; // 存储字符到节点的映射

        public Trie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        public void insert(String word) {
            Trie cur = this;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                char ch = word.charAt(i);
                if (!cur.children.containsKey(ch)) {
                    cur.children.put(ch, new Trie());
                }
                cur = cur.children.get(ch);
            }
            // 到达叶子节点，存储字符串
            cur.word = word;
        }
    }

    // 定义board的搜索方向
    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<String> findWords(char[][] board, String[] words) {
        /**
         * 字典树 + "回溯"：
         */
        Trie root = new Trie();
        // 将word[]插入到字典树中
        for (String word : words) {
            root.insert(word);
        }
        // 由于不同的路径可能会检索出不同的字符，因此需要Set进行去重
        Set<String> set = new HashSet<>();
        // 遍历所有单元格
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 进行深搜
                dfs(root, board, i, j, set);
            }
        }
        return new ArrayList<>(set);
    }

    private static void dfs(Trie cur, char[][] board, int row, int column, Set<String> ans) {
        char ch = board[row][column];
        // 剪枝：当前节点不包含目标元素，直接返回
        if (!cur.children.containsKey(ch)) {
            return;
        }
        // 往下跳转
        cur = cur.children.get(ch);
        // 当前是叶子节点，将字符串插入ans
        if (!"".equals(cur.word)) {
            ans.add(cur.word);
        }
        // 搜索邻边
        board[row][column] = '#';  // 将当前单元格记录为‘#’，避免再次经过该单元格
        for (int[] direct : directions) {
            int row1 = row + direct[0];
            int column1 = column + direct[1];
            // 如果没有越界则继续深搜
            if (row1 >= 0 && row1 < board.length && column1 >= 0 && column1 < board[0].length) {
                dfs(cur, board, row1, column1, ans);
            }
        }
        // 回溯
        board[row][column] = ch;
    }

    public static void main(String[] args) {
        // 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], 
        // words = ["oath","pea","eat","rain"]
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println(findWords(board, words));  // [oath, eat]
    }
}
