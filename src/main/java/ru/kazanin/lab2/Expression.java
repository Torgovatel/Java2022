package ru.kazanin.lab2;

import java.util.Stack;
import java.util.Objects;

/**
 * Класс для обработки выражения.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Expression{

    /**
     * Строка, содержащая выражение (скобки, числа, операторы).
     */
    private String buffer;

    /**
     * Строка, содержащая выражение в постфиксной форме.
     */
    private String postfix;

    /**
     * Результат подсчета выражения.
     */
    private Double result;

    /**
     * Флаг корректности выражения.
     */
    private Boolean isCorrect;

    /**
     * Конструктор выражения по строке.<br>
     * Выполняет {@link #setNewExpression(String)} для переданного параметра.
     * @param str строка, содержащая выражение.
     */
    public Expression(String str) {
        setNewExpression(str);
    }

    /**
     * Метод проверки корректности выражения.
     * @return
     * <ul>
     *     <li><b><i>True</i></b> - если выражение составлено корректно</li>
     *     <li><b><i>False</i></b> - в ином случае</li>
     * </ul>
     */
    public boolean checkCorrect()
    {
        // Если уже подсчитано, возвращаем поле
        if (isCorrect != null)
            return isCorrect;

        // Иначе проверяем корректность
        if (buffer.isEmpty())
            return false;

        String buf = delSpace(buffer);
        int cnt_bracket = 0;
        for (int i = 0; i < buf.length(); i++) {
            if (cnt_bracket >= 0) {
                switch (buf.charAt(i)) {
                    case '+': case '-': case '*': case '/':
                        // На концах выражения не может быть знаков операций
                        if(i == 0 || i == buf.length() - 1)
                            return false;
                        // Недопустимы две операции подряд (и операция перед закрывающей скобкой)
                        else if(buf.charAt(i + 1) == '+' || buf.charAt(i + 1) == '-' ||
                                buf.charAt(i + 1) == '*' || buf.charAt(i + 1) == '/' ||
                                buf.charAt(i + 1) == ')')
                            return false;
                        break;

                    case '(':
                        ++cnt_bracket;
                        // Недопустима операция после открывающей скобки (и закрывающая сразу после открывающей)
                        if (buf.charAt(i + 1) == '+' || buf.charAt(i + 1) == '-' ||
                                buf.charAt(i + 1) == '*' || buf.charAt(i + 1) == '/' ||
                                buf.charAt(i + 1) == ')')
                            return false;
                        // Еще 1 проверка на операцию в конце выражения
                        else if (i == buf.length() - 1)
                            return false;
                        break;

                    case ')':
                        --cnt_bracket;
                        // Выражение не может начинаться с закрывающей скобки
                        if(i == 0)
                            return false;
                        // Проверка некорректного символа перед закрывающей скобкой
                        else if(buf.charAt(i - 1) == '+' ||
                                buf.charAt(i - 1) == '-' ||
                                buf.charAt(i - 1) == '*' ||
                                buf.charAt(i - 1) == '/' ||
                                buf.charAt(i - 1) == '(' )
                            return false;
                        break;

                    default:
                        // Случай ввода символа
                        if (buf.charAt(i) >= '0' && buf.charAt(i) <= '9') {
                            if (i != 0)
                                if (buf.charAt(i - 1) == ')' )
                                    // Перед цифрой не должна стоять закрывающая скобка
                                    return false;
                            if (i != buf.length() - 1)
                                if (buf.charAt(i + 1) == '(' )
                                    // Перед цифрой не должна стоять открывающая скобка
                                    return false;
                        // В выражении не может быть недопустимых знаков
                        } else
                            return false;
                }
            }
            else
                // cnt_bracket < 0 : Если условие баланса скобок было нарушено в цикле
                return false;
        }
        // Проверяем соблюдение баланса после цикла
        return cnt_bracket == 0;
    }

    /**
     * Метод определения приоритета оператора.
     * @param ch символ для проверки.
     * @return целое число в интервале -1..3 - приоритет операции.
     */
    private int priority(char ch) {
        if (ch == '*' || ch == '/')
            return 3;
        else if (ch == '+' || ch == '-')
            return 2;
        else if (ch == '(')
            return 1;
        else if (ch == ')')
            return -1;
        return 0;
    }

    /**
     * Метод приведения выражения в постфиксную форму.<br>
     * Алгоритм со времен 2 курса, ЯМП.
     * @return
     * <ul>
     *     <li><b><i>null</i></b> - если форма не может быть построена</li>
     *     <li>{@link String}, содерщая постфиксную форму - в ином случае</li>
     * </ul>
     */
    private String createPostfixNotation() {
        // Если форма уже создана, возвращаем ее
        if (postfix != null)
            return postfix;

        // Иначе, конструируем ее
        // Для некорректного выражения форма не может быть построена
        if (!isCorrect)
            return null;

        Stack<Character> charStack = new Stack<>();
        StringBuilder newString = new StringBuilder();
        String buf = delSpace(buffer);

        for(int i = 0; i < buf.length(); i++) {
            // Считаем приоритет текущей операции (или символа)
            int operPriority = priority(buf.charAt(i));

            // Если это приоритет как у символа, то записываем в результат
            if (operPriority == 0)
                newString.append(buf.charAt(i));

            // Открывающие скобки добавляем в стек
            else if (operPriority == 1)
                charStack.push(buf.charAt(i));

            // Знаки операций проверяем и по необходимости раскручиваем стек с большими по приоритету операциями
            else if (operPriority > 1){
                newString.append(' ');
                while (!charStack.empty()) {
                    if (priority(charStack.peek()) >= operPriority)
                        newString.append(charStack.pop());
                    else
                        break;
                }
                charStack.push(buf.charAt(i));
            }

            // Для закрывающих скобок раскручиваем стек по операциям
            else if (operPriority == -1) {
                newString.append(' ');
                while (priority(charStack.peek()) != 1)
                    newString.append(charStack.pop());
                charStack.pop();
            }
        }
        // После цикла раскручиваем со стека оставшиеся операции
        while (!charStack.empty())
            newString.append(charStack.pop());

        return newString.toString();
    }

    /**
     * Метод вычисления значения выражения.<br>
     * @return
     * <ul>
     *     <li><b><i>null</i></b> - если выражение некорректно</li>
     *     <li><b><i>double</i></b> значение выражения - в ином случае</li>
     * </ul>
     */
    public Double calculate() {

        if (!isCorrect)
            return null;

        StringBuilder res = new StringBuilder();
        Stack<Double> st = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            // Пробелы пропускаем
            if (postfix.charAt(i) == ' ')
                continue;

            // Числа добавляем в стек, обходя пробелы
            if (priority(postfix.charAt(i)) == 0) {
                while (postfix.charAt(i) != ' ' && priority(postfix.charAt(i)) == 0) {
                    res.append(postfix.charAt(i++));
                    if (i == postfix.length())
                        break;
                }
                st.push(Double.parseDouble(res.toString()));
                res = new StringBuilder();
            }

            // В случае встреченной операции, выполняем ее и результат ложим в стек
            if (priority(postfix.charAt(i)) > 1) {
                double num1 = st.pop();
                double num2 = st.pop();

                if (postfix.charAt(i) == '+')
                    st.push(num2 + num1);

                if (postfix.charAt(i) == '-')
                    st.push(num2 - num1);

                if (postfix.charAt(i) == '*')
                    st.push(num2 * num1);

                if (postfix.charAt(i) == '/')
                    st.push(num2 / num1);
            }
        }

        // Снимаем результат с вершины стека
        Double x = null;
        x = st.pop();
        return x;
    }

    /**
     * Метод замены выражения, содержащегося в классе.
     * Устанавливает поля:
     * <ul>
     *      <li>{@link #buffer} = str</li>
     *      <li>{@link #postfix} = <b><i>null</i></b> или постфиксной форме выражения, когда она допустима</li>
     *      <li>{@link #result} = <b><i>null</i></b> или значению выражения, в случае когда оно корректно</li>
     *      <li>{@link #isCorrect} = {@link #checkCorrect()}</li>
     * </ul>
     * @param newExpression новое выражение-строка для подстановки.
     */
    public void setNewExpression(String newExpression) {
        buffer = newExpression;
        postfix = null;
        result = null;
        isCorrect = null;
        isCorrect = checkCorrect();
        if (isCorrect) {
            postfix = createPostfixNotation();
            result = calculate();
        }
    }

    /**
     * Вспомогательный метод для удаления лишних пробелов из выражения.
     */
    public String delSpace(String arg) {
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < arg.length(); i++)
            if (arg.charAt(i) != ' ')
                newString.append(arg.charAt(i));
        return newString.toString();
    }

    /**
     * Метод переопределения хеш-кода.
     * @return {@link String#hashCode()} от поля {@link #buffer}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(buffer);
    }

    /**
     * Метод получения строки-выражения.
     * @return {@link #buffer}.
     */
    @Override
    public String toString() {
        return buffer;
    }

    /**
     * Метод глубокого сравнения классов-обработчиков.
     * @param obj объект для сравнения.
     * @return Возвращает:<br>
     * <i>True</i> - если объекты соответствуют друг другу по полям и содержимому<br>
     * <i>False</i> - в ином случае
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Expression that = (Expression) obj;
        return Objects.equals(buffer, that.buffer);
    }

    /**
     * Метод получения постфиксной формы.
     * @return {@link #postfix}
     */
    public String getPostfix() {
        return delSpace(postfix);
    }

}
