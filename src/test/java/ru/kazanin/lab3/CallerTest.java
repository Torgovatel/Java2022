package ru.kazanin.lab3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс для тестирования Caller (3 Лаба).<br>
 * @author Andrey Kazanin
 * @version 1.0
 */
class CallerTest {

    /**
     * unit-тест метода {@link Caller#timeForMethod(Object, Caller.MethodType)}.<br>
     * Проверяет тип возвращаемого значения и возможность выбросить исключение.
     */
    @Test
    void timeByMethod() {
        LinkedList<Integer> list = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();

        Caller check = new Caller(1000);
        Integer badValue = 7;
        Long goodValue = 7L;

        // Тестирование Add
        assertThrows(IllegalArgumentException.class, () -> {check.timeForMethod(1000, Caller.MethodType.Add);});

        assertEquals(check.timeForMethod(list, Caller.MethodType.Add).getClass(), goodValue.getClass());
        assertEquals(check.timeForMethod(arr, Caller.MethodType.Add).getClass(), goodValue.getClass());

        assertNotEquals(check.timeForMethod(list, Caller.MethodType.Add).getClass(), badValue.getClass());
        assertNotEquals(check.timeForMethod(arr, Caller.MethodType.Add).getClass(), badValue.getClass());

        // Тестирования Get
        assertThrows(IllegalArgumentException.class, () -> {check.timeForMethod(1000, Caller.MethodType.Get);});

        assertEquals(check.timeForMethod(list, Caller.MethodType.Get).getClass(), goodValue.getClass());
        assertEquals(check.timeForMethod(arr, Caller.MethodType.Get).getClass(), goodValue.getClass());

        assertNotEquals(check.timeForMethod(list, Caller.MethodType.Get).getClass(), badValue.getClass());
        assertNotEquals(check.timeForMethod(arr, Caller.MethodType.Get).getClass(), badValue.getClass());

        // Тестирование Delete
        assertThrows(IllegalArgumentException.class, () -> {check.timeForMethod(1000, Caller.MethodType.Delete);});

        assertEquals(check.timeForMethod(list, Caller.MethodType.Delete).getClass(), goodValue.getClass());
        assertEquals(check.timeForMethod(arr, Caller.MethodType.Delete).getClass(), goodValue.getClass());

        assertNotEquals(check.timeForMethod(list, Caller.MethodType.Delete).getClass(), badValue.getClass());
        assertNotEquals(check.timeForMethod(arr, Caller.MethodType.Delete).getClass(), badValue.getClass());
    }
}