package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: hashtable
 * @CreateTime : 2024/5/6 11:03
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 202. 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」 定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。然后重复这个过程
     * 直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     * <p>
     * 示例 1：
     * 输入：n = 19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * <p>
     * 示例 2：
     * 输入：n = 2
     * 输出：false
     */
    public static boolean isHappy(int n) {
        // 将问题抽象为检测一个链表是否有环，当有环时说明这个数一定不是快乐数，因此可以采用弗洛伊德循环查找算法，
        // 当快指针到达1时，返回true
        int slow = n;
        int fast = getNext(n);
        while (1 != fast && fast != slow) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    public static boolean isHappy1(int n) {
        // 通过set进行过滤，当n==1 或 set已经包含n时退出循环，并判断当前n是否为1
        Set<Integer> set = new HashSet<>();
        // 当set当中已经包含当前值时，说明结果集进入循环
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private static int getNext(int num) {
        int total = 0;
        // 通过循环获取每个位数上的平方和
        while (num > 0) {
            int tmp = num % 10;
            num /= 10;
            total += tmp * tmp;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(2));

        System.out.println(isHappy1(19));
        System.out.println(isHappy1(2));
    }
}