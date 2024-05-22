package test;

import java.util.Objects;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: test
 * @CreateTime : 2024/5/21 19:36
 * @Description: TODO
 * @Author: code_hlb
 */

class User {
    Long id;
    String name;
    Integer age;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }
}

public class Demo1 {
    public static void main(String[] args) {
        User user = new User();
        user.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        // HashCode 相等，对象不一定相等
        // 对象相等，那么equals()返回true，且HashCode相等
        System.out.println(user == user1);  // false
        System.out.println(user.hashCode() == user1.hashCode());  // true
        System.out.println(user.equals(user1));  // true
    }
}
