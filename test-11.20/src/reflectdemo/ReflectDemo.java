package reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: reflectdemo
 * @CreateTime : 2023-11-20 11:09
 * @Description: 反射demo
 * @Author: code_hlb
 */
public class ReflectDemo {
    // 通过反射 实例化对象
    public static void reflectNewInstance() {
        Class<?> c1;
        try {
            // 先拿到Class对象
            c1 = Class.forName("reflectdemo.Student");
            // newInstance 调用的是无参构造方法
            Student student = (Student) c1.newInstance();
            System.out.println(student);
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    // 反射私有的构造方法，屏蔽内容为获得公有的构造方法
    // 反射缺点：因为频繁开辟栈帧，所以效率不高  优点：动态获取信息，无论私有公有都能拿到
    public static void reflectPrivateConstructor() {
        Class<?> c1;
        try {
            c1 = Class.forName("reflectdemo.Student");
            // 获取指定构造器  getDeclaredConstructor 可以拿到私有，getConstructor拿不到私有
            Constructor<Student> constructor = (Constructor<Student>) c1.getDeclaredConstructor(String.class,int.class);
            // 打破Java语言访问权限的限制，使得即使目标成员是私有的，也可以通过反射来进行访问和操作
            constructor.setAccessible(true);
            Student student = constructor.newInstance("C++",20);
            System.out.println(student);
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 反射私有属性
    public static void reflectPrivateField() {
        Class<?> c1;
        try {
            c1 = Class.forName("reflectdemo.Student");
            Field field = c1.getDeclaredField("name");

            field.setAccessible(true);
            Student student = (Student) c1.newInstance();
            field.set(student,"Python");

            System.out.println(student);
        }catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    // 反射私有方法
    public static void reflectPrivateMethod() {
        Class<?> c1;
        try {
            c1 = Class.forName("reflectdemo.Student");
            Method method = c1.getDeclaredMethod("function", String.class);
            method.setAccessible(true);

            Student student = (Student) c1.newInstance();
            method.invoke(student,"这是通过反射传入的参数");
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("========reflectNewInstance()========");
        reflectNewInstance();
        System.out.println("========reflectPrivateConstructor()========");
        reflectPrivateConstructor();
        System.out.println("========reflectPrivateField()========");
        reflectPrivateField();
        System.out.println("========reflectPrivateMethod()========");
        reflectPrivateMethod();
    }
}
