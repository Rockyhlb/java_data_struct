import netscape.security.UserTarget;

/**
 * @author: code_hlb
 * @date :  2023/10/24 16:30
 * @desc :  泛型
 */

// 泛型的主要目的：指定当前的容器，要持有什么样的对象，让编译器去检查
//          优点：数据类型参数化，编译时自动进行类型检查和转换
// 泛型是如何编译的：编译时所有的T都被擦除并替换成Object类型

/*需求：写一个泛型类：实现一个方法，求指定类型数组的最大值 */
// 由于编译时T会被擦除为Object,Object没有比较方法，因此此处T应该实现Comparable接口
/*class maxNum<T extends Comparable>{

    public T findMax(T[] arrays){

        T max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].compareTo(max) > 0){
                max = arrays[i];
            }
        }
        return max;
    }
}*/

class maxNum {
    // 泛型方法
    public static <T extends Comparable> T findMax(T[] arrays){

        T max = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            if (arrays[i].compareTo(max) > 0){
                max = arrays[i];
            }
        }
        return max;
    }
}

// 类也可作为泛型传入
class A implements Comparable{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

public class Demo2 {

    public static void main(String[] args) {

        /*maxNum<Integer> integermaxNum = new maxNum<>();
        maxNum<String> stringmaxNum = new maxNum<>();
        maxNum<Double> doublemaxNum = new maxNum<>();

        maxNum<A> Amax = new maxNum<>();*/

        // 泛型方法的使用
        // maxNum findmax = new maxNum();
        Integer[] nums = {1,2,1,34,5};
        System.out.println(maxNum.findMax(nums));
    }

    public static void main1(String[] args) {

        // 需求：实现一个类，可以传入任意类型的参数，并且可以取到指定下标处的值
        // 尖括号不能省略，且尖括号中只能是引用类型，不能是基本类型(必须是基本数据类型的包装类)
        testDemo2<String> testDemo2 = new testDemo2<>();
        testDemo2.set(0,"hello");
        // 实例化对象时已经指定‘T’为'String'，因此已经不能放整型了
        // testDemo2.set(0,90);
        String str = testDemo2.get(0);
        System.out.println(testDemo2.get(0));

        Object[] myarray = testDemo2.getArray();
        for (Object array:
             myarray) {
            System.out.println(array);
        }
    }
}