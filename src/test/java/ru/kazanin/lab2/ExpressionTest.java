package ru.kazanin.lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования Expression (2 Лаба).
 * @author Andrey Kazanin
 * @version 1.0
 */
class ExpressionTest {

    /**
     * unit-тест метода {@link Expression#checkCorrect()}.<br>
     * Проверяет возвращаемое значение.
     */
    @Test
    void checkCorrect() {
        Expression expr = new Expression("++(4-3)*5++");
        assertFalse(expr.checkCorrect());
        expr.setNewExpression("2+(+9)");
        assertFalse(expr.checkCorrect());
        expr.setNewExpression("(5-4)*(2*(1+1)+6)/10");
        assertTrue(expr.checkCorrect());
    }

    /**
     * unit-тест метода {@link Expression#calculate()}.<br>
     * Проверяет значение вычисленного выражения.
     */
    @Test
    void calculate() {
        Expression expr = new Expression("(5-4)*(2*(1+1)+6)/10");
        assertEquals(expr.calculate(), 1.0);
    }

    /**
     * unit-тест метода {@link Expression#toString()}.<br>
     * Проверяет по {@link Object#equals(Object)} содержимое поля {@link Expression}.
     */
    @Test
    void testToString() {
        Expression expr = new Expression("(10-5)*7");
        assertTrue(expr.toString().equals("(10-5)*7"));
        expr.setNewExpression("aboba");
        assertTrue(expr.toString().equals("aboba"));
    }

    /**
     * unit-тест метода {@link Expression#equals(Object)}.<br>
     * Сравнивает с null и другим выражением (равным и не равным).
     */
    @Test
    void testEquals() {
        Expression expr1 = new Expression("boba");
        Expression expr2 = new Expression("boba");
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(null));
        expr2.setNewExpression("biba");
        assertFalse(expr1.equals(expr2));
    }
}