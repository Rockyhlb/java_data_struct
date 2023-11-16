import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 19:45
 * @Description: TreeMap
 * @Author: code_hlb
 */
public class MapTest {
    /**
     * 1、纯 key 模型，Set中只存储了Key  --->   TreeSet  HashSet(基于哈希表[散列表])
     * 2、Key-Value模型，Map中存储的就是key-value的键值对  --->  TreeMap  HashMap(基于哈希表)
     */
    public static void main(String[] args) {
        // Map 中存放的Key是唯一的，Value是可以重复的，并且Key不能为空，Value可以为空，但是HashMap当中的key和value都可以为空
        // Map 中的 Key 可以全部分离出来，存储到 Set 来进行访问(因为Key不能重复)
        // Map 中的 value 可以全部分离出来，存储在 Collection 的任何一个子集合中(value可能有重复)
        // 编译看左，执行看右，左边是Map接口，不能直接实例化对象，但是可以通过TreeMap或者HashMap实现类来实例化对象
        Map<String,Integer> map = new TreeMap<>();
        TreeMap<String,Integer> treeMap = new TreeMap<>();
        // TreeMap 和TreeSet 本质是一颗红黑树，因此每次放入元素都会进行比较 [compare(key, key)];
        System.out.println("========put()=========");
        treeMap.put("a",1);
        treeMap.put("c",3);
        treeMap.put("b",2);
        System.out.println(treeMap.toString());

        System.out.println("========get(\"b\")=========");
        System.out.println(treeMap.get("b"));

        System.out.println("========getOrDefault(\"d\",-1)=========");
        System.out.println(treeMap.getOrDefault("d", -1));

        System.out.println("========remove(\"a\")=========");
        treeMap.remove("a");
        System.out.println(treeMap.toString());

        System.out.println("========getOrDefault(\"d\",-1)=========");

        System.out.println("========keySet()=========");
        // Set<K> keySet()  返回所有key的不重复集合
        Set<String> keySet = treeMap.keySet();
        System.out.println(keySet.toString());

        // Collection<V> values()  返回所有value的可重复集合
        System.out.println("========values()=========");
        Collection<Integer> values = treeMap.values();
        System.out.println(values.toString());

        // Set<Map.Entry<K,V>> entrySet()  返回所有的key-value映射关系
        System.out.println("========entrySet()=========");
        Set<Map.Entry<String,Integer>> entries = treeMap.entrySet();
        for (Map.Entry<String,Integer> entry: entries) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        System.out.println("========put(\"c\",10)=========");
        // Map 当中的Key不能直接修改，value可以修改，如果要修改Key,只能先将key删掉,然后重新插入
        treeMap.put("c",10);
        System.out.println(treeMap.toString());
    }
}
