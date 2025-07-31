package com.wingflare.lib.captcha.image;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字符串计算器
 * @link https://www.cnblogs.com/woider/p/5331391.html
 */
public class Calculator {

    private static String transform(String expression) {
        char[] arr = expression.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '-') {
                if (i == 0) {
                    arr[i] = '~';
                } else {
                    char c = arr[i - 1];
                    if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == 'E' || c == 'e') {
                        arr[i] = '~';
                    }
                }
            }
        }
        // 仅当整个表达式以~开头时才添加0
        return (arr[0] == '~') ? "0" + new String(arr) : new String(arr);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')';
    }

    public static double conversion(String expression) {
        double result = 0;
        try {
            String transformed = transform(expression);
            result = calculate(transformed);
        } catch (Exception e) {
            return 0.0 / 0.0; // 返回NaN表示计算错误
        }
        return result;
    }

    private static double calculate(String expression) {
        Deque<String> postfixQueue = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        opStack.push(','); // 栈底标记

        char[] arr = expression.toCharArray();
        int currentIndex = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (isOperator(c)) {
                if (count > 0) {
                    postfixQueue.addLast(new String(arr, currentIndex, count));
                }
                count = 0;
                currentIndex = i + 1;

                char peekOp = opStack.peek();
                if (c == ')') {
                    while (opStack.peek() != '(') {
                        postfixQueue.addLast(String.valueOf(opStack.pop()));
                    }
                    opStack.pop(); // 弹出 '('
                } else {
                    while (c != '(' && peekOp != ',' && compare(c, peekOp)) {
                        postfixQueue.addLast(String.valueOf(opStack.pop()));
                        peekOp = opStack.peek();
                    }
                    opStack.push(c);
                }
            } else {
                count++;
            }
        }

        if (count > 0) {
            postfixQueue.addLast(new String(arr, currentIndex, count));
        }

        while (opStack.size() > 1) {
            postfixQueue.addLast(String.valueOf(opStack.pop()));
        }

        // 计算后缀表达式
        Deque<String> resultStack = new ArrayDeque<>();
        while (!postfixQueue.isEmpty()) {
            String current = postfixQueue.removeFirst();
            char firstChar = current.charAt(0);

            if (!isOperator(firstChar)) {
                resultStack.push(current.replace("~", "-"));
            } else {
                String b = resultStack.pop();
                String a = resultStack.pop();
                a = a.replace("~", "-");
                b = b.replace("~", "-");

                String tempResult = calculate(a, b, firstChar);
                resultStack.push(tempResult);
            }
        }

        return Double.parseDouble(resultStack.pop());
    }

    private static String calculate(String first, String second, char op) {
        return switch (op) {
            case '+' -> String.valueOf(ArithHelper.add(first, second));
            case '-' -> String.valueOf(ArithHelper.sub(first, second));
            case '*' -> String.valueOf(ArithHelper.mul(first, second));
            case '/' -> String.valueOf(ArithHelper.div(first, second));
            default -> throw new IllegalArgumentException("Unsupported operator: " + op);
        };
    }

    private static boolean compare(char cur, char peek) {
        int[] priority = {0, 3, 2, 1, -1, 1, 0, 2};
        return priority[peek - 40] >= priority[cur - 40];
    }

}
