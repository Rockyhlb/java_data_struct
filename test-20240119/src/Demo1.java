import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @BelongsProject: test-20240118
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/1/19 21:35
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {
    /**
     * 2、给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 OJ链接【LeetCode题号：75. 颜色分类】【中等】
     * 示例 1：
     * 输入：nums = [2,0,2,1,1,0]  输出：[0,0,1,1,2,2]
     * 示例 2：
     * 输入：nums = [2,0,1]   输出：[0,1,2]
     * 示例 3：
     * 输入：nums = [0]       输出：[0]
     * 示例 4：
     * 输入：nums = [1]       输出：[1]
     * 提示：n == nums.length   1 <= n <= 300   nums[i] 为 0、1 或 2
     */
    public void sortColors(int[] nums) {
        // 采用双指针限制0和1的位置
        int color0 = 0,color1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 1、类似于刷油漆，因为所有数都小于等于2，因此第一次索性全部刷为2
            nums[i] = 2;
            // 2、然后再将正确的1覆盖到错误的2上，就保证了所有的2都是正确的
            if (num < 2) {
                nums[color1++] = 1;
            }
            // 3、最后再将正确的0覆盖错误的1，最后就保证了正确的0，1，2的顺序
            if (num < 1) {
                nums[color0++] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,1,2,0,1,2,1};
        Demo1 demo1 = new Demo1();
        demo1.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
