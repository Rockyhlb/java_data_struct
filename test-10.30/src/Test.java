/**
 * @author: code_hlb
 * @date :  2023/10/30 15:28
 * @desc :
 */
public class Test {
    public static void main(String[] args) {

        Demo1 demo1 = new Demo1();
        System.out.println();
        int[] nums = {1,3,5,6};
        System.out.println(demo1.searchInsert(nums, 7));
        System.out.println(6>>>1);  // 等价于6/2

        System.out.println("==================");

        int[] heights = {2,3,4,5,18,17,6};
        System.out.println(demo1.maxArea1(heights));
    }
}
