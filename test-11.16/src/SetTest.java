import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 19:46
 * @Description: TreeSet
 * @Author: code_hlb
 */
public class SetTest {
    /**
     * 1、Set 是继承自Collection的一个接口类，并且只存储了 Key,key一定要唯一
     * 2、TreeSet的底层是使用 Map 来实现的，其使用key 与 Object的一个默认对象作为键值对插入到Map中
     * 3、Set最大的功能就是对集合中的元素进行去重
     * 4、Set中的 Key 不能进行修改，并且不能插入null 的 key,HashSet可以
     * 5、TreeSet和TreeMap的 底层都是红黑树，HashSet的底层结构是哈希桶
     */
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        set.add(2);
        set.add(4);
        set.add(6);
        set.add(8);
        System.out.println(set.toString());
        set.remove(6);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
