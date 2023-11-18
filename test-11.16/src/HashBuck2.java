
import java.util.Objects;

/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-18 16:29
 * @Description: HashMap 对自定义类型的存储
 * @Author: code_hlb
 */
class Person {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String id) {
        this.id = id;
    }

    // 重写equals 和 hashCode 方法后，我们就可以让相同的对象产生相同的 hashCode
    // 因此将对象插入到 HashMap 中后就可以通过hashCode判断应该插入的索引位置
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

public class HashBuck2<K,V> {
    static class Node<K,V> {
        public K key;
        public V val;
        public Node<K,V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public Node<K,V>[] arrays;
    public int usedSize;

    public HashBuck2() {
        arrays = (Node<K,V>[])new Node[10];
    }

    /**
     * 问题：在HashMap中，HashCode 和 equals() 的区别
     *  答：HashCode 可以理解为模糊查找，只是找到了一个大致范围，最后 key是否一致还是要通过equals() 来判断
     */
    public void put(K key,V val) {
        int hashCode = key.hashCode();
        int index = hashCode % arrays.length;
        Node<K,V> cur = arrays[index];
        while (cur != null) {
            // 因为是引用类型，因此不能使用 == 来判断
            if (cur.key.equals(key)) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }
        Node<K,V> node = new Node<K, V>(key,val);
        node.next = arrays[index];
        arrays[index] = node;
        usedSize++;
    }

    public V get(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % arrays.length;
        Node<K,V> cur = arrays[index];
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node<K,V> node : arrays) {
            while (node != null) {
                // key是引用，因此打印出来的还是地址，需要再重写 Person 的 toString()方法打印
                res.append("key: ").append(node.key.toString()).append(" val: ").append(node.val).append("\n");
                node = node.next;
            }
        }
        return res.toString();
    }
}
