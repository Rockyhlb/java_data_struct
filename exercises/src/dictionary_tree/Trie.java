package dictionary_tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dictionary_tree
 * @CreateTime : 2024/6/25 9:19
 * @Description: TODO
 * @Author: code_hlb
 */
public class Trie {
    /**
     * 208. 实现 Trie (前缀树)
     * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
     * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
     * 请你实现 Trie 类：
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     * 示例：
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     * 提示：
     * 1 <= word.length, prefix.length <= 2000
     * word 和 prefix 仅由小写英文字母组成
     * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
     */
    private class TrieNode {
        private boolean isEnd;  // 记录是否是最后一个字母

        private TrieNode[] next; // 记录该字母的下一位字母所有可能的字母坐标

        public TrieNode() {
            isEnd = false;
            // 初始化26个字母
            next = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        int len = word.length();
        int c2i = 0;
        for (int i = 0; i < len; i++) {
            // 将字符转换成整型存储
            c2i = word.charAt(i) - 'a';
            if (cur.next[c2i] == null) {
                cur.next[c2i] = new TrieNode();
            }
            cur = cur.next[c2i];
        }
        // 到达字符串末尾
        cur.isEnd = true;
    }

    public boolean search(String word) {
        // 获取前缀匹配的最后一个字母节点
        TrieNode node = searchPrefix(word);
        // 只有当返回不为空，且遍历到的字母节点已经是末尾节点时，才能说明给定字母包含在字典树中
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        // 只要前缀匹配存在，则返回true
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode cur = root;
        int len = prefix.length(), c2i = 0;
        for (int i = 0; i < len; i++) {
            c2i = prefix.charAt(i) - 'a';
            if (cur.next[c2i] == null) {
                // 没有找到前缀
                return null;
            }
            cur = cur.next[c2i];
        }
        // 找到前缀
        return cur;
    }
}
