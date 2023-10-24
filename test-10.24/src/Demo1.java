/**
 * @author: code_hlb
 * @date :  2023/10/24 15:58
 * @desc :  包装类的装箱、拆箱操作
 */
public class Demo1 {

    public static void main(String[] args) {

        // 自动装箱  本质是 Integer nums1 = Integer.valueOf(100);
        Integer nums1 = 100;
        Integer nums2 = 100;
        // true,   Integer.valueOf()方法中定义了一个数组存储[-128,127]的数据,100在范围内，因此可以直接在cache数组中取
        System.out.println(nums1 == nums2);
        Integer nums3 = 128;
        Integer nums4 = 128;
        // false,   超出了127的范围，因此 return new Integer(i);已经是一个新的对象了
        System.out.println(nums3 == nums4);
    }

    public static void main2(String[] args) {
        Integer num = new Integer(10);
        int num1 = num;  // 自动拆箱
        System.out.println(num1);

        // 显式拆箱 拆箱为自己指定的元素
        int num2 = num.intValue();
        System.out.println(num2);

        double num4 = num.doubleValue();
        System.out.println(num4);
    }

    public static void main1(String[] args) {

        // 装箱：把一个基本数据类型转化为包装类型的过程
        // 自动装箱  &  显示装箱
        int num = 10;
        Integer num1 = num;  // 自动装箱
        System.out.println(num1);

        Integer num2 = Integer.valueOf(num);  // 显示装箱
        System.out.println(num2);
    }
}
