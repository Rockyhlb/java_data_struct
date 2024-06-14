package sort;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: sort
 * @CreateTime : 2024/6/14 11:46
 * @Description: TODO
 * @Author: code_hlb
 */
public class Test {
    public static void main(String[] args) {
        TestInterface bubble = new BubbleSort();
        bubble.testSingle(new int[]{1,3,1,2,4,2,5,6,4,8,7,10,9});
        // 测试 有序情况，逆序情况，随机情况 排序时长
        bubble.testAll(CaseFactory.createOrderCase(),
                       CaseFactory.createReverseOrderCase(),
                       CaseFactory.createRandomCase());

        TestInterface insert = new InsertSort();
        insert.testSingle(new int[]{1,3,1,2,4,2,5,6,4,8,7,10,9});
        insert.testAll(CaseFactory.createOrderCase(),
                       CaseFactory.createReverseOrderCase(),
                       CaseFactory.createRandomCase());
    }
}
