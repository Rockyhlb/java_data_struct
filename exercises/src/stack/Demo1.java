package stack;

import java.util.Stack;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: stack
 * @CreateTime : 2024/4/10 14:15
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo1 {

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = stack.peek();
                if (left == '(' && ch == ')') {
                    stack.pop();
                }else if (left == '{' && ch == '}') {
                    stack.pop();
                }else if (left == '[' && ch == ']') {
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "(){}";
        System.out.println(isValid(str));
    }
}
