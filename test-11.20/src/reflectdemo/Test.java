package reflectdemo;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: reflectdemo
 * @CreateTime : 2023-11-20 10:42
 * @Description: 反射
 * @Author: code_hlb
 */
public class Test {
    /* 定义：动态获取信息 以及 动态调用对象方法的功能成为java语言的反射机制
     用途：当某个类的某个成员变量、方法或属性是私有的或只是对系统应用开放，这时候就可以利用Java的反射机制来获取所需的私有成员或方法
     反射相关的类：Class类(代表类的实体，在运行的Java应用程序中表示类和接口)、Field类(代表类的成员变量/属性)、
                  Method类(代表类的方法)、Constructor类(代表类的构造方法)
       优点：对于任意一个类，都能知道这个类的所有属性和方法，对于任意一个对象，都能调用它的任意方法
            增加了程序的灵活性和扩展性，降低耦合性，提高自适应能力，并且已经用于很多流行框架：Struts、Hibernate、Spring等
       缺点：会导致程序效率降低，并且反射技术绕过了源代码的技术，因而会带来维护问题，比相应的直接代码更复杂
    */
    public static void main(String[] args) {
        // 获取Class对象的三种方式  -->  反射的前提
        // 1、使用类的全限定名作为参数，加载并返回对应的Class对象
        Class<?> c1 = null;
        try {
            c1 = Class.forName("reflectdemo.Student");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 2、使用类名后加上.class来获取它的Class对象
        Class<?> c2;
        c2 = Student.class;
        // 3、创建一个类的实例，然后调用getClass()方法来获取它的Class对象
        Student student = new Student();
        Class<?> c3 = student.getClass();

        // Object的equals方法默认是比较地址，但是Class对象只有一个，因此都输出true
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
        System.out.println(c2.equals(c3));
    }
}
