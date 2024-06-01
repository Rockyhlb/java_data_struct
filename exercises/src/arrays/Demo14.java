package arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/9 14:34
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo14 {
    /**
     * 134. 加油站
     * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
     * 示例 1:
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int size = gas.length;
        for (int i = 0; i < size; i++) {
            // 记录当前起点的剩余油量
            int rest = gas[i] - cost[i];
            // 通过取模运算记录下一个加油站的位置
            int index = (i + 1) % size;
            while (rest > 0 && index != i) {
                rest += gas[index] - cost[index];
                index = (index + 1) % size; // 更新下一个加油站的地址
            }
            // 回到起点且油量大于0
            if (rest >= 0 && index == i) {
                return index;
            }
            // 遍历完毕，且都不满足到站条件
            if (index <= i) {
                return -1;
            }
            i = index - 1;
        }
        //  没有找到合法起点
        return -1;
    }

    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        // 贪心算法：局部最优，从而找出全局最优
        int curSum = 0;
        int totalSum = 0;
        // 记录可以跑完一圈的起点
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {
                curSum = 0;
                // 因为当前总油量依赖于前面总油量的累计，所以当 [0,i] 之间的curSum<0时，说明[0,i]这个区间都不能作为起始位置
                index = (i + 1) % gas.length;
            }
        }
        if (totalSum < 0) {
            return -1;
        }
        return index;
    }

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));

        int[] gas1 = {2, 3, 4};
        int[] cost1 = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas1, cost1));

        int[] gas2 = {5, 1, 2, 3, 4};
        int[] cost2 = {4, 4, 1, 5, 1};
        System.out.println(canCompleteCircuit1(gas2, cost2));
    }
}
