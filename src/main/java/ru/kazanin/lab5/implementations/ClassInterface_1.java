package ru.kazanin.lab5.implementations;

import ru.kazanin.lab5.interfaces.Interface_1;

/**
 * Первая реализация {@link Interface_1}
 * @see OtherClassInterface_1
 */
public class ClassInterface_1 implements Interface_1 {
    @Override
    public void doSomething() {
        System.out.println("Это строка из ClassInterface_1");
    }
}
