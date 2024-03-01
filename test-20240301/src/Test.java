import java.util.Arrays;
import java.util.Random;

/**
 * @BelongsProject: test-20240301
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2024/3/1 8:42
 * @Description: TODO
 * @Author: code_hlb
 */
public class Test {
    public static int[] orderArrays(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = i+1;
        }
        return arrays;
    }
    public static int[] disOrderArrays(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = arrays.length - i;
        }
        return arrays;
    }
    public static int[] randomArrays(int[] arrays) {
        Random random = new Random();
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = random.nextInt(100000);
        }
        return arrays;
    }
    private void testQuickSort(int[] arrays) {
        SortDemo sortDemo = new SortDemo();
        int[] tmp = Arrays.copyOf(arrays,arrays.length);
        int[] orderArrays = orderArrays(Arrays.copyOf(arrays,arrays.length));
//        sortDemo.quickSort(orderArrays);
        sortDemo.quickSortNor(orderArrays);
        int[] disOrderArrays = disOrderArrays(Arrays.copyOf(arrays,arrays.length));
//        sortDemo.quickSort(disOrderArrays);
        sortDemo.quickSortNor(disOrderArrays);

        int[] randomArrays = randomArrays(Arrays.copyOf(arrays,arrays.length));
//        sortDemo.quickSort(randomArrays);
        sortDemo.quickSortNor(randomArrays);
    }
    public static void main(String[] args) {
        Test test = new Test();
        int[] arrays = new int[100000];
        test.testQuickSort(arrays);
        int[] nums = {55,32,222,3,3,1,44,22,99};
        SortDemo sortDemo = new SortDemo();
//        sortDemo.quickSort(nums);
//        sortDemo.quickSortNor(nums);
        sortDemo.mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
