
import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/25 10:26
 * @desc :
 */
public class Demo1 {

    public static void func3(int arr1[],int m,int[] arr2,int n){
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        // 对原有数组进行扩容
        int[] arr3 = Arrays.copyOf(arr1,m+n);
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr2));

        while (i >= 0 && j >= 0) {
            if (arr3[i] > arr2[j]) {
                arr3[k] = arr3[i];
                k--;
                i--;
            } else {
                arr3[k] = arr2[j];
                k--;
                j--;
            }
        }

        // 到底是谁走完了，把剩下的元素拷贝到arr3数组中
        while(i >= 0){
            arr3[k] = arr3[i];
            k--;
            i--;
        }

        // 此时：i = -1, j = 2,k = 2
        // arr3[] = {4,5,6,4,5,6}
        while(j >= 0){
            arr3[k] = arr2[j];
            k--;
            j--;
        }

        // {1，2，3，4，5，6}
        System.out.println(Arrays.toString(arr3));
    }

    public static void main(String[] args) {

        int[] arr1 = {4,5,6};
        int[] arr2 = {1,2,3};

        func3(arr1,arr1.length,arr2,arr2.length);
    }


    public static void main1(String[] args) {
        System.out.println(func("dsadsa"));
        System.out.println(func1("dsadsa"));
    }

    public static String func(String str){

        String newStr = "";

        for (int i = 0; i < str.length(); i++) {
            // 解决：Required type:CharSequence  Provided: char
            // 只需 str.charAt(i) + "" 拼接一个字符串即可
            if (!newStr.contains(str.charAt(i) + "")){
                newStr += str.charAt(i);
            }
        }
        return newStr;
    }

    public static String func1(String str){

        StringBuilder newStr = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (newStr.indexOf(str.charAt(i) + "") < 0){
                newStr.append(str.charAt(i));
            }
        }
        return newStr.toString();
    }
}
