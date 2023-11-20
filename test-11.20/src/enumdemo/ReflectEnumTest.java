package enumdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: enumdemo
 * @CreateTime : 2023-11-20 13:49
 * @Description: 测试能不能通过反射创建枚举对象
 * @Author: code_hlb
 */
public class ReflectEnumTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c1 = Class.forName("enumdemo.EnumDemo");
        try {
            // 报java.lang.NoSuchMethodException:
            // Constructor<?> constructor = c1.getDeclaredConstructor(int.class,String.class);
            // 前两个参数是传给父类的
            // 最终报java.lang.IllegalArgumentException: Cannot reflectively create enum objects
            // 得出结论：不能通过反射创建枚举对象
            Constructor<?> constructor = c1.getDeclaredConstructor(String.class,int.class,int.class,String.class);
            constructor.setAccessible(true);
            // EnumDemo enumDemo = (EnumDemo) constructor.newInstance(1,"优秀");
            EnumDemo enumDemo = (EnumDemo) constructor.newInstance("test",88,1,"优秀");
            System.out.println(enumDemo);
        }catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
