package stack;

import netscape.security.UserTarget;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: stack
 * @CreateTime : 2024/5/20 21:05
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo3 {
    /**
     * 150. 逆波兰表达式求值
     * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * 请你计算该表达式。返回一个表示表达式值的整数。
     * 注意：
     * 有效的算符为 '+'、'-'、'*' 和 '/' 。
     * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
     * 两个整数之间的除法总是 向零截断 。
     * 表达式中不含除零运算。
     * 输入是一个根据逆波兰表示法表示的算术表达式。
     * 答案及所有中间计算结果可以用 32 位 整数表示。
     * 示例 1：
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * 示例 2：
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     * 示例 3：
     * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
     * 输出：22
     * 解释：该算式转化为常见的中缀算术表达式为：
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * 提示：
     * 1 <= tokens.length <= 104
     * tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
     */
    public static int evalRPN(String[] tokens) {
        Deque<Integer> digit = new LinkedList<>();
        for (String s : tokens) {
            if (!(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))) {
                // 当前字符是数字时
                digit.push(Integer.valueOf(s));
            } else {
                // 当前字符是操作符时
                int right = digit.pop();  // 弹出的第一个元素是右值
                int left = digit.pop();
                int res = 0;
                switch (s) {
                    case "+":
                        res = left + right;
                        break;
                    case "-":
                        res = left - right;
                        break;
                    case "*":
                        res = left * right;
                        break;
                    default:
                        res = left / right;
                        break;
                }
                digit.push(res);
            }
        }
        return digit.peek();
    }

    public static int evalRPN1(String[] tokens) {
        // 在上面的基础上进行优化
        Deque<Integer> stack = new LinkedList<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    // 2 - 3  <==> -3 + 2
                    stack.push(-1 * stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int right = stack.pop();
                    stack.push(stack.pop() / right);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        // 输入：tokens = ["2","1","+","3","*"]
        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens1 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));    // 9
        System.out.println(evalRPN(tokens1));   // 22

        System.out.println(evalRPN1(tokens));    // 9
        System.out.println(evalRPN1(tokens1));   // 22
    }
}
