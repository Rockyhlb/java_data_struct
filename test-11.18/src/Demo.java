
import java.util.*;

/**
 * @BelongsProject: test-11.18
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-19 12:36
 * @Description: Leetcode --> Map、Set
 * @Author: code_hlb
 */
public class Demo {
    /**
     * 136. 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * 思路：遍历数组，将每个数组插入到集合Set中，若集合中已经存在则移除
     */
    // 方法1：Set
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        // return set.iterator().next();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
        }
        return -1;
    }
    // 方法2：异或
    public int singleNumber1(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                /* nums = {1,2,1,3,3}
                 异或有交换律定理，可以将相同的数字先异或，因此两两异或就只剩0了，然后0再和最后的一个数字异或即可得到最终结果
                 a^a=0, 0^a=a, a^b=b^a
                 ans = 1^1^3^3^2 = 0^0^2 = 2
                 */
                ans = ans ^ nums[i];
            }
        }
        return ans;
    }

    /**
     * 138. 随机链表的复制
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
     * 复制链表中的指针都不应指向原链表中的节点。
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。
     */
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        // 创建一个装 老结点 和 新结点地址的 HashMap;
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        // 遍历老链表
        while (cur != null) {
            Node newNode = new Node(cur.val);
            // 老链表结点为key, 与之对应的新结点为value
            map.put(cur,newNode);
            cur = cur.next;
        }
        // 新结点建立完成后，重新让cur 回到 head;
        cur = head;
        while (cur != null) {
            // 根据 key 取出 value,将对应value的next 和 random地址串起来
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 返回新链表的头结点
        return map.get(head);
    }

    /**
     * 771. 宝石与石头
     *  给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。
     *  stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
     * 思路：将宝石作为一个HashSet,遍历石头数组，如果set当中包含石头中的元素，则count++;
     */
    public int numJewelsInStones(String jewels, String stones) {
        // TreeSet 底层是TreeMap, HashSet 底层是HashMap;一个是搜索树，一个是散列表
        // TreeSet时间复杂度为O(logN),HashSet 时间复杂度为O(1)，因此此处使用TreeSet 比 HashSet慢
        // TreeSet<Character> treeSet = new TreeSet<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            set.add(jewels.charAt(i));
        }
        /*for (char ch: jewels.toCharArray()) {
            set.add(ch);
        }*/
        int count = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (set.contains(stones.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    /**
     * 旧键盘上坏了几个键，于是在敲一段文字的时候，对应的字符就不会出现。
     * 现在给出应该输入的一段文字、以及实际被输入的文字，请你列出肯定坏掉的那些键。
     * 链接：https://www.nowcoder.com/questionTerminal/f88dafac00c8431fa363cd85a37c2d5e
     * 输入描述:
     * 输入在2行中分别给出应该输入的文字、以及实际被输入的文字。每段文字是不超过80个字符的串，由字母A-Z（包括大、小写）、数字0-9、
     * 以及下划线“_”（代表空格）组成。题目保证2个字符串均非空。
     * 输出描述:
     * 按照发现顺序，在一行中输出坏掉的键。其中英文字母只输出大写，每个坏键只输出一次。题目保证至少有1个坏键。
     *
     * 输入：
     * 实际输入：7_This_is_a_test
     * 实际输出：_hs_s_a_es
     * 输出坏键盘：7TI
     *
     * 思路：这道题还是和去重相关的，因此还是使用Set解决问题
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            func(s1,s2);
        }
    }
    public static void func(String s1,String s2) {
        Set<Character> set = new HashSet<>();
        for (char ch : s2.toUpperCase().toCharArray()) {
            // 将实际 输出字符添加入set中
            set.add(ch);
        }
        // set1用于将坏键去重
        Set<Character> set1 = new HashSet<>();
        // 将实际输入的字符与set当中的字符比较，若不存在于set中，说明该字符就是坏键
        for (char ch :s1.toUpperCase().toCharArray()) {
            if (!set.contains(ch) && !set1.contains(ch)) {
                System.out.print(ch);
                set1.add(ch);
            }
        }
    }

    /**
     * 692. 前K个高频单词
     * 给定一个单词列表 words 和一个整数 k ，返回前 k 个出现次数最多的单词。
     * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
     * 示例 1：
     * 输入: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * 输出: ["i", "love"]
     * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
     *     注意，按字母顺序 "i" 在 "love" 之前。
     * 思路：1、词频统计  2、优先级队列的topK问题
     */
    public List<String> topKFrequent(String[] words, int k) {
        // 1、词频统计
        Map<String,Integer> map = new HashMap<>();
        for (String s : words) {
            if (map.get(s) == null) {
                map.put(s, 1);
            } else {
                int value = map.get(s);
                map.put(s, value + 1);
            }
        }

        // 2、建立小根堆  因为默认不知道是以String 还是Integer 建立小根堆，因此需要通过匿名内部类传入一个比较器指定比较方式
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(
                new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue().compareTo(o2.getValue()) == 0) {
                    // 按照字母顺序建立大根堆
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue() - o2.getValue();
            }
        });

        // 3、通过map调整优先级队列
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            if (minHeap.size() < k) {
                // 建立大小为 k 的小根堆
                minHeap.offer(entry);
            }else {
                Map.Entry<String,Integer> top = minHeap.peek();
                // 当不同的单词有相同出现频率，按字典顺序排序
                // 如果当前频率相同
                if (top.getValue().compareTo(entry.getValue()) == 0) {
                    // 字母顺序小的进来  --> 因为要按字典顺序排序，因此应该将字母顺序小的进来
                    if (top.getKey().compareTo(entry.getKey()) > 0) {
                        // 出队
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }else {
                    // 让大的元素进来
                    if (top.getValue().compareTo(entry.getValue()) < 0) {
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
        }

        // 接收小根堆返回的元素
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            Map.Entry<String,Integer> top = minHeap.poll();
            ret.add(top.getKey());
        }
        Collections.reverse(ret);
        return ret;
    }
}
