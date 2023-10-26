import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/26 11:14
 * @desc :  test: 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
 */
public class Demo1 {

    public static int[] merge(int A[], int m, int B[], int n) {

        // i从A[]的有效数组最后一位往前遍历
        int i = m - 1;
        // j从B[]的有效数组最后一位往前遍历
        int j = n - 1;
        // k 从A[] 的后面往前遍历
        int k = m + n - 1;

        while (i >= 0 && j >= 0){
            if (A[i] > B[j]){
                A[k] = A[i];
                k--;
                i--;
            }else if (A[i] < B[j]){
                A[k] = B[j];
                k--;
                j--;
            }
        }
        // 谁先遍历完，就把剩余的另外一个数组赋给A[k],此时的k已经为2
        while (i >= 0){
            A[k] = A[i];
            i--;
            k--;
        }

        while (j >= 0){
            A[k] = B[j];
            k--;
            j--;
        }
        return A;
    }

    public static void main(String[] args) {

        int[] arr1 = {4,5,6,0,0,0};
        int[] arr2 = {1,2,3};
        System.out.println(Arrays.toString(merge(arr1, 3, arr2, 3)));
    }
}
