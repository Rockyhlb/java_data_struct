
/**
 * @BelongsProject: test-11.16
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-16 16:08
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {

    // 测试自定义哈希表
    public static void main(String[] args) {
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
