package ru.kazanin.lab5.implementations;

import ru.kazanin.lab5.interfaces.Interface_2;

/**
 * Первая реализация {@link Interface_2}
 * @see OtherClassInterface_2
 */
public class ClassInterface_2 implements Interface_2 {
    @Override
    public void doSomething() {
        System.out.println("Это строка из ClassInterface_2");
    }
}