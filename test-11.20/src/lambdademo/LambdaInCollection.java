package lambdademo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: lambdademo
 * @CreateTime : 2023-11-20 15:18
 * @Description: lambda 表达式用的最多的地方就是在集合中
 * @Author: code_hlb
 */
public class LambdaInCollection {

    /**
     * Lambda表达式的优点：代码简洁，开发迅速，方便函数式编程，非常容易进行并行计算，同时改善了集合操作
     *              缺点： 代码可读性差，在非并行计算中，很多计算未必有传统的 for 性能高，且不任意进行调试
     */
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("abc",3);
        map.put("ab",2);

        /*map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println("key: " + s +", value: " + integer);
            }
        });*/
        map.forEach((k,v) -> System.out.println("key: " + k +", value: " + v));
    }

    public static void main1(String[] args) {
        // 1、lambda 表达式在 foreach 中的应用
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("abc");
        list.add("ab");
        // 通过查看源码得知 foreach 接收一个 Consumer 函数式接口
        /*list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });*/
        list.forEach((s) -> System.out.println(s));

        // 2、lambda 表达式在 排序 中的应用
        System.out.println("================");
        /*list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });*/
        list.sort((o1,o2) -> o1.compareTo(o2));
        list.forEach(s -> System.out.println(s));
    }
}
