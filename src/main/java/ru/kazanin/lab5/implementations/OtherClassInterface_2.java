package ru.kazanin.lab5.implementations;

import ru.kazanin.lab5.interfaces.Interface_2;

/**
 * Вторая реализация {@link Interface_2}
 * @see ClassInterface_2
 */
public class OtherClassInterface_2 implements Interface_2 {
    @Override
    public void doSomething() {
        System.out.println("Это строка из OtherClassInterface_2");
    }
}
