/**
 * @BelongsProject: test-11.19
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-19 15:39
 * @Description: 字符串常量池的介绍
 * @Author: code_hlb
 */
public class Demo1 {

    public static void main(String[] args) {
        // intern()方法，是一个Native方法：底层使用C++实现，看不到其实现的源代码，
        // 该方法的作用是将创建大的String 对象添加到常量池中
        char[] chars = new char[]{'a','b','c'};
        String s1 = new String(chars);  // 此时 s1 对象并不在常量池中
        // 调用 intern() 之后，会将是 s1 对象的引用放入到常量池中，因此 s1 == s2
        // s1.intern();
        String s2 = "abc";
        System.out.println(s1 == s2);
    }

    public static void main1(String[] args) {
        // 字符串常量
        String s1 = "hello";
        // 字符串常量池存在于堆中，当我们存储字符串常量的时候，会先检查常量池中是否存在该常量因此 s1 == s2
        String s2 = "hello";
        System.out.println(s1 == s2);

        String s3 = new String("hello");
        // s3 和 s4 都是新建的对象，虽然它们内部存储的都是常量池当中的同一个地址，但是他们本身的地址并不相同
        String s4 = new String("hello");
        System.out.println(s3 == s4);
    }
}
