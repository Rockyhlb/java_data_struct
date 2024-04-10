package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: stack
 * @CreateTime : 2024/4/10 14:29
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo2 {
    /**
     * 71. 简化路径
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
     * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
     * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     * 请注意，返回的 规范路径 必须遵循下述格式：
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     * <p>
     * 示例 1：
     * 输入：path = "/home/"
     * 输出："/home"
     * 解释：注意，最后一个目录名后面没有斜杠。
     * <p>
     * 示例 2：
     * 输入：path = "/../"
     * 输出："/"
     * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
     */
    public static String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        // 思路：直接将path 按照“/”进行分割，遇见'..'弹出一个元素(路径)，遇见'.'不进行处理，这两种字符都不是时，说明该字符只能是路径，就压栈
        // 最后再将栈中读取到的元素再每个的前面加上分隔符“/”进行拼接
        // 采用栈记录简化后的路径
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }else if (!item.isEmpty() && !item.equals(".")) {
                stack.push(item);
            }
        }
        // 遍历栈，拼接结果
        String res = "";
        for (String item : stack) {
            res = "/" + item + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    public static void main(String[] args) {
        String path = "/home/";
        String path1 = "/../";
        String path2 = "/a/./b/../../c/";
        System.out.println(simplifyPath(path));
        System.out.println(simplifyPath(path1));
        System.out.println(simplifyPath(path2));
    }
}
