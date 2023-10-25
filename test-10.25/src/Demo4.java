import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: code_hlb
 * @date :  2023/10/25 18:08
 * @desc :  杨辉三角
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * 1 4 6 4 1
 */
public class Demo4 {

    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        // 杨辉三角的第一行数据已经处理完毕
        row.add(1);
        ret.add(row);
        // 下面从第二行开始
        for (int i = 1; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            // 当前行的第一个值
            curRow.add(1);
            // 当前行的中间值
            // 上一行
            List<Integer> prevRow = ret.get(i - 1);
            for (int j = 1; j < i; j++) {
                // 同列数据等于 上一行的同列数据加上一列数据
                curRow.add(prevRow.get(j) + prevRow.get(j - 1));
            }
            // 当前行的最后一个值
            curRow.add(1);
            // 将该行插入到整个列表中
            ret.add(curRow);
        }
        return ret;
    }

    public static List<List<Integer>> func2(int numRow){
        // 行
        List<List<Integer>> list2 = new ArrayList<>();
        // 列
        List<Integer> col = new ArrayList<>();
        // 第一行数据
        col.add(1);
        list2.add(col);

        for (int i = 1; i < numRow; i++) {

            List<Integer> newCol = new ArrayList<>();
            // 第一行
            newCol.add(1);
            for (int j = 1; j < i; j++) {
                // 中间数据
                // 保存上一行
                List<Integer> prevRRow = list2.get(i - 1);
                newCol.add(prevRRow.get(j) + prevRRow.get(j-1));
            }
            // 最后一列
            newCol.add(1);

            list2.add(newCol);
        }
        return list2;
    }

    public static void main(String[] args) {

        // System.out.println(generate(5));

        Scanner sc = new Scanner(System.in);

        System.out.print("请输入杨辉三角的行数：");
        int row = sc.nextInt();

        List<List<Integer>> list = generate(row);
        // 取出行
        for (int i = 0; i < list.size(); i++) {
            // 取出列
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.print(list.get(i).get(j) + " ");
            }
            System.out.println();
        }

        System.out.println(func2(5));

    }

    public static void main1(String[] args) {
        // 列表当中再包含列表  --> 类似于二维数组
        List<List<Integer>> list1 = new ArrayList<>();
        list1.add(new ArrayList<>());
        list1.get(0).add(1);
        System.out.println(list1.get(0).get(0));

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        System.out.println(list1.get(0));
    }
}
