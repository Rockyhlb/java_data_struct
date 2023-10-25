
import java.util.ArrayList;
import java.util.List;

/**
 * @author: code_hlb
 * @date :  2023/10/25 17:21
 * @desc :  给定两个字符串
 *          str1:  welcome to cctv
 *          str2:  come
 *          需求： 删除第一个字符串当中出现的所有的第二个字符串的字符，用ArrayList完成！！
 *          结果： wl t tv
 */
public class Demo3 {

    public static List<Character> func(String str1, String str2){

        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            if (!str2.contains(ch + "")){
                list.add(ch);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        String str1 = "welcome to cctv";
        String str2 = "come";

        List<Character> ret  =  func(str1, str2);
        for (Character c: ret) {
            System.out.print(c);
        }
    }
}
