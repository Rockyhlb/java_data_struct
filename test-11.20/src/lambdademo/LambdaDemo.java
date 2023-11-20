package lambdademo;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: lambdademo
 * @CreateTime : 2023-11-20 14:18
 * @Description: Lambda表达式的应用
 * @Author: code_hlb
 */
public class LambdaDemo {
    /**
     * lambda表达式(也称为闭包)：是在 javaSE 8 中一个重要的新特性,lambda表达式允许你通过表达式来代替功能接口，
     * 它和方法一样提供了一个正常的参数列表和一个使用这些参数的主体
     * 基本语法：(parameters) -> expression 或 (parameters) -> { statements; }
     * 组成部分： parameters:类似方法中的形参列表，这里的参数类型可以明确声明，也可不声明，而由 JVM 隐含推断
     *           ->：可以理解为 “被用于” 的意思
     *           方法体：可以是表达式也可以是代码块，是函数式接口里方法的实现
     *
     * 函数式接口：一个接口有且仅有一个抽象方法，如果我们在某个接口上声明了@FunctionalInterface注解，
     *            那么编译器就会按照函数式接口的定义来要求该接口。
     */
    // 无返回值无参数
    @FunctionalInterface  // 加上注解检查接口是否规范
    interface NoParameterNoReturn {
        // 只能有一个抽象方法
        void test();
    }
    // 无返回值一个参数
    @FunctionalInterface
    interface OneParameterNoReturn {
        void test(int x);
    }
    // 无返回值多个参数
    @FunctionalInterface
    interface MoreParameterNoReturn {
        void test(int x,int y);
    }

    // 有返回值无参数
    @FunctionalInterface  // 加上注解检查接口是否规范
    interface NoParameterReturn {
        int test();
    }
    // 有返回值一个参数
    @FunctionalInterface
    interface OneParameterReturn {
        int test(int x);
    }
    // 有返回值多个参数
    @FunctionalInterface
    interface MoreParameterReturn {
        int test(int x,int y);
    }

    // 测试带返回值的 lambda 表达式
    public static void main3(String[] args) {
        // 单句语句返回-1可以省略 return
        NoParameterReturn noParameterReturn1 = () -> -1;
        System.out.println(noParameterReturn1.test());

        NoParameterReturn noParameterReturn =
                () -> {System.out.print("lambdaTest NoParameterReturn: " ); return -1;};
        System.out.println(noParameterReturn.test());

        OneParameterReturn oneParameterReturn =
                x -> { System.out.print("lambdaTest OneParameterReturn: " ); return x;};
        System.out.println(oneParameterReturn.test(99));

        MoreParameterReturn moreParameterReturn =
                (int x,int y) -> { System.out.print("lambdaTest MoreParameterReturn sum: "); return x + y;};
        System.out.println(moreParameterReturn.test(10, 20));
    }

    // 测试不带返回值的 lambda 表达式
    public static void main2(String[] args) {
        // 匿名内部类
        NoParameterNoReturn noParameterNoReturn = new NoParameterNoReturn() {
            @Override
            public void test() {
                System.out.println("test NoParameterNoReturn.....");
            }
        };
        noParameterNoReturn.test();
        // lambda 表达式
        NoParameterNoReturn noParameterNoReturn1 =
                () -> System.out.println("lambdaTest NoParameterNoReturn.....");
        noParameterNoReturn1.test();

        OneParameterNoReturn oneParameterNoReturn =
                x -> System.out.println("lambdaTest OneParameterNoReturn: " + x);
        oneParameterNoReturn.test(99);

        MoreParameterNoReturn moreParameterNoReturn =
                (int x,int y) -> System.out.println("lambdaTest MoreParameterNoReturn sum: " + (x + y));
        moreParameterNoReturn.test(10,20);
    }

    public static void main1(String[] args) {
        int a = 99;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 变量捕获：捕获到的变量只能当作常量来使用，不能进行修改
                // a = 9999;
                System.out.println(a);
                return o1 - o2;
            }
        });
        // 两式等价
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(((o1,o2) -> {
            // lambda表达式中同理
            // a = 9999;
            System.out.println(a);
            return o1 - o2;
        }));
    }
}
