package graph;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/6/23 23:03
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo8 {
    /**
     * 433. 最小基因变化
     * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
     * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
     * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
     * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
     * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。
     * 如果无法完成此基因变化，返回 -1 。
     * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
     * 示例 1：
     * 输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
     * 输出：1
     * 示例 2：
     * 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
     * 输出：2
     * 示例 3：
     * 输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
     * 输出：3
     * 提示：
     * start.length == 8
     * end.length == 8
     * 0 <= bank.length <= 10
     * bank[i].length == 8
     * start、end 和 bank[i] 仅由字符 ['A', 'C', 'G', 'T'] 组成
     */
    public static int minMutation(String startGene, String endGene, String[] bank) {
        // 记录已经存在的基因序列记录
        Set<String> records = new HashSet<>(Arrays.asList(bank));
        // 记录已经遍历过的组合
        Set<String> visited = new HashSet<>();
        // 记录能修改的基因变换
        char[] keys = {'A', 'C', 'G', 'T'};
        if (startGene.equals(endGene)) {
            return 0;
        }
        if (!records.contains(endGene)) {
            // 目的基因不包含在基因库中，返回-1
            return -1;
        }
        // 广搜
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        visited.add(startGene);
        int steps = 1;
        while (!queue.isEmpty()) {
            // 遍历当前基因及其临边基因
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                // 遍历第j个字母
                for (int j = 0; j < 8; j++) {
                    // 遍历每一种变换
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != cur.charAt(j)) {
                            // 构造变换出的基因
                            StringBuilder sbd = new StringBuilder(cur);
                            sbd.setCharAt(j, keys[k]);
                            String next = sbd.toString();
                            if (!visited.contains(next) && records.contains(next)) {
                                // 到达目的基因
                                if (next.equals(endGene)) {
                                    return steps;
                                }
                                // 遍历临边变换
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String[] args) {
        // 输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println(minMutation(start, end, bank));  // 2
    }
}
