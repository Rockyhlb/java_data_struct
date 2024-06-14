package sort;

import java.util.Arrays;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sort
 * @CreateTime : 2024/6/14 11:32
 * @Description: TODO
 * @Author: code_hlb
 */
public class InsertSort implements TestInterface{
    /**
     * 插入排序
     * 时间复杂度：最好情况( O(n) ), 最坏情况( O(n^2) )
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */
    private void insertSort(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int tmp = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > tmp) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = tmp;
        }
    }

    @Override
    public void testSingle(int[] nums) {
        // 测试样例
        insertSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Override
    public void testAll(int[] order, int[] disOrder, int[] rdOrder) {
        long start = System.currentTimeMillis();
        insertSort(order);
        System.out.println("order spend time: " + (System.currentTimeMillis() - start) + " ms");

        long start1 = System.currentTimeMillis();
        insertSort(disOrder);
        System.out.println("disOrder spend time: " + (System.currentTimeMillis() - start1) + " ms");

        long start2 = System.currentTimeMillis();
        insertSort(rdOrder);
        System.out.println("rdOrder spend time: " + (System.currentTimeMillis() - start2) + " ms");
    }
}
