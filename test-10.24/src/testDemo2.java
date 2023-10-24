import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/24 16:43
 * @desc :  泛型类
 */

// <T>：声明这是一个泛型类
// 泛型的上界：
// <T extends Number>: T是Number或者Number的子类
public class testDemo2<T> {

    public Object[] array = new Object[10];
    // 不允许实例化一个泛型数组
    // public T[] arrays = new T[10];


    public T get(int pos) {
        // 强制类型转换，不然返回的还是Object类型
        return (T)array[pos];
    }

    public void set(int pos,T value) {
        array[pos] = value;
    }

    public Object[] getArray(){
        return array;
    }
}
