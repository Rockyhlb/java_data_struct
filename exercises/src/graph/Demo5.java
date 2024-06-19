package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: graph
 * @CreateTime : 2024/6/19 8:34
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo5 {
    /**
     * 207. 课程表
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
     * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * 示例 1：
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     * 提示：
     * 1 <= numCourses <= 2000
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * prerequisites[i] 中的所有课程对 互不相同
     */
    private static List<List<Integer>> edges; // 构建图
    private static int[] visited; // 记录已经学习过的课程
    private static boolean valid = true;

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
        *【拓扑排序】：是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。
        * 且该序列必须满足下面两个条件：
        * 1、每个顶点出现且只出现一次。
        * 2、若存在一条从顶点 A 到顶点 B 的路径，那么在序列中顶点 A 出现在顶点 B 的前面。
        * 注：拓扑排序只存在于有向无环图中，并且一个有向无环图的拓扑排序结果不止一种
        * 因此本题可以建模成一个求拓扑排序的问题，求出该题是否存在拓扑排序，就可以确定是否有一种符合课程要求的学习路线
        * */
        edges = new ArrayList<>(); // 初始化 edges
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 初始化visited
        visited = new int[numCourses];
        // 构建图
        for (int[] require : prerequisites) {
            edges.get(require[1]).add(require[0]);
        }
        // 深搜 ，时间复杂度：O(n+m),空间复杂度：O(n+m)
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                // 当前课程未被搜索过时，进行深搜
                dfs(i);
            }
        }
        return valid;
    }

    private static void dfs(int course) {
        // 对于图中的任意一个节点，我们有三种状态：【未搜索】【正在搜索】【已完成搜索】
        visited[course] = 1;  // 将状态标记为正在搜索
        for (int edge : edges.get(course)) {
            if (visited[edge] == 0) {
                // 当前搜索节点的邻边未被搜索过时，继续递归，当valid被标记为false时打断递归
                dfs(edge);
                if (!valid) {
                    return;
                }
            } else if (visited[edge] == 1) {
                // 临边节点也处于【正在搜索】中时，构成环，返回false
                valid = false;
                return;
            }
        }
        // 当前节点【已完成搜索】
        visited[course] = 2;
    }

    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        // 广搜，时间复杂度：O(n+m),空间复杂度：O(n+m)
        List<List<Integer>> edges = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        // 初始化 edges
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 构建图
        for (int[] require : prerequisites) {
            edges.get(require[1]).add(require[0]);
            // 记录入度
            inDegree[require[0]]++;
        }
        // 广搜
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 初始化时将所有入度为 0 的节点都放入队列中
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            visited++;
            int point = queue.poll();
            // 移除 point 的所有出边，也就是将 point 的所有相邻节点的入度减少 1.
            for (int edge : edges.get(point)) {
                inDegree[edge]--;
                // 如果某个相邻节点 edge 的入度变为 0，那么将 edge 放入队列中.
                if (inDegree[edge] == 0) {
                    queue.offer(edge);
                }
            }
        }
        return visited == numCourses;
    }

    public static void main(String[] args) {
        // numCourses = 2, prerequisites = [[1,0],[0,1]]
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };
        System.out.println(canFinish(2, prerequisites)); // false
        System.out.println(canFinish1(2, prerequisites)); // false
    }
}