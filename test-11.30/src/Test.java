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
        // Demo.bubbleSort(nums);
        // Demo.insertSort(nums);
        // Demo.selectSort1(nums);
        // Demo.shellSort(nums);
        // Demo.heapSort(nums);
        // Demo.quickSort(nums);
        Demo.countSort(nums);
        System.out.println();
        for (int elem:nums) {
            System.out.printf(elem + " ");
        }
    }
}
