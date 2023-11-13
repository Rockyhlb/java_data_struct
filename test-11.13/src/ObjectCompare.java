
import java.util.Comparator;
import java.util.Objects;

/**
 * @author: code_hlb
 * @date :  2023/11/13 12:50
 * @desc :  对象比较
 */
public class ObjectCompare {
    /**
     * 1、Object.equals  因为所有类都是继承自Object,所以直接重写即可，不够只能比较相等与否
     * 2、Comparable.compareTo  需要手动实现接口，侵入性比较强，但一旦实现，每次用该类都有顺序，属于内部顺序
     * 3、Comparator.compare    需要实现一个比较器对象，对待比较类的侵入性弱，但对算法代码实现侵入性较强
     */
    static class Student {
        public int age;
        public String name;

        public Student(String name) {
            this.name = name;
        }
    }

    static class Student1 {
        public int age;
        public String name;

        public Student1(String name) {
            this.name = name;
        }

        // 手动重写equals
//        @Override
//        public boolean equals(Object obj) {
//            Student1 o = (Student1) obj;
//            return this.name.equals(o.name);
//        }

        // Alt + insert 自动生成
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student1 student = (Student1) o;
            return age == student.age &&
                    Objects.equals(name, student.name);
        }
        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }

    static class Student2 implements Comparable{
        public int age;
        public String name;

        public Student2(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            Student2 obj = (Student2)o;
            return this.name.compareTo(obj.name);
        }
    }

    static class Student3 implements Comparator<Student3> {
        public int age;
        public String name;

        public Student3(String name) {
            this.name = name;
        }

        @Override
        public int compare(Student3 o1, Student3 o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static void main(String[] args) {
        // 此时还未重写equals()方法和实现比较器接口
        Student student1 = new Student("张三");
        Student student2 = new Student("张三");
        System.out.println(student1 == student2);
        System.out.println("Don't Override equals(): " + student1.equals(student2));

        System.out.println("===========重写equals()方法=============");
        Student1 student3 = new Student1("张三");
        Student1 student4 = new Student1("张三");
        System.out.println(student3 == student4);
        System.out.println("Override equals(): " + student3.equals(student4));

        System.out.println("===========实现Comparable接口=============");
        Student2 student5 = new Student2("ab");
        Student2 student6 = new Student2("ab");
        Student2 student7 = new Student2("abc");
        System.out.println(student5 == student6);
        System.out.println("implements Comparable.compareTo: " + student5.compareTo(student6));
        System.out.println("implements Comparable.compareTo: " + student5.compareTo(student7));

        System.out.println("===========实现Comparator接口=============");
        Student3 student8 = new Student3("ab");
        Student3 student9 = new Student3("ab");
        Student3 student10 = new Student3("abc");
        System.out.println(student8 == student9);
        System.out.println("implements Comparable.compareTo: " + student8.compare(student8,student9));
        System.out.println("implements Comparator.compare: " + student8.compare(student8,student10));
    }
}
