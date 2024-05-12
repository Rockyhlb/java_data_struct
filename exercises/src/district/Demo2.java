package district;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: district
 * @CreateTime : 2024/5/12 14:56
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * 提示：
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     */
    public static int[][] merge(int[][] intervals) {
        // 处理边界
        if (0 == intervals.length) {
            return new int[0][2];
        }
        // 将数组按照区间左端点进行排序
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0] - o2[0];
//            }
//        });
        List<int[]> merge = new ArrayList<>();
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];
            // 当merge为空 || merge的最后一个区间的右端点小于当前left时，将当前区间加入merge
            if (merge.size() == 0 || merge.get(merge.size() - 1)[1] < left) {
                merge.add(new int[]{left, right});
            } else {
                // left 小于等于 merge最后一个区间的右端点时,更新右端点
                merge.get(merge.size() - 1)[1] = Math.max(merge.get(merge.size() - 1)[1], right);
            }
        }
        return merge.toArray(new int[merge.size()][]);
    }

    public static void main(String[] args) {
        // intervals = [[1,3],[2,6],[8,10],[15,18]]
        int[][] intervals = new int[4][2];
        intervals[0][0] = 1;
        intervals[0][1] = 3;
        intervals[1][0] = 2;
        intervals[1][1] = 6;
        intervals[2][0] = 8;
        intervals[2][1] = 10;
        intervals[3][0] = 15;
        intervals[3][1] = 18;
        System.out.println(Arrays.deepToString(merge(intervals)));
    }
}
