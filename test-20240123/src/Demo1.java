import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: test-20240123
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/23 20:33
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {

    /**
     * 1、给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表
     * 示。OJ链接 【LeetCode题号：539. 最小时间差】【中等】
     * 示例 1：
     * 输入：timePoints = ["23:59","00:00"]           输出：1
     * 示例 2：
     * 输入：timePoints = ["00:00","23:59","00:00"]   输出：0
     * 提示：
     * 2 <= timePoints <= 2 * 104
     * timePoints[i] 格式为 "HH:MM"
     */
    public int findMinDifference(List<String> timePoints) {
        // 一个时钟，两个时间点的最小间距只能是相邻时间点，或者就是第二天终点到前一天起始点
        // 声明一个长度为时间列表长度2倍的数组，因为还需要记录当前时间点在第二天的偏移量
        int n = timePoints.size() * 2;
        int[] nums = new int[n];

        for (int i = 0,index = 0; i < n / 2; i++,index += 2) {
            // 读取时间列表元素并按分号划分
            String[] time = timePoints.get(i).split(":");
            int minutes = Integer.parseInt(time[0]) * 60;
            int seconds = Integer.parseInt(time[1]);
            // 存储当天时间的偏移量
            nums[index] = minutes+seconds;
            // 记录第二天当前时间的偏移量
            nums[index+1] = nums[index] + 60*24;
        }
        // 对这些时间点的偏移量进行排序
        Arrays.sort(nums);
        // 初始化返回结果
        int res = nums[1] - nums[0];
        for (int i = 0; i < n-1; i++) {
            res = Math.min(res,nums[i+1]-nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("23:59");
        list.add("00:00");
        Demo1 demo1 = new Demo1();
        System.out.println(demo1.findMinDifference(list));
    }
}
