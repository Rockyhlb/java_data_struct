import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: code_hlb
 * @date :  2023/10/26 11:14
 * @desc :  牛客刷题
 */
public class Demo1 {

    /* test: 读取字符串，中间以空格隔开，返回最后一个字符串的长度 */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.nextLine();

            if(str.length() < 5000){
                String[] newStr = str.split(" ");

                String last = newStr[newStr.length - 1];
                System.out.print(last.length());

            }
        }
    }

    /*   test: 给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
     *         并且字符串长度不大于1000000，且仅由小写字母组成
     *         示例1
     *         输入  ："absba"
     *         返回值：true
     * */
    public static boolean judge (String str) {
        // write code here
        if (str.length() <= 1000000){
            if (str.length() == 1){
                return true;
            }else {
                int left = 0;
                int right = str.length() - 1;
                while (left <= right){

                    Character ch1 = str.charAt(left);
                    char ch2 = str.charAt(right);

                    if (ch1 <= 'Z' || ch2 <= 'Z'){
                        return false;
                    }else if (ch1.equals(ch2)){
                        left++;
                        right--;
                    }else {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;

        // 相当简洁！！
        // return str.equals(new StringBuffer(str).reverse().toString());
    }

    public static void main2(String[] args) {
        System.out.println(judge("dsadsa"));
        System.out.println(Integer.valueOf('A'));  // 65
        System.out.println(Integer.valueOf('a'));  // 97
    }

    /* test: 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组*/
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

    public static void main1(String[] args) {

        int[] arr1 = {4,5,6,0,0,0};
        int[] arr2 = {1,2,3};
        System.out.println(Arrays.toString(merge(arr1, 3, arr2, 3)));
    }
}
