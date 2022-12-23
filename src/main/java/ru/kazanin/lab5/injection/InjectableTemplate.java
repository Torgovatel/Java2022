package ru.kazanin.lab5.injection;

import ru.kazanin.lab5.annotations.AutoInjectable;
import ru.kazanin.lab5.interfaces.Interface_1;
import ru.kazanin.lab5.interfaces.Interface_2;

/**
 * Класс для демонстрации работы {@link Injector#inject(Object)}
 * @author Andrey Kazanin
 * @see AutoInjectable
 */
public class InjectableTemplate {
    @AutoInjectable
    private Interface_1 field1;
    @AutoInjectable
    private Interface_2 field2;

    public void printSomething() {
        field1.doSomething();
        field2.doSomething();
    }
}
