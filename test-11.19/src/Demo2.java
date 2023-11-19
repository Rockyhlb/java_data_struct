
/**
 * @BelongsProject: test-11.19
 * @BelongsPackage: PACKAGE_NAME
 * @CreateTime : 2023-11-19 16:15
 * @Description: 泛型进阶  通配符
 * @Author: code_hlb
 */

public class Demo2 {
    static class Food {}
    static class Fruit extends Food {}
    static class Apple extends Fruit{}
    static class Banana extends Fruit{}

    static class Dish<T> {
        private T dish;

        public T getDish() {
            return dish;
        }

        public void setDish(T dish) {
            this.dish = dish;
        }
    }

    public static void funcSuper(Dish <? super Fruit> tmp) {
        tmp.setDish(new Apple());  // Fruit的子类  向上转型
        tmp.setDish(new Fruit());  // Fruit本身
        // 无法接受，因为不确定是哪个父类
        // Fruit fruit = tmp.getDish();
        System.out.println(tmp.getDish());
    }

    public static void main(String[] args) {
       Dish<Apple> dish1 = new Dish<>();
       Dish<Fruit> dish2 = new Dish<>();
       Dish<Food> dish3 = new Dish<>();
       // 只能传父类或者该类 ,dish1属于Fruit的子类，因此不能传入
       // funcSuper(dish1);
       funcSuper(dish2);
       funcSuper(dish3);
    }

    static class Message<E> {
        private E inform;

        public void setInform(E inform) {
            this.inform = inform;
        }
        public E getInform() {
            return inform;
        }
    }

    // 通配符的使用
    public static void func(Message<?> mes) {
        System.out.println(mes.getInform());
    }

    // 通配符的上界
    public static void funcExtendsNumber(Message<? extends Number> mes) {
        System.out.println(mes.getInform());
        // 由于不确定具体类型，因此通配符的上界限制了不可以修改元素的值
        /*mes.setInform(new Integer(98));
        mes.setInform(new Double(98.2));*/
        // 向上转型
        Number number = mes.getInform();
    }

    public static void main1(String[] args) {
        Message<String> stringMessage = new Message<String>();
        stringMessage.setInform("hello");
        func(stringMessage);

        Message<Integer> intMessage = new Message<>();
        intMessage.setInform(2023);
        func(intMessage);

        Message<Double> doubleMessage = new Message<>();
        doubleMessage.setInform(76.8);

        funcExtendsNumber(intMessage);
        funcExtendsNumber(doubleMessage);
        // 此处的通配符上限是 Number,因此不能传入 stringMessage
        // funcExtendsNumber(stringMessage);
    }
}
