package graph;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/6/16 15:39
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 399. 除法求值
     * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
     * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
     * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
     * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
     * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
     * 示例 1：
     * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
     * 解释：
     * 条件：a / b = 2.0, b / c = 3.0
     * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
     * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
     * 注意：x 是未定义的 => -1.0
     * 示例 2：
     * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
     * 输出：[3.75000,0.40000,5.00000,0.20000]
     * 示例 3：
     * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
     * 输出：[0.50000,2.00000,-1.00000,-1.00000]
     * 提示：
     * 1 <= equations.length <= 20
     * equations[i].length == 2
     * 1 <= Ai.length, Bi.length <= 5
     * values.length == equations.length
     * 0.0 < values[i] <= 20.0
     * 1 <= queries.length <= 20
     * queries[i].length == 2
     * 1 <= Cj.length, Dj.length <= 5
     * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 对问题建模成一张图：
        // 图中的点为式中变量，边的权值为两个变量的比值，需要对图中任意两点求出其权值
        // 首先遍历 equations 数组，找出其中所有不同的字符串，并通过哈希表将每个不同的字符串映射成整数
        int nvars = 0;  // 标记变量的数量
        Map<String, Integer> variables = new HashMap<>();
        int n = equations.size();
        for (List<String> var : equations) {
            if (!variables.containsKey(var.get(0))) {
                variables.put(var.get(0), nvars++);
            }
            if (!variables.containsKey(var.get(1))) {
                variables.put(var.get(1), nvars++);
            }
        }
        // 对于每个点，存储其直接连接到的所有点及对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            // 初始化
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }
        int queryCounts = queries.size();
        // 结果数组
        double[] result = new double[queryCounts];
        for (int i = 0; i < queryCounts; i++) {
            List<String> query = queries.get(i);
            double weight = -1.0;
            // 当前待求变量在图中时
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int qa = variables.get(query.get(0));
                int qb = variables.get(query.get(1));
                if (qa == qb) {
                    // qa / qa = 1.0
                    weight = 1.0;
                } else {
                    // 广搜, 对于任何一个查询，可以从起点出发，通过广度优先搜索的方式，不断更新起点与当前点之间的路径长度，直到搜索到终点为止
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(qa);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);
                    ratios[qa] = 1.0;
                    while (!points.isEmpty() && ratios[qb] < 0) {
                        int x = points.poll();
                        for (Pair Pair : edges[x]) {
                            int y = Pair.index;
                            double value = Pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * value;
                                points.offer(y);
                            }
                        }
                    }
                    weight = ratios[qb];
                }
            }
            result[i] = weight;
        }
        return result;
    }

    static class Pair {
        int index;
        double value;

        public Pair() {
        }

        public Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        // 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        // 输出：[6.0, 0.5, -1.0, 1.0, -1.0]
        System.out.println(Arrays.toString(calcEquation(equations, values, queries)));
    }
}
