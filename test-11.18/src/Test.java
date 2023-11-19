
/**
 * @BelongsProject: test-11.18
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-18 13:21
 * @Description: 测试类
 * @Author: code_hlb
 */
public class Test {

    /**
     * HashMap 源码当中弊案立案的介绍：
     * 1、static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // HashMap默认的数组容量
     * 2、static final int MAXIMUM_CAPACITY = 1 << 30;        // HashMap最大的数组容量
     * 3、static final float DEFAULT_LOAD_FACTOR = 0.75f;     // 默认的负载因子是0.75
     * 4、static final int TREEIFY_THRESHOLD = 8;    static final int MIN_TREEIFY_CAPACITY = 64;
     * // 当链表长度超过8 && 当数组长度超过64变成红黑树 （树化的条件）
     * 5、static final int UNTREEIFY_THRESHOLD = 6;           // 解除树化的条件
     */
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,3};
        Demo demo = new Demo();
        System.out.println(demo.singleNumber1(nums));
        System.out.println(45000 >>> 1);  // 45000 / 2^1
        System.out.println(45000 >>> 2);  // 45000 / 2^2
        System.out.println(45000 >>> 3);  // 45000 / 2^3
        System.out.println(45000 >>> 4);  // 45000 / 2^4
    }
}
