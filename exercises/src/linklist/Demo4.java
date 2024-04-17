package linklist;

import java.util.HashMap;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: linklist
 * @CreateTime : 2024/4/17 10:23
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 138. 随机链表的复制
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
     * 返回复制链表的头节点。
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     */
    public static ListNode copyRandomList(ListNode head) {
        HashMap<ListNode,ListNode> hash = new HashMap<>();
        ListNode cur = head;
        // 导入原链表
        while (cur != null) {
            hash.put(cur,new ListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 重定向到原来节点
            hash.get(cur).next = hash.get(cur.next);
            hash.get(cur).random = hash.get(cur.random);
            cur = cur.next;
        }
        return hash.get(head);
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode copy = copyRandomList(list1);
        System.out.println("Before: " + list1);
        System.out.println("After : " + copy);
    }
}
