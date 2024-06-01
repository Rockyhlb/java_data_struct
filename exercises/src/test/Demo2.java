package test;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: test
 * @CreateTime : 2024/6/1 22:48
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    public static void main1(String[] args) {
        Integer io1 = 99;
        int io2 = 99;
        System.out.println(io1 == io2);
        Integer io3 = Integer.valueOf(99);
        System.out.println(io1 == io3);
        System.out.println(io2 == io3);
        Integer io4 = new Integer(99);
        System.out.println(io1 == io4);
        System.out.println(io2 == io4);
        System.out.println(io3 == io4);
    }

    public static void main2(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println(System.currentTimeMillis());
        });

        t1.start();
        t2.start();
    }
}
