
import java.util.Stack;

/**
 * @author: code_hlb
 * @date :  2023/11/3 15:13
 * @desc :  LeetCode--stack
 */
public class Demo1 {
    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     *
     * 1、输入：s = "()"        输出：true
     * 2、输入：s = "()[]{}"    输出：true
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }else {
                if(stack.empty()) {
                    return false;
                }
                char left = stack.peek();
                if(left == '(' && ch == ')') {
                    stack.pop();
                }else if (left == '[' && ch == ']') {
                    stack.pop();
                }else if(left == '{' && ch == '}') {
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        if(stack.empty()) {
            return true;
        }
        return false;
    }

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
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (isNumber(str)) {
                stack.push(Integer.parseInt(str));
            } else {
                int right = stack.pop();
                int left = stack.pop();
                switch (str) {
                    case "+":
                        stack.push(left + right);
                        break;
                    case "-":
                        stack.push(left - right);
                        break;
                    case "*":
                        stack.push(left * right);
                        break;
                    case "/":
                        stack.push(left / right);
                        break;
                    default:
                        break;
                }
            }
        }
        if(!stack.empty()) {
            return stack.pop();
        }
        return 0;
    }

    private boolean isNumber(String str) {
       return !(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"));
    }
}
