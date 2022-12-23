package ru.kazanin.lab5.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Аннотация для последующей рефлексии
 * @author Kazanin Andrey
 * @version 1.0
 * @see ru.kazanin.lab5.injection.Injector
 */
@Retention(RUNTIME)     // доступна во время выполнения
@Target(FIELD)          // привязана к полям
@Documented             // отображается в JavaDoc
public @interface AutoInjectable {}