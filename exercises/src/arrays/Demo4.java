package arrays;

import java.net.InetAddress;
import java.net.StandardSocketOptions;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: arrays
 * @CreateTime : 2024/4/15 15:26
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 18. 在字符串中找出连续最长的数字串
     * 现有一个字符串str，输出字符串str中的最长的数字子串。
     * 输入描述：
     * 一个包含字母和数字的字符串，长度不超过255。
     * 保证最少有一个字符是数字，且只有一个最长的数字子串。
     * 输出描述：
     * 最长的数字子串。
     */
    public static String findLongest(String str) {
        int curLen = 0, maxLen = 0; // 记录当前数字长度 和 历史最长数字长度
        int curStart = 0, maxStart = 0; // 记录当前开始的下标 和 历史最长数字的开始下标
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    maxStart = curStart;
                }
            } else {
                // 复位
                curLen = 0;
                curStart = i + 1;
            }
        }
        return str.substring(maxStart, maxStart + maxLen);
    }

    public static void main(String[] args) {
        String str = "dada22fs222sd11das678686dsad";
        System.out.println(findLongest(str));
    }

    public static void main1(String[] args) {
        Integer io1 = 99;
        int io2 = 99;
        System.out.println(io1 == io2);
        Integer io3 = Integer.valueOf(99);
        System.out.println(io1 == io3);
        System.out.println(io2 == io3);
        Integer io4 = new Integer(99);
        System.out.println(io1 == io4);
        System.out.println(io2 == io4);
        System.out.println(io3 == io4);
    }

    public static void main2(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println(System.currentTimeMillis());
        });

        t1.start();
        t2.start();
    }
}
