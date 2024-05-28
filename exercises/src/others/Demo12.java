package others;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: others
 * @CreateTime : 2024/5/28 14:12
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo12 {
    /**
     * 149. 直线上最多的点数
     * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
     * 示例 1：
     * 输入：points = [[1,1],[2,2],[3,3]]
     * 输出：3
     * 示例 2：
     * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * 输出：4
     * 提示：
     * 1 <= points.length <= 300
     * points[i].length == 2
     * -104 <= xi, yi <= 104
     * points 中的所有点 互不相同
     */
    public static int maxPoints(int[][] points) {
        /**
         * 两点确定一条直线，如果两直线斜率相等，则说明这两条线要么平行，要么重合，
         * 此处只需枚举三个点，因此只需判断是否重合即可
         * 假设现有a(Ax,Ay) b(Bx,By) c(Cx,Cy) 三个点, 则要使其在同一条直线上只能:
         * (Cy - By) / (Cx - Bx) = (By - Ay) / (Bx - Ax);
         * 由于除法存在精度缺失的问题，因此可以转换为：
         * (Cy - By) * (Bx - Ax) = (By - Ay) * (Cx - Bx);
         */
        int res = 1;
        // 方法1：暴力枚举，时间复杂度：O(n^3)  空间复杂度：O(1)
        for (int i = 0; i < points.length; i++) {
            // 确定第一个点
            int[] a = points[i];
            for (int j = i + 1; j < points.length; j++) {
                // 确定第二个点
                int[] b = points[j];
                int count = 2;  // 记录初始坐标点数量
                for (int k = j + 1; k < points.length; k++) {
                    // 确定第三个点
                    int[] c = points[k];
                    int slope1 = (c[1] - b[1]) * (b[0] - a[0]);
                    int slope2 = (b[1] - a[1]) * (c[0] - b[0]);
                    if (slope1 == slope2) {
                        count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static int maxPoints1(int[][] points) {
        // 方法2：枚举+Hash表统计，时间复杂度：O(n^2*logn)  空间复杂度：O(n)
        int res = 1;
        for (int i = 0; i < points.length; i++) {
            // 记录当前节点为基准 所处直线的 坐标点数量
            int max = 0;
            // 利用Map记录基于第一个点所产生的斜率
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                // y = kx, k = Δy/Δx = b/a
                int a = x1 - x2, b = y1 - y2;
                // 求出最大公因数，避免了后续浮点数除法，也就避免了精度问题
                int divisor = gcd(a, b);
                // 构造key值
                String key = (a / divisor) + "_" + (b / divisor);
                // 插入斜率，同时更新节点数量
                map.put(key, map.getOrDefault(key, 0) + 1);
                // 更新当前直线坐标点数量
                max = Math.max(max, map.get(key));
            }
            // max+1: 加上基准坐标点
            res = Math.max(res, max + 1);
        }
        return res;
    }

    /**
     * 求最大公因数
     */
    private static int gcd(int a, int b) {
        // 1、辗转相除法(欧几里得法)
        return b == 0 ? a : gcd(b, a % b);
        // 2、辗转相减法(尼考曼彻斯法)
        // return a == b ? a:gcd(a>b ? a-b:a, b>a ? b-a:b);
    }

    public static void main(String[] args) {
        // 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(maxPoints(points));
        System.out.println(maxPoints1(points));
    }
}
