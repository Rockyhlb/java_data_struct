package reflectdemo;

/**
 * @BelongsProject: test-11.20
 * @BelongsPackage: reflectdemo
 * @CreateTime : 2023-11-20 10:43
 * @Description: 学生类，定义了私有和公有的成员属性、成员方法、构造方法
 * @Author: code_hlb
 */
public class Student {
    // 私有属性
    private String name = "java";
    // 公有属性
    public int  age = 18;
    // 公有的不带参数的构造方法
    public Student() {
        System.out.println("Student()");
    }
    // 私有的带参数的构造方法
    private Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student(name,age)");
    }
    // 私有的成员方法
    private void eat() {
        System.out.println("I am eating");
    }
    // 公有的成员方法
    public void sleep() {
        System.out.println("I am sleeping");
    }
    // 私有的带参成员方法
    private void function(String str) {
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
