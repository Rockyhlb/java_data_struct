import java.util.Arrays;

/**
 * @BelongsProject: test-20240229
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/2/29 13:57
 * @Description: TODO
 * @Author: code_hlb
 */
public class Test {
    public static void main(String[] args) {
        SortDemo sortDemo = new SortDemo();
        int[] nums = {2,3,1,1,5,6,4};
//        sortDemo.bubbleSort(nums);
//        System.out.print(Arrays.toString(nums));
//        sortDemo.insertSort(nums);
//        System.out.println(Arrays.toString(nums));
//        sortDemo.shellSort(nums);
//        System.out.println(Arrays.toString(nums));
//        sortDemo.selectSort(nums);
//        System.out.println(Arrays.toString(nums));
//        sortDemo.countSort(nums);
//        System.out.println(Arrays.toString(nums));
        sortDemo.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
