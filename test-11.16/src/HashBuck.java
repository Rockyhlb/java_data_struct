
/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 08:46
 * @Description: 哈希表(HashMap)的实现
 * @Author: code_hlb
 */
public class HashBuck {
    /**
     * 哈希表： 不经过任何比较，一次直接从表中得到要搜索的元素，通过哈希函数使元素的存储位置与它的关键码之间存在一一映射的关系
     * 哈希方法又称散列方法，哈希方法中使用的转换函数成为哈希(散列)函数，构建出的结构成为哈希表(散列列表)
     * 冲突：由于哈希底层数组的容量往往小于实际要存储的关键字的数量，依次冲突的发生是必然的
     * 如何避免冲突：
     *   1、合理的哈希函数：
     *    直接定制法：取关键字的线性函数为散列地址：Hash(key) = A * Key + B,适合查找比较小且连续的情况
     *    除留余数法：设散列表的长度为 m,取一个不大于m,但最接近或者等于m的质数p作为除数，Hash(key) = key % p (p<=m)
     *   2、降低散列表的负载因子：α = 填入表中的元素个数 / 散列表的长度；负载因子越高，冲突率越高，因此一般控制在0.5
     *                          可以设置负载因子达到一定值时就通过扩容降低负载因子
     * 解决哈希冲突：
     *   1、闭散列(开放地址法)：将key存放到冲突位置的下一个空位置去，有线性探测法(会将产生冲突的数据堆积在一起) 和
     *                         二次探测法：找下一个空位置的方法为 H(i) = [H(0) + i^2] % m;
     *   2、开散列(链地址法或开链法)：对具有相同散列地址的关键码归于同一子集合每一个子集合成为一个桶，
     *                              各个桶中的元素通过单链表连接起来，再将各链表的头节点存储在哈希表中，数组 + 链表的结构
     *                              当链表长度超过8 且 数组长度超过64时，采用 数组 + 红黑树的结构
     *                              在 JDK1.8之前链表采用头插法，1.8开始采用尾插法
     *   3、HashMap 增删查改的时间复杂度都是O(1),是典型的牺牲空间节省时间的例子
     */
    static class Node {
        public int key;
        public int val;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node[] arrNode;
    public int usedSize;

    public HashBuck() {
        arrNode = new Node[10];
    }

    // 插入键值对，key-value
    public void put(int key,int val) {
        // 求出散列地址
        int index = key % arrNode.length;
        Node cur = arrNode[index];
        while (cur != null) {
            // 当链表中已经存在当前key时
            if (cur.key == key) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }
        // key 没有重复
        Node newNode = new Node(key, val);
        // 头插法
        newNode.next = arrNode[index];
        arrNode[index] = newNode;
        usedSize++;

        double loadFactor = loadFactor();
        if (loadFactor > 0.75) {
            reSize();
        }
    }

    // 根据 key 取出 value
    public int get(int key) {
        int index = key % arrNode.length;
        Node cur = arrNode[index];
        while (cur != null) {
            if (cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    // 扩容哈希表
    private void reSize() {
        Node[] tmpArr = new Node[2 * arrNode.length];
        // 遍历原来的数组 将所有的元素 重新哈希 到新的数组当中
        for (Node node : arrNode) {
            Node cur = node;
            while (cur != null) {
                int index = cur.key % tmpArr.length;
                Node curNext = cur.next;
                // 将原列表元素 头插 到新列表中
                cur.next = tmpArr[index];
                tmpArr[index] = cur;
                cur = curNext;
            }
        }
        arrNode = tmpArr;
    }

    // 计算当前负载因子，当负载因子过大时应该扩容数组，并且扩容数组时不能只扩容数组，还要将之前存储的元素重新哈希
    private double loadFactor() {
        return usedSize * 1.0 / arrNode.length;
    }
}
