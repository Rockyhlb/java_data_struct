import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: code_hlb
 * @date :  2023/10/26 12:26
 * @desc :
 */
public class Demo3 {

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
}
