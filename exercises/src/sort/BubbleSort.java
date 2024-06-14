package sort;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sort
 * @CreateTime : 2024/6/14 9:38
 * @Description: 冒泡排序
 * @Author: code_hlb
 */
public class BubbleSort implements TestInterface {
    /**
     * 冒泡排序
     * 时间复杂度：O(n^2) 加了优化以后最好情况为O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */
    private void bubbleSort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            boolean flag = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = false;
                }
            }
            // flag 为true 说明数组已经有序
            if (flag) {
                return;
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    @Override
    public void testSingle(int[] nums) {
        // 测试样例
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void testAll(int[] order, int[] disOrder, int[] rdOrder) {
        long start = System.currentTimeMillis();
        bubbleSort(order);
        System.out.println("order spend time: " + (System.currentTimeMillis() - start) + " ms");

        long start1 = System.currentTimeMillis();
        bubbleSort(disOrder);
        System.out.println("disOrder spend time: " + (System.currentTimeMillis() - start1) + " ms");

        long start2 = System.currentTimeMillis();
        bubbleSort(rdOrder);
        System.out.println("rdOrder spend time: " + (System.currentTimeMillis() - start2) + " ms");
    }
}
