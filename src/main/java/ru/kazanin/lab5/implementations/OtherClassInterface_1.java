package ru.kazanin.lab5.implementations;

import ru.kazanin.lab5.interfaces.Interface_1;

/**
 * Вторая реализация {@link Interface_1}
 * @see ClassInterface_1
 */
public class OtherClassInterface_1 implements Interface_1 {
    @Override
    public void doSomething() {
        System.out.println("Это строка из OtherClassInterface_1");
    }
}
