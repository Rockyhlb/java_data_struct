package binary_search;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: binary_search
 * @CreateTime : 2024/5/20 11:04
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo7 {
    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * 提示：
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 106
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 方法一：合并数组后再返回中值, 时间复杂度：O(m+n)  空间复杂度：O(m+n)
        // 1、处理边界
        int m = nums1.length;
        int n = nums2.length;
        if (0 == m && 0 == n) {
            return 0.0;
        }
        if (0 == m) {
            if (n % 2 == 0) {
                // 当nums2含有偶数个元素时
                return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
            } else {
                // 当nums2含有偶数个元素时
                return nums2[n / 2];
            }
        }
        if (0 == n) {
            if (m % 2 == 0) {
                // 当nums2含有偶数个元素时
                return (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
            } else {
                // 当nums2含有偶数个元素时
                return nums1[m / 2];
            }
        }
        // 2、合并数组
        int[] nums3 = new int[m + n];
        int count = 0;
        int index1 = 0, index2 = 0;
        while (count != (m + n)) {
            if (nums1[index1] <= nums2[index2]) {
                nums3[count++] = nums1[index1++];
            } else {
                nums3[count++] = nums2[index2++];
            }
            // 当数组nums1遍历完时
            if (index1 == m) {
                while (index2 != n) {
                    nums3[count++] = nums2[index2++];
                }
                break;
            }
            // 当数组nums2遍历完时
            if (index2 == n) {
                while (index1 != m) {
                    nums3[count++] = nums1[index1++];
                }
                break;
            }
        }
        // 3、返回中值
        if (count % 2 == 0) {
            // 偶数
            return (nums3[count / 2 - 1] + nums3[count / 2]) / 2.0;
        } else {
            // 奇数
            return nums3[count / 2];
        }
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // 方法二：不需要合并数组，只需要通过下标找到中值即可，时间复杂度：O(m+n),空间复杂度: O(1)
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        if (0 == len) {
            return 0.0;
        }
        int left = 0, right = 0;
        int index1 = 0, index2 = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (index1 < m && (index2 >= n || nums1[index1] < nums2[index2])) {
                right = nums1[index1++];
            } else {
                right = nums2[index2++];
            }
        }
        if (len % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 方法三：折半+递归,时间复杂度：O(log (m+n)),空间复杂度: O(1)
        int m = nums1.length;
        int n = nums2.length;
        if (0 == m && 0 == n) {
            return 0.0;
        }
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;
        // 将奇数和偶数的情况合并，如果是奇数，则对其求两次中值
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) + getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) / 2.0;
    }

    // 尾递归，因此空间复杂度还是O(1)
    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 传入的是索引，因此长度需要+1
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        // 处理边界
        if (len1 > len2) {
            // 保证nums1的长度小于nums2, 这样就能保证后续出现空数组时，一定是nums1空了
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            // 当nums1为空时，返回nums2的中值
            return nums2[start2 + k - 1];
        }
        if (k == 1) {
            // 当k==1 时，返回当前两个数组头的较小值即可
            return Math.min(nums1[start1], nums2[start2]);
        }
        // 确定mid下标
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        // 折半查找，进入递归
        if (nums1[i] > nums2[j]) {
            // k变为k - (j - start2 + 1)，即减去逻辑上排除的元素个数(要加1，因为索引相减，相对于实际排除的是要少一个的)
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        // 输入：nums1 = [1,2], nums2 = [3,4]
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));  // 2.5
        System.out.println(findMedianSortedArrays1(nums1, nums2)); // 2.5
        System.out.println(findMedianSortedArrays2(nums1, nums2)); // 2.5
    }
}
