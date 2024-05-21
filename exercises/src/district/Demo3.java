package district;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: district
 * @CreateTime : 2024/5/12 15:47
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 57. 插入区间
     * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，
     * 并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
     * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
     * 返回插入之后的 intervals。
     * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
     * 示例 1：
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * 示例 2：
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * 提示：
     * 0 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 105
     * intervals 根据 starti 按 升序 排列
     * newInterval.length == 2
     * 0 <= start <= end <= 105
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 处理边界
        if (0 == intervals.length) {
            return new int[][]{newInterval};
        }
        int left = newInterval[0];
        int right = newInterval[1];
        boolean isInsert = false;
        List<int[]> insert = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 当前区间在待插入区间的右侧且无交集
                if (!isInsert) {
                    // 加入 待插入区间
                    insert.add(new int[]{left, right});
                    isInsert = true;
                }
                // 插入原始区间
                insert.add(interval);
            } else if (interval[1] < left) {
                // 当前区间在待插入区间的左侧且无交集
                insert.add(interval);
            } else {
                // 有交集，计算并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        // 插入并集区间
        if (!isInsert) {
            insert.add(new int[]{left, right});
        }
        return insert.toArray(new int[insert.size()][]);
    }

    public static void main(String[] args) {
        // intervals = [[1,3],[6,9]], newInterval = [2,5]
        int[][] intervals = new int[2][2];
        intervals[0][0] = 1;
        intervals[0][1] = 3;
        intervals[1][0] = 6;
        intervals[1][1] = 9;
        int[] newInterval = new int[2];
        newInterval[0] = 2;
        newInterval[1] = 5;
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }
}
