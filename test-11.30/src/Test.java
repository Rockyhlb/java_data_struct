import java.util.Random;

/**
 * @BelongsProject: test-11.30
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023/11/30 8:41
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {
    public static void main(String[] args) {
        int[] nums = {1,5,3,10,2,7,4,33,2};
        for (int elem:nums) {
            System.out.printf(elem + " ");
        }
        Random rd = new Random();
        int[] nums1 = new int[10000];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = rd.nextInt(100000);
        }
        // SortDemo.bubbleSort(nums);
        // SortDemo.bubbleSort(nums1);

        // SortDemo.insertSort(nums);
        // SortDemo.insertSort(nums1);

        // SortDemo.selectSort1(nums);
        // SortDemo.selectSort1(nums1);

        // SortDemo.shellSort(nums);
        // SortDemo.shellSort(nums1);

        // SortDemo.heapSort(nums);
        // SortDemo.heapSort(nums1);

        SortDemo.quickSort(nums);
        SortDemo.quickSort(nums1);

        // SortDemo.countSort(nums);
        // SortDemo.countSort(nums1);

        System.out.println();
        for (int elem:nums) {
            System.out.printf(elem + " ");
        }
    }
}
