package graph;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/6/20 19:41
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo6 {
    /**
     * 210. 课程表 II
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，
     * 其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
     * 如果不可能完成所有课程，返回 一个空数组 。
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：[0,1]
     * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 示例 2：
     * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * 输出：[0,2,1,3]
     * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     * 示例 3：
     * 输入：numCourses = 1, prerequisites = []
     * 输出：[0]
     * 提示：
     * 1 <= numCourses <= 2000
     * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * ai != bi
     * 所有[ai, bi] 互不相同
     */
    private static List<List<Integer>> edges;
    private static int[] visited; // 记录已遍历节点
    private static int[] ans; // 结果数组
    private static int index;  // 记录ans下标，模拟栈
    private static boolean valid = true;  // 标记是否构成环

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // 拓扑排序
        edges = new ArrayList<>();
        // 初始化
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        index = numCourses - 1; // 模拟栈
        visited = new int[numCourses];
        ans = new int[numCourses];
        // 构建图
        for (int[] require : prerequisites) {
            edges.get(require[1]).add(require[0]);
        }
        // 遍历图,深搜
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid ? ans : new int[0];
    }

    private static void dfs(int point) {
        // 将节点标记为【搜索中】
        visited[point] = 1;
        for (int edge : edges.get(point)) {
            if (visited[edge] == 0) {
                dfs(edge);
                if (!valid) {
                    return;
                }
            } else if (visited[edge] == 1) {
                // 发现环
                valid = false;
                return;
            }
        }
        // 标记为已遍历过
        visited[point] = 2;
        // 将遍历节点入栈
        ans[index--] = point;
    }

    public static int[] findOrder1(int numCourses, int[][] prerequisites) {
        // 广搜
        List<List<Integer>> edges = new ArrayList<>();
        int[] ans = new int[numCourses];
        int index = 0;
        int[] inDegree = new int[numCourses]; // 记录入度
        // 初始化
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 构建图
        for (int[] require : prerequisites) {
            edges.get(require[1]).add(require[0]);
            // 入度加1
            inDegree[require[0]]++;
        }
        // 广搜
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int point = queue.poll();
            ans[index++] = point;
            // 将point的相邻边 入度 都减1
            for (int edge : edges.get(point)) {
                inDegree[edge]--;
                // 如果某个相邻节点入度为0，则将该节点加入队列，后续访问队列时即可将该节点插入结果数组中
                if (inDegree[edge] == 0) {
                    queue.offer(edge);
                }
            }
        }
        return index == numCourses ? ans : new int[0];
    }

    public static void main(String[] args) {
        // numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };
        // 输出：[0, 2, 1, 3]
        System.out.println(Arrays.toString(findOrder(4, prerequisites)));
        System.out.println(Arrays.toString(findOrder1(4, prerequisites)));
    }
}
