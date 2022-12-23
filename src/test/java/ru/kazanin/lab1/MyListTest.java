package ru.kazanin.lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирования MyList (1 Лаба).<br>
 * @author Andrey Kazanin
 * @version 1.0
 */
class MyListTest {

    /**
     * unit-тест метода {@link MyList#push(Object)}.<br>
     * Проверяет по {@link Object#equals(Object)} равенство содержимого списков.
     */
    @Test
    void push() {
        int data = 7;
        MyList<Integer> list1 = new MyList<>();
        MyList<Integer> list2 = new MyList<>();
        assertTrue(list1.equals(list2));
        list1.push(data);
        list2.push(data);
        assertTrue(list1.equals(list2));
    }

    /**
     * unit-тест метода {@link MyList#pop()}.<br>
     * Проверяет значение метода и итоговый список через {@link Object#toString()}.
     */
    @Test
    void pop() {
        int data = 7;
        MyList<Integer> cont = new MyList<>();
        assertFalse(cont.pop());
        cont.push(data);
        assertTrue(cont.pop());
        assertTrue(cont.toString().equals("[]"));
    }

    /**
     * unit-тест метода {@link MyList#peek()}.<br>
     * Проверяет факт выбрасывания исключения {@link IndexOutOfBoundsException} и возвращаемый элемент списка.
     */
    @Test
    void peek() {
        MyList<Integer> cont = new MyList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> {cont.peek();});
        Integer data = 7;
        cont.push(data);
        assertTrue(cont.peek().equals(data));
    }

    /**
     * unit-тест метода {@link MyList#size()}.
     * Проверяет размерность списка после: {@link MyList#push(Object)}, {@link MyList#pop()}, {@link MyList#clear()}
     */
    @Test
    void size() {
        MyList<Integer> cont = new MyList<>();
        assertTrue(cont.size() == 0);
        for(int i = 5; i>=1; --i)
            cont.push(i);
        assertTrue(cont.size() == 5);
        cont.pop();
        assertTrue(cont.size() == 4);
        cont.clear();
        assertTrue(cont.size() == 0);
    }

    /**
     * unit-тест метода {@link MyList#toString()}.<br>
     * Проверяет строку после вставки элементов методом {@link MyList#push(Object)}
     * через строковый {@link Object#equals(Object)}.
     */
    @Test
    void testToString() {
        MyList<Integer> cont = new MyList<>();
        assertEquals(cont.toString(), "[]");
        for(int i = 1; i <= 3; i++)
            cont.push(i);
        assertEquals(cont.toString(), "[3, 2, 1]");
    }
}