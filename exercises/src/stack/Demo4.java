package stack;

import java.util.*;

/**
 * @BelongsProject: exercises
 * @BelongsPackage: stack
 * @CreateTime : 2024/5/21 8:30
 * @Description: TODO
 * @Author: code_hlb
 */
public class Demo4 {
    /**
     * 224. 基本计算器
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * 示例 1：
     * 输入：s = "1 + 1"
     * 输出：2
     * 示例 2：
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * 示例 3：
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     * 提示：
     * 1 <= s.length <= 3 * 105
     * s 由数字、'+' , '-' , '(' , ')' 和 ' ' 组成
     * s 表示一个有效的表达式
     * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
     * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
     * 输入中不存在两个连续的操作符
     * 每个数字和运行的计算将适合于一个有符号的 32位 整数
     */
    public static int calculate(String s) {
        // 1、将中缀表达式 转换 为后缀表达式
        String postfix = inToPost(s);
        // 2、对后缀表达式进行求解
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < postfix.length(); i++) {
            // 不能使用char接收，不然后续会转换成ASCII码存储
            String elem = postfix.charAt(i) + "";
            switch (elem) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    if (1 == stack.size()) {
                        // 处理负数
                        stack.push(-1 * stack.pop());
                        break;
                    }
                    // 交换律
                    stack.push(-1 * stack.pop() + stack.pop());
                    break;
                default:
                    stack.push(Integer.valueOf(elem));
                    break;
            }
        }
        if (stack.size() == 1) {
            return stack.peek();
        }
        // 说明没有操作符
        return Integer.parseInt(postfix);
    }

    /**
     * 中缀表达式 --> 后缀表达式
     *
     * @param infix
     * @return 后缀表达式
     */
    private static String inToPost(String infix) {
        Deque<String> digitStack = new LinkedList<>();
        Deque<Character> operateStack = new LinkedList<>();
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (Character.isDigit(ch)) {
                digitStack.push(ch + "");
            } else if (ch != '(' && ch != ')') {
                // 处理操作符
                int curPri = getPriority(ch);   // 获取优先级
                if (0 == curPri) {
                    // 当前字符是非操作符时(空格)，跳出本次循环
                    continue;
                }
                while (true) {
                    if (operateStack.isEmpty() || operateStack.peek() == '(') {
                        // 1、如果operateStack为空 || 栈顶运算符为'(',压入运算符栈
                        operateStack.push(ch);
                        break;
                    } else if (curPri > getPriority(operateStack.peek())) {
                        // 2、如果当前优先级 比 操作符栈顶元素优先级高，也压入操作符栈
                        operateStack.push(ch);
                        break;
                    } else {
                        // 3、默认弹出operateStack栈顶并压入digitStack
                        digitStack.push(operateStack.pop() + "");
                    }
                }
            } else {
                // 处理括号
                if (ch == '(') {
                    operateStack.push(ch);
                } else {
                    // 如果遇见右括号，则依次从operateStack弹出元素压入digitStack，直到'('为止
                    while (!operateStack.isEmpty() && operateStack.peek() != '(') {
                        char ope = operateStack.pop();
                        digitStack.push(ope + "");
                    }
                    // 丢掉左括号
                    operateStack.pop();
                }
            }
        }
        // 遍历完中缀表达式，将operateStack剩余元素压入digitStack
        while (!operateStack.isEmpty()) {
            digitStack.push(operateStack.pop() + "");
        }
        // 返回后缀表达式
        StringBuilder sbd = new StringBuilder();
        while (!digitStack.isEmpty()) {
            sbd.append(digitStack.pop());
        }
        return sbd.reverse().toString();
    }

    /**
     * 获取优先级
     */
    private static int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                break;
        }
        return 0;
    }

    private static final Deque<Integer> diStack = new LinkedList<>();
    private static final Deque<Character> opStack = new LinkedList<>();

    /**
     * 通用解法
     */
    public static int calculate1(String s) {
        // 输入只包含数字，操作符，空格，及左右括号
        s = s.replaceAll(" ", "");  // 去除空格
        int len = s.length();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < len; i++) {
            char ch = charArray[i];
            if (ch == '(') {
                // 处理左括号
                opStack.push(ch);
            } else if (ch == ')') {
                // 处理右括号
                while (opStack.peek() != '(') {
                    calc();
                }
                // 弹出左括号
                opStack.pop();
            } else if (Character.isDigit(ch)) {
                // 处理数字
                int num = 0;
                int j = i;  // 遍历连续数字
                while (j < len && Character.isDigit(charArray[j])) {
                    num = num * 10 + (charArray[j++] - '0');
                }
                diStack.push(num);
                // 当前j是操作符的位置，将i归位到当前操作符的前一个位置(后续i++再复位)
                i = j - 1;
            } else {
                // 处理操作符
                // i==0 && ch=='-' 应对第一个数是负数的情况：-2+1
                // i > 0 && charArray[i - 1]=='(' 应对的是这种情况：1-(-2)
                if (i == 0 && ch == '-' || i > 0 && charArray[i - 1] == '(') {
                    diStack.push(0);
                }
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    char prevOp = opStack.peek();
                    if (getPriority(prevOp) >= getPriority(ch)) {
                        calc();
                    } else {
                        break;
                    }
                }
                opStack.push(ch);
            }
        }
        // 计算剩余运算符
        while (!opStack.isEmpty()) {
            calc();
        }
        return diStack.peek();
    }

    /**
     * 计算两个操作数的计算结果
     */
    private static void calc() {
        // 处理边界
        if (diStack.size() < 2 || opStack.size() < 1) {
            return;
        }
        // 计算逻辑
        int right = diStack.pop(), left = diStack.pop();
        char op = opStack.pop();
        int res = 0;
        switch (op) {
            case '+':
                res = left + right;
                break;
            case '-':
                res = left - right;
                break;
            case '*':
                res = left * right;
                break;
            case '/':
                res = left / right;
                break;
            case '^':
                res = (int) Math.pow(left, right);
                break;
            case '%':
                res = left % right;
                break;
            default:
                break;
        }
        diStack.push(res);
    }

    public static void main(String[] args) {
        String tokens = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(inToPost(tokens));
        System.out.println(calculate(tokens));
        System.out.println(calculate1(tokens));

        String tokens1 = "2113321221";
        System.out.println(inToPost(tokens1));
        System.out.println(calculate(tokens1));
        System.out.println(calculate1(tokens1));
    }
}
