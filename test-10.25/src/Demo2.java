import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: code_hlb
 * @date :  2023/10/25 12:42
 * @desc :  ArrayList
 */
public class Demo2 {

    // 循环的遍历
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        System.out.println("=========for(;;)============");
        // 1、for 循环遍历
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }

        System.out.println("\n=========foreach(Integer num: arrayList)============");
        // 2、foreach循环  -->  不能拿到下标，只适用于遍历
        for (Integer num: arrayList) {
            System.out.print(num + " ");
        }

        System.out.println("\n=========list.iterator()============");
        // 3、迭代器遍历
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }

        System.out.println("\n=========list.listIterator()============");
        // ListIterator 继承自 Iterator
        ListIterator<Integer> listIterator = arrayList.listIterator();
        while (listIterator.hasNext()){
            System.out.print(listIterator.next() + " ");
        }

        System.out.println("\n=========list.iterator(list.size())============");
        // 通过迭代器从后往前遍历
        ListIterator<Integer> listIterator1 = arrayList.listIterator(arrayList.size());
        while (listIterator1.hasPrevious()){
            System.out.print(listIterator1.previous() + " ");
        }
    }

    // 测试集合框架中的ArrayList  subList不是对原列表的拷贝！！而是指向这个地址；
    public static void main3(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);

        System.out.println("=========remove(2)===========");
        // 此时默认是删除下标为2的数据
        arrayList.remove(2);
        // 一般情况下能直接sout输出引用指向对象当中的内容的时候，此时一定重写了toString方法
        System.out.println(arrayList);

        System.out.println("=========remove(new Integer(2)===========");
        // 如果是想删除数据2呢
        arrayList.remove(new Integer(2));
        System.out.println(arrayList);

        System.out.println("=========get(1)===========");
        System.out.println(arrayList.get(1));

        System.out.println("=========set(0,99)===========");
        System.out.println(arrayList.set(0,99));
        System.out.println(arrayList);

        System.out.println("=========subList(0,2)===========");
        // subList 的返回值是List  左闭右开
        List<Integer> arrayList1 = arrayList.subList(0,2);
        // Out: [99, 4]
        System.out.println(arrayList1);

        System.out.println("================================");
        // 注：subList 的本质并不将之前的list数据拿出来，而是指向了这部分的地址，
        // 因此如果arrayList1修改数据，arraylist对应的数据也会被更改
        arrayList1.set(0,-1);
        // Out: [-1, 4]    [-1, 4, 5, 6]
        System.out.println(arrayList1);
        System.out.println(arrayList);
    }

    // 测试集合框架中的ArrayList
    public static void main2(String[] args) {

        // 方法更多
        // ArrayList 是泛型实现的，实现了 RandomAccess 支持随机访问, Cloneable 支持克隆, java.io.Serializable 支持序列化
        // 和Vector不同，ArrayList不是线程安全的，在单线程下可以使用，在多线程中可以选择Vector或者CopyOnWriteArrayList
        // ArrayList底层是一段连续空间，并且可以动态扩容，是一个动态类型的顺序表
        ArrayList<Integer> arrayList = new ArrayList<>(15); // 指定容量

        /* private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
          public ArrayList() { this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; } */

        // 上述源码表示 ArrayList的无参构造方法并没有分配内存
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        // 没有分配内存，但是可以在里面放元素
        // 这是因为 当我们调用不带参数的构造方法的时候，默认在第一次add的时候才会分配大小为10的内存！！
        // 并且扩容是按照 1.5 倍的方式进行扩容的，具体细节可以观察底层源码得知~
        arrayList1.add(12);
        System.out.println(arrayList1);

        // 更加灵活，可以发生向上向下转型，调用的add等方法本质上还是ArrayList重写的方法
        List<Integer> list = new ArrayList<>();
        list.add(23);
        list.add(13);
        list.add(43);

        // 调用构造方法：public ArrayList(Collection<? extends E> c)
        List<Integer> list1 = new ArrayList<>(list);
        list1.add(89);
        System.out.println(list1);

    }

    // 测试MyArrayList
    public static void main1(String[] args) {

        MyArrayList myArrayList = new MyArrayList(4);

        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.display();
        System.out.println("\noldArrayList.length= " + myArrayList.size());

        // 数组已满，对数组进行扩容
        myArrayList.add(5);
        myArrayList.add(10);
        myArrayList.display();
        System.out.println("newArrayList.length= " + myArrayList.size());

        System.out.println("====================");
        myArrayList.add(2,10);
        // myArrayList.add(10,10);
        myArrayList.display();

        System.out.println("========contains()============");
        // 判定是否包含元素4  --> true
        System.out.println(myArrayList.contains(4));

        System.out.println("========indexOf()============");
        // 查找元素"1"对应的位置 --> 0
        System.out.println(myArrayList.indexOf(1));

        System.out.println("========get()============");
        // 查找下标2对应的元素  --> 10
        System.out.println(myArrayList.get(2));

        System.out.println("========set()============");
        // 将pos位置的元素设置为value  [100 2 10 3 4 5 ]
        myArrayList.set(0, 100);
        myArrayList.display();

        System.out.println("========remove()============");
        // 删除第一次出现的关键字key
        myArrayList.remove(10);
        myArrayList.display();

        System.out.println("========remove()============");
        myArrayList.clear();
        myArrayList.display();
    }
}
