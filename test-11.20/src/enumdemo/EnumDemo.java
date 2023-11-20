package enumdemo;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: enumdemo
 * @CreateTime : 2023-11-20 12:04
 * @Description: 枚举
 * @Author: code_hlb
 */
public enum EnumDemo {

    /**
     * 枚举：在jdk1.5后出来,将常量组织起来统一进行管理, 本质是java.lang.Enum的子类,
     * java当中的枚举类非常特殊，都是默认继承于java原生的 Enum;
     * 枚举优点：枚举常量更简单安全，枚举具有内置方法，代码更优雅
     *    缺点： 不可继承，无法扩展
     * 适用场景: 错误状态码，颜色的划分，状态....
     */
    // 枚举对象
    A(1,"优秀"),
    B(2,"良好"),
    C(3,"中等");

    public int ordinal;
    public String level;
    // 枚举类的构造方法默认是私有的，因此不能加 public
    EnumDemo(int ordinal,String level) {
        this.ordinal = ordinal;
        this.level = level;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public String getLevel() {
        return level;
    }

    public static void main(String[] args) {
        EnumDemo enumDemo = EnumDemo.A;
        EnumDemo enumDemo1 = EnumDemo.C;
        System.out.println(enumDemo.getOrdinal() + ": " + enumDemo.getLevel());
        System.out.println(enumDemo1.getOrdinal() + ": " + enumDemo1.getLevel());
    }

    /**
     * 1、values(): 以数组形式返回枚举类型的所有成员
     * 2、ordinal()： 获取枚举成员的索引位置
     * 3、valueOf()： 将普通字符串转换为枚举实例
     * 4、compareTo()： 比较两个枚举成员在定义时的顺序
     */
    // A,B,C;
    public static void main2(String[] args) {
        EnumDemo[] enumDemo = EnumDemo.values();
        for (EnumDemo demo : enumDemo) {
            System.out.println(demo + ": " + demo.ordinal());
        }

        System.out.println("=============");
        EnumDemo enumDemo1 = EnumDemo.valueOf("A");
        System.out.println(enumDemo1);

        System.out.println("=============");
        System.out.println(A.compareTo(B));  // return A.ordinal - B.ordinal;
    }

    public static void main1(String[] args) {
        switch (EnumDemo.A) {
            case A:
                System.out.println("优秀");
                break;
            case B:
                System.out.println("良好");
                break;
            case C:
                System.out.println("中等");
                break;
            default:
                break;
        }
    }

}
