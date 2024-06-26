package dictionary_tree;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: dictionary_tree
 * @CreateTime : 2024/6/26 14:04
 * @Description: TODO
 * @Author: code_hlb
 */
public class WordDictionary {
    /**
     * 211. 添加与搜索单词 - 数据结构设计
     * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
     * 实现词典类 WordDictionary ：
     * WordDictionary() 初始化词典对象
     * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
     * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
     * 示例：
     * 输入：
     * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
     * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
     * 输出：
     * [null,null,null,null,false,true,true,true]
     * 解释：
     * WordDictionary wordDictionary = new WordDictionary();
     * wordDictionary.addWord("bad");
     * wordDictionary.addWord("dad");
     * wordDictionary.addWord("mad");
     * wordDictionary.search("pad"); // 返回 False
     * wordDictionary.search("bad"); // 返回 True
     * wordDictionary.search(".ad"); // 返回 True
     * wordDictionary.search("b.."); // 返回 True
     * 提示：
     * 1 <= word.length <= 25
     * addWord 中的 word 由小写英文字母组成
     * search 中的 word 由 '.' 或小写英文字母组成
     * 最多调用 104 次 addWord 和 search
     */
    private class DictionNode {
        private boolean isEnd;

        private DictionNode[] next;

        public DictionNode() {
            isEnd = false;
            // 26个字母
            next = new DictionNode[26];
        }
    }

    private DictionNode root;

    public WordDictionary() {
        this.root = new DictionNode();
    }

    public void addWord(String word) {
        DictionNode cur = root;
        int len = word.length();
        for (int i = 0; i < len; i++) {
            int c2i = word.charAt(i) - 'a';
            if (cur.next[c2i] == null) {
                cur.next[c2i] = new DictionNode();
            }
            cur = cur.next[c2i];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return searchHelp(root, word, 0);
    }

    private boolean searchHelp(DictionNode root, String word, int index) {
        DictionNode cur = root;
        int len = word.length();
        for (int i = index; i < len; i++) {
            char ch = word.charAt(i);
            int c2i = ch - 'a';
            if (ch == '.') {
                // 符号'.'实现模糊查询
                // 进行深度遍历
                boolean flag = false;
                for (int j = 0; j < 26; j++) {
                    if (cur.next[j] != null) {
                        // 利用 || 运算 “一真则真”的性质
                        flag = flag || searchHelp(cur.next[j], word, i + 1);
                    }
                }
                return flag;
            } else {
                // 字母
                if (cur.next[c2i] == null) {
                    return false;
                }
            }
            cur = cur.next[c2i];
        }
        return cur.isEnd;
    }
}
