/**
 * @author: code_hlb
 * @date :  2023/10/26 11:42
 * @desc :  test: 给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
 *                并且字符串长度不大于1000000，且仅由小写字母组成
 *          示例1
 *          输入  ："absba"
 *          返回值：true
 */
public class Demo2 {

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

    public static void main(String[] args) {
        System.out.println(judge("dsadsa"));
        System.out.println(Integer.valueOf('A'));  // 65
        System.out.println(Integer.valueOf('a'));  // 97
    }
}
