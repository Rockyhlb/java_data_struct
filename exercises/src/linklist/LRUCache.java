package linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/5/8 22:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class LRUCache {
    /**
     * 146. LRU 缓存
     * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
     * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * <p>
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     */
    private static class DLinkNode {
        int key;
        int value;
        DLinkNode prev;  // 前驱
        DLinkNode next;  // 后继

        public DLinkNode() {
        }

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final DLinkNode dummy = new DLinkNode(-1, -1);  // 哨兵节点
    private final Map<Integer, DLinkNode> KeyToDLinkNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        // 获取节点
        DLinkNode DLinkNode = getDLinkNode(key);
        return DLinkNode == null ? -1 : DLinkNode.value;
    }

    public void put(int key, int value) {
        DLinkNode DLinkNode = getDLinkNode(key);
        if (DLinkNode != null) {
            // 已经含有该节点，更新value
            DLinkNode.value = value;
            return;
        }
        // 插入新节点
        DLinkNode = new DLinkNode(key, value);
        // 将新节点放入映射中
        KeyToDLinkNode.put(key, DLinkNode);
        // 将头节点置于链表头
        pushFront(DLinkNode);
        // 数量超过 capacity ，去除尾节点
        if (KeyToDLinkNode.size() > capacity) {
            DLinkNode tail = dummy.prev;
            // 消除映射
            KeyToDLinkNode.remove(tail.key);
            // 移除链表节点
            remove(tail);
        }
    }

    private DLinkNode getDLinkNode(int key) {
        // 当映射中不包含当前节点时
        if (!KeyToDLinkNode.containsKey(key)) {
            return null;
        }
        // 根据 key 获取该节点
        DLinkNode DLinkNode = KeyToDLinkNode.get(key);
        // 将该节点从原链表中删除
        remove(DLinkNode);
        // 将该节点头插到顶端
        pushFront(DLinkNode);
        return DLinkNode;
    }

    private void remove(DLinkNode DLinkNode) {
        // 删除 双向链表 的节点
        DLinkNode.next.prev = DLinkNode.prev;
        DLinkNode.prev.next = DLinkNode.next;
    }

    private void pushFront(DLinkNode DLinkNode) {
        // 通过哨兵节点(即带头双向链表的头)进行头插
        DLinkNode.prev = dummy;
        DLinkNode.next = dummy.next;
        DLinkNode.prev.next = DLinkNode;
        DLinkNode.next.prev = DLinkNode;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}
