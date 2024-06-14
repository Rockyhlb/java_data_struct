package sort;

import java.util.Random;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sort
 * @CreateTime : 2024/6/14 9:52
 * @Description: 静态方法，创建
 * @Author: code_hlb
 */
public class CaseFactory {
    public static int[] createOrderCase() {
        // 返回有序样例
        int[] nums = new int[100_000];
        // 最好情况
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }

    public static int[] createReverseOrderCase() {
        // 返回逆序样例
        int[] nums = new int[100_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums.length - i;
        }
        return nums;
    }

    public static int[] createRandomCase() {
        // 返回随机样例
        Random rd = new Random();
        int[] nums = new int[100_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rd.nextInt(10_000);
        }
        return nums;
    }
}