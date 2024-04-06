import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @BelongsProject: test-20240405
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/4/5 18:50
 * @Description: LeetCode
 * @Author: code_hlb
 */
public class demo1 {
    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，
     * 分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
     * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int e1 = m - 1;    // nums1有效元素尾部
        int e2 = n - 1;    // nums2尾部
        int k = m + n - 1; // nums1尾部
        // 通过从两个数组的尾部进行比较，再将较大值从数组尾部开始插入(因为数组nums1 后 n个元素都是0)
        while (e1 >= 0 && e2 >= 0) {
            if (nums1[e1] >= nums2[e2]) {
                nums1[k--] = nums1[e1--];
            }else {
                nums1[k--] = nums2[e2--];
            }
        }
        // 当数组nums2已经检索结束时
        while (e1 >= 0) {
            nums1[k--] = nums1[e1--];
        }
        // 当数组nums1已经检索完毕时
        while (e2 >= 0) {
            nums1[k--] = nums2[e2--];
        }
    }

    public static void main(String[] args) {
        int[] num1 = {1,3,5,7,9,0,0,0,0,0};
        int[] num2 = {2,4,6,8,10};
        merge(num1,5,num2,5);
        System.out.println(Arrays.toString(num1));
    }
}
