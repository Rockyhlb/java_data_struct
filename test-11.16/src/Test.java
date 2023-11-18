
/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 16:08
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {

    // 测试 HashBuck2
    public static void main(String[] args) {
        Person person1 = new Person("100");
        Person person2 = new Person("100");
        System.out.println(person1.hashCode());
        // 虽然 它们的 id 相同，但是打印出来的 HashCode 不同，
        // 因为是自定义类型，我们可以重写hashCode方法让 id相同 的对象生成的 HashCode相同；
        System.out.println(person2.hashCode());

        System.out.println("=======hashBuck2.put(Person,Integer)============");
        HashBuck2<Person,Integer> hashBuck2 = new HashBuck2<>();
        // 本质上还是同一个对象，只是更新了val
        hashBuck2.put(person1,2);
        hashBuck2.put(person2,10);
        System.out.println(hashBuck2.toString());
        System.out.println("=======hashBuck2.get(Person)============");
        // 取出的都是同一个对象的val
        System.out.println(hashBuck2.get(person1));
        System.out.println(hashBuck2.get(person2));
    }

    // 测试自定义哈希表 HashBuck
    public static void main2(String[] args) {
        HashBuck hashBuck = new HashBuck();
        hashBuck.put(1,11);
        hashBuck.put(11,111);
        hashBuck.put(2,22);
        hashBuck.put(12,122);
        hashBuck.put(22,222);
        hashBuck.put(3,33);
        hashBuck.put(13,133);
        hashBuck.put(23,233);
        // 此时负载因子大于0.75,扩容数组
        hashBuck.put(4,5);

        System.out.println("======get(12)========");
        System.out.println(hashBuck.get(12));
        System.out.println(hashBuck.get(43));
    }

    // 测试自定义搜索树
    public static void main1(String[] args) {
        BinarySelectTree binarySelectTree = new BinarySelectTree();
        binarySelectTree.add(2);
        binarySelectTree.add(1);
        binarySelectTree.add(5);
        binarySelectTree.add(22);
        System.out.println();

        System.out.println(binarySelectTree.search(5));
        System.out.println(binarySelectTree.search(15));

        binarySelectTree.remove(5);
    }
}
