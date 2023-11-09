/**
 * @author: code_hlb
 * @date :  2023/11/9 9:40
 * @desc :
 */
public class Demo {
    /**
     * 151. 反转字符串中的单词
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
     * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。6
     */
    public static String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder sbd = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if(!strs[i].equals("") && i != 0) {
                sbd.append(strs[i]);
                sbd.append(" ");
            }
            if (i == 0) {
                sbd.append(strs[i]);
            }
        }
        return sbd.toString().trim();
    }

    /**
     * 80. 删除有序数组中的重复项 II
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留
     * 对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留
     */
    public static int removeDuplicates(int[] nums) {
        return process(nums, 2);
    }
    static int process(int[] nums, int k) {
        int u = 0;
        for (int x : nums) {
            if (u < k || nums[u - k] != x) nums[u++] = x;
        }
        return u;
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        int[] nums = {1,1,1,2,3,3,3,4,4};
        removeDuplicates(nums);
    }
}
