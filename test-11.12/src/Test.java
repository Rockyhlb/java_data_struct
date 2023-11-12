import javax.xml.transform.sax.SAXSource;
import java.util.Arrays;
import java.util.Random;

/**
 * @author: code_hlb
 * @date :  2023/11/12 12:49
 * @desc :  测试类
 */
public class Test {

    // 最好情况：完全有序
    public static int[] orderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    // 最坏情况：完全逆序
    public static int[] notOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        return array;
    }

    // 随机情况：既不有序也不逆序
    public static int[] randomOrderArray(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
        return array;
    }

    public static void testInsertSort(int[] array) {
        SortDemo sortDemo = new SortDemo();
        // 拷贝传入参数，避免对原数组进行数据的更改，防止后续测试其它排序算法时出现数据不一致
        int[] tmp = Arrays.copyOf(array,array.length);

        int[] arrays1 = orderArray(tmp);
        long startTime = System.currentTimeMillis();
        sortDemo.insertSort(arrays1);
        long endTime = System.currentTimeMillis();
        System.out.println("有序数组插入排序运行时间" + (endTime - startTime));

        int[] arrays2 = notOrderArray(tmp);
        long startTime1 = System.currentTimeMillis();
        sortDemo.insertSort(arrays2);
        long endTime1 = System.currentTimeMillis();
        System.out.println("逆序数组插入排序运行时间" + (endTime1 - startTime1));

        int[] arrays3 = randomOrderArray(tmp);
        long startTime2 = System.currentTimeMillis();
        sortDemo.insertSort(arrays3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("随机数组插入排序运行时间" + (endTime2 - startTime2));
    }

    public static void testShellSort(int[] array) {
        SortDemo sortDemo = new SortDemo();
        // 拷贝传入参数，避免对原数组进行数据的更改，防止后续测试其它排序算法时出现数据不一致
        int[] tmp = Arrays.copyOf(array,array.length);

        int[] arrays1 = orderArray(tmp);
        long startTime = System.currentTimeMillis();
        sortDemo.shellSort(arrays1);
        long endTime = System.currentTimeMillis();
        System.out.println("有序数组希尔排序运行时间" + (endTime - startTime));

        int[] arrays2 = notOrderArray(tmp);
        long startTime1 = System.currentTimeMillis();
        sortDemo.shellSort(arrays2);
        long endTime1 = System.currentTimeMillis();
        System.out.println("逆序数组希尔排序运行时间" + (endTime1 - startTime1));

        int[] arrays3 = randomOrderArray(tmp);
        long startTime2 = System.currentTimeMillis();
        sortDemo.shellSort(arrays3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("随机数组希尔排序运行时间" + (endTime2 - startTime2));
    }

    public static void testSelectSort(int[] array) {
        SortDemo sortDemo = new SortDemo();
        // 拷贝传入参数，避免对原数组进行数据的更改，防止后续测试其它排序算法时出现数据不一致
        int[] tmp = Arrays.copyOf(array,array.length);

        int[] arrays1 = orderArray(tmp);
        long startTime = System.currentTimeMillis();
        sortDemo.selectSort(arrays1);
        long endTime = System.currentTimeMillis();
        System.out.println("有序数组选择排序运行时间" + (endTime - startTime));

        int[] arrays2 = notOrderArray(tmp);
        long startTime1 = System.currentTimeMillis();
        sortDemo.selectSort(arrays2);
        long endTime1 = System.currentTimeMillis();
        System.out.println("逆序数组选择排序运行时间" + (endTime1 - startTime1));

        int[] arrays3 = randomOrderArray(tmp);
        long startTime2 = System.currentTimeMillis();
        sortDemo.selectSort(arrays3);
        long endTime2 = System.currentTimeMillis();
        System.out.println("随机数组选择排序运行时间" + (endTime2 - startTime2));
    }

    public static void main(String[] args) {
        int[] arrays = new int[100000];
        System.out.println("=========直接插入排序==============");
        testInsertSort(arrays);
        System.out.println("=========希尔排序==============");
        testShellSort(arrays);
        System.out.println("=========选择排序==============");
        testSelectSort(arrays);
    }
}
